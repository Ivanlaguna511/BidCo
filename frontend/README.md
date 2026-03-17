# BidCo Frontend (Versión Local)

Este proyecto fue generado con [Angular CLI](https://github.com/angular/angular-cli) versión 19.2.1. Representa la interfaz de usuario original del proyecto universitario BidCo.

## Servidor de Desarrollo

Para levantar la interfaz en tu máquina local:

```bash
npm install
ng serve
```

Una vez que el servidor esté funcionando, abre tu navegador y entra en `http://localhost:4200/`. La aplicación se recargará automáticamente si modificas algún archivo fuente. 
*Asegúrate de tener el backend corriendo en el puerto `8080` para que las peticiones a la API funcionen.*

## Generación de Código

Angular CLI incluye herramientas para generar estructuras de código. Para crear un nuevo componente:

```bash
ng generate component nombre-componente
```

## Construcción (Build)

Para compilar el proyecto y prepararlo para producción:

```bash
ng build
```
Los archivos compilados se guardarán en el directorio `dist/`.
