package com.bidco.api_rest.repository;

import com.bidco.api_rest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuario U WHERE U.nombre_usuario = ?1", nativeQuery = true)
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
