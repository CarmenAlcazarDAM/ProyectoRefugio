# Proyecto Refugio: Sistema de Gestión de Protectora de Animales

Este proyecto consiste en el diseño y desarrollo de una aplicación de escritorio (*standalone*) orientada a optimizar y digitalizar la gestión integral de un refugio de animales. El software unifica en una única interfaz gráfica el control de dos de los pilares fundamentales de la organización: los animales en adopción y el equipo de voluntarios.

A través de un sistema gestor de persistencia de datos, la aplicación realiza un seguimiento exhaustivo de la estancia de los animales (ingresos, bajas, adopciones), la organización de los datos de contacto de los adoptantes y el registro de las tareas de voluntariado en los diferentes cheniles y jaulas del refugio.

---

## 📌 Índice de Contenidos

1. [Descripción del Proyecto](#-descripción-del-proyecto)
2. [Objetivos y Utilidad](#-objetivos-y-utilidad)
3. [Tecnologías y Arquitectura](#-tecnologías-y-arquitectura)
4. [Diseño y Modelado de Datos](#-diseño-y-modelado-de-datos)
5. [Galería de Imágenes y Prototipos](#-galería-de-imágenes-y-prototipos)
6. [Planificación (Sprints)](#-planificación-sprints)
7. [Características Técnicas Destacadas](#-características-técnicas-destacadas)

---

## 📖 Descripción del Proyecto

La interfaz de usuario ha sido diseñada buscando la máxima accesibilidad y fluidez, permitiendo que cualquier voluntario pueda utilizarla de forma intuitiva con independencia de su nivel informático. Además, cuenta con estrictos controles de seguridad y validación para evitar la introducción de datos erróneos en el sistema.

### 🎯 Objetivos y Utilidad

* **Centralización de la información:** Sustituir los flujos obsoletos (como la coordinación por grupos de WhatsApp o el papel) por un sistema centralizado que garantice la integridad de los datos.
* **Automatización de estados:** Control estricto del estado de los animales (altas y bajas) para agilizar las consultas del personal.
* **Coordinación del voluntariado:** Registro actualizado de tareas vinculadas a los espacios físicos del refugio.
* **Reducción de errores administrativos:** Validaciones automáticas en tiempo real.

---

## 🛠️ Tecnologías y Arquitectura

* **Lenguaje de Programación:** Java (JDK 21).
* **Entorno de Desarrollo:** IntelliJ IDEA Community Edition.
* **Gestión de Dependencias:** Maven.
* **Interfaz Gráfica:** JavaFX (`javafx-controls`, `javafx-fxml`) diseñado con Scene Builder.
* **Base de Datos:** MySQL (gestionado mediante MySQL Workbench).
* **Patrón de Arquitectura:** MVC (Modelo-Vista-Controlador) junto con una capa DAO dedicada a la persistencia.
* **Conexión y Persistencia:** Centralizada mediante el patrón *Singleton* y archivos de configuración XML procesados con JAXB.

---

## 📐 Diseño y Modelado de Datos

El sistema cuenta con un total de **8 tablas** organizadas en tres bloques de entidades principales (Persona, Animal y Ubicación).

* **Jerarquía de Herencia (Persona):** Una `Persona` se especializa de forma estricta (1:1) en `Voluntario` o `Adoptante`.
* **Jerarquía de Herencia (Animal):** La tabla base `Animal` se especializa mediante relaciones 1:1 en las tablas `Perro` y `Gato`.
* **Relación N:M (Voluntario ↔ Ubicación):** Materializada a través de la entidad asociativa `Ayuda`, que registra la fecha y la tarea concreta realizada por el voluntario.

---

## 🖼️ Galería de Imágenes y Prototipos

A continuación se detallan los accesos directos a los diagramas técnicos y las capturas de pantalla de la interfaz gráfica implementada:

### 🧩 Diagramas de Arquitectura
* **[Ir al Modelo Entidad/Relación conceptual](Documentos Adjuntos/ENTIDAD-RELACIÓN.pdf)**
* **[Ir al Modelo Relacional](Documentos Adjuntos/ESQUEMA RELACIONAL.drawio.pdf)**
* **[Ir al Diagrama de Clases](Documentos Adjuntos/DIAGRAMA DE CLASES.drawio.pdf)**
* **[Ir al Diagrama de Casos de Uso](Documentos Adjuntos/Casos de uso.png)**



