package com.bidco.api_rest.service.contract;

import java.util.List;

import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.pujasorteo.PujaSorteoResponseDTO;
import com.bidco.api_rest.dto.sorteo.SorteoCreateDTO;
import com.bidco.api_rest.dto.sorteo.SorteoResponseDTO;
import com.bidco.api_rest.model.PujaSorteo;
import com.bidco.api_rest.model.Sorteo;

public interface SorteoService {

    SorteoResponseDTO crearSorteo(SorteoCreateDTO sorteoCreateDTO);
    SorteoResponseDTO buscarSorteoPorId(Long id);
    SorteoResponseDTO asignarGanadorYActualizarPrecioFinal(Long id);
    List<SorteoResponseDTO> obtenerTodosLosSorteos();
}
