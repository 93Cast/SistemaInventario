# Inventario FPA Android

Aplicación móvil Android desarrollada en Kotlin para la gestión y control del inventario de equipos informáticos de la Fundación Padre Arrupe de El Salvador.

## Descripción

El proyecto tiene como objetivo facilitar el registro, consulta, actualización y control de los equipos informáticos distribuidos en los edificios 1, 2, 3 y 4 de la Fundación Padre Arrupe.

La aplicación permite mantener un inventario organizado, mejorar el seguimiento de activos tecnológicos y optimizar la gestión de recursos institucionales.

## Objetivo General

Desarrollar una aplicación Android que permita administrar el inventario de equipos informáticos de la Fundación Padre Arrupe mediante una interfaz intuitiva y almacenamiento local utilizando Room Database.

## Funcionalidades

### Gestión de Equipos

* Registrar equipos informáticos.
* Consultar inventario completo.
* Editar información de equipos.
* Eliminar registros.
* Visualizar detalles de cada equipo.

### Organización por Edificios

* Edificio 1
* Edificio 2
* Edificio 3
* Edificio 4

### Control de Estado

* Activo
* En mantenimiento
* Dañado
* Dado de baja

### Dashboard

* Total de equipos registrados.
* Equipos por edificio.
* Equipos en mantenimiento.
* Resumen general del inventario.

## Tecnologías Utilizadas

* Kotlin
* Android Studio
* Jetpack Compose
* Navigation Compose
* Room Database
* ViewModel (MVVM)
* Material Design 3
* Git
* GitHub

## Arquitectura

El proyecto implementa el patrón de arquitectura MVVM (Model - View - ViewModel) para garantizar una adecuada separación de responsabilidades.

### Estructura del Proyecto

```text
com.fpa.inventario

├── data
│   ├── dao
│   ├── database
│   ├── entity
│   └── repository
│
├── navigation
│
├── ui
│   ├── components
│   └── screens
│
├── viewmodel
│
├── utils
│
└── MainActivity.kt
```

## Base de Datos

### Entidad Principal: EquipoInformatico

| Campo         | Tipo   |
| ------------- | ------ |
| id            | Int    |
| nombre        | String |
| tipo          | String |
| numeroSerie   | String |
| edificio      | Int    |
| estado        | String |
| observaciones | String |
| fechaRegistro | String |

## Pantallas Implementadas

1. Login
2. Dashboard Principal
3. Listado de Inventario
4. Registro de Equipo
5. Detalle de Equipo
6. Edición de Equipo

## Requisitos

* Android Studio Narwhal o superior
* Android SDK 24+
* Kotlin 2.x
* Gradle 8+

## Instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/TU-USUARIO/inventario-fpa-android.git
```

2. Abrir el proyecto en Android Studio.

3. Sincronizar dependencias Gradle.

4. Ejecutar la aplicación en un emulador o dispositivo físico.

## Control de Versiones

Este proyecto utiliza Git siguiendo una estrategia de commits descriptivos:

```text
feat: nueva funcionalidad
fix: corrección de errores
docs: actualización documentación
refactor: mejora de código
test: pruebas unitarias
```

## Estado del Proyecto

🚧 En desarrollo

Proyecto académico desarrollado para la asignatura:

**Técnicas de Producción Industrial de Software**

## Autor

Daniel Sosa

Fundación Padre Arrupe de El Salvador

2026
