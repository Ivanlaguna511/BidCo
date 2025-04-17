package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.usuario.LoginDTO;
import com.bidco.api_rest.dto.usuario.UsuarioCreateDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.dto.usuario.UsuarioUpdateDTO;
import com.bidco.api_rest.service.contract.UsuarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    // Actualizar usuario por ID (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(
        @PathVariable Long id,
        @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) { // Usar DTO específico para actualización
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuarioUpdateDTO));
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

    // Endpoint de login que devuelve un token JWT
    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginDTO loginDTO) {
        String token = usuarioService.login(loginDTO);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(
        @PathVariable Long id,
        @RequestBody Map<String, String> passwords
    ) {
        try {
            usuarioService.updatePassword(id, passwords.get("currentPassword"), passwords.get("newPassword"));
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Usuario no encontrado"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error interno"));
        }
    }
}