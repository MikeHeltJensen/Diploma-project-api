package com.example.backendapi.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ScriptService {

    public byte[] getScriptContent() throws IOException {
        // Load the script from resources
        Resource resource = new ClassPathResource("scripts/collect_data.ps1");

        // Check if the resource exists
        if (!resource.exists()) {
            // If the file does not exist, throw an IOException with a clear message
            throw new IOException("Script file not found: " + resource.getURL());
        }

        // Try to get the file and read its content
        try {
            // Get the file path and read all bytes
            Path filePath = resource.getFile().toPath();
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            // Catch any IOExceptions and rethrow with additional details
            throw new IOException("Error reading the script file: " + e.getMessage(), e);
        }
    }
}
