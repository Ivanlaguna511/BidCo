package com.bidco.api_rest.service.impl;


import com.bidco.api_rest.dto.usuario.UsuarioCreateDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.mapper.UsuarioMapper;
import com.bidco.api_rest.model.Usuario;
import com.bidco.api_rest.repository.UsuarioRepository;
import com.bidco.api_rest.service.contract.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        if(usuarioCreateDTO==null){
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        Usuario usuario = usuarioMapper.usuarioCreateDTOToUsuario(usuarioCreateDTO);
        usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        if (usuarioCreateDTO == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(usuarioCreateDTO.getNombreUsuario());
        if (!usuarioOptional.isPresent()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOptional.get();
        Usuario usuarioActualizado = actualizarParametrosUsuario(usuario,usuarioCreateDTO);
        usuarioRepository.save(usuarioActualizado);
        return usuarioMapper.usuarioToUsuarioResponseDTO(usuarioActualizado);
    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));

        return usuarioMapper.usuarioToUsuarioResponseDTO(usuario);

    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorNombreUsuario(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con nombre de usuario " + nombreUsuario + " no encontrado"));

        return usuarioMapper.usuarioToUsuarioResponseDTO(usuario);
    }

    private Usuario actualizarParametrosUsuario(Usuario usuario,UsuarioCreateDTO usuarioCreateDTO) {
        usuario.setNombre(usuarioCreateDTO.getNombre());
        usuario.setPrimerApellido(usuarioCreateDTO.getPrimerApellido());
        usuario.setSegundoApellido(usuarioCreateDTO.getSegundoApellido());
        usuario.setCorreoElectronico(usuarioCreateDTO.getCorreoElectronico());
        usuario.setContraseña(usuarioCreateDTO.getContraseña());
        usuario.setSaldo(usuarioCreateDTO.getSaldo());
        usuario.setPuntos(usuarioCreateDTO.getPuntos());
        usuario.setPais(usuarioCreateDTO.getPais());
        usuario.setCiudad(usuarioCreateDTO.getCiudad());
        usuario.setCodigoPostal(usuarioCreateDTO.getCodigoPostal());
        usuario.setCalle(usuarioCreateDTO.getCalle());
        usuario.setNumeroPiso(usuarioCreateDTO.getNumeroPiso());
        usuario.setLetraPiso(usuarioCreateDTO.getLetraPiso());
        return usuario;
    }

}
