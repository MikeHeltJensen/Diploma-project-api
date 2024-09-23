package com.example.backendapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkInfo {
    private String description;
    private String macAddress;
    private String[] ipAddress;

    // Constructors
    public NetworkInfo() {}

    public NetworkInfo(String description, String macAddress, String[] ipAddress) {
        this.description = description;
        this.macAddress = macAddress;
        this.ipAddress = ipAddress;
    }

    // Getters and Setters
    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("MACAddress")
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @JsonProperty("IPAddress")
    public String[] getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String[] ipAddress) {
        this.ipAddress = ipAddress;
    }
}
