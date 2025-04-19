package com.bidco.api_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidco.api_rest.dto.admin.AdminLoginDTO;
import com.bidco.api_rest.dto.admin.ExpertCreateDTO;
import com.bidco.api_rest.service.contract.AdminService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired private AdminService adminSvc;

    @PostMapping("/login")
    public void login(@RequestBody AdminLoginDTO dto, HttpSession session) {
        adminSvc.login(dto, session);
    }

    @PostMapping("/sorteos")
    public void addSorteo(@ModelAttribute com.bidco.api_rest.dto.admin.SorteoCreateDTO dto, HttpSession session) {
        adminSvc.createSorteo(dto, session);
    }

    @PostMapping("/expertos")
    public void addExpert(@RequestBody ExpertCreateDTO dto, HttpSession session) {
        adminSvc.createExpert(dto, session);
    }
}