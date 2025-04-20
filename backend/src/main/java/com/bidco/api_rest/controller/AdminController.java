package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.admin.SorteoCreateDTO;
import com.bidco.api_rest.dto.admin.AdminLoginDTO;
import com.bidco.api_rest.service.contract.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody AdminLoginDTO dto,
            HttpSession session
    ) {
        adminService.login(dto, session);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sorteos")
    public ResponseEntity<Void> addSorteo(
        @RequestPart("sorteo") SorteoCreateDTO sorteoCreateDTO,
        @RequestPart(value = "imagen", required = false) MultipartFile imagen,
        HttpSession session
    ) {
        sorteoCreateDTO.setImagen(imagen);
        adminService.createSorteo(sorteoCreateDTO, session);
        return ResponseEntity.ok().build();
    }

}