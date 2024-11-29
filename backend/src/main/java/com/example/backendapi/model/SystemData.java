package com.example.backendapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Inventory")
public class SystemData {

    @Id
    private String id;

    @NotBlank(message = "System information is required")
    private String system;

    @NotBlank(message = "Node information is required")
    private String node;

    @NotBlank(message = "Device name is required")
    private String deviceName;

    @NotBlank(message = "Release information is required")
    private String release;

    @NotBlank(message = "Version information is required")
    private String version;

    @NotBlank(message = "Machine information is required")
    private String machine;

    @NotBlank(message = "Processor information is required")
    private String processor;

    @JsonProperty("CPU_Usage")
    @PositiveOrZero(message = "CPU usage must be zero or a positive value")
    private double cpuUsage;

    @Positive(message = "Total memory must be a positive number")
    private long totalMemory;

    @PositiveOrZero(message = "Free memory must be zero or a positive number")
    private long freeMemory;

    @Positive(message = "Total disk must be a positive number")
    private long totalDisk;

    @PositiveOrZero(message = "Free disk must be zero or a positive number")
    private long freeDisk;

    @NotNull(message = "Network information is required")
    private List<String> networkInfo; // Flattened list of network adapter details

    private List<Integer> openPorts; // List of open ports

    private boolean securePorts; // Indicates if all ports are secure

    private LocalDateTime created; // Timestamp for creation
    private LocalDateTime lastUpdate; // Timestamp for last update

    // Constructors
    public SystemData() {}

    public SystemData(String system, String node, String deviceName, String release, String version, String machine,
                      String processor, double cpuUsage, long totalMemory, long freeMemory, long totalDisk, long freeDisk,
                      List<String> networkInfo, List<Integer> openPorts, boolean securePorts) {
        this.system = system;
        this.node = node;
        this.deviceName = deviceName;
        this.release = release;
        this.version = version;
        this.machine = machine;
        this.processor = processor;
        this.cpuUsage = cpuUsage;
        this.totalMemory = totalMemory;
        this.freeMemory = freeMemory;
        this.totalDisk = totalDisk;
        this.freeDisk = freeDisk;
        this.networkInfo = networkInfo;
        this.openPorts = openPorts;
        this.securePorts = securePorts;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public long getTotalDisk() {
        return totalDisk;
    }

    public void setTotalDisk(long totalDisk) {
        this.totalDisk = totalDisk;
    }

    public long getFreeDisk() {
        return freeDisk;
    }

    public void setFreeDisk(long freeDisk) {
        this.freeDisk = freeDisk;
    }

    public List<String> getNetworkInfo() {
        return networkInfo;
    }

    public void setNetworkInfo(List<String> networkInfo) {
        this.networkInfo = networkInfo;
    }

    public List<Integer> getOpenPorts() {
        return openPorts;
    }

    public void setOpenPorts(List<Integer> openPorts) {
        this.openPorts = openPorts;
    }

    public boolean isSecurePorts() {
        return securePorts;
    }

    public void setSecurePorts(boolean securePorts) {
        this.securePorts = securePorts;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
