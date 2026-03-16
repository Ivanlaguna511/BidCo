package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.FiltroDTO;
import com.bidco.api_rest.dto.sorteo.SorteoCreateDTO;
import com.bidco.api_rest.dto.sorteo.SorteoResponseDTO;
import com.bidco.api_rest.mapper.PujaSorteoMapper;
import com.bidco.api_rest.mapper.SorteoMapper;
import com.bidco.api_rest.model.PujaSorteo;
import com.bidco.api_rest.model.Sorteo;
import com.bidco.api_rest.repository.PujaSorteoRepository;
import com.bidco.api_rest.repository.SorteoRepository;
import com.bidco.api_rest.repository.UsuarioRepository;
import com.bidco.api_rest.service.contract.CloudinaryService;
import com.bidco.api_rest.service.contract.SorteoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SorteoServiceImpl implements SorteoService {

    private final SorteoRepository sorteoRepository;
    private final SorteoMapper sorteoMapper;
    private final PujaSorteoMapper pujaSorteoMapper;
    private final PujaSorteoRepository pujaSorteoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CloudinaryService cloudinaryService;

    // Constructor actualizado con CloudinaryService
    public SorteoServiceImpl(SorteoRepository sorteoRepository, SorteoMapper sorteoMapper, 
                            PujaSorteoMapper pujaSorteoMapper, PujaSorteoRepository pujaSorteoRepository, 
                            UsuarioRepository usuarioRepository, CloudinaryService cloudinaryService) {
        this.sorteoRepository = sorteoRepository;
        this.sorteoMapper = sorteoMapper;
        this.pujaSorteoMapper = pujaSorteoMapper;
        this.pujaSorteoRepository = pujaSorteoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public SorteoResponseDTO crearSorteo(SorteoCreateDTO sorteoCreateDTO) {
        if(sorteoCreateDTO == null){
            throw new IllegalArgumentException("el Sorteo no puede ser nulo");
        }
        
        Sorteo sorteo = sorteoMapper.sorteoCreateDTOToSorteo(sorteoCreateDTO);

        if (sorteoCreateDTO.getImagen() != null && !sorteoCreateDTO.getImagen().isEmpty()) {
            try {
                // Subimos la imagen a la nube y guardamos la URL
                String urlImagen = cloudinaryService.uploadFile(sorteoCreateDTO.getImagen());
                sorteo.setImagen(urlImagen);
            } catch (Exception e) {
                throw new RuntimeException("Error al subir la imagen del sorteo a Cloudinary", e);
            }
        }

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
    public SorteoResponseDTO asignarGanadorYActualizarPrecioFinal(Long id) {
        Sorteo sorteo = sorteoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el sorteo con ID: " + id));

        List<PujaSorteo> participaciones = pujaSorteoRepository.findBySorteoSorteoID(sorteo.getSorteoID());
        
        if (participaciones.isEmpty()) {
            sorteo.setGanador(null); // O manejar como prefieras si no hay participantes
        } else {
            int totalPuntos = participaciones.stream().mapToInt(p -> p.getPuntos().intValue()).sum();
            int corte = new Random().nextInt(totalPuntos);
            int acumulado = 0;
            
            PujaSorteo participacion = participaciones.get(0);
            for(int i = 0; i < participaciones.size() && corte >= acumulado; i++) {
                participacion = participaciones.get(i);
                acumulado += participacion.getPuntos().intValue();
            }
            sorteo.setGanador(participacion.getPujador().getUsuarioID());
        }

        sorteoRepository.save(sorteo);
        return sorteoMapper.sorteoToSorteoResponseDTO(sorteo);
    }

    @Override
    public List<SorteoResponseDTO> obtenerTodosLosSorteos() {
        List<Sorteo> sorteos = sorteoRepository.findAllByOrderByFechaInicioDesc();
        return sorteos.stream()
                .map(sorteoMapper::sorteoToSorteoResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SorteoResponseDTO> buscarPorFiltro(FiltroDTO filtro) {
        String[] listaCategorias = {"Tecnología", "Hogar", "Moda", "Deportes", "Juguetes", "Otros"};
        String[] queryCategorias = (filtro.getCategorias() == null || filtro.getCategorias().length == 0) 
                                    ? listaCategorias : filtro.getCategorias();

        List<Sorteo> sorteo = sorteoRepository.findByFiltro(filtro.getMinPrice(), filtro.getMaxPrice(), queryCategorias, filtro.getDateOrder());
        return sorteo.stream()
                .map(sorteoMapper::sorteoToSorteoResponseDTO)
                .toList();
    }
}