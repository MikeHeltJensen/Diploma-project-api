package com.example.backendapi.service;

import com.example.backendapi.model.SystemData;
import com.example.backendapi.repository.SystemDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemDataService {

    @Autowired
    private SystemDataRepository systemDataRepository;

    public List<SystemData> getAllSystemData() {
        return systemDataRepository.findAll();
    }

    public Optional<SystemData> getSystemDataById(String id) {
        return systemDataRepository.findById(id);
    }

    public SystemData createSystemData(SystemData systemData) {
        return systemDataRepository.save(systemData);
    }

    public SystemData updateSystemData(String id, SystemData systemData) {
        systemData.setId(id);
        return systemDataRepository.save(systemData);
    }

    public void deleteSystemData(String id) {
        systemDataRepository.deleteById(id);
    }
}
