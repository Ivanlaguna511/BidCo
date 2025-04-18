package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.usuario.LoginDTO;
import com.bidco.api_rest.dto.usuario.PrivacidadDTO;
import com.bidco.api_rest.dto.usuario.StatsDTO;
import com.bidco.api_rest.dto.usuario.UsuarioCreateDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.dto.usuario.UsuarioUpdateDTO;
import com.bidco.api_rest.mapper.UsuarioMapper;
import com.bidco.api_rest.model.Usuario;
import com.bidco.api_rest.repository.PujaRepository;
import com.bidco.api_rest.repository.PujaSorteoRepository;
import com.bidco.api_rest.repository.SubastaRepository;
import com.bidco.api_rest.repository.UsuarioRepository;
import com.bidco.api_rest.service.contract.UsuarioService;
import com.bidco.api_rest.config.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PujaRepository pujaRepository;
    private final SubastaRepository subastaRepository;
    private final PujaSorteoRepository pujaSorteoRepository;
    private final UsuarioMapper usuarioMapper;
    // Puedes inyectar JwtUtil si lo configuras como bean; para este ejemplo lo instancio manualmente.
    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            UsuarioMapper usuarioMapper,
            PujaRepository pujaRepository,
            SubastaRepository subastaRepository,
            PujaSorteoRepository pujaSorteoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.pujaRepository = pujaRepository;
        this.subastaRepository = subastaRepository;
        this.pujaSorteoRepository = pujaSorteoRepository;
    }

    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        if (usuarioCreateDTO == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        Usuario usuario = usuarioMapper.usuarioCreateDTOToUsuario(usuarioCreateDTO);
        usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        
        usuario.setNombreUsuario(usuarioUpdateDTO.getNombreUsuario());
        usuario.setCorreoElectronico(usuarioUpdateDTO.getCorreoElectronico());
        usuario.setCiudad(usuarioUpdateDTO.getCiudad());
        usuario.setCodigoPostal(usuarioUpdateDTO.getCodigoPostal());
        usuario.setCalle(usuarioUpdateDTO.getCalle());
        usuario.setNumeroPiso(usuarioUpdateDTO.getNumeroPiso());
        usuario.setLetraPiso(usuarioUpdateDTO.getLetraPiso());
        
        usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioResponseDTO(usuario);
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

    @Override
    public String login(LoginDTO loginDTO) {
        // Buscar usuario por nombre de usuario
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(loginDTO.getIdentificador());
        // Si no encuentra, se puede buscar por correo electrónico (asegúrate de tener el método en el repositorio)
        if (!usuarioOpt.isPresent()) {
            usuarioOpt = usuarioRepository.findByCorreoElectronico(loginDTO.getIdentificador());
        }
        if (!usuarioOpt.isPresent()) {
            throw new EntityNotFoundException("Credenciales incorrectas");
        }
        Usuario usuario = usuarioOpt.get();
        // Validar la contraseña (en producción, utiliza hashing)
        if (!usuario.getContraseña().equals(loginDTO.getContraseña())) {
            throw new IllegalArgumentException("Credenciales incorrectas");
        }
        // Generar el token JWT usando el ID del usuario (se guarda en el claim "sub")
        return jwtUtil.generateToken(usuario.getUsuarioID().toString());
    }

    public void updatePassword(Long id, String currentPassword, String newPassword) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        
        // Verificar contraseña actual (si usas hashing, aplica aquí)
        if (!usuario.getContraseña().equals(currentPassword)) {
            throw new IllegalArgumentException("Contraseña actual incorrecta");
        }
        
        // Validar nueva contraseña
        if (newPassword == null || newPassword.length() < 6) {
            throw new IllegalArgumentException("La nueva contraseña debe tener al menos 6 caracteres");
        }
        
        usuario.setContraseña(newPassword);
        usuarioRepository.save(usuario);
    }

    @Override
    public PrivacidadDTO getPrivacidad(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        PrivacidadDTO dto = new PrivacidadDTO();
        dto.setPrivacidadAnonimoPujas(usuario.getPrivacidadAnonimoPujas());
        dto.setPrivacidadEstadisticas(usuario.getPrivacidadEstadisticas());
        dto.setPrivacidadPerfilVisible(usuario.getPrivacidadPerfilVisible());
        return dto;
    }
    
    public UsuarioResponseDTO actualizarPrivacidad(Long id, PrivacidadDTO privacidadUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        
        usuario.setPrivacidadAnonimoPujas(privacidadUpdateDTO.getPrivacidadAnonimoPujas());
        usuario.setPrivacidadEstadisticas(privacidadUpdateDTO.getPrivacidadEstadisticas());
        usuario.setPrivacidadPerfilVisible(privacidadUpdateDTO.getPrivacidadPerfilVisible());
        
        usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioResponseDTO(usuario);
    }

    public StatsDTO getStats(Long usuarioId) {
        return new StatsDTO(
            pujaRepository.countParticipatedBidsByUsuarioId(usuarioId),
            pujaRepository.countWonBidsByUsuarioId(usuarioId),
            subastaRepository.countCreatedBidsByUsuarioId(usuarioId),
            pujaSorteoRepository.countParticipatedDrawsByUsuarioId(usuarioId),
            0, // wonDraws (requiere ajuste en BD)
            0  // createdDraws (solo para trabajadores)
        );
    }
}