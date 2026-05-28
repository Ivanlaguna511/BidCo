package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.trabajador.LoginTrabajadorDTO;
import com.bidco.api_rest.dto.trabajador.TrabajadorCreateDTO;
import com.bidco.api_rest.dto.trabajador.TrabajadorResponseDTO;
import com.bidco.api_rest.mapper.TrabajadorMapper;
import com.bidco.api_rest.model.Trabajador;
import com.bidco.api_rest.repository.TrabajadorRepository;
import com.bidco.api_rest.service.contract.TrabajadorService;
import com.bidco.api_rest.config.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final TrabajadorMapper trabajadorMapper;
    private final JwtUtil jwtUtil = new JwtUtil();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository, TrabajadorMapper trabajadorMapper) {
        this.trabajadorRepository = trabajadorRepository;
        this.trabajadorMapper = trabajadorMapper;
    }

    @Override
    public TrabajadorResponseDTO registrarTrabajador(TrabajadorCreateDTO trabajadorCreateDTO) {
        if (trabajadorCreateDTO == null) {
            throw new IllegalArgumentException("El trabajador no puede ser nulo");
        }
        Trabajador trabajador = trabajadorMapper.trabajadorCreateDTOToTrabajador(trabajadorCreateDTO);
        // Hashear contraseña antes de guardar
        trabajador.setContraseña(passwordEncoder.encode(trabajadorCreateDTO.getContraseña()));
        trabajadorRepository.save(trabajador);
        return trabajadorMapper.trabajadorToTrabajadorResponseDTO(trabajador);
    }

    @Override
    public TrabajadorResponseDTO actualizarTrabajador(TrabajadorCreateDTO trabajadorCreateDTO) {
        if (trabajadorCreateDTO == null) {
            throw new IllegalArgumentException("El trabajador no puede ser nulo");
        }
        Trabajador trabajador = trabajadorRepository.findByNombreUsuario(trabajadorCreateDTO.getNombreUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));

        // Actualizar campos correctamente (fix del bug return null)
        trabajador.setNombreUsuario(trabajadorCreateDTO.getNombreUsuario());
        trabajador.setCorreoElectronico(trabajadorCreateDTO.getCorreoElectronico());
        if (trabajadorCreateDTO.getContraseña() != null && !trabajadorCreateDTO.getContraseña().isBlank()) {
            trabajador.setContraseña(passwordEncoder.encode(trabajadorCreateDTO.getContraseña()));
        }

        trabajadorRepository.save(trabajador);
        return trabajadorMapper.trabajadorToTrabajadorResponseDTO(trabajador);
    }

    @Override
    public TrabajadorResponseDTO buscarTrabajadorPorId(Long id) {
        Trabajador trabajador = trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador con ID " + id + " no encontrado"));
        return trabajadorMapper.trabajadorToTrabajadorResponseDTO(trabajador);
    }

    @Override
    public TrabajadorResponseDTO buscarTrabajadorPorNombreUsuario(String nombreUsuario) {
        Trabajador trabajador = trabajadorRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador con nombre de usuario " + nombreUsuario + " no encontrado"));
        return trabajadorMapper.trabajadorToTrabajadorResponseDTO(trabajador);
    }

    @Override
    public String login(LoginTrabajadorDTO loginDTO) {
        Optional<Trabajador> trabajadorOpt = trabajadorRepository.findByNombreUsuario(loginDTO.getIdentificador());
        if (!trabajadorOpt.isPresent()) {
            trabajadorOpt = trabajadorRepository.findByCorreoElectronico(loginDTO.getIdentificador());
        }
        if (!trabajadorOpt.isPresent()) {
            throw new EntityNotFoundException("Credenciales incorrectas");
        }
        Trabajador trabajador = trabajadorOpt.get();

        // Comparación segura con BCrypt
        if (!passwordEncoder.matches(loginDTO.getContraseña(), trabajador.getContraseña())) {
            throw new IllegalArgumentException("Credenciales incorrectas");
        }

        if (trabajador.isExperto() != loginDTO.getRol()) {
            throw new IllegalArgumentException("Credenciales incorrectas");
        }

        return jwtUtil.generateToken(trabajador.getTrabajadorID().toString());
    }
}