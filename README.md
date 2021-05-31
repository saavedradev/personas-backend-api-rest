# personas-backend-api-rest

Este repositorio está destinado para los servicios Backend del proyecto de adoptar personas

## Instalación en ambiente local

Prerrequisitos

- Versión java 11.0.10 
- Se recomienda usar el IDE spring Tool Suite 4
- Base de datos MySql

1. Crear la base de datos db_personas_backend en la base de datos MySQL con el comando CREATE DATABASE db_personas_backend;

2. Ir al archivo de las propiedades de la aplicación que se encuentra en src/main/resources/application.properties

3. Configurar el spring.datasource.url del archivo application.properties. Ejemplo: spring.datasource.url=jdbc:mysql://localhost/db_personas_backend 

4. Configurar el username de la base de datos MySql del archivo application.properties. Ejemplo: spring.datasource.username=root

5. Configurar el password de la base de datos MySql del archivo application.properties. Ejemplo: spring.datasource.password=123

6. Para ejecutar el 