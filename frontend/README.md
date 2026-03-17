# BidCo Frontend Client (Local Version)

Aplicación cliente (SPA) para la plataforma BidCo. Desarrollada con Angular, proporciona la interfaz de usuario original diseñada para participar en subastas y sorteos.

## Stack Tecnológico

* **Framework:** Angular 19+ (Standalone Components)
* **Lenguaje:** TypeScript
* **Estilos:** CSS3 Puro
* **Gestor de paquetes:** npm

## Requisitos Previos

* Node.js (v18 o superior).
* Angular CLI instalado globalmente. Si no lo tienes, instálalo con:
```bash
npm install -g @angular/cli
```
* El servidor Backend de BidCo debe estar ejecutándose en `http://localhost:8080`.

## Instalación y Ejecución

1. Navega al directorio del frontend:
```bash
cd frontend
```

2. Instala las dependencias del proyecto:
```bash
npm install
```

3. Arranca el servidor de desarrollo:
```bash
ng serve
```

4. Abre tu navegador y navega a `http://localhost:4200/`.

La aplicación se conectará automáticamente al backend local y se recargará si modificas algún archivo del código fuente.
