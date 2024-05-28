# _**Inventory Management System**_

## Descripción

Este proyecto es una aplicación de gestión de inventarios desarrollada en Java utilizando JavaFX para la interfaz de usuario y Hibernate para la persistencia de datos. La aplicación permite gestionar productos, empleados, facturas, proveedores, compras y ventas en un entorno de depósito. Está diseñada para ser utilizada en un entorno de trabajo, donde los empleados pueden realizar movimientos de stock y agregar nuevos productos, mientras que el administrador tiene privilegios avanzados para evitar la pérdida de información importante.

## Requisitos

- Java 8 o superior
- Maven 3.1 o superior
- MySQL 5.7
- Git

## Configuración

### 1. Clonar el Repositorio

Clona el repositorio en tu máquina local:

```
git clone https://github.com/tu-usuario/inventory-management.git
cd inventory-management
```
### 2. Configurar la Base de Datos
Crea una base de datos MySQL llamada inventory y ejecuta el script inventory.sql para crear las tablas necesarias:

```
mysql -u root -p < src/main/resources/inventory.sql
```

### 3. Configurar las Credenciales de la Base de Datos
Crea un archivo database.properties en el directorio src/main/resources/ con el siguiente contenido:

```properties
hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.connection.driver_class=com.mysql.jdbc.Driver
hibernate.connection.url=jdbc:mysql://localhost:3306/inventory?useSSL=false
hibernate.connection.username=tu-usuario
hibernate.connection.password=tu-contraseña
hibernate.hbm2ddl.auto=update
hibernate.show_sql=true
hibernate.format_sql=true
hibernate.current_session_context_class=thread
```
_Nota: Asegúrate de no subir este archivo a GitHub. Añade database.properties a tu .gitignore para evitar que se suba._


## 4. Construir el Proyecto
Usa Maven para construir el proyecto:
```
mvn clean install
```

## 5. Ejecutar la Aplicación
Una vez que el proyecto esté construido, puedes ejecutar la aplicación usando el siguiente comando:

```
mvn exec:java -Dexec.mainClass="com.taba.inventory.MainApp"
```

# Uso
## Pantalla de Inicio de Sesión
La aplicación comienza con una pantalla de inicio de sesión. Ingresa las credenciales del administrador o de un empleado para acceder a la aplicación.

### Funcionalidades Principales
* Gestión de Productos: Agregar, editar y eliminar productos en el inventario.
* Gestión de Empleados: Administrar la información de los empleados.
* Gestión de Facturas: Crear y gestionar facturas de compras y ventas.
* Gestión de Proveedores: Agregar y administrar proveedores.
* Gestión de Compras y Ventas: Registrar las compras y ventas de productos.

# Seguridad
Para proteger las credenciales de la base de datos y otra información sensible, asegúrate de no subir el archivo database.properties al repositorio. Usa variables de entorno en su lugar si es posible.

# Contribución
Si deseas contribuir a este proyecto, por favor realiza un fork del repositorio y envía un pull request con tus cambios.

**_Todas las contribuciones son bienvenidas!_**