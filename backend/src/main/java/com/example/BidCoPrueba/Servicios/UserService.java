package com.example.BidCoPrueba.Servicios;

import com.example.BidCoPrueba.Modelos.Usuario;
import com.example.BidCoPrueba.Repositorios.IUserRepositry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

   private final IUserRepositry usuarioRepositry;

    public UserService(IUserRepositry userRepositry) {
        this.usuarioRepositry = userRepositry;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepositry.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepositry.findById(id);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepositry.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepositry.deleteById(id);
    }
}
