# CV Management

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](./LICENSE)
[![Version: 1.0](https://img.shields.io/badge/Version-1.0-blue.svg)](./README.md)

**CV Management** es un proyecto desarrollado en **Kotlin** para gestionar currículums y reuniones con personal de selección, ayudando a las personas a organizar su búsqueda de empleo de manera eficiente.

## Características

- **Gestión de currículums**: Organiza, edita y almacena múltiples versiones de tu currículum.
- **Seguimiento de reuniones**: Registra entrevistas y reuniones con detalles como fechas, empresas y contactos.
- **Integración futura**: Plan para integrarse con plataformas de empleo como LinkedIn o Glassdoor.
- **Diseño modular**: Fácil de expandir y personalizar según las necesidades del usuario.

## Tecnologías Utilizadas

- **Lenguaje**: Kotlin
- **Frameworks**: 
  - Para futuras integraciones, se está evaluando Ktor o Spring Boot.
- **Base de Datos**: SQLite (en desarrollo) o cualquier otra solución embebida.
- **Herramientas de Construcción**: Gradle

## Instalación

### Requisitos previos

1. **JDK 11 o superior**: Asegúrate de tener instalado Java Development Kit (JDK).
2. **Gradle**: Para gestionar dependencias.

### Pasos de Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/mier48/cv-management.git
   cd cv-management
   ```

2. Construye el proyecto usando Gradle:
   ```bash
   ./gradlew build
   ```

3. Ejecuta la aplicación:
   ```bash
   ./gradlew run
   ```

## Uso

### Funcionalidades Actuales

1. **Gestión de currículums**:
   - Crea un nuevo currículum.
   - Edita currículums existentes.
   - Exporta en formatos comunes como PDF.

2. **Seguimiento de entrevistas**:
   - Agrega reuniones con información clave.
   - Consulta el historial de reuniones pasadas.

### Ejemplo de Ejecución

Al ejecutar la aplicación, verás un menú interactivo que te permitirá navegar entre las funcionalidades disponibles.

```kotlin
fun main() {
    println("Bienvenido a CV Management")
    println("Seleccione una opción:")
    println("1. Gestionar currículums")
    println("2. Seguimiento de entrevistas")
    // Más opciones próximamente...
}
```

## Contribuciones

¡Contribuciones, reportes de errores y sugerencias son bienvenidos! Siéntete libre de abrir un _issue_ o enviar un _pull request_.

### Planes Futuros

- **Integración con APIs externas**: LinkedIn, Glassdoor, etc.
- **Soporte para múltiples idiomas**.
- **Aplicación de escritorio multiplataforma** con herramientas como Compose Desktop.

## Licencia

Este proyecto está licenciado bajo la [Licencia MIT](./LICENSE).

---
