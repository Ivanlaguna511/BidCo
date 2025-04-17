package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.pujasorteo.PujaSorteoResponseDTO;
import com.bidco.api_rest.dto.sorteo.SorteoCreateDTO;
import com.bidco.api_rest.dto.sorteo.SorteoResponseDTO;
import com.bidco.api_rest.mapper.PujaSorteoMapper;
import com.bidco.api_rest.mapper.SorteoMapper;
import com.bidco.api_rest.model.Puja;
import com.bidco.api_rest.model.PujaSorteo;
import com.bidco.api_rest.model.Sorteo;
import com.bidco.api_rest.model.Subasta;
import com.bidco.api_rest.repository.SorteoRepository;
import com.bidco.api_rest.service.contract.SorteoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SorteoServiceImpl implements SorteoService {

    private final SorteoRepository sorteoRepository;
    private final SorteoMapper sorteoMapper;
    private final PujaSorteoMapper pujaSorteoMapper;

    public SorteoServiceImpl(SorteoRepository sorteoRepository, SorteoMapper sorteoMapper, PujaSorteoMapper pujaSorteoMapper) {
        this.sorteoRepository = sorteoRepository;
        this.sorteoMapper = sorteoMapper;
        this.pujaSorteoMapper = pujaSorteoMapper;
    }

    @Override
    public SorteoResponseDTO crearSorteo(SorteoCreateDTO sorteoCreateDTO) {
        if(sorteoCreateDTO==null){
            throw new IllegalArgumentException("el Sorteo no puede ser nulo");
        }
        Sorteo sorteo = sorteoMapper.sorteoCreateDTOToSorteo(sorteoCreateDTO);
        sorteoRepository.save(sorteo);
        return sorteoMapper.sorteoToSorteoResponseDTO(sorteo);
    }

    @Override
    public SorteoResponseDTO buscarSorteoPorId(Long id) {
        Sorteo sorteo = sorteoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("el Sorteo no existe"));
        return sorteoMapper.sorteoToSorteoResponseDTO(sorteo);
    }

    @Override
    public PujaSorteoResponseDTO asignarGanadorYActualizarPrecioFinal(Long id) {
        // Verificamos que la subasta exista
        Sorteo sorteo = sorteoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la subasta con ID: " + id));

        // Comprobamos que ya haya finalizado
        if (sorteo.getFechaFin().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La subasta todavía no se ha cerrado.");
        }

        // Obtenemos el usuario ganador
        Optional<PujaSorteo> puja = sorteoRepository.findGanadorSorteoBySorteoId(id);
        Optional<Integer> puntosfinales = sorteoRepository.findImporteMayorBySorteoId(id);

        if (puja.isEmpty() || puntosfinales.isEmpty()) {
            throw new IllegalStateException("No hubo ninguna puja en la subasta.");
        }

        // Actualizamos el campo precioFinal y ganador
        sorteo.setPuntosFinales(puntosfinales.get());
        sorteoRepository.save(sorteo);

        return pujaSorteoMapper.pujaSorteoToPujaSorteoResponseDTO(puja.get());
    }

    @Override
    public List<SorteoResponseDTO> obtenerTodosLosSorteos() {
        List<Sorteo> sorteos = sorteoRepository.findAll();
        return sorteos.stream()
                .map(sorteoMapper::sorteoToSorteoResponseDTO)
                .collect(Collectors.toList());
    }
}
