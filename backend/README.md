# ⚙️ BidCo Backend Server (Local Version)

API RESTful desarrollada en Java y Spring Boot que gestiona la lógica de negocio de la plataforma BidCo. Esta versión original está diseñada para ejecutarse en entornos de desarrollo locales.

## 🛠️ Stack Tecnológico

* **Framework:** Spring Boot 3.4.4
* **Lenguaje:** Java 17
* **Gestor de dependencias:** Maven
* **Base de Datos:** MySQL
* **Autenticación:** JWT (JSON Web Tokens)
* **Mapeo de Entidades:** MapStruct (1.5.5.Final)

## 📋 Requisitos Previos

* JDK 17 instalado.
* Maven 3.6+ instalado.
* Servidor MySQL ejecutándose en el puerto `3306`.

## 🗄️ Configuración de Base de Datos

1. Asegúrate de tener tu servidor MySQL encendido.
2. Ejecuta tu script de inicialización SQL en tu servidor MySQL (puedes usar MySQL Workbench) para generar el esquema y los datos de prueba.
3. Verifica que las credenciales en el archivo `src/main/resources/application.properties` coinciden con tu entorno local:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bidco_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

## 🚀 Instalación y Ejecución

1. Navega al directorio del backend:
```bash
cd backend
```

2. Limpia, compila el proyecto y genera los mappers:
```bash
mvn clean install
```

3. Arranca el servidor:
```bash
mvn spring-boot:run
```

El servidor estará disponible en `http://localhost:8080`.
