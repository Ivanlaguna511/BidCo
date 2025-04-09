package com.proyectoSSW.backend.controller;

import com.proyectoSSW.backend.model.Subasta;
import com.proyectoSSW.backend.service.SubastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubastaController {

    @Autowired
    private SubastaService SubastaService;

}


