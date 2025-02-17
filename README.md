## Api Gestión de Tareas
***

# 🚀 Descripción 
***
La API Gestión de Tareas proporciona funcionalidades para gestionar información de los usuarios y sus tareas. Permite realizar operaciones de CRUD (crear, leer, actualizar y eliminar) sobre los registros de las tareas y usuarios almacenados en una base de datos.

# 🛅 Tecnologías
***

- **Java 17** (o superior) como lenguaje de programación.
- **Spring Boot** Versión 3.1.0 para la creación de la API RESTful.
- **JPA (Java Persistence API)** y **Hibernate** para la gestión de la base de datos.
- **Swagger** Para documentación de API
- **MySQL** Version 8.0.40 como base de datos.

# 🔧 Instalación 
***

1. Crear un carpeta local donde será el área de trabajo del proyecto.

2. Ubicarse en la carpeta local

3. Clonar el repositorio en rama main del proyecto

git clone --branch main https://github.com/magnitrom/api-admin-tareas

4. Importar carpeta del proyecto en Spring Tool Suite

5. Configuración de las Siguientes Variables de Entorno

| **Nombre**    | **valor** | **Descripción** |
| -------- | ------- | ------- |
| **DATASOURCE_URL**    | `jdbc:mysql://localhost:3306/reto`    | Cadena de Conexión a la Base de Datos    |
| **DATASOURCE_USER**    | `root`    |Usuario de acceso a la Base de Datos|
| **DATASOURCE_PASS**    | `xxxx`    |Contraseña de acceso a la Base de Datos|

6. Seleccionar el aplicativo y dar click derecho y ejecutar el aplicativo como Spring Boot App

7. Ingresar a la siguiente url: http://{server_ip:port}

# 📖 Documentación 
***

**Documentación de API:** https://studio-ws.apicur.io/sharing/e5645e44-eb65-43a3-8a2f-ea498c5a155f
**Documentación de Swagger:** http://{server_ip:port}/swagger-ui/index.html

# 📖 Autor 
***

**Bryan Francisco Núñez**  
**bryan_nupi@hotmail.com**