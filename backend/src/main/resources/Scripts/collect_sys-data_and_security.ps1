# Define the log file
$logFile = Join-Path -Path (Split-Path -Parent $MyInvocation.MyCommand.Path) -ChildPath "logfile.txt"

function Log-Data {
    param (
        [string]$message
    )
    $message | Out-File -Append -FilePath $logFile
}

# Function to check if a port is open
function Test-Port {
    param (
        [string]$hostname,
        [int]$port
    )
    try {
        $connection = Test-NetConnection -ComputerName $hostname -Port $port -WarningAction SilentlyContinue
        return $connection.TcpTestSucceeded
    } catch {
        return $false
    }
}

# Define the critical ports to check
$criticalPorts = @(21, 22, 23, 25, 53, 80, 443, 3389)

# Scan for open ports
function Scan-OpenPorts {
    param (
        [string]$hostname
    )
    $openPorts = @()

    foreach ($port in $criticalPorts) {
        if (Test-Port -hostname $hostname -port $port) {
            $openPorts += $port
            Log-Data "Port $port is open."
        }
    }
    return $openPorts
}

# Try-Catch block for detailed error logging
try {
    Log-Data "Starting port scan and data collection..."

    # Get open ports
    $openPorts = Scan-OpenPorts -hostname "localhost"

    # Ensure openPorts is explicitly an array, even if empty
    if (-not $openPorts) {
        $openPorts = @() # Initialize as an empty array
    }

    Log-Data "Open Ports: $($openPorts -join ', ')"

    # Collect other system data
    $networkAdapters = Get-CimInstance -Class Win32_NetworkAdapterConfiguration |
        Where-Object { $_.IPEnabled -eq $true } |
        Select-Object Description, MACAddress, @{Name="IPAddress";Expression={$_.IPAddress}}

    # Flatten networkInfo into a list of strings
    $networkInfo = @()
    foreach ($adapter in $networkAdapters) {
        $networkInfo += "Description: $($adapter.Description), MAC: $($adapter.MACAddress), IP: $($adapter.IPAddress -join ', ')"
    }

    $systemData = @{
        system      = (Get-CimInstance -Class Win32_OperatingSystem).Caption
        node        = $env:COMPUTERNAME
        deviceName  = (Get-CimInstance -Class Win32_ComputerSystem).Name
        release     = (Get-CimInstance -Class Win32_OperatingSystem).Version
        version     = (Get-CimInstance -Class Win32_OperatingSystem).BuildNumber
        machine     = (Get-CimInstance -Class Win32_ComputerSystem).Model
        processor   = (Get-CimInstance -Class Win32_Processor | Select-Object -First 1 -ExpandProperty Name)
        cpuUsage    = (Get-CimInstance -Class Win32_Processor | Select-Object -ExpandProperty LoadPercentage | Measure-Object -Average).Average
        totalMemory = (Get-CimInstance -Class Win32_ComputerSystem).TotalPhysicalMemory
        freeMemory  = (Get-CimInstance -Class Win32_OperatingSystem).FreePhysicalMemory * 1KB
        totalDisk   = (Get-CimInstance -Class Win32_LogicalDisk -Filter "DriveType=3" | Measure-Object -Property Size -Sum).Sum
        freeDisk    = (Get-CimInstance -Class Win32_LogicalDisk -Filter "DriveType=3" | Measure-Object -Property FreeSpace -Sum).Sum
        networkInfo = $networkInfo
        openPorts   = $openPorts
        securePorts = $openPorts.Count -eq 0
    }

    Log-Data "Data collection completed successfully."

    # Convert to JSON and send to server
    $jsonData = $systemData | ConvertTo-Json -Depth 5 -Compress
    Log-Data "JSON Data: $jsonData"

    $serverUrl = 'http://localhost:8080/systemdata'
    $response = Invoke-RestMethod -Uri $serverUrl -Method Post -Body $jsonData -ContentType 'application/json'
    Log-Data "Data successfully sent to the server. Response: $($response | Out-String)"
}
catch {
    Log-Data "Error: $($_.Exception.Message)"
    Log-Data "Error Details: $($_ | Out-String)"
}
