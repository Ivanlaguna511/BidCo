package com.proyectoSSW.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoSSW.backend.model.Subasta;

public interface SubastaRepository extends JpaRepository<Subasta, Long> {
    // Aquí puedes definir consultas personalizadas si las necesitas
}