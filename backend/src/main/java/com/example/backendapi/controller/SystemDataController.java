package com.example.backendapi.controller;

import com.example.backendapi.model.SystemData;
import com.example.backendapi.service.SystemDataService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/systemdata")
@Validated // Optional but good for consistency
public class SystemDataController {

    private final SystemDataService systemDataService;

    public SystemDataController(SystemDataService systemDataService) {
        this.systemDataService = systemDataService;
    }

    @GetMapping("/data")
    public Page<SystemData> getAllSystemData(
            @RequestParam(defaultValue = "0") int page, // Start with 0 for Spring's Pageable
            @RequestParam(defaultValue = "10") int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit); // Pageable object
        return systemDataService.getAllSystemData(pageRequest);
    }

    @GetMapping("/{id}")
    public SystemData getSystemDataById(@PathVariable String id) {
        Optional<SystemData> systemDataOptional = systemDataService.getSystemDataById(id);
        return systemDataOptional.orElseThrow(() -> new RuntimeException("SystemData not found"));
    }

    @PostMapping
    public SystemData createSystemData(@Valid @RequestBody SystemData systemData) { // Add @Valid
        return systemDataService.createSystemData(systemData);
    }

    @PutMapping("/{id}")
    public SystemData updateSystemData(@PathVariable String id, @Valid @RequestBody SystemData systemData) { // Add @Valid
        return systemDataService.updateSystemData(id, systemData);
    }

    @DeleteMapping("/{id}")
    public void deleteSystemData(@PathVariable String id) {
        systemDataService.deleteSystemData(id);
    }
}
