<h1 align="center">
  <a href=""><img src="https://i.postimg.cc/pT46yp2P/logo.webp?ex=67c27283&is=67c12103&hm=8d94b05ff81a4aabf60829e23a63c69182f587c14ce548d175333fd53ea58090&=&format=webp&quality=lossless&width=1889&height=465" width="400" alt="Logo"></a>
</h1>

<h3 align="center">📌 Gestión de Equipos en Servicio Técnico</h3>
<p align="center">
  Una aplicación web que permite a los clientes realizar un seguimiento de sus equipos en el servicio técnico. <br>
  Los técnicos pueden registrar órdenes de trabajo y los administradores pueden generar informes y gestionar usuarios.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/spring%20boot-2.5.3-brightgreen">
  <img src="https://img.shields.io/badge/react-18.2.0-blue">
  <img src="https://img.shields.io/badge/tailwind%20css-latest-purple">
  <img src="https://img.shields.io/badge/figma-latest-orange">
  <img src="https://img.shields.io/badge/jasmine-3.8.0-yellow">
  <img src="https://img.shields.io/badge/git-latest-lightgrey">
  <img src="https://img.shields.io/badge/github-latest-darkblue">
  <img src="https://img.shields.io/badge/docker-20.10.12-blue">
</p>

<p align="center">
  <img src="https://i.postimg.cc/8kfY9BHc/main.webp?ex=67c2662c&is=67c114ac&hm=1d92b4f51b8cb314c0af50888843dc480c8c524a87918032b7d0395d2002e86f&=&format=webp&quality=lossless&width=1151&height=618" alt="Vista de la aplicación" width="800">
</p>

## 📋 **Índice**
1. [🚀 Tecnologías Utilizadas](#-tecnologías-utilizadas)  
2. [🛠️ Instalación y Configuración](#️-instalación-y-configuración)  
3. [🗄️ Modelo de Datos](#️-modelo-de-datos)  
4. [📡 Documentación de la API](#-documentación-de-la-api)  
5. [👥 Contribuidores](#-contribuidores)  

---  
## 🚀 **Tecnologías Utilizadas**  

- **Backend:** ☕ Java 17, Spring Boot, Spring Security, JWT  
- **Frontend:** 💻 React.js, Tailwind CSS, Figma  
- **Base de Datos:** 🗄️ MySQL, Hibernate, JPA  
- **QA:** 🔍 Jasmine  
- **Colaboración:** 🌐 GitHub  
- **DevOps:** 🐳 Docker  

## 🛠️ **Instalación y Configuración**  

### 🔧 **Requisitos Previos**
- Java 17+
- Maven
- MySQL 
- Node.js 
- Docker  

---

## ⚙️ **Configuración del Proyecto Backend**  

### **1️⃣ Clonar el Repositorio**
```bash
git clone https://github.com/No-Country-simulation/c23-78-webapp.git
cd c23-78-webapp/backend
```

### **2️⃣ Configurar la Base de Datos**
Edita `application.properties` con tus credenciales:

```properties
spring.application.name=trackmyfix
spring.datasource.url=${DB_HOST}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.defer-datasource-initialization=false
spring.sql.init.mode=never
spring.output.ansi.enabled=ALWAYS
logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss}){magenta} %clr(%-5p) %clr(%c{1}){blue}:%clr(%L){cyan} - %m%n
server.port=9091
server.error.whitelabel.enabled=false
```

### **3️⃣ Configurar las Variables de Entorno**
Crea un archivo `.env` con las siguientes variables:

```env
DB_HOST=jdbc:mysql://localhost:3306/track_my_fix_db?createDatabaseIfNotExist=true&serverTimezone=UTC
DB_USER=root
DB_PASSWORD=admin
MYSQL_ROOT_PASSWORD=admin
MYSQL_PASSWORD=admin
MYSQL_DATABASE=track_my_fix_db
ACCESS_SECRET="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"
REFRESH_SECRET="3vk+1tEK9e7FRXVLiKZmlfpCUqML7hY4ppptgminQmk="
REFRESH_EXPIRATION=900000
ACCESS_EXPIRATION=600000
```

### **4️⃣ Configurar el IDE para Utilizar el Archivo .env**
Para que tu IDE (por ejemplo, IntelliJ IDEA) reconozca las variables de entorno del archivo `.env`, sigue estos pasos:

1. Instala el plugin **"EnvFile"** en IntelliJ IDEA.
2. Ve a **Run/Debug Configurations**.
3. Selecciona tu configuración de ejecución.
4. Marca la opción **"Enable EnvFile"**.
5. Añade el archivo `.env` a la lista de archivos de entorno.

### **5️⃣ Ejecutar el Backend desde IntelliJ IDEA**
- **Usando el Atajo de Teclado**:  
  Presiona `Shift + F10` para ejecutar el proyecto desde IntelliJ IDEA.

- **Usando el Botón de Ejecución**:  
  Haz clic en el ícono de **play verde** en la esquina superior derecha de IntelliJ IDEA para ejecutar el backend.

IntelliJ IDEA detectará automáticamente el comando de Maven y lo ejecutará, sin necesidad de usar la terminal directamente.

### **6️⃣ Probar la API desde Postman**
1. **Acceder a la documentación de la API**:  
   Para comenzar, abre la documentación de la API en Postman desde el siguiente enlace:  
   [Documentación API - Postman](https://documenter.getpostman.com/view/27409208/2sAYX2LiFz).  
   En la documentación, encontrarás todos los detalles sobre los endpoints disponibles y cómo interactuar con ellos.

2. **Iniciar sesión**:  
   Antes de poder acceder a las APIs, necesitas autenticarte con uno de los dos roles disponibles: **Technician** o **Admin**. Según el rol con el que te loguees, tendrás acceso a diferentes permisos para interactuar con las APIs.

   - **Login como Technician**:  
     Realiza una solicitud `POST` a la URL indicada en la documentación para obtener un **token de acceso** con los permisos de Technician.

   - **Login como Admin**:  
     De manera similar, realiza una solicitud `POST` para obtener un **token de acceso** con los permisos de Admin.  
     El proceso de autenticación para ambos roles está detallado en la documentación, incluyendo los parámetros necesarios.

3. **Acceder a las APIs protegidas**:  
   Una vez que hayas obtenido el token correspondiente, puedes incluirlo en las cabeceras de tus solicitudes para acceder a las APIs protegidas. Por ejemplo, para probar el endpoint de equipos, realiza una solicitud `GET` a la siguiente URL:
   ```bash
   GET http://localhost:9091/work-order
   ```
---
## ⚙️ **Configuración del Proyecto Frontend**
### **1️⃣ Clonar el Repositorio**
```bash
git clone https://github.com/No-Country-simulation/c23-78-webapp.git
cd c23-78-webapp/frontend
```
### **2️⃣ Instalar Dependencias**
Una vez dentro del directorio `frontend`, instala las dependencias necesarias para ejecutar el proyecto:
```bash
npm install
```
### **3️⃣ Ejecutar el Proyecto**
Luego de instalar las dependencias, puedes ejecutar el proyecto en modo desarrollo con el siguiente comando:
```bash
npm run dev
```
### **4️⃣ Abrir la Web**
Después de ejecutar el proyecto, verás en la terminal un mensaje similar al siguiente:
```bash
VITE v6.1.0  ready in 1337 ms
  ➜  Local:   http://localhost:5173/
  ➜  Network: use --host to expose
  ➜  press h + enter to show help
 ```
Haz clic en el enlace Local: http://localhost:5173/ para abrir la aplicación en tu navegador.

---
## 🗄️ **Modelo de Datos**  

### 📌 **Diagrama Entidad-Relación (ER)**
<p align="center">
  <img src="https://i.postimg.cc/dtMfYkHB/der.webp?ex=67a29ebf&is=67a14d3f&hm=37bda4cab5e6717da3879bf1b52275cf86e842f059133a355bed25e73a20cf92&=&format=webp&quality=lossless&width=675&height=623" alt="Modelo de Datos" width="800">
</p>

### 📄 **Explicación de las Entidades**
- **User:** Representa a los clientes y técnicos.
- **Order:** Orden de servicio generada para cada equipo.
- **Device:** Equipos registrados en servicio técnico.
- **Movement:** Historial de movimientos del equipo.
- **UserChange:** Registro de cambios realizados sobre los clientes.

### 🏷️ **Enums Importantes**
- **Action:** Acciones realizadas en órdenes de trabajo.
- **ActionUser:** Acciones relacionadas con clientes.
- **Role:** Roles disponibles (Admin, Technician, Client).
- **State:** Estados de un dispositivo en reparación.
- **Type:** Tipos de dispositivos registrados.

---

## 📖 **Documentación de la API**  
La API está documentada con **Swagger**. Accede en:  
🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  

---

## 👥 **Contribuidores**  

| **Rol**   | **Nombre** | **Correo** |
|-----------|--------------------------------|-------------------------------|
| Frontend  | Daniel Perez  | danny2003renato@gmail.com  |
| Frontend  | Lorenzo Segada López  | lorenzosegada@gmail.com  |
| Frontend  | Dennis Benavides Ponce  | denizponce16@gmail.com  |
| Backend   | Delmer Rodríguez  | jindrg@gmail.com  |
| Backend   | Esteban Christian Durante  | estebandurante194@gmail.com  |
| Backend   | Gustavo Rodolfo Paz  | gusti.paz@gmail.com  |
| QA        | Gregori Rafael Urbaneja Morales  | gregorirafaelurbanejamorales@gmail.com  |

---
