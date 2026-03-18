<div align="center">
  <h1 align="center">BidCo</h1>

  <p align="center">
    <strong>Plataforma web Full-Stack diseñada para gestionar subastas (normales y a ciegas) y sorteos.</strong>
    <br />
    <br />
    <a href="https://bid-co.vercel.app/auction"><strong>Ver demostración en vivo (Esperar 1-2 min a que carge todo, está todo alojado en servicios cloud gratis)»</strong></a>
    <br />
    <br />
  </p>

  <p align="center">
    <img src="https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white" alt="Angular" />
    <img src="https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white" alt="TypeScript" />
    <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot" />
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
    <img src="https://img.shields.io/badge/Vercel-000000?style=for-the-badge&logo=vercel&logoColor=white" alt="Vercel" />
    <img src="https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=white" alt="Render" />
    <img src="https://img.shields.io/badge/Cloudinary-3448C5?style=for-the-badge&logo=cloudinary&logoColor=white" alt="Cloudinary" />
  </p>
</div>

---

## Sobre el Proyecto

Este proyecto fue creado para la asignatura Servicios y Sistemas Web, asignatura perteneciente al segundo cuatrimestre del tercer curso. BidCo es una aplicación moderna estructurada con una arquitectura cliente-servidor. Su objetivo es ofrecer una experiencia fluida y segura en la participación de subastas y sorteos, separando un frontend basado en componentes de un backend robusto. El branch `master` es el original que desarrollamos y entregamos durante la asignatura, el branch `deploy` es el que hemos ido mejorando y actualizando y está completamente alojado en la nube.

**Características principales:**
- Gestión de subastas tradicionales y a ciegas.
- Sistema integrado de sorteos.
- Almacenamiento seguro de archivos e imágenes en la nube (Cloudinary).
- Lógica de negocio protegida con políticas CORS estrictas.

---

## Tecnologías Utilizadas

### Frontend
- **Framework:** Angular 21+ *(Standalone Components, `@if`/`@for` control flow)*
- **Lenguaje:** TypeScript
- **Estilos:** CSS3 Puro
- **Entornos:** Gestión dinámica mediante `environment.ts` (Producción) y `environment.development.ts` (Local).

### Backend
- **Framework:** Java Spring Boot 3+ (Java 25+)
- **Build Tool:** Maven
- **Base de Datos:** MySQL
- **Gestión de Archivos:** Integración con **Cloudinary** para almacenamiento persistente en la nube.

---

## Requisitos Previos

Para levantar este proyecto desde cero en tu máquina local, asegúrate de tener instalado:

* [Node.js y npm](https://nodejs.org/)
* [Angular CLI](https://angular.io/cli) (`npm install -g @angular/cli`)
* [Java Development Kit (JDK) 25+](https://jdk.java.net/)
* [Maven](https://maven.apache.org/)
* [Git](https://git-scm.com/)

---

## Instalación y Ejecución Local

Sigue estos pasos en orden para asegurar la correcta conexión entre el cliente y el servidor.

### 1. Clonar el repositorio

Abre tu terminal y ejecuta:

```bash
git clone [https://github.com/Ivanlaguna511/BidCo.git](https://github.com/Ivanlaguna511/BidCo.git)
cd BidCo
```

### 2. Configurar y Levantar el Backend (Spring Boot)

El servidor debe estar corriendo antes que el frontend para que la base de datos y las APIs estén disponibles.
Navega a la carpeta del backend:

```bash
cd backend
```

Abre el archivo de propiedades de Spring Boot (`src/main/resources/application.properties`) o configura las variables en tu entorno local. Asegúrate de que las credenciales apuntan a tu base de datos:

```properties
spring.datasource.url=jdbc:mysql://[TU-URL-DE-BASE-DE-DATOS]
spring.datasource.username=[TU-USUARIO]
spring.datasource.password=[TU-CONTRASEÑA]
```

Instala las dependencias y compila el proyecto con Maven:

```bash
mvn clean install
mvn spring-boot:run
```

*Nota: El backend arrancará en `http://localhost:8080`.*

### 3. Configurar y Levantar el Frontend (Angular)

Abre otra pestaña en tu terminal (deja el backend corriendo en la primera) y haz lo siguiente:

Navega a la carpeta del frontend:

```bash
cd frontend
```

Instala todas las dependencias del `package.json`:

```bash
npm install
```

Arranca el servidor de desarrollo de Angular:

```bash
ng serve
```

Abre tu navegador y entra en `http://localhost:4200`. ¡La aplicación ya está funcionando en local conectada a tu backend!
