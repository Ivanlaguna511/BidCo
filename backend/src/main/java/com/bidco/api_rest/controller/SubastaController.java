package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.subasta.SubastaCreateDTO;
import com.bidco.api_rest.dto.subasta.SubastaResponseDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.service.contract.SubastaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public SubastaResponseDTO crearSubasta(@Valid @RequestBody SubastaCreateDTO subastaCreateDTO) {
        return subastaService.crearSubasta(subastaCreateDTO);
    }

    // Buscar subasta por ID
    @GetMapping("/{id}")
    public SubastaResponseDTO buscarSubastaPorId(@PathVariable Long id) {
        return subastaService.buscarSubastaPorId(id);
    }

    @PutMapping("/ganador/{id}")
    public PujaResponseDTO findGanadorSubasta(@PathVariable Long id) {
        return subastaService.asignarGanadorYActualizarPrecioFinal(id);
    }

    @GetMapping("/filtrar")
    public List<SubastaResponseDTO> filtrarPorTipo(@RequestParam boolean normal) {
        return subastaService.buscarPorTipo(normal);
    }

}
