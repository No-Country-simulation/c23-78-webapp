<h1 align="center">
  <br>
  <a href=""><img src="https://media.discordapp.net/attachments/1326628838939295815/1336097155458400300/image.png?ex=67a290f1&is=67a13f71&hm=82ca390cd723719005162c6a49c6e1b4515f09e2a8d16bb8fdbe8cb9a7f5ccea&=&format=webp&quality=lossless&width=665&height=148" width="400"></a>
</h1>

<h4 align="center">Esta aplicación web permite a los clientes realizar un seguimiento de sus equipos en el servicio técnico. Los usuarios con rol de Técnico pueden registrar las órdenes de trabajo asociadas a los productos. Los usuarios con rol de Administrador pueden ver y generar informes de las acciones y movimientos realizados por los técnicos. Además, la aplicación permite gestionar a los empleados y trabajar con seguridad, limitando los accesos según el tipo de rol asignado.</h4>

<p align="center">
  <a href="">
    <img src="https://img.shields.io/badge/spring%20boot-2.5.3-brightgreen">
  </a>
  <a href="">
    <img src="https://img.shields.io/badge/react-18.2.0-blue">
  </a>
  <a href="">
    <img src="https://img.shields.io/badge/tailwind%20css-latest-purple">
  </a>
  <a href="">
    <img src="https://img.shields.io/badge/figma-latest-orange">
  </a>
  <a href="">
    <img src="https://img.shields.io/badge/jasmine-3.8.0-yellow">
  </a>
  <a href="">
    <img src="https://img.shields.io/badge/git-latest-lightgrey">
  </a>
  <a href="">
    <img src="https://img.shields.io/badge/github-latest-darkblue">
  </a>
  <a href="">
    <img src="https://img.shields.io/badge/docker-20.10.12-blue">
  </a>
</p>



![screenshot](https://media.discordapp.net/attachments/1326630298812813352/1336102303110791199/image.png?ex=67a295bc&is=67a1443c&hm=afaaa01bd8e7f7cdb48c12464500f410773e2876336c2a81289565636e8f07e7&=&format=webp&quality=lossless&width=806&height=623)

## 📋 **Índice**
1. [🚀 Tecnologías Utilizadas](#-tecnologías-utilizadas)  
2. [🛠️ Instalación y Configuración](#️-instalación-y-configuración)  
3. [🗄️ Modelo de Datos](#️-modelo-de-datos)  
4. [📡 Endpoints de la API](#-endpoints-de-la-api)  
5. [👥 Contribuidores](#-contribuidores)  
6. [📄 Licencia](#-licencia)

---

## 🚀 **Tecnologías Utilizadas**  

- ☕ **Backend:** Java 17, Spring Boot, Spring Security, JWT
- 💻 **Frontend:** React.js, Tailwind CSS, Figma
- 🗄️ **Base de Datos:** MySQL, Hibernate, JPA  
- 🔍 **QA:** Jasmine
- 🌐 **Colaboración:** Git ![logo](https://img.shields.io/badge/GitHub-latest-darkblue)
- 🐳 **DevOps:** Docker

## 🛠️ **Instalación y Configuración**  

### 🔧 **Requisitos Previos**
- Java 17+
- Maven
- MySQL 
- Node.js 
- Docker  

### ⚙️ **Configuración del Proyecto**  

#### **1️⃣ Clonar el Repositorio**  
```bash
git clone https://github.com/No-Country-simulation/c23-78-webapp.git
cd c23-78-webapp
```
2️⃣ Configurar la Base de Datos
Modifica el archivo application.properties con tus credenciales:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/trackmyfix
spring.datasource.username=root
spring.datasource.password=tu_clave
spring.jpa.hibernate.ddl-auto=update
```
3️⃣ Configurar la Base de Datos
```bash
mvn spring-boot:run
```
4️⃣ Probar la API
```
curl -X GET http://localhost:8080/api/equipos
```

## 🗄️ **Modelo de Datos**
La aplicación utiliza una base de datos relacional con las siguientes entidades principales:

### **📌 Diagrama Entidad-Relación (ER)**
<p align="center">
  <img src="https://media.discordapp.net/attachments/1326630298812813352/1336111977042284604/image.png?ex=67a29ebf&is=67a14d3f&hm=37bda4cab5e6717da3879bf1b52275cf86e842f059133a355bed25e73a20cf92&=&format=webp&quality=lossless&width=675&height=623" alt="Modelo de Datos" width="800">
</p>

### **📄 Explicación de las Entidades**
- User (Usuarios): Representa a los clientes, técnicos y administradores.
- Device (Dispositivos): Dispositivos enviados al servicio técnico.
- State (Estado): lista de posibles estados.

### **🔍 Migraciones**
Si utilizas Flyway, ejecuta:
```bash
mvn flyway:migrate
```
---
