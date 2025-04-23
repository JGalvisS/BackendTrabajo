# BackendTrabajo

> Proyecto académico colaborativo desarrollado como parte del programa de formación de **Digital House**.

## 📚 Descripción

**BackendTrabajo** es una aplicación backend desarrollada con **Spring Boot**, diseñada para gestionar [descripción breve de la funcionalidad principal, por ejemplo: "la administración de usuarios y roles en una plataforma educativa"]. Este proyecto fue realizado de manera colaborativa por estudiantes como parte de un ejercicio académico, con el objetivo de aplicar conceptos de desarrollo de software y trabajo en equipo.

## 👥 Colaboradores

- [JGalvisS](https://github.com/JGalvisS)
- [AnaCamargo2023](https://github.com/AnaCamargo2023)

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3.x
- Maven
- H2 Database (base de datos en memoria)

## ⚙️ Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- Java 17 o superior
- Maven 3.8 o superior

## 🛠️ Configuración del proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/JGalvisS/BackendTrabajo.git
   cd BackendTrabajo
   ```

2. Configura las variables de entorno o el archivo `application.properties` con los parámetros adecuados para tu entorno. Por ejemplo, para utilizar la base de datos H2 en memoria, puedes incluir las siguientes configuraciones:

   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Compila y ejecuta la aplicación:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   La aplicación estará disponible en `http://localhost:8080`.

## 🧪 Pruebas

El proyecto incluye pruebas unitarias para verificar el correcto funcionamiento de los componentes principales. Para ejecutar todas las pruebas, utiliza el siguiente comando:

```bash
mvn test
```

Si deseas ejecutar una prueba específica, puedes usar:

```bash
mvn -Dtest=NombreDeLaClaseDePrueba test
```

Reemplaza `NombreDeLaClaseDePrueba` con el nombre de la clase de prueba que deseas ejecutar.

## 📌 Notas adicionales

- Este proyecto fue desarrollado con fines educativos como parte del programa de formación de **Digital House** y puede no estar optimizado para entornos de producción.
- Se agradecen las contribuciones y sugerencias para mejorar el proyecto.

## 📜 Licencia

Este proyecto no cuenta con una licencia específica. Si deseas utilizar o modificar el código, por favor contacta a los autores para obtener más información.
