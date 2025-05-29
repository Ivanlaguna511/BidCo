package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.trabajador.LoginTrabajadorDTO;
import com.bidco.api_rest.dto.trabajador.TrabajadorCreateDTO;
import com.bidco.api_rest.dto.trabajador.TrabajadorResponseDTO;
import com.bidco.api_rest.service.contract.TrabajadorService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    @Autowired
    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

     // Registrar trabajador (POST)
    @PostMapping
    public TrabajadorResponseDTO registrarTrabajador(@Valid @RequestBody TrabajadorCreateDTO trabajadorCreateDTO) {
        return trabajadorService.registrarTrabajador(trabajadorCreateDTO);
    }

    // Actualizar trabajador (PUT)
    @PutMapping
    public TrabajadorResponseDTO actualizarTrabajador(@Valid @RequestBody TrabajadorCreateDTO trabajadorCreateDTO) {
        return trabajadorService.actualizarTrabajador(trabajadorCreateDTO);
    }

    // Buscar trabajador por ID (GET)
    @GetMapping("/{id}")
    public TrabajadorResponseDTO buscarTrabajadorPorId(@PathVariable Long id) {
        return trabajadorService.buscarTrabajadorPorId(id);
    }

    // Buscar trabajador por nombre de usuario (GET)
    @GetMapping("/buscar")
    public TrabajadorResponseDTO buscarTrabajadorPorNombreUsuario(@RequestParam String nombreUsuario) {
        return trabajadorService.buscarTrabajadorPorNombreUsuario(nombreUsuario);
    }

    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginTrabajadorDTO loginDTO) {
        String token = trabajadorService.login(loginDTO);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
