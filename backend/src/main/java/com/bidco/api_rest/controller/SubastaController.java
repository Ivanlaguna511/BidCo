package com.bidco.api_rest.controller;

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SubastaResponseDTO crearSubasta(
                @RequestPart("subasta") @Valid SubastaCreateDTO subastaCreateDTO,
                @RequestPart("imagen") MultipartFile imagen) {
        return subastaService.crearSubasta(subastaCreateDTO, imagen);
    }

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
    public List<SubastaResponseDTO> buscarPorFiltroNormal(FiltroDTO filtro) {
        return subastaService.buscarPorFiltroNormal(filtro);
    }

    @GetMapping("/filtro-ciega")
    public List<SubastaResponseDTO> buscarPorFiltroCiega(FiltroDTO filtro) {
        return subastaService.buscarPorFiltroCiega(filtro);
    }

    @GetMapping("/filtro-mis-subastas")
    public List<SubastaResponseDTO> buscarPorFiltroMisSubastas(FiltroDTO filtro, int id) {
        return subastaService.buscarPorFiltroMisSubastas(filtro, id);
    }

    @GetMapping("/filtro-mis-pujas")
    public List<SubastaResponseDTO> buscarPorFiltroMisPujas(FiltroDTO filtro, int id) {
        return subastaService.buscarPorFiltroMisPujas(filtro, id);
    }

    @GetMapping("/filtro-nombre")
    public List<SubastaResponseDTO> buscarPorFiltroYNombre(FiltroDTO filtro, String searchTerm) {
        return subastaService.buscarPorFiltroYNombre(filtro, searchTerm);
    }
}