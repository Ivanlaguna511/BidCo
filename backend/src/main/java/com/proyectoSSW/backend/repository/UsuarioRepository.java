package com.proyectoSSW.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoSSW.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes definir consultas personalizadas si las necesitas
}