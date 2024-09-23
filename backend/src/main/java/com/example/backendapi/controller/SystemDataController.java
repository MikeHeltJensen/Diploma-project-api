package com.example.backendapi.controller;

import com.example.backendapi.model.SystemData;
import com.example.backendapi.service.SystemDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@Slf4j
@Tag(name = "admin")
@RequestMapping("/systemdata")
public class SystemDataController {

    @Autowired
    private SystemDataService systemDataService;

    @GetMapping("/data")
    public List<SystemData> getAllSystemData() {

        return systemDataService.getAllSystemData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemData> getSystemDataById(@PathVariable String id) {
        Optional<SystemData> systemData = systemDataService.getSystemDataById(id);
        return systemData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SystemData> createSystemData(@RequestBody SystemData systemData) {
        SystemData createdSystemData = systemDataService.createSystemData(systemData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSystemData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemData> updateSystemData(@PathVariable String id, @RequestBody SystemData systemData) {
        SystemData updatedSystemData = systemDataService.updateSystemData(id, systemData);
        return ResponseEntity.ok(updatedSystemData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSystemData(@PathVariable String id) {
        systemDataService.deleteSystemData(id);
        return ResponseEntity.noContent().build();
    }
}
