package com.bidco.api_rest.service.contract;

import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.subasta.SubastaCreateDTO;
import com.bidco.api_rest.dto.subasta.SubastaResponseDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.dto.FiltroDTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface SubastaService {

    SubastaResponseDTO crearSubasta(SubastaCreateDTO subastaCreateDTO, MultipartFile imagen);
    SubastaResponseDTO buscarSubastaPorId(Long id);
    void asignarGanadorYActualizarPrecioFinal(Long id);
    List<SubastaResponseDTO> buscarPorTipo(boolean normal);
    List<SubastaResponseDTO> listarSubastasPorCreador(Long id);
    List<SubastaResponseDTO> buscarPorFiltroNormal(FiltroDTO filtro);

}
