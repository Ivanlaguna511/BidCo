# BidCo Frontend Client (Cloud Edition)

Interfaz de usuario premium para la plataforma web BidCo. Esta rama ha sido completamente rediseñada e incluye un Design System propio, mejoras drásticas de UX/UI y está optimizada para su despliegue en la nube.

## Stack Tecnológico Actualizado

* **Framework:** Angular 21.2.0 (Standalone Components)
* **Lenguaje:** TypeScript 5.9
* **Analíticas:** Chart.js 4.4.8
* **Seguridad:** JWT Decode 4.0.0
* **Estilos:** CSS3 Avanzado (Grid, Flexbox, Glassmorphism)

## Novedades de esta versión

* **UI/UX Premium:** Efectos visuales modernos y estados de carga personalizados.
* **Fricción Cero:** Flujos de autenticación rediseñados y simplificados.
* **Dashboards Integrados:** Paneles de datos visuales impulsados por Chart.js.
* **Cloud Ready:** Configuración preparada para el despliegue automático en plataformas como Vercel.

## Configuración de Entornos

Las URLs de la API se gestionan dinámicamente. Debes configurar tus archivos de entorno en la carpeta `src/environments/`:

1. **Entorno Local** (`environment.development.ts`):
Debe apuntar a tu servidor local:
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

2. **Entorno de Producción** (`environment.ts`):
Debe apuntar a la URL de tu backend en la nube:
```typescript
export const environment = {
  production: true,
  apiUrl: '[https://tu-backend-api.onrender.com/api](https://tu-backend-api.onrender.com/api)'
};
```

## Instalación y Despliegue Local

Para levantar el proyecto en tu máquina:

1. Instala las dependencias:
```bash
npm install
```

2. Inicia el servidor de desarrollo:
```bash
ng serve
```

Abre tu navegador en `http://localhost:4200/`.

## Build para Producción

Para compilar los artefactos estáticos optimizados:
```bash
ng build
```

Los archivos se generarán en la carpeta `dist/`. Este es el comando que plataformas como Vercel ejecutan automáticamente.
