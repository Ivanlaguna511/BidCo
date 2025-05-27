package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.subasta.SubastaCreateDTO;
import com.bidco.api_rest.dto.subasta.SubastaResponseDTO;
import com.bidco.api_rest.dto.FiltroDTO;
import com.bidco.api_rest.service.contract.SubastaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.util.List;

@RestController
@RequestMapping("/api/subastas")
public class SubastaController {

    private final SubastaService subastaService;

    @Autowired
    public SubastaController(SubastaService subastaService) {
        this.subastaService = subastaService;
    }

    // Crear subasta
    @PostMapping (consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SubastaResponseDTO crearSubasta(
                @RequestPart("subasta") @Valid SubastaCreateDTO subastaCreateDTO,
                @RequestPart("imagen") MultipartFile imagen) {
        return subastaService.crearSubasta(subastaCreateDTO, imagen);
    }

    // Buscar subasta por ID
    @GetMapping("/{id}")
    public SubastaResponseDTO buscarSubastaPorId(@PathVariable Long id) {
        return subastaService.buscarSubastaPorId(id);
    }

    @PutMapping("/final/{id}")
    public void finalizarSubasta(@PathVariable Long id) {
        subastaService.asignarGanadorYActualizarPrecioFinal(id);
    }

    @GetMapping("/filtrar")
    public List<SubastaResponseDTO> filtrarPorTipo(@RequestParam boolean normal) {
        return subastaService.buscarPorTipo(normal);
    }

    @GetMapping("/filtrar-creador")
    public List<SubastaResponseDTO> filtrarPorCreador(@RequestParam Long id) {
        return subastaService.listarSubastasPorCreador(id);
    }

    @GetMapping("/filtro-normal")
    public List<SubastaResponseDTO> filtrarPorTipoNormal(FiltroDTO filtro) {
        System.out.println(filtro.toString());
        return subastaService.buscarPorFiltroNormal(filtro);
    }
}