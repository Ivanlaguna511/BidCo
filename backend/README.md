# Proyecto BidCoDB - API de Usuarios

Este es un proyecto de ejemplo para gestionar usuarios mediante una API REST con **Spring Boot** y una base de datos **MySQL**.

## 🚀 Requisitos

Antes de comenzar, asegúrate de tener instalados los siguientes programas:

- **MySQL Workbench 8** o superior: Para gestionar tu base de datos MySQL.
- **Java 17** o superior: Asegúrate de tener el JDK instalado y configurado correctamente.

## 🛠️ Pasos de configuración

### 1. Crear una base de datos en MySQL Workbench

1. Abre **MySQL Workbench**.
2. Conéctate a tu servidor MySQL.
3. Crea una nueva base de datos con el nombre **`BidCoDB`** 
4. Ejecuta el siguiente script
  CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    primer_apellido VARCHAR(100) NOT NULL,
    segundo_apellido VARCHAR(100),
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL
   );

# Guía para usar Postman con la API de Usuarios

Esta sección te proporcionará ejemplos de cómo utilizar **Postman** para interactuar con la API de **Usuarios** de tu aplicación **Spring Boot**. Aquí aprenderás a insertar nuevos usuarios y obtener usuarios de la base de datos.

## 🚀 **1. Insertar Usuarios (POST /usuarios)**

### Paso 1: Abrir Postman

1. Abre **Postman** y crea una nueva solicitud.
2. Configura el método como `POST` y la URL como `http://localhost:8080/usuarios`.

### Paso 2: Configurar el cuerpo de la solicitud

En la pestaña **Body** de Postman, selecciona **raw** y **JSON**. Luego ingresa el siguiente JSON para crear un nuevo usuario:

#### Ejemplo de datos JSON:
{
  "nombre": "Carlos",
  "primerApellido": "Gómez",
  "segundoApellido": "Pérez",
  "nombreUsuario": "carlosgp"
}


# Guía para usar Postman - Obtener Usuarios

Esta sección proporciona ejemplos de cómo usar **Postman** para obtener todos los usuarios o un usuario específico de la base de datos a través de la API de **Usuarios** de tu aplicación **Spring Boot**.

## 🚀 **1. Obtener Todos los Usuarios (GET /usuarios)**

### Paso 1: Crear una nueva solicitud en Postman

1. Abre **Postman**.
2. Crea una nueva solicitud.
3. Configura el método HTTP como `GET`.
4. En la barra de URL, ingresa la siguiente URL:http://localhost:8080/usuarios

# Guía para usar Postman - Obtener un Usuario por ID

Esta sección proporciona los pasos para obtener un usuario específico de la base de datos a través de la API de **Usuarios** en tu aplicación **Spring Boot** usando **Postman**.

## 🚀 **1. Obtener un Usuario por ID (GET /usuarios/{id})**

### Paso 1: Crear una nueva solicitud en Postman

1. Abre **Postman**.
2. Crea una nueva solicitud.
3. Configura el método HTTP como `GET`.
4. En la barra de URL, ingresa la siguiente URL, reemplazando `{id}` con el ID del usuario que deseas obtener. Por ejemplo, para obtener el usuario con ID **1**, la URL sería: http://localhost:8080/usuarios/1
