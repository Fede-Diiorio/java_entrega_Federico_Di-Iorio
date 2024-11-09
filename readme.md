# Proyecto Java con Spring Boot

Este es un proyecto simple desarrollado en Java con Spring Boot como parte del curso de Programación con Java de la platataforma Coderhouse. La aplicación está configurada para generar automáticamente tablas en una base de datos MySQL al ejecutarse.

## Tecnologías utilizadas
- **Java** - [JavaSE-21]
- **Spring Boot** - [versión 3.3.4]
- **MySQL** - para la gestión de la base de datos.
- **Eclipse** - IDE usado para el desarrollo [varsión 2024-09].
- **MySQL Workbench** - herramienta para visualizar y gestionar la base de datos [versión 8.0.40].

## Configuración de la base de datos
La aplicación creará automáticamente tablas en una base de datos llamada **coderhouse**. Asegúrate de que tu servidor MySQL esté corriendo y configurado para aceptar conexiones.

### 1. Crear la base de datos
1. Abre **MySQL Workbench** y conéctate a tu servidor.
2. Ejecuta el siguiente comando para crear la base de datos:
   ```sql
   CREATE DATABASE IF NOT EXISTS coderhouse;
   ```
3. Asegúrate de que tu usuario de MySQL tenga permisos para acceder y modificar esta base de datos.
4. Para más información puedes guiarte con el archivo [CoderScript](https://github.com/Fede-Diiorio/java_entrega_Federico_Di-Iorio/blob/main/coderScript.sql) adjunto en este mismo repositorio.

### 2. Configurar el archivo `application.properties`
En el archivo `application.properties` (ubicado en `src/main/resources/`), verifica que la configuración de conexión a la base de datos coincida con tus credenciales y detalles de conexión. Aquí tienes un ejemplo básico de cómo debería verse:

```properties
# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/coderhouse
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA

# Configuración adicional
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

### 3. Ejecutar la aplicación
Para ejecutar el proyecto y generar automáticamente las tablas:
1. Abre el proyecto en **Eclipse**.
2. Dirígete al archivo principal de la aplicación (con la anotación `@EntregaCoderApplication`) y ejecútalo.
3. Al iniciarse, Spring Boot se conectará a la base de datos y generará las tablas configuradas en tus entidades.
4. Ten en cuenta que puedes ejecutar el código SQL del archivo [CoderScript](https://github.com/Fede-Diiorio/java_entrega_Federico_Di-Iorio/blob/main/coderScript.sql) tanto como para crear la base de datos y, además, insertar algunos registros para poder trabajar con los mismos.
5. Hecho todo esto y con la aplicación corriendo podrás ejecutar todos los endpoints, adjuntos en este repositorio, que encontraras en el archivo [JavaAPI](https://github.com/Fede-Diiorio/java_entrega_Federico_Di-Iorio/blob/main/JavaAPI.postman_collection.json).

### Notas adicionales
- Asegúrate de que el puerto y host en `spring.datasource.url` coincidan con tu configuración de MySQL.
- **Requisitos**: Java, MySQL y Eclipse instalados y configurados.

### Configuración del archivo `application.properties`

El archivo `application.properties` incluye la configuración de conexión a la base de datos MySQL, además de algunas propiedades para la gestión de Hibernate. Aquí tienes una explicación de cada una de ellas:

```properties
spring.application.name=EntregaCoder
```
- **`spring.application.name`**: Nombre de la aplicación (opcional).

```properties
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```
- **`spring.jpa.properties.hibernate.format_sql`**: Formatea las consultas SQL para que sean más legibles en los logs.
- **`spring.jpa.show-sql`**: Muestra las consultas SQL generadas por Hibernate en la consola.
- **`spring.jpa.hibernate.ddl-auto=update`**: Indica a Hibernate que actualice automáticamente las tablas en función de los cambios en las entidades (útil para desarrollo).

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/coderhouse
spring.datasource.username=root
spring.datasource.password=federico
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.hikari.maximum-pool-size=10
```
- **`spring.datasource.url`**: URL de conexión a la base de datos MySQL.
- **`spring.datasource.username`** y **`spring.datasource.password`**: Credenciales para acceder a la base de datos.
- **`spring.datasource.driver-class-name`**: Clase del controlador de MySQL.
- **`spring.datasource.hikari.maximum-pool-size`**: Tamaño máximo del pool de conexiones HikariCP (útil para gestionar múltiples conexiones simultáneas).

> **Nota**: Asegúrate de reemplazar el nombre de usuario y la contraseña (`spring.datasource.username` y `spring.datasource.password`) si el proyecto se comparte, para proteger las credenciales de la base de datos.
