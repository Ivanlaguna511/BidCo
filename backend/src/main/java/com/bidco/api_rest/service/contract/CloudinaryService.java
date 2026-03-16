package com.bidco.api_rest.service.contract;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    /**
     * Sube un archivo a Cloudinary y devuelve la URL pública.
     */
    String uploadFile(MultipartFile file);
}