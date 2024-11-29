package com.example.backendapi.controller;

import com.example.backendapi.service.ScriptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class ScriptController {

    private final ScriptService scriptService;

    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @GetMapping("/generate-script")
    public ResponseEntity<byte[]> generateScript() {
        try {
            byte[] scriptContent = scriptService.getScriptContent();

            // Set headers for file download
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"system-data-collector.ps1\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

            return new ResponseEntity<>(scriptContent, headers, HttpStatus.OK);
        }  catch (IOException e) {
            // Log error using SLF4J
            log.error("Error generating script: {}", e.getMessage(), e);

            // Return internal server error with error message in the body
            String errorMessage = "Failed to generate the script: " + e.getMessage();
            return new ResponseEntity<>(errorMessage.getBytes(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
