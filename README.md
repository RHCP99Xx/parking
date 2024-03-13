# Proyecto de Estacionamiento

Este es un proyecto de ejemplo para un estacionamiento que utiliza Spring Boot y una base de datos PostgreSQL. Antes de ejecutar el proyecto, asegúrate de configurar correctamente las credenciales de la base de datos.

## Configuración de la Base de Datos

El proyecto utiliza una base de datos PostgreSQL. Para ejecutar el proyecto, necesitas configurar las credenciales de la base de datos en el archivo `application.properties`.

Abre el archivo `src/main/resources/application.properties` y busca las siguientes líneas:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/parking
spring.datasource.username=postgres
spring.datasource.password=password
