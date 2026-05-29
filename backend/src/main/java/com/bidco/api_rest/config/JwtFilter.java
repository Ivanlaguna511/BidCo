package com.bidco.api_rest.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil = new JwtUtil();

    // Rutas públicas: no requieren token
    // Formato: "MÉTODO:/ruta" o "*:/ruta" para cualquier método
    private static final List<String> PUBLIC_PATHS = List.of(
            "POST:/api/usuarios",          // registro usuario
            "POST:/api/usuarios/login",
            "POST:/api/trabajadores/login",
            "POST:/api/admin/login",
            "GET:/api/ping",
            "GET:/api/subastas",           // listado público
            "GET:/api/subastas/filtrar",
            "GET:/api/subastas/filtro-normal",
            "GET:/api/subastas/filtro-ciega",
            "GET:/api/subastas/filtro-nombre",
            "GET:/api/sorteos",            // listado sorteos
            "GET:/api/sorteos/filtro",
            
            // --- AÑADIDO PARA DESBLOQUEAR SWAGGER EN RENDER ---
            "*:/v3/api-docs",
            "*:/v3/api-docs/swagger-config",
            "*:/swagger-ui",
            "*:/swagger-ui.html",
            "GET:/swagger-ui/index.html",
            "GET:/swagger-ui/swagger-ui.css",
            "GET:/swagger-ui/index.css",
            "GET:/swagger-ui/swagger-ui-bundle.js",
            "GET:/swagger-ui/swagger-ui-standalone-preset.js",
            "GET:/swagger-ui/swagger-initializer.js",
            "GET:/swagger-ui/favicon-32x32.png",
            "GET:/swagger-ui/favicon-16x16.png"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String method = request.getMethod();
        String path = request.getRequestURI();

        // Siempre permitir preflight de CORS
        if ("OPTIONS".equalsIgnoreCase(method)) {
            chain.doFilter(request, response);
            return;
        }

        // Permitir rutas públicas
        if (isPublic(method, path)) {
            chain.doFilter(request, response);
            return;
        }

        // Las rutas de admin usan sesión, no JWT
        if (path.startsWith("/api/admin/")) {
            chain.doFilter(request, response);
            return;
        }

        // Validar token Bearer
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendUnauthorized(response, "Token no proporcionado");
            return;
        }

        try {
            String token = authHeader.substring(7);
            jwtUtil.getClaims(token); // lanza excepción si el token es inválido o caducado
            chain.doFilter(request, response);
        } catch (Exception e) {
            sendUnauthorized(response, "Token inválido o caducado");
        }
    }

    private boolean isPublic(String method, String path) {
        for (String rule : PUBLIC_PATHS) {
            String[] parts = rule.split(":", 2);
            String ruleMethod = parts[0];
            String rulePath = parts[1];

            boolean methodMatch = "*".equals(ruleMethod) || ruleMethod.equalsIgnoreCase(method);
            boolean pathMatch = path.equals(rulePath) || path.startsWith(rulePath + "/");

            if (methodMatch && pathMatch) return true;
        }
        return false;
    }

    private void sendUnauthorized(HttpServletResponse response, String mensaje) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"" + mensaje + "\"}");
    }
}
