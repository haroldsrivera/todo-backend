# Todo-backend
Backend en Spring Boot para un gestor de tareas

## Instalación del proyecto

1. Clonar el repositorio
```bash
https://github.com/haroldsrivera/todo-backend.git
```

## Dependencias necesarias

Java 21

Maven o Gradle

Un IDE compatible (IntelliJ, Eclipse u otro)

## Ejecución del proyecto

Ejecución del proyecto:
```bash
./gradlew bootRun
```

Una vez ejecutado el proyecto, realizar peticiones a la API REST a través de la siguiente URL:
```bash
http://localhost:8080/
```

### Login 
Iniciar sesión (POST)
```bash
http://localhost:8080/auth/login
```
Body en Json
```json
{
  "username": "string",
  "password": "string"
}
```
### Tareas
```bash
http://localhost:8080/tasks
```

Crear tarea (POST)
```bash
```json
{
  "title": "Tarea de prueba",
  "description": "Esta es una tarea de prueba",
  "date": "2025-04-11"
}
```

Eliminar tarea (DELETE)
```bash
http://localhost:8080/tasks/{id}
```

Traer todas las tareas (GET)
```bash
http://localhost:8080/tasks
```

## Acceder a la base de datos en memoria

Configuración

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
```

Para acceder a la interfaz (debe estar el servidor corriendo):
```bash
http://localhost:8080/h2-console
```

- La base de datos se reinicia cada vez que se para el servidor.

- Se cargan usuarios de prueba desde main/resource/data.sql.