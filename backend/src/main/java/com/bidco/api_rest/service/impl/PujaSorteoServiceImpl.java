package com.bidco.api_rest.service.impl;


import com.bidco.api_rest.dto.pujasorteo.PujaSorteoCreateDTO;
import com.bidco.api_rest.dto.pujasorteo.PujaSorteoResponseDTO;
import com.bidco.api_rest.mapper.PujaSorteoMapper;
import com.bidco.api_rest.model.PujaSorteo;
import com.bidco.api_rest.model.Sorteo;
import com.bidco.api_rest.model.Usuario;
import com.bidco.api_rest.repository.PujaSorteoRepository;
import com.bidco.api_rest.repository.SorteoRepository;
import com.bidco.api_rest.repository.UsuarioRepository;
import com.bidco.api_rest.service.contract.PujaSorteoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PujaSorteoServiceImpl implements PujaSorteoService {

    private final PujaSorteoRepository pujaSorteoRepository;
    private final PujaSorteoMapper pujaSorteoMapper;
    private final SorteoRepository sorteoRepository;
    private final UsuarioRepository usuarioRepository;

    public PujaSorteoServiceImpl(PujaSorteoRepository pujaSorteoRepository, PujaSorteoMapper pujaSorteoMapper,
                                 SorteoRepository sorteoRepository, UsuarioRepository usuarioRepository) {
        this.pujaSorteoRepository = pujaSorteoRepository;
        this.pujaSorteoMapper = pujaSorteoMapper;
        this.sorteoRepository = sorteoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public PujaSorteoResponseDTO crearPujaSorteo(PujaSorteoCreateDTO pujaSorteoCreateDTO) {
        if (pujaSorteoCreateDTO == null) {
            throw new IllegalArgumentException("La puja no puede ser nula");
        }

        // Verificar que el sorteo exista
        Sorteo sorteo = sorteoRepository.findById(pujaSorteoCreateDTO.getSorteoId())
                .orElseThrow(() -> new IllegalArgumentException("El sorteo no existe"));



        // Crear la entidad PujaSorteo
        PujaSorteo pujaSorteo = pujaSorteoMapper.pujaSorteoCreateDTOToPujaSorteo(pujaSorteoCreateDTO);



        // Guardar la puja en el repositorio
        pujaSorteo = pujaSorteoRepository.save(pujaSorteo);

        // Retornar el DTO de respuesta
        return pujaSorteoMapper.pujaSorteoToPujaSorteoResponseDTO(pujaSorteo);
    }

    @Override
    public PujaSorteoResponseDTO buscarPujaSorteoId(Long id) {
        PujaSorteo pujaSorteo = pujaSorteoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Puja del sorteo no encontrada"));

        return pujaSorteoMapper.pujaSorteoToPujaSorteoResponseDTO(pujaSorteo);
    }
}
