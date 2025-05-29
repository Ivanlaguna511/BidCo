package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.puja.PujaCreateDTO;
import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.mapper.PujaMapper;
import com.bidco.api_rest.model.Puja;
import com.bidco.api_rest.model.Subasta;
import com.bidco.api_rest.model.Usuario;
import com.bidco.api_rest.repository.PujaRepository;
import com.bidco.api_rest.repository.SubastaRepository;
import com.bidco.api_rest.repository.UsuarioRepository;
import com.bidco.api_rest.service.contract.PujaService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PujaServiceImpl implements PujaService {

    private final PujaRepository pujaRepository;
    private final PujaMapper pujaMapper;
    private final SubastaRepository subastaRepository;
    private final UsuarioRepository usuarioRepository;

    public PujaServiceImpl(PujaRepository pujaRepository, PujaMapper pujaMapper, SubastaRepository subastaRepository, UsuarioRepository usuarioRepository) {
        this.pujaRepository = pujaRepository;
        this.pujaMapper = pujaMapper;
        this.subastaRepository = subastaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public PujaResponseDTO crearPuja(PujaCreateDTO pujaCreateDTO) {
        if(pujaCreateDTO==null){
            throw new IllegalArgumentException("La puja no puede ser nula");
        }
        Puja puja = pujaMapper.pujaCreateDTOToPuja(pujaCreateDTO);
    
        Subasta subasta = subastaRepository.findById(pujaCreateDTO.getSubastaID())
            .orElseThrow(() -> new IllegalArgumentException("La Subasta no existe"));
    
        if (pujaCreateDTO.getFecha().isAfter(subasta.getFechaFinal())) {
            throw new IllegalArgumentException("La subasta ya cerró");
        }

        Puja pujaAnterior = pujaRepository.findTopBySubastaIdOrderByImporteDesc(pujaCreateDTO.getSubastaID());

        if (pujaAnterior != null) {
            Usuario pujador = usuarioRepository.findById(pujaAnterior.getPujador().getUsuarioID())
                .orElseThrow(() -> new IllegalArgumentException("Usuario No Encontrado"));

            pujador.setSaldo(pujador.getSaldo().add(pujaAnterior.getImporte()));
            usuarioRepository.save(pujador);
        }

        Usuario pujador = usuarioRepository.findById(puja.getPujador().getUsuarioID())
                .orElseThrow(() -> new IllegalArgumentException("Usuario No Encontrado"));

        pujador.setSaldo(pujador.getSaldo().subtract(puja.getImporte()));
        
        subasta.setPrecioFinal(puja.getImporte());
        
        subastaRepository.save(subasta);
        usuarioRepository.save(pujador);
        pujaRepository.save(puja);
        

        return pujaMapper.pujaToPujaResponseDTO(puja);
    }

    @Override
    public PujaResponseDTO buscarPujaPorId(Long id) {
        Puja puja = pujaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Puja no encontrada"));

        return pujaMapper.pujaToPujaResponseDTO(puja);
    }

    @Override
    public PujaResponseDTO obtenerPujaMasAlta(Long subastaId) {
        Puja pujaMaxima = pujaRepository.findTopBySubastaIdOrderByImporteDesc(subastaId);
        if (pujaMaxima == null) {
            throw new EntityNotFoundException("No hay pujas para esta subasta");
        }

        return pujaMapper.pujaToPujaResponseDTO(pujaMaxima);
    }

    @Override
    public List<Subasta> obtenerSubastasPorUsuarioId(Long usuarioId) {
        return pujaRepository.findSubastasByUsuarioId(usuarioId);
    }

}
