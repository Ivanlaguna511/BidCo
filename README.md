<div align="center">
  <h1 align="center">BidCo (Versión Original - Local)</h1>

  <p align="center">
    <strong>Plataforma web Full-Stack para gestión de subastas y sorteos (Proyecto Universitario).</strong>
    <br />
    <br />
    ⚠️ <strong>Nota:</strong> Esta es la rama original de entrega. Para ver la versión mejorada, optimizada y alojada en la nube, cambia a la rama <a href="https://github.com/TU_USUARIO/BidCo/tree/deploy"><strong>deploy</strong></a>.
    <br />
    <br />
  </p>
</div>

---

## Sobre el Proyecto

Este proyecto fue creado para la asignatura **Servicios y Sistemas Web** (2º cuatrimestre, 3º curso). BidCo es una aplicación con arquitectura cliente-servidor diseñada para gestionar subastas tradicionales, subastas a ciegas y sorteos.

Esta rama (`master`) contiene el código original evaluado durante el curso, diseñado para ejecutarse estrictamente en un entorno local (**localhost**) utilizando **MySQL** como base de datos.

## Tecnologías Utilizadas (Entorno Local)

* **Frontend:** Angular 19+, TypeScript, CSS3.
* **Backend:** Java Spring Boot 3+, Maven.
* **Base de Datos:** MySQL (Servidor Local).

---

## Guía de Instalación Rápida

Para probar esta versión antigua en tu máquina:

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/Ivanlaguna511/BidCo.git](https://github.com/Ivanlaguna511/BidCo.git)
   cd BidCo
   ```

2. **Base de Datos (MySQL):**
   * Abre MySQL Workbench.
   * Ejecuta el script SQL que se encuentra detallado en el `README.md` de la carpeta `/backend` para generar las tablas y datos de prueba.

3. **Backend (Spring Boot):**
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```
   *(El servidor arrancará en el puerto 8080).*

4. **Frontend (Angular):**
   Abre una nueva terminal:
   ```bash
   cd frontend
   npm install
   ng serve
   ```
   *(Abre `http://localhost:4200` en tu navegador).*
