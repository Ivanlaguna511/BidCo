package com.bidco.api_rest.controller;

import com.bidco.api_rest.dto.puja.PujaResponseDTO;
import com.bidco.api_rest.dto.subasta.SubastaCreateDTO;
import com.bidco.api_rest.dto.subasta.SubastaResponseDTO;
import com.bidco.api_rest.dto.usuario.UsuarioResponseDTO;
import com.bidco.api_rest.service.contract.SubastaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PutMapping("/ganador/{id}")
    public PujaResponseDTO findGanadorSubasta(@PathVariable Long id) {
        return subastaService.asignarGanadorYActualizarPrecioFinal(id);
    }

    @GetMapping("/filtrar")
    public List<SubastaResponseDTO> filtrarPorTipo(@RequestParam boolean normal) {
        return subastaService.buscarPorTipo(normal);
    }

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verImagen(@PathVariable String filename) {
        try {
            Path ruta = Paths.get(System.getProperty("user.dir") + "/uploads").resolve(filename);
            Resource recurso = new UrlResource(ruta.toUri());
            if (recurso.exists() && recurso.isReadable()) {
                return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"")
                    .body(recurso);
            } else {
                throw new RuntimeException("No se pudo leer el archivo");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al cargar la imagen", e);
        }
    }
}
