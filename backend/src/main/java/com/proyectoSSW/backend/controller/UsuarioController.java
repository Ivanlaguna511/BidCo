package com.proyectoSSW.backend.controller;

import com.proyectoSSW.backend.model.Usuario;
import com.proyectoSSW.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService SubastaService;

}


