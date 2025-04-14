package com.bidco.api_rest.service.contract;

import com.bidco.api_rest.dto.puja.PujaCreateDTO;
import com.bidco.api_rest.dto.puja.PujaResponseDTO;

public interface PujaService {

    PujaResponseDTO crearPuja(PujaCreateDTO pujaCreateDTO);
    PujaResponseDTO buscarPujaPorId(Long id);
}
