package com.bidco.api_rest.service.contract;

import com.bidco.api_rest.dto.admin.AdminLoginDTO;
import com.bidco.api_rest.dto.admin.ExpertCreateDTO;
import com.bidco.api_rest.dto.admin.SorteoCreateDTO;

import jakarta.servlet.http.HttpSession;

public interface AdminService {
    void login(AdminLoginDTO dto, HttpSession session);
    void createSorteo(SorteoCreateDTO dto, HttpSession session);
    void createExpert(ExpertCreateDTO dto, HttpSession session);
}