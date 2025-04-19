package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.dto.admin.AdminLoginDTO;
import com.bidco.api_rest.dto.admin.ExpertCreateDTO;
import com.bidco.api_rest.dto.admin.SorteoCreateDTO;
import com.bidco.api_rest.model.Sorteo;
import com.bidco.api_rest.model.Trabajador;
import com.bidco.api_rest.repository.SorteoRepository;
import com.bidco.api_rest.repository.TrabajadorRepository;
import com.bidco.api_rest.service.contract.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AdminServiceImpl implements AdminService {

    private final TrabajadorRepository trabajadorRepo;
    private final SorteoRepository    sorteoRepo;

    @Autowired
    public AdminServiceImpl(
            TrabajadorRepository trabajadorRepo,
            SorteoRepository sorteoRepo) {
        this.trabajadorRepo = trabajadorRepo;
        this.sorteoRepo     = sorteoRepo;
    }

    @Override
    public void login(AdminLoginDTO dto, HttpSession session) {
        Trabajador admin = trabajadorRepo
            .findByNombreUsuario(dto.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Comprueba que sea administrador
        if (!admin.isExperto()) {
            throw new IllegalArgumentException("Acceso denegado: no es administrador");
        }
        // Comparación de contraseñas en texto plano
        if (!admin.getContraseña().equals(dto.getPassword())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
        // Guarda el ID en sesión
        session.setAttribute("adminId", admin.getTrabajadorID());
    }

    @Override
    public void createSorteo(SorteoCreateDTO dto, HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            throw new IllegalArgumentException("No autenticado como administrador");
        }
        // Valida fechas
        if (dto.getFechaInicio().isAfter(dto.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la de fin");
        }

        Sorteo sorteo = new Sorteo();
        sorteo.setNombreArticulo(dto.getNombreArticulo());
        sorteo.setDescripcion(dto.getDescripcion());
        sorteo.setFechaInicio(dto.getFechaInicio());
        sorteo.setFechaFin(dto.getFechaFin());
        sorteo.setPuntosNecesarios(dto.getPuntosNecesarios());
        sorteo.setPuntosFinales(0);
        // Relación ManyToOne: setCreador espera un Trabajador
        Trabajador creador = new Trabajador();
        creador.setTrabajadorID(adminId);
        sorteo.setCreador(creador);

        // Procesa imagen si existe
        MultipartFile img = dto.getImagen();
        if (img != null && !img.isEmpty()) {
            try {
                String uploadsDir = "/uploads/sorteos/";
                File folder = new File(uploadsDir);
                if (!folder.exists()) folder.mkdirs();

                String filename = System.currentTimeMillis() + "_" + img.getOriginalFilename();
                File dest = new File(folder, filename);
                img.transferTo(dest);
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

    @Override
    public void createExpert(ExpertCreateDTO dto, HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            throw new IllegalArgumentException("No autenticado como administrador");
        }

        Trabajador expert = new Trabajador();
        expert.setNombreUsuario(dto.getNombreUsuario());
        expert.setCorreoElectronico(dto.getCorreoElectronico());
        // Almacenamiento en texto plano
        expert.setContraseña(dto.getContraseña());
        // 'experto' en tu entidad marca si es experto o admin; aquí false = experto normal
        expert.setExperto(false);

        try {
            trabajadorRepo.save(expert);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("El nombre de usuario o correo ya existe", e);
        }
    }
}