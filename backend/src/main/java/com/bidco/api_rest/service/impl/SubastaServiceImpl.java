package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.subasta.SubastaCreateDTO;
import com.bidco.api_rest.dto.subasta.SubastaResponseDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.mapper.PujaMapper;
import com.bidco.api_rest.mapper.SubastaMapper;
import com.bidco.api_rest.mapper.UsuarioMapper;
import com.bidco.api_rest.model.Puja;
import com.bidco.api_rest.model.Subasta;
import com.bidco.api_rest.model.Usuario;
import com.bidco.api_rest.repository.SubastaRepository;
import com.bidco.api_rest.repository.UsuarioRepository;
import com.bidco.api_rest.service.contract.SubastaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;


@Service
public class SubastaServiceImpl implements SubastaService {

    private final SubastaRepository subastaRepository;
    private final SubastaMapper subastaMapper;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository userRepository;
    private final UsuarioRepository usuarioRepository;
    private final PujaMapper pujaMapper;

    public SubastaServiceImpl(SubastaRepository subastaRepository, SubastaMapper subastaMapper, UsuarioMapper usuarioMapper, UsuarioRepository userRepository, UsuarioRepository usuarioRepository, PujaMapper pujaMapper) {
        this.subastaRepository = subastaRepository;
        this.subastaMapper = subastaMapper;
        this.usuarioMapper = usuarioMapper;
        this.userRepository = userRepository;
        this.usuarioRepository = usuarioRepository;
        this.pujaMapper = pujaMapper;
    }

    @Override
    public SubastaResponseDTO crearSubasta(SubastaCreateDTO subastaCreateDTO) {
        if(subastaCreateDTO==null){
            throw new IllegalArgumentException("La subasta no puede ser nula");
        }
        Subasta subasta = subastaMapper.subastaCreateDTOToSubasta(subastaCreateDTO);
        subasta.setPrecioFinal(subasta.getPrecioInicial());
        subastaRepository.save(subasta);
        return subastaMapper.subastaToSubastaResponseDTO(subasta);
    }

    @Override
    public SubastaResponseDTO buscarSubastaPorId(Long id) {
        Subasta subasta = subastaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subasta no encontrada"));

        return subastaMapper.subastaToSubastaResponseDTO(subasta);
    }

    @Override
    @Transactional
    public PujaResponseDTO asignarGanadorYActualizarPrecioFinal(Long id) {
        // Verificamos que la subasta exista
        Subasta subasta = subastaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la subasta con ID: " + id));

        // Comprobamos que ya haya finalizado
        if (subasta.getFechaFinal().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La subasta todavía no se ha cerrado.");
        }

        // Obtenemos el usuario ganador
        Optional<Puja> puja = subastaRepository.findGanadorSubastaBySubastaId(id);
        Optional<BigDecimal> importeMayorOpt = subastaRepository.findImporteMayorBySubastaId(id);

        if (puja.isEmpty() || importeMayorOpt.isEmpty()) {
            throw new IllegalStateException("No hubo ninguna puja en la subasta.");
        }



        // Actualizamos el campo precioFinal y ganador
        subasta.setPrecioFinal(importeMayorOpt.get());
        subastaRepository.save(subasta);

        return pujaMapper.pujaToPujaResponseDTO(puja.get());
    }

    @Override
    public List<SubastaResponseDTO> buscarPorTipo(boolean normal) {
        List<Subasta> subastas = subastaRepository.findBySubastaNormal(normal);
        return subastas.stream()
                .map(subastaMapper::subastaToSubastaResponseDTO)
                .toList();
    }

}
