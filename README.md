# API RESTful de Alumnos

## About
Esta es la segunda actividad del módulo 10 "API RESTful con Spring Boot" del Diplomado "Desarrollo de Sistemas con Tecnología Java Emisión 15" impartido por el M. en C. Jesús Hernández Cabrera.

Esta API permite gestionar información de alumnos a través de operaciones CRUD. Está construida con Spring Boot y sigue el formato RESTful.

## Endpoints

### Obtener todos los alumnos

- **URL**: `/api/alumnos/`
- **Método**: `GET`
- **Descripción**: Obtiene una lista con todos los alumnos.
- **Respuesta**:
  - **200 OK**: Devuelve una lista de objetos `Alumno`.

### Obtener un alumno por ID

- **URL**: `/api/alumnos/{id}`
- **Método**: `GET`
- **Descripción**: Obtiene los datos de un alumno por su ID.
- **Parámetros**:
  - `id` (Path Variable): ID del alumno a consultar.
- **Respuesta**:
  - **200 OK**: Devuelve el objeto `Alumno` correspondiente.
  - **404 Not Found**: No se encontró el alumno con el ID especificado.

### Crear un nuevo alumno

- **URL**: `/api/alumnos/`
- **Método**: `POST`
- **Descripción**: Crea un nuevo registro de alumno.
- **Body**: JSON del objeto `Alumno` a crear.
- **Respuesta**:
  - **201 Created**: Devuelve el objeto `Alumno` creado y la ubicación del recurso.
