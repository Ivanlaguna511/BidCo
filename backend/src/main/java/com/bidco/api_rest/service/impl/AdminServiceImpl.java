package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.admin.SorteoCreateDTO;
import com.bidco.api_rest.dto.admin.AdminLoginDTO;
import com.bidco.api_rest.model.Sorteo;
import com.bidco.api_rest.model.Trabajador;
import com.bidco.api_rest.repository.SorteoRepository;
import com.bidco.api_rest.repository.TrabajadorRepository;
import com.bidco.api_rest.service.contract.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AdminServiceImpl implements AdminService {

    private final TrabajadorRepository trabajadorRepo;
    private final SorteoRepository    sorteoRepo;

    public AdminServiceImpl(
        TrabajadorRepository trabajadorRepo,
        SorteoRepository sorteoRepo
    ) {
        this.trabajadorRepo = trabajadorRepo;
        this.sorteoRepo     = sorteoRepo;
    }

    @Override
    public void login(AdminLoginDTO dto, HttpSession session) {
        Trabajador admin = trabajadorRepo
            .findByNombreUsuario(dto.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (!admin.isExperto()) {
            throw new IllegalArgumentException("Acceso denegado: no es administrador");
        }
        if (!admin.getContraseña().equals(dto.getPassword())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
        session.setAttribute("adminId", admin.getTrabajadorID());
    }

    @Override
    public void createSorteo(SorteoCreateDTO dto, HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            throw new IllegalArgumentException("No autenticado como admin");
        }

        // Validar fechas
        if (dto.getFechaInicio().isAfter(dto.getFechaFin())) {
            throw new IllegalArgumentException("Fecha inicio debe ser anterior a fin");
        }

        Sorteo sorteo = new Sorteo();
        sorteo.setNombreArticulo(dto.getNombreArticulo());
        sorteo.setDescripcion(dto.getDescripcion());
        sorteo.setFechaInicio(dto.getFechaInicio());
        sorteo.setFechaFin(dto.getFechaFin());
        sorteo.setPuntosNecesarios(dto.getPuntosNecesarios());
        sorteo.setPuntosFinales(0);

        // Relación ManyToOne
        Trabajador creador = new Trabajador();
        creador.setTrabajadorID(adminId);
        sorteo.setCreador(creador);

        // Guardar imagen en disco
        MultipartFile img = dto.getImagen();
        if (img != null && !img.isEmpty()) {
            try {
                // Igual que en SubastaServiceImpl: carpeta "uploads" dentro de tu working dir
                Path folder = Paths.get(System.getProperty("user.dir"), "uploads");
                Files.createDirectories(folder);
                String filename = System.currentTimeMillis() + "_" + img.getOriginalFilename();
                Path filePath = folder.resolve(filename);
                img.transferTo(filePath.toFile());
                sorteo.setImagen(filename);
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la imagen", e);
            }
        }

        try {
            sorteoRepo.save(sorteo);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Datos inválidos para sorteo", e);
        }
    }
}