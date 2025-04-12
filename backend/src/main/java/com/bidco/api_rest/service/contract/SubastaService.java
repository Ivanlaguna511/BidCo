package com.bidco.api_rest.service.contract;

import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.subasta.SubastaCreateDTO;
import com.bidco.api_rest.dto.subasta.SubastaResponseDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;

public interface SubastaService {

    SubastaResponseDTO crearSubasta(SubastaCreateDTO subastaCreateDTO);
    SubastaResponseDTO buscarSubastaPorId(Long id);
    PujaResponseDTO asignarGanadorYActualizarPrecioFinal(Long id);

}
