package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.puja.PujaCreateDTO;
import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.mapper.PujaMapper;
import com.bidco.api_rest.model.Puja;
import com.bidco.api_rest.repository.PujaRepository;
import com.bidco.api_rest.repository.SubastaRepository;
import com.bidco.api_rest.service.contract.PujaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PujaServiceImpl implements PujaService {

    private final PujaRepository pujaRepository;
    private final PujaMapper pujaMapper;
    private final SubastaRepository subastaRepository;

    public PujaServiceImpl(PujaRepository pujaRepository, PujaMapper pujaMapper, SubastaRepository subastaRepository) {
        this.pujaRepository = pujaRepository;
        this.pujaMapper = pujaMapper;
        this.subastaRepository = subastaRepository;
    }

    @Override
    public PujaResponseDTO crearPuja(PujaCreateDTO pujaCreateDTO) {
        if(pujaCreateDTO==null){
            throw new IllegalArgumentException("La puja no puede ser nula");
        }
        Puja puja = pujaMapper.pujaCreateDTOToPuja(pujaCreateDTO);
        if((!subastaRepository.existsById(puja.getSubasta().getSubastaID()))){
            throw new IllegalArgumentException("La Subasta no existe");
        }
        if(puja.getFecha().isAfter(puja.getSubasta().getFechaFinal())){
            throw new IllegalArgumentException("La subasta ya cerro");
        }
        pujaRepository.save(puja);
        return pujaMapper.pujaToPujaResponseDTO(puja);
    }

    @Override
    public PujaResponseDTO buscarPujaPorId(Long id) {
        Puja puja = pujaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Puja no encontrada"));

        return pujaMapper.pujaToPujaResponseDTO(puja);
    }
}
