package com.example.backendapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Slf4j

@Document(collection = "Inventory")
public class SystemData {

    @Id
    private String id;
    private String system;
    private String node;
    private String deviceName; // New field
    private String release;
    private String version;
    private String machine;
    private String processor;

    @JsonProperty("CPU_Usage")
    private double cpuUsage;

    private long totalMemory;
    private long freeMemory;
    private long totalDisk;
    private long freeDisk;
    private String networkInfo;

    // Constructors
    public SystemData() {}

    public SystemData(String system, String node, String deviceName, String release, String version, String machine, String processor, double cpuUsage, long totalMemory, long freeMemory, long totalDisk, long freeDisk, String networkInfo) {
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

    public String getNetworkInfo() {
        return networkInfo;
    }

    public void setNetworkInfo(String networkInfo) {
        this.networkInfo = networkInfo;
    }
}
