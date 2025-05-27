package com.bidco.api_rest.repository;

import com.bidco.api_rest.model.Trabajador;
import com.bidco.api_rest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

    @Query(value = "SELECT * FROM trabajador U WHERE U.nombre_usuario = ?1", nativeQuery = true)
    Optional<Trabajador> findByNombreUsuario(String nombreUsuario);

}
