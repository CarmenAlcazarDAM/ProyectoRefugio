<div align="center">

# Proyecto Refugio

### Aplicación de escritorio para la gestión integral de un refugio de animales

<img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk">
<img src="https://img.shields.io/badge/JavaFX-Desktop-blue?style=for-the-badge">
<img src="https://img.shields.io/badge/MySQL-Database-00758F?style=for-the-badge&logo=mysql">
<img src="https://img.shields.io/badge/MVC-Arquitectura-success?style=for-the-badge">
<img src="https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven">

<br>

Aplicación desarrollada en Java orientada a digitalizar y optimizar la gestión diaria de un refugio de animales mediante una interfaz gráfica intuitiva y persistencia de datos en MySQL.

</div>

---

## Descripción

Proyecto *standalone* centrado en centralizar la gestión de:

- Animales en adopción
- Voluntarios
- Adoptantes
- Ubicaciones del refugio

La aplicación permite gestionar ingresos, adopciones, tareas realizadas por voluntarios y organización de los distintos espacios del refugio desde una única interfaz gráfica.

---

## Objetivos del proyecto

- Centralizar la información del refugio
- Mejorar la coordinación del voluntariado
- Reducir errores administrativos
- Optimizar el tiempo de gestión
- Facilitar el seguimiento de los animales

---

## Tecnologías utilizadas

| Tecnología | Función |
|---|---|
| Java 21 | Lenguaje principal |
| JavaFX | Interfaz gráfica |
| FXML + Scene Builder | Diseño visual |
| MySQL | Base de datos |
| Maven | Gestión de dependencias |
| JAXB | Persistencia XML |
| IntelliJ IDEA | Entorno de desarrollo |

---

## Arquitectura

El proyecto sigue una arquitectura basada en:

- MVC (Modelo - Vista - Controlador)
- DAO (Data Access Object)

```bash
src/
├── controller/
├── dao/
├── model/
├── utils/
├── view/
└── connection/
