package com.mssv.tecpr.Controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class OpenApiController {

    @GetMapping("/static/openapi.yaml")
    public ResponseEntity<String> getOpenApiYaml() throws IOException {
        var resource = new ClassPathResource("static/openapi.yaml");
        String yamlContent = Files.readString(resource.getFile().toPath());
        return ResponseEntity.ok(yamlContent);
    }
}