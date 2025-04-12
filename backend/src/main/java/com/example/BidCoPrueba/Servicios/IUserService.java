package com.example.BidCoPrueba.Servicios;

import com.example.BidCoPrueba.Modelos.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IUserService {
    List<Usuario> obtenerTodos();
    Optional<Usuario> obtenerPorId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
}

