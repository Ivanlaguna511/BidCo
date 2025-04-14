package com.bidco.api_rest.service.contract;

import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.pujasorteo.PujaSorteoResponseDTO;
import com.bidco.api_rest.dto.sorteo.SorteoCreateDTO;
import com.bidco.api_rest.dto.sorteo.SorteoResponseDTO;
import com.bidco.api_rest.model.PujaSorteo;
import com.bidco.api_rest.model.Sorteo;

public interface SorteoService {

    SorteoResponseDTO crearSorteo(SorteoCreateDTO sorteoCreateDTO);
    SorteoResponseDTO buscarSorteoPorId(Long id);
    PujaSorteoResponseDTO asignarGanadorYActualizarPrecioFinal(Long id);
}
