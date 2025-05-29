package com.bidco.api_rest.repository;

import com.bidco.api_rest.model.Trabajador;
import com.bidco.api_rest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

    @Query(value = "SELECT U FROM Trabajador U WHERE U.nombreUsuario = :nombreUsuario")
    Optional<Trabajador> findByNombreUsuario(String nombreUsuario);

    @Query(value = "SELECT U FROM Trabajador U WHERE U.correoElectronico = :correo")
    Optional<Trabajador> findByCorreoElectronico(String correo);

}
