package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.admin.SorteoCreateDTO;
import com.bidco.api_rest.dto.admin.AdminLoginDTO;
import com.bidco.api_rest.model.Sorteo;
import com.bidco.api_rest.model.Trabajador;
import com.bidco.api_rest.repository.SorteoRepository;
import com.bidco.api_rest.repository.TrabajadorRepository;
import com.bidco.api_rest.service.contract.AdminService;
import com.bidco.api_rest.service.contract.CloudinaryService;

import jakarta.servlet.http.HttpSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminServiceImpl implements AdminService {

    private final TrabajadorRepository trabajadorRepo;
    private final SorteoRepository sorteoRepo;
    private final CloudinaryService cloudinaryService; // Nueva dependencia

    // Actualizamos el constructor para inyectar CloudinaryService
    public AdminServiceImpl(
        TrabajadorRepository trabajadorRepo,
        SorteoRepository sorteoRepo,
        CloudinaryService cloudinaryService
    ) {
        this.trabajadorRepo = trabajadorRepo;
        this.sorteoRepo = sorteoRepo;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void login(AdminLoginDTO dto, HttpSession session) {
        Trabajador admin = trabajadorRepo
            .findByNombreUsuario(dto.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (admin.isExperto() != dto.getRol()) {
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
        sorteo.setCategoria(dto.getCategoria());

        Trabajador creador = new Trabajador();
        creador.setTrabajadorID(adminId);
        sorteo.setCreador(creador);

        MultipartFile img = dto.getImagen();
        if (img != null && !img.isEmpty()) {
            try {
                // Subimos a la nube y guardamos la URL de retorno
                String urlImagen = cloudinaryService.uploadFile(img);
                sorteo.setImagen(urlImagen);
            } catch (Exception e) {
                throw new RuntimeException("Error al subir la imagen del sorteo a Cloudinary", e);
            }
        }

        try {
            sorteoRepo.save(sorteo);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Datos inválidos para sorteo", e);
        }
    }
}