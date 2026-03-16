package com.bidco.api_rest.service.impl;

import com.bidco.api_rest.service.contract.CloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    // Inyectamos las claves desde el application.properties
    public CloudinaryServiceImpl(
            @Value("${cloudinary.cloud_name}") String cloudName,
            @Value("${cloudinary.api_key}") String apiKey,
            @Value("${cloudinary.api_secret}") String apiSecret) {
        
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            // Sube el archivo y coge la respuesta de Cloudinary
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            // Devuelve la URL (ej: http://res.cloudinary...)
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            // Si algo falla, lanzamos una excepción clara
            throw new RuntimeException("Error al subir la imagen a Cloudinary", e);
        }
    }
}