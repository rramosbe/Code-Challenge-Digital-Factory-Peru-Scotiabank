# Code Challenge – Digital Factory Perú – Scotiabank

## Objetivo

El reto tiene como fin poder conocer la experiencia previa del candidato en cuanto a desarrollo de microservicios con Spring Boot y Java.

## Descripción del Reto

Se requiere la creación de un microservicio el cual exponga 2 servicios reactivos REST bajo la siguiente descripción:

### Endpoint para grabar un alumno:

- **Descripción:**
  - Dada una entidad “alumno” con atributos `id`, `nombre`, `apellido`, `estado` (activo, inactivo) y `edad`, se requiere validar la consistencia de cada campo y posteriormente hacer la grabación del alumno.
  - En caso el `id` sea repetido, debe enviarse un mensaje de error indicando que no se pudo hacer la grabación.
  - En caso de realizarse correctamente la grabación, puede devolver una respuesta vacía.

### Endpoint para obtener todos los alumnos con estado activo:

- **Descripción:**
  - Debe retornar una lista de todos los alumnos cuyo `estado` sea "activo".

## Requisitos del Proyecto

- El proyecto deberá realizarse bajo un patrón de arquitectura a elección. Ejemplo: Arquitectura por capas (controller, service, repository), arquitectura hexagonal o clean architecture.
- Los datos deben ser persistentes en memoria, para ello se sugiere que desde el paquete repository use una colección de Java para el almacenamiento. Es permitido también usar una base de datos en memoria.
- Se requiere agregar unos tests para el código realizado.

## Indicaciones

- Puede hacer uso de un proyecto ya existente o iniciar uno desde [Spring Initializr](https://start.spring.io/)
  - Usar la versión de Spring Boot `2.7.7`, JDK `11`.
  - Puede usarse Gradle o Maven. Se prefiere Gradle - Groovy.
- Para construir el servicio reactivo debe usarse el módulo `Spring WebFlux`.
- La entidad “alumno” tendrá los siguientes atributos: `id`, `nombre`, `apellido`, `estado` (activo o inactivo) y `edad`.
  - El tipo de dato y las validaciones de cada uno es a elección del candidato.

## Ejemplo de Uso

### Grabar un alumno

- **Request:**
  ```bash
  POST /students
  {
    "id": 4,
    "firstName": "Richard",
    "lastName": "Doe",
    "status": "ACTIVE",
    "age": 20
  }

### Consultar alumnos activos

  ```bash
  POST /students/active
