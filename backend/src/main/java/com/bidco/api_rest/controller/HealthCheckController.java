package com.bidco.api_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    private final DataSource dataSource;

    public HealthCheckController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            // Hacemos una consulta minúscula para mantener Aiven despierto
            stmt.execute("SELECT 1");
            return ResponseEntity.ok("Backend y Base de Datos ACTIVOS");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en BD");
        }
    }
}