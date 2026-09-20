# Ejercicio 21 — Aplicacion (Gestión de Usuarios y Aplicaciones)

Aplicación web desarrollada con **Java Servlet/JSP + JDBC** (sin frameworks), como parte de la actividad de Unidad 1 de Desarrollo Web. Implementa CRUD completo de dos entidades (`Usuario` y `Aplicacion`), autenticación con control de acceso, reportes parametrizados, y recuperación de clave por correo electrónico.

**Aplicación desplegada:** https://ejercicio21.onrender.com
**Repositorio:** https://github.com/LetMeSoloHerZ/ejercicio21

> ⚠️ El plan gratuito de Render "duerme" el servicio tras 15 minutos sin tráfico. La primera petición después de eso puede tardar 30-60 segundos en responder mientras el servicio despierta.

---

## Stack y arquitectura

- **Java 11** (Eclipse Adoptium / Temurin)
- **Apache Tomcat 9** con especificación `javax.servlet` (no `jakarta`)
- **Maven** como gestor de dependencias y empaquetado (`war`)
- **MySQL 8**, accedido por JDBC puro (sin ORM)
- **JSTL** para las vistas JSP
- **Arquitectura en capas:** `modelo` (POJOs) → `dao` (acceso a datos con JDBC) → `controlador` (servlets) → JSP (vistas)
- Patrón **modelo–DAO–servlet–JSP**, sin frameworks tipo Spring, tal como exige la guía de la actividad.

## Estructura del proyecto

```
ejercicio21/
├── src/main/
│   ├── java/co/unicartagena/aplicacion/
│   │   ├── controlador/   → Servlets (Usuario, Aplicacion, Login, Logout, Reportes, RecuperarClave)
│   │   ├── dao/            → Acceso a datos (UsuarioDAO, AplicacionDAO, ConexionBD)
│   │   ├── filtro/         → SesionFiltro (control de acceso)
│   │   ├── modelo/         → Entidades (Usuario, Aplicacion)
│   │   └── util/           → PasswordUtil (hasheo SHA-256), CorreoUtil (envío de correo vía Resend)
│   └── webapp/
│       ├── usuario/        → JSPs de Usuario (listar, formulario, reporte)
│       ├── aplicacion/     → JSPs de Aplicacion (listar, formulario, reporte)
│       ├── login.jsp, recuperar.jsp, index.jsp
│       └── WEB-INF/
├── db/
│   └── script_creacion_bd.sql   → Script de creación de tablas + datos iniciales
├── Dockerfile
├── pom.xml
└── README.md
```

## Modelo de datos

**Usuario:** `id`, `clave` (hash SHA-256), `nombre`, `rol`, `correo` (agregado sobre el mínimo exigido, necesario para la recuperación de clave).

**Aplicacion:** `id`, `nombre`, `proveedor`, `categoria`, `lenguajePrincipal`, `lenguajeSecundario`, `usaBd`, `requiereConexionRed`, `numBits`, `sistemaOperativo`, `requisitosHardware`, `licencia`, `precio`, `descripcion`, `web`, `correo`, `tamanoInstalador`.

El script `db/script_creacion_bd.sql` crea ambas tablas y las puebla con datos de ejemplo (contraseñas ya hasheadas con SHA-256).

## Funcionalidades implementadas

- CRUD completo de ambas entidades (crear, listar, editar, eliminar).
- Autenticación (login/logout) con hasheo de contraseñas (SHA-256) y control de sesión mediante `SesionFiltro`.
- Reportes parametrizados (2 por entidad):
  - Usuario: filtro por rol, búsqueda por nombre parcial.
  - Aplicacion: filtro por categoría + rango de precio, filtro por sistema operativo + requiere conexión de red.
- Recuperación de clave por correo: genera una clave temporal aleatoria, la guarda hasheada, y la envía por correo electrónico usando la API de **Resend**.
- Despliegue funcional en Internet (Render + base de datos MySQL en Clever Cloud).

## Variables de entorno

La aplicación lee la configuración desde variables de entorno, con valores por defecto orientados a desarrollo local (`ConexionBD.java`).

| Variable | Descripción | Valor por defecto (local) |
|---|---|---|
| `DB_HOST` | Host de la base de datos MySQL | `localhost` |
| `DB_PORT` | Puerto de MySQL | `3306` |
| `DB_NAME` | Nombre de la base de datos | `ejercicio21` |
| `DB_USER` | Usuario de MySQL | `root` |
| `DB_PASSWORD` | Contraseña de MySQL | *(definir en tu entorno)* |
| `RESEND_API_KEY` | API Key de [Resend](https://resend.com) para el envío de correos de recuperación de clave | *(sin valor por defecto — requerida para que funcione la recuperación de clave)* |

> **Nota sobre el envío de correo:** se usa la API HTTPS de Resend en lugar de SMTP tradicional, porque muchos proveedores de hosting gratuito (incluido Render) bloquean el tráfico saliente por los puertos SMTP estándar (587/465/25) para prevenir spam. En el plan gratuito de Resend, sin verificar un dominio propio, solo se pueden enviar correos a la dirección con la que se registró la cuenta de Resend.

## Requisitos previos

- JDK 11
- Maven 3.9+
- MySQL 8 (local) o acceso a un MySQL en la nube
- (Opcional) Docker Desktop, si prefieres correr el proyecto en contenedor

## Cómo ejecutar el proyecto

### Opción A — Localmente con Maven + Tomcat

1. Crea la base de datos y las tablas ejecutando `db/script_creacion_bd.sql` en tu instancia de MySQL local (por ejemplo desde MySQL Workbench).
2. Configura las variables de entorno necesarias en tu sistema o en la sesión de terminal antes de arrancar Tomcat (como mínimo `DB_PASSWORD` si no usas la contraseña por defecto, y `RESEND_API_KEY` para que funcione la recuperación de clave):
   ```powershell
   $env:DB_PASSWORD = "tu_contraseña_mysql"
   $env:RESEND_API_KEY = "tu_api_key_de_resend"
   ```
3. Compila el proyecto:
   ```powershell
   mvn clean package
   ```
4. Copia el archivo `target/ejercicio21.war` a la carpeta `webapps/` de tu instalación de Tomcat 9.
5. Inicia Tomcat:
   ```powershell
   & "$env:CATALINA_HOME\bin\startup.bat"
   ```
6. Accede a la aplicación en `http://localhost:8080/ejercicio21/login`.

### Opción B — Con Docker

El `Dockerfile` incluido construye la aplicación con Maven y la empaqueta en una imagen de Tomcat 9, sirviéndola en la raíz del dominio (sin el prefijo `/ejercicio21/`).

1. Construye la imagen:
   ```powershell
   docker build -t ejercicio21-app .
   ```
2. Ejecuta el contenedor, pasando las variables de entorno necesarias:
   ```powershell
   docker run -p 8080:8080 `
     -e DB_HOST="tu_host_mysql" `
     -e DB_PORT="3306" `
     -e DB_NAME="ejercicio21" `
     -e DB_USER="tu_usuario" `
     -e DB_PASSWORD="tu_contraseña" `
     -e RESEND_API_KEY="tu_api_key_de_resend" `
     ejercicio21-app
   ```
3. Accede a la aplicación en `http://localhost:8080/login` (nota: sin `/ejercicio21/`, ya que la imagen despliega el `.war` como `ROOT.war`).

## Despliegue en producción

- **Base de datos:** MySQL gestionado en [Clever Cloud](https://www.clever-cloud.com/) (plan DEV, gratuito).
- **Aplicación:** desplegada como servicio web Docker en [Render](https://render.com/), construida directamente desde este repositorio (`Dockerfile` en la raíz).
- **Correo transaccional:** [Resend](https://resend.com/), vía API HTTPS.

Cada vez que se hace `git push` a la rama `main`, Render reconstruye y redespliega automáticamente la aplicación.

## Usuarios de prueba

| Correo | Rol |
|---|---|
| `admin@correo.com` | administrador |
| `juan@correo.com` | usuario |
| `maria@correo.com` | usuario |

(Las contraseñas de prueba fueron migradas a hash SHA-256 en la base de datos; consultar con el autor del proyecto si se necesitan para evaluación.)

## Autor

Proyecto desarrollado para la asignatura Desarrollo Web — Unidad 1, Universidad de Cartagena.