package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.trabajador.TrabajadorCreateDTO;
import com.bidco.api_rest.dto.trabajador.TrabajadorResponseDTO;
import com.bidco.api_rest.dto.usuario.LoginDTO;
import com.bidco.api_rest.mapper.TrabajadorMapper;
import com.bidco.api_rest.model.Trabajador;
import com.bidco.api_rest.model.Usuario;
import com.bidco.api_rest.repository.TrabajadorRepository;
import com.bidco.api_rest.service.contract.TrabajadorService;
import com.bidco.api_rest.config.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final TrabajadorMapper trabajadorMapper;
    private final JwtUtil jwtUtil = new JwtUtil();

    public TrabajadorServiceImpl(TrabajadorRepository trabajadorRepository, TrabajadorMapper trabajadorMapper) {
        this.trabajadorRepository = trabajadorRepository;
        this.trabajadorMapper = trabajadorMapper;
    }


    @Override
    public TrabajadorResponseDTO registrarTrabajador(TrabajadorCreateDTO trabajadorCreateDTO) {
        if(trabajadorCreateDTO==null){
            throw new IllegalArgumentException("El trabajador no puede ser nulo");
        }
        Trabajador trabajador = trabajadorMapper.trabajadorCreateDTOToTrabajador(trabajadorCreateDTO);
        trabajadorRepository.save(trabajador);
        return trabajadorMapper.trabajadorToTrabajadorResponseDTO(trabajador);
    }

    @Override
    public TrabajadorResponseDTO actualizarTrabajador(TrabajadorCreateDTO trabajadorCreateDTO) {
        if (trabajadorCreateDTO == null) {
            throw new IllegalArgumentException("El trabajador no puede ser nulo");
        }
        Optional<Trabajador> trabajadorOptional = trabajadorRepository.findByNombreUsuario(trabajadorCreateDTO.getNombreUsuario());
        if (!trabajadorOptional.isPresent()) {
            throw new EntityNotFoundException("Trabajador no encontrado");
        }
        Trabajador trabajador = trabajadorOptional.get();
        Trabajador trabajadorActualizado = actualizarParametrosTrabajador(trabajador,trabajadorCreateDTO);
        trabajadorRepository.save(trabajadorActualizado);
        return trabajadorMapper.trabajadorToTrabajadorResponseDTO(trabajadorActualizado);
    }

    private Trabajador actualizarParametrosTrabajador(Trabajador trabajador, TrabajadorCreateDTO trabajadorCreateDTO) {
        return null;
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
                .orElseThrow(() -> new EntityNotFoundException("Usuario con nombre de usuario " + nombreUsuario + " no encontrado"));

        return trabajadorMapper.trabajadorToTrabajadorResponseDTO(trabajador);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        // Buscar usuario por nombre de usuario
        Optional<Trabajador> trabajadorOpt = trabajadorRepository.findByNombreUsuario(loginDTO.getIdentificador());
        // Si no encuentra, se puede buscar por correo electrónico (asegúrate de tener el método en el repositorio)
        if (!trabajadorOpt.isPresent()) {
            trabajadorOpt = trabajadorRepository.findByCorreoElectronico(loginDTO.getIdentificador());
        }
        if (!trabajadorOpt.isPresent()) {
            throw new EntityNotFoundException("Credenciales incorrectas");
        }
        Trabajador trabajador = trabajadorOpt.get();
        // Validar la contraseña (en producción, utiliza hashing)
        if (!trabajador.getContraseña().equals(loginDTO.getContraseña())) {
            throw new IllegalArgumentException("Credenciales incorrectas");
        }
        // Generar el token JWT usando el ID del usuario (se guarda en el claim "sub")
        return jwtUtil.generateToken(trabajador.getTrabajadorID().toString());
    }
}
