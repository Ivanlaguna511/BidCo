# BidCo - Plataforma de Subastas y Sorteos

BidCo es una aplicación web Full-Stack diseñada para gestionar subastas (normales y a ciegas) y sorteos. El proyecto está estructurado con una arquitectura cliente-servidor, separando un frontend moderno basado en componentes y un backend robusto que gestiona la lógica de negocio, la seguridad (CORS) y el almacenamiento de archivos.

**[Ver demostración en vivo](https://vercel.com/ivanlaguna511s-projects/bid-co)**

---

## Tecnologías Utilizadas

### Frontend (Desplegado en Vercel)
* **Framework:** Angular 21+ (Uso de Standalone Components y `@if`/`@for` control flow).
* **Lenguaje:** TypeScript.
* **Estilos:** CSS3 puro.
* **Gestión de Entornos:** Configuración dinámica de URLs de API mediante `environment.ts` (producción) y `environment.development.ts` (local).

### Backend (Desplegado en Render)
* **Framework:** Java Spring Boot 3+ (Java 25+).
* **Gestor de dependencias:** Maven.
* **Base de Datos:** PostgreSQL (Alojada en la nube mediante **Aiven**).
* **Archivos:** Gestión de imágenes subidas por el usuario en sistema de archivos local (`/uploads`) mapeado a través de `ResourceHandlerRegistry`.

---

## Requisitos Previos

Para levantar este proyecto desde cero en tu máquina local, necesitarás tener instalado:
* **Node.js** y **npm** (para el Frontend).
* **Angular CLI** (`npm install -g @angular/cli`).
* **Java Development Kit (JDK) 25** o superior.
* **Maven** (para compilar el Backend).
* **Git**.

---

## Instalación y Ejecución Local (Paso a Paso)

Sigue estos pasos en orden para no tener problemas de conexión entre el cliente y el servidor.

### 1. Clonar el repositorio
Abre tu terminal y ejecuta:
```bash
git clone [https://github.com/Ivanlaguna511/BidCo.git](https://github.com/Ivanlaguna511/BidCo.git)
cd BidCo
```
### 2. Configurar y Levantar el Backend (Spring Boot)
El servidor debe estar corriendo antes que el frontend para que la base de datos y las APIs estén disponibles.
Navega a la carpeta del backend
```bash
cd backend
```
Abre el archivo de propiedades de Spring Boot (src/main/resources/application.properties).
Asegúrate de que las credenciales apuntan a tu base de datos:

```bash
Properties
spring.datasource.url=jdbc:postgresql://[URL-DE-AIVEN]
spring.datasource.username=[TU-USUARIO]
spring.datasource.password=[TU-CONTRASEÑA]
Instala las dependencias y compila el proyecto con Maven:
```

```bash
mvn clean install
mvn spring-boot:run
```

Nota: El backend arrancará en http://localhost:8080. El sistema creará automáticamente la carpeta uploads/ en la raíz si no existe para almacenar las imágenes de los productos.

### 3. Configurar y Levantar el Frontend (Angular)
Abre otra pestaña en tu terminal (deja el backend corriendo en la primera) y haz lo siguiente:

Navega a la carpeta del frontend:

```bash
cd frontend
```
Instala todas las dependencias del package.json:
```bash
npm install
```
Arranca el servidor de desarrollo de Angular:
```bash
ng serve
```
Abre tu navegador y entra en http://localhost:4200. ¡La aplicación ya está funcionando en local conectada a tu backend!
