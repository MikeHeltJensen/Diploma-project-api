package com.example.backendapi.service;

import com.example.backendapi.model.SystemData;
import com.example.backendapi.repository.SystemDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SystemDataService {

    private final SystemDataRepository systemDataRepository;

    public SystemDataService(SystemDataRepository systemDataRepository) {
        this.systemDataRepository = systemDataRepository;
    }

    public Page<SystemData> getAllSystemData(PageRequest pageRequest) {
        return systemDataRepository.findAll(pageRequest);
    }

    public Optional<SystemData> getSystemDataById(String id) {
        return systemDataRepository.findById(id);
    }

    public SystemData createSystemData(SystemData systemData) {
        systemData.setCreated(LocalDateTime.now());
        systemData.setLastUpdate(LocalDateTime.now());
        return systemDataRepository.save(calculateSecurePorts(systemData));
    }

    public SystemData updateSystemData(String id, SystemData systemData) {
        if (systemDataRepository.existsById(id)) {
            systemData.setId(id);
            systemData.setLastUpdate(LocalDateTime.now());
            return systemDataRepository.save(calculateSecurePorts(systemData));
        } else {
            throw new RuntimeException("SystemData not found");
        }
    }

    public void deleteSystemData(String id) {
        systemDataRepository.deleteById(id);
    }

    private SystemData calculateSecurePorts(SystemData systemData) {
        if (systemData.getOpenPorts() == null || systemData.getOpenPorts().isEmpty()) {
            systemData.setSecurePorts(true);
        } else {
            systemData.setSecurePorts(false);
        }
        return systemData;
    }

}
