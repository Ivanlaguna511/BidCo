package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.usuario.LoginDTO;
import com.bidco.api_rest.dto.usuario.UsuarioCreateDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.service.contract.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Registrar usuario (POST)
    @PostMapping
    public UsuarioResponseDTO registrarUsuario(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return usuarioService.registrarUsuario(usuarioCreateDTO);
    }

    // Actualizar usuario (PUT)
    @PutMapping
    public UsuarioResponseDTO actualizarUsuario(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return usuarioService.actualizarUsuario(usuarioCreateDTO);
    }

    // Buscar usuario por ID (GET)
    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    // Buscar usuario por nombre de usuario (GET)
    @GetMapping("/buscar")
    public UsuarioResponseDTO buscarUsuarioPorNombreUsuario(@RequestParam String nombreUsuario) {
        return usuarioService.buscarUsuarioPorNombreUsuario(nombreUsuario);
    }

    // UsuarioController.java (fragmento)
    @PostMapping("/login")
    public UsuarioResponseDTO login(@Valid @RequestBody LoginDTO loginDTO) {
        return usuarioService.login(loginDTO);
    }
}
