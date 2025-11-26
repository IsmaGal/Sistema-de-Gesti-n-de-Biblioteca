# DOCUMENTACIÓN TÉCNICA DEL PROYECTO
 ## Sistema de Gestión de Biblioteca 
 Nombre del docente : Erick Pariona Yauricasa
---

## 👥 Autor

**Ismael** - Estudiante 5to Ciclo  
**Curso:** DSW (Desarrollo de Software Web)  
**Proyecto:** EVC3 - Sistema de Biblioteca

---

## 📋 1.1 PORTADA

| **Campo** | **Detalle** |
|-----------|-------------|
| **Nombre del Estudiante** | [Completar con nombre completo] |
| **Carrera** | [Completar carrera] |
| **Ciclo** | 4to Ciclo |
| **Curso** | Desarrollo de Software Web |
| **Sección** | [Completar sección] |
| **Docente** | [Completar nombre del docente] |
| **Proyecto** | Sistema de Gestión de Biblioteca - Autores y Libros |
| **Fecha de Entrega** | 26 de Noviembre de 2025 |
| **Versión** | 1.0.0 |

---

## 📖 1.2 INTRODUCCIÓN DEL PROYECTO

### Resumen Ejecutivo

El **Sistema de Gestión de Biblioteca** es una aplicación web desarrollada para digitalizar y optimizar el control del inventario bibliográfico de una biblioteca. Este sistema permite gestionar de manera eficiente la información de autores, libros, usuarios, préstamos, multas y ejemplares, proporcionando una interfaz intuitiva y funcional basada en tecnologías modernas de desarrollo web.

### Propósito del Sistema

Digitalizar y automatizar el control del inventario bibliográfico, permitiendo:
- Registro y gestión de autores y sus obras
- Control de libros organizados por categorías
- Gestión de ejemplares físicos de cada libro
- Administración de usuarios de la biblioteca
- Control de préstamos y devoluciones
- Gestión de multas por retrasos

### Alcance del Proyecto

El sistema implementa funcionalidades CRUD (Crear, Leer, Actualizar, Eliminar) completas para las siguientes entidades:

1. **Autores** - Gestión de escritores con soporte para nacionalidad y estado activo/inactivo
2. **Libros** - Registro de obras asociadas a autores y categorías
3. **Categorías** - Clasificación de libros por géneros literarios
4. **Ejemplares** - Control de copias físicas de cada libro
5. **Usuarios** - Administración de miembros de la biblioteca
6. **Préstamos** - Control de préstamos con fechas de devolución
7. **Multas** - Gestión de sanciones por retrasos

### Tecnologías Utilizadas

| **Categoría** | **Tecnología** | **Versión** | **Propósito** |
|---------------|----------------|-------------|---------------|
| **Backend Framework** | Spring Boot | 4.0.0 | Framework principal de desarrollo |
| **Lenguaje** | Java | 21 | Lenguaje de programación |
| **ORM** | Spring Data JPA | 4.0.0 | Persistencia de datos |
| **Mapeo de Objetos** | MapStruct | 1.5.5 | Conversión entre DTOs y Entidades |
| **Motor de Plantillas** | Thymeleaf | 3.1.3 | Renderizado de vistas HTML |
| **Base de Datos** | SQL Server | 2019+ | Sistema de gestión de base de datos |
| **Frontend** | Bootstrap | 5.3 | Framework CSS para diseño responsivo |
| **Gestión de Dependencias** | Maven | 3.9+ | Automatización de construcción |
| **Herramienta de Reducción de Código** | Lombok | Latest | Reducción de código boilerplate |

---

## 🎯 2. OBJETIVOS DEL PROYECTO

### 2.1 Objetivo General

Desarrollar un sistema web integral para la gestión eficiente de autores, libros y operaciones bibliotecarias, implementando una arquitectura robusta basada en Spring Boot que garantice escalabilidad, mantenibilidad y rendimiento óptimo.

### 2.2 Objetivos Específicos

1. **Implementación de CRUD Completo**
   - Desarrollar operaciones completas de Crear, Leer, Actualizar y Eliminar para todas las entidades del sistema
   - Implementar validaciones de datos en backend para garantizar integridad
   - Crear interfaces de usuario intuitivas para cada operación

2. **Establecer Relaciones Entre Entidades**
   - Relación 1:N entre Autor y Libro (un autor puede tener múltiples libros)
   - Relación N:1 entre Libro y Categoría (múltiples libros en una categoría)
   - Relación 1:N entre Libro y Ejemplar (un libro puede tener múltiples copias físicas)
   - Relación 1:N entre Usuario y Préstamo (un usuario puede tener múltiples préstamos)
   - Relación 1:1 entre Préstamo y Ejemplar (un ejemplar solo puede estar en un préstamo a la vez)
   - Relación 1:1 entre Préstamo y Multa (un préstamo puede generar una multa)

3. **Implementar Arquitectura por Capas**
   - Capa de Presentación (Controllers y Vistas Thymeleaf)
   - Capa de Lógica de Negocio (Services)
   - Capa de Acceso a Datos (Repositories)
   - Capa de Transferencia de Datos (DTOs y Mappers)
   - Capa de Persistencia (Entities)

4. **Garantizar Experiencia de Usuario Óptima**
   - Diseño responsivo compatible con dispositivos móviles y escritorio
   - Navegación intuitiva entre módulos
   - Mensajes de confirmación y validación claros
   - Tiempos de respuesta rápidos

5. **Implementar Funcionalidades Avanzadas**
   - Búsqueda de libros por título
   - Filtrado de libros por estado
   - Eliminación lógica de autores (soft delete)
   - Cálculo automático de multas por retrasos
   - Control de disponibilidad de ejemplares

---

## 🏗️ 3. ARQUITECTURA DEL SISTEMA

### 3.1 Arquitectura en Capas

El sistema sigue el patrón de arquitectura en capas (Layered Architecture) con las siguientes capas:

```
┌─────────────────────────────────────────────┐
│         CAPA DE PRESENTACIÓN                │
│    (Controllers + Vistas Thymeleaf)         │
│  - AutorController                          │
│  - LibroController                          │
│  - UsuarioController, etc.                  │
└─────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────┐
│       CAPA DE LÓGICA DE NEGOCIO             │
│            (Services)                       │
│  - AutorService                             │
│  - LibroService                             │
│  - PrestamoService, etc.                    │
└─────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────┐
│      CAPA DE TRANSFERENCIA DE DATOS         │
│           (DTOs + Mappers)                  │
│  - AutorDTO / AutorMapper                   │
│  - LibroDTO / LibroMapper                   │
└─────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────┐
│      CAPA DE ACCESO A DATOS                 │
│          (Repositories)                     │
│  - AutorRepository                          │
│  - LibroRepository                          │
└─────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────┐
│      CAPA DE PERSISTENCIA                   │
│           (Entities)                        │
│  - Autor, Libro, Usuario, etc.              │
└─────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────┐
│          BASE DE DATOS                      │
│          (SQL Server)                       │
└─────────────────────────────────────────────┘
```

### 3.2 Patrón de Diseño

**Patrón MVC (Model-View-Controller)**

- **Model (Modelo)**: Entidades JPA + DTOs
- **View (Vista)**: Plantillas Thymeleaf + CSS
- **Controller (Controlador)**: Controllers de Spring

**Patrón Repository**: Abstracción de acceso a datos mediante Spring Data JPA

**Patrón DTO (Data Transfer Object)**: Separación entre entidades de persistencia y objetos de transferencia

**Patrón Mapper**: MapStruct para conversión automática entre DTOs y Entidades

---

## 📊 4. MODELO DE DATOS

### 4.1 Diagrama Entidad-Relación

```
┌─────────────┐
│   AUTOR     │
├─────────────┤
│ autorId (PK)│───┐
│ nombre      │   │
│ apellidos   │   │ 1:N
│ nacionalidad│   │
│ activo      │   │
└─────────────┘   │
                  │
┌─────────────┐   │   ┌──────────────┐
│  CATEGORIA  │   │   │    LIBRO     │
├─────────────┤   │   ├──────────────┤
│categoriaId  │───┼──→│ libroId (PK) │
│  (PK)       │ 1:N   │ titulo       │
│ nombre      │       │ año          │
│ descripcion │       │ autorId (FK) │
│ activo      │       │categoriaId   │
└─────────────┘       │  (FK)        │
                      │ activo       │
                      └──────────────┘
                            │
                            │ 1:N
                            ↓
                      ┌──────────────┐
                      │  EJEMPLAR    │
                      ├──────────────┤
                      │ ejemplarId   │───┐
                      │   (PK)       │   │
                      │ libroId (FK) │   │ 1:1
                      │ estado       │   │
                      │ ubicacion    │   │
                      └──────────────┘   │
                                         │
┌─────────────┐                          │
│  USUARIO    │                          │
├─────────────┤                          │
│ usuarioId   │───┐                      │
│   (PK)      │   │                      │
│ nombre      │   │ 1:N                  │
│ apellido    │   │                      │
│ email       │   │                      │
│ direccion   │   │                      │
│fechaRegistro│   │                      │
│ activo      │   │                      │
└─────────────┘   │                      │
                  │                      │
                  ↓                      │
            ┌──────────────┐             │
            │   PRESTAMO   │←────────────┘
            ├──────────────┤
            │ prestamoId   │───┐
            │   (PK)       │   │
            │ ejemplarId   │   │ 1:1
            │   (FK)       │   │
            │ usuarioId    │   │
            │   (FK)       │   │
            │fechaPrestamo │   │
            │fechaDevEsp   │   │
            │fechaDevReal  │   │
            └──────────────┘   │
                               │
                               ↓
                         ┌──────────────┐
                         │    MULTA     │
                         ├──────────────┤
                         │ multaId (PK) │
                         │ prestamoId   │
                         │   (FK)       │
                         │ monto        │
                         │fechaGenerac  │
                         │ pagado       │
                         └──────────────┘
```

### 4.2 Descripción de Entidades

#### 4.2.1 Autor
Almacena información de los autores de libros.

| **Campo** | **Tipo** | **Descripción** | **Restricciones** |
|-----------|----------|-----------------|-------------------|
| autorId | Integer | Identificador único | PK, Auto-increment |
| nombre | String | Nombre del autor | NOT NULL, VARCHAR(100) |
| apellidos | String | Apellidos del autor | VARCHAR(100) |
| nacionalidad | String | País de origen | VARCHAR(50) |
| activo | Boolean | Estado del autor | NOT NULL, Default: true |

**Relaciones:**
- Tiene muchos Libros (1:N)

#### 4.2.2 Libro
Almacena información de los libros disponibles en la biblioteca.

| **Campo** | **Tipo** | **Descripción** | **Restricciones** |
|-----------|----------|-----------------|-------------------|
| libroId | Integer | Identificador único | PK, Auto-increment |
| titulo | String | Título del libro | NOT NULL, VARCHAR(200) |
| año | Integer | Año de publicación | |
| autorId | Integer | Referencia al autor | FK, NOT NULL |
| categoriaId | Integer | Referencia a categoría | FK |
| activo | Boolean | Estado del libro | NOT NULL, Default: true |

**Relaciones:**
- Pertenece a un Autor (N:1)
- Pertenece a una Categoría (N:1)
- Tiene muchos Ejemplares (1:N)

#### 4.2.3 Categoría
Clasifica los libros por género o temática.

| **Campo** | **Tipo** | **Descripción** | **Restricciones** |
|-----------|----------|-----------------|-------------------|
| categoriaId | Integer | Identificador único | PK, Auto-increment |
| nombre | String | Nombre de la categoría | NOT NULL, VARCHAR(100) |
| descripcion | String | Descripción detallada | VARCHAR(500) |
| activo | Boolean | Estado de la categoría | NOT NULL, Default: true |

**Relaciones:**
- Tiene muchos Libros (1:N)

#### 4.2.4 Ejemplar
Representa las copias físicas de cada libro.

| **Campo** | **Tipo** | **Descripción** | **Restricciones** |
|-----------|----------|-----------------|-------------------|
| ejemplarId | Integer | Identificador único | PK, Auto-increment |
| libroId | Integer | Referencia al libro | FK, NOT NULL |
| estado | String | Estado del ejemplar | VARCHAR(50) |
| ubicacion | String | Ubicación física | VARCHAR(100) |

**Estados posibles:** Disponible, Prestado, En reparación, Perdido

**Relaciones:**
- Pertenece a un Libro (N:1)
- Puede tener un Préstamo activo (1:1)

#### 4.2.5 Usuario
Almacena información de los usuarios de la biblioteca.

| **Campo** | **Tipo** | **Descripción** | **Restricciones** |
|-----------|----------|-----------------|-------------------|
| usuarioId | Integer | Identificador único | PK, Auto-increment |
| nombre | String | Nombre del usuario | NOT NULL, VARCHAR(100) |
| apellido | String | Apellido del usuario | VARCHAR(100) |
| email | String | Correo electrónico | UNIQUE, VARCHAR(100) |
| direccion | String | Dirección física | VARCHAR(200) |
| fechaRegistro | Date | Fecha de registro | NOT NULL |
| activo | Boolean | Estado del usuario | NOT NULL, Default: true |

**Relaciones:**
- Tiene muchos Préstamos (1:N)

#### 4.2.6 Préstamo
Registra los préstamos de ejemplares a usuarios.

| **Campo** | **Tipo** | **Descripción** | **Restricciones** |
|-----------|----------|-----------------|-------------------|
| prestamoId | Integer | Identificador único | PK, Auto-increment |
| ejemplarId | Integer | Referencia al ejemplar | FK, NOT NULL |
| usuarioId | Integer | Referencia al usuario | FK, NOT NULL |
| fechaPrestamo | Date | Fecha del préstamo | NOT NULL |
| fechaDevolucionEsperada | Date | Fecha límite de devolución | NOT NULL |
| fechaDevolucionReal | Date | Fecha real de devolución | NULL si no devuelto |

**Relaciones:**
- Pertenece a un Usuario (N:1)
- Pertenece a un Ejemplar (N:1)
- Puede tener una Multa (1:1)

#### 4.2.7 Multa
Registra las multas generadas por retrasos en devoluciones.

| **Campo** | **Tipo** | **Descripción** | **Restricciones** |
|-----------|----------|-----------------|-------------------|
| multaId | Integer | Identificador único | PK, Auto-increment |
| prestamoId | Integer | Referencia al préstamo | FK, NOT NULL, UNIQUE |
| monto | Decimal | Monto de la multa | NOT NULL, DECIMAL(10,2) |
| fechaGeneracion | Date | Fecha de creación | NOT NULL |
| pagado | Boolean | Estado de pago | NOT NULL, Default: false |

**Relaciones:**
- Pertenece a un Préstamo (1:1)

---

## 💻 5. ESTRUCTURA DEL PROYECTO

### 5.1 Organización de Paquetes

```
src/main/java/com/idat/evc3_Biblioteca/
├── Controller/          # Controladores REST y MVC
│   ├── AutorController.java
│   ├── LibroController.java
│   ├── CategoriaController.java
│   ├── EjemplarController.java
│   ├── UsuarioController.java
│   ├── PrestamoController.java
│   └── MultaController.java
│
├── Dtos/               # Data Transfer Objects
│   ├── AutorDTO.java
│   ├── LibroDTO.java
│   ├── CategoriaDTO.java
│   ├── EjemplarDTO.java
│   ├── UsuarioDTO.java
│   ├── PrestamoDTO.java
│   └── MultaDTO.java
│
├── Entity/             # Entidades JPA
│   ├── Autor.java
│   ├── Libro.java
│   ├── Categoria.java
│   ├── Ejemplar.java
│   ├── Usuario.java
│   ├── Prestamo.java
│   └── Multa.java
│
├── Mapper/             # Mappers MapStruct
│   ├── AutorMapper.java
│   ├── LibroMapper.java
│   ├── CategoriaMapper.java
│   ├── EjemplarMapper.java
│   ├── UsuarioMapper.java
│   ├── PrestamoMapper.java
│   └── MultaMapper.java
│
├── Repository/         # Repositorios Spring Data JPA
│   ├── AutorRepository.java
│   ├── LibroRepository.java
│   ├── CategoriaRepository.java
│   ├── EjemplarRepository.java
│   ├── UsuarioRepository.java
│   ├── PrestamoRepository.java
│   └── MultaRepository.java
│
├── Service/            # Servicios de lógica de negocio
│   ├── AutorService.java
│   ├── LibroService.java
│   ├── CategoriaService.java
│   ├── EjemplarService.java
│   ├── UsuarioService.java
│   ├── PrestamoService.java
│   └── MultaService.java
│
└── Evc3BibliotecaApplication.java  # Clase principal

src/main/resources/
├── application.properties    # Configuración de la aplicación
├── static/
│   └── styles.css          # Estilos CSS personalizados
└── templates/              # Plantillas Thymeleaf
    ├── index.html
    ├── autores-listar.html
    ├── autores-registrar.html
    ├── autores-editar.html
    ├── autores-detalle.html
    ├── autores-inactivar.html
    ├── libros-listar.html
    ├── libros-registrar.html
    ├── libros-editar.html
    ├── libros-detalle.html
    ├── categorias-listar.html
    ├── categorias-registrar.html
    ├── categorias-editar.html
    ├── categorias-detalle.html
    ├── ejemplares-registrar.html
    ├── ejemplares-editar.html
    ├── usuarios-listar.html
    ├── usuarios-registrar.html
    ├── usuarios-editar.html
    ├── usuarios-detalle.html
    ├── prestamos-listar.html
    ├── prestamos-registrar.html
    ├── prestamos-devolver.html
    ├── prestamos-detalle.html
    ├── multas-listar.html
    ├── multas-detalle.html
    └── multas-pagar.html
```

### 5.2 Descripción de Capas

#### 5.2.1 Capa Controller
**Responsabilidad:** Manejar las solicitudes HTTP y coordinar la interacción entre la vista y los servicios.

**Anotaciones principales:**
- `@Controller`: Marca la clase como controlador MVC
- `@GetMapping`: Maneja peticiones GET
- `@PostMapping`: Maneja peticiones POST
- `@PathVariable`: Captura variables de la URL
- `@RequestParam`: Captura parámetros de consulta

#### 5.2.2 Capa Service
**Responsabilidad:** Implementar la lógica de negocio y coordinar las operaciones entre repositorios.

**Anotaciones principales:**
- `@Service`: Marca la clase como servicio
- `@Transactional`: Gestiona transacciones de base de datos

#### 5.2.3 Capa Repository
**Responsabilidad:** Proporcionar acceso a datos mediante Spring Data JPA.

**Anotaciones principales:**
- `@Repository`: Marca la interfaz como repositorio
- Extiende `JpaRepository<Entity, ID>`

#### 5.2.4 Capa DTO
**Responsabilidad:** Transferir datos entre capas sin exponer las entidades de dominio.

**Ventajas:**
- Desacoplamiento entre capas
- Control sobre datos expuestos
- Validaciones específicas por operación

#### 5.2.5 Capa Mapper
**Responsabilidad:** Convertir entre DTOs y Entidades usando MapStruct.

**Anotaciones principales:**
- `@Mapper(componentModel = "spring")`
- Generación automática de código de conversión

#### 5.2.6 Capa Entity
**Responsabilidad:** Modelar las entidades de dominio y su mapeo a tablas de base de datos.

**Anotaciones principales:**
- `@Entity`: Marca la clase como entidad JPA
- `@Table`: Especifica el nombre de la tabla
- `@Id`: Marca el campo como clave primaria
- `@GeneratedValue`: Estrategia de generación de ID
- `@Column`: Mapea el campo a una columna
- `@ManyToOne`, `@OneToMany`: Relaciones entre entidades

---

## 🔧 6. FUNCIONALIDADES IMPLEMENTADAS

### 6.1 Gestión de Autores (RF8.1 - RF8.4, RF8.10)

#### RF8.1 - Registrar Autores
**Endpoint:** `POST /autores/registrar`

**Campos requeridos:**
- Nombre (obligatorio)
- Apellidos (opcional)
- Nacionalidad (opcional)
- Estado activo (por defecto: true)

**Validaciones:**
- Nombre no puede estar vacío
- Estado activo se establece automáticamente en true

**Vista:** `autores-registrar.html`

#### RF8.2 - Listar Autores Registrados
**Endpoint:** `GET /autores`

**Características:**
- Muestra todos los autores activos e inactivos
- Incluye información de nombre, nacionalidad y estado
- Enlaces a operaciones: Ver detalle, Editar, Inactivar

**Vista:** `autores-listar.html`

#### RF8.3 - Editar Datos de Autores
**Endpoint:** `GET /autores/editar/{id}` (formulario) / `POST /autores/editar/{id}` (guardar)

**Campos editables:**
- Nombre
- Apellidos
- Nacionalidad
- Estado activo

**Vista:** `autores-editar.html`

#### RF8.4 - Inactivar Autores (Eliminación Lógica)
**Endpoint:** `GET /autores/inactivar/{id}` (formulario) / `POST /autores/inactivar/{id}` (confirmar)

**Funcionamiento:**
- Cambia el estado `activo` a `false`
- No elimina físicamente el registro de la base de datos
- Previene la pérdida de datos históricos

**Vista:** `autores-inactivar.html`

#### RF8.10 - Mostrar Detalle de Autor
**Endpoint:** `GET /autores/detalle/{id}`

**Información mostrada:**
- Datos completos del autor
- Lista de libros asociados al autor
- Cantidad total de libros publicados

**Vista:** `autores-detalle.html`

---

### 6.2 Gestión de Libros (RF8.5 - RF8.10)

#### RF8.5 - Registrar Libros Asociados a un Autor
**Endpoint:** `POST /libros/registrar`

**Campos requeridos:**
- Título (obligatorio)
- Año de publicación
- Autor (obligatorio, selección de lista)
- Categoría (obligatorio, selección de lista)
- Estado activo (por defecto: true)

**Validaciones:**
- Título no puede estar vacío
- Autor debe existir en el sistema
- Categoría debe existir en el sistema

**Vista:** `libros-registrar.html`

#### RF8.6 - Listar Libros por Autor Seleccionado
**Endpoint:** `GET /libros?autorId={id}`

**Características:**
- Filtrado por autor específico
- Muestra título, año, categoría y estado
- Enlaces a ver detalle y editar

**Vista:** `libros-listar.html`

#### RF8.7 - Editar Datos de un Libro
**Endpoint:** `GET /libros/editar/{id}` (formulario) / `POST /libros/editar/{id}` (guardar)

**Campos editables:**
- Título
- Año de publicación
- Autor
- Categoría
- Estado activo

**Vista:** `libros-editar.html`

#### RF8.8 - Buscar Libros por Título
**Endpoint:** `GET /libros?titulo={texto}`

**Características:**
- Búsqueda parcial (coincidencia de cadena)
- Case-insensitive (no distingue mayúsculas/minúsculas)
- Muestra resultados en la misma vista de listado

**Implementación:** Método `findByTituloContainingIgnoreCase()` en LibroRepository

#### RF8.9 - Filtrar Libros por Estado
**Endpoint:** `GET /libros?estado={estado}`

**Estados disponibles:**
- Disponible
- Prestado
- Fuera de catálogo

**Implementación:** Método `findByEstado()` en LibroRepository

#### RF8.10 - Mostrar Detalle de un Libro
**Endpoint:** `GET /libros/detalle/{id}`

**Información mostrada:**
- Datos completos del libro
- Información del autor (nombre completo, nacionalidad)
- Información de la categoría
- Lista de ejemplares disponibles
- Estado de disponibilidad

**Vista:** `libros-detalle.html`

---

### 6.3 Gestión de Categorías

#### Registrar Categorías
**Endpoint:** `POST /categorias/registrar`

**Campos:**
- Nombre (obligatorio)
- Descripción (opcional)
- Estado activo (por defecto: true)

#### Listar Categorías
**Endpoint:** `GET /categorias`

**Características:**
- Muestra todas las categorías
- Contador de libros por categoría
- Enlaces a editar y ver detalle

#### Editar Categorías
**Endpoint:** `POST /categorias/editar/{id}`

#### Ver Detalle de Categoría
**Endpoint:** `GET /categorias/detalle/{id}`

**Muestra:**
- Información de la categoría
- Lista de libros asociados

---

### 6.4 Gestión de Ejemplares

#### Registrar Ejemplares
**Endpoint:** `POST /ejemplares/registrar`

**Campos:**
- Libro (selección de lista)
- Estado (Disponible, Prestado, En reparación, Perdido)
- Ubicación física (estante, sección)

**Funcionamiento:**
- Permite agregar múltiples copias físicas de un mismo libro
- Cada ejemplar tiene su propio código único

#### Editar Ejemplares
**Endpoint:** `POST /ejemplares/editar/{id}`

**Casos de uso:**
- Cambiar ubicación física
- Actualizar estado (ej: marcar como perdido)
- Cambiar a "En reparación"

---

### 6.5 Gestión de Usuarios

#### Registrar Usuarios
**Endpoint:** `POST /usuarios/registrar`

**Campos:**
- Nombre (obligatorio)
- Apellido (obligatorio)
- Email (obligatorio, único)
- Dirección (opcional)
- Fecha de registro (automática)
- Estado activo (por defecto: true)

**Validaciones:**
- Email debe ser único
- Email debe tener formato válido

#### Listar Usuarios
**Endpoint:** `GET /usuarios`

**Características:**
- Muestra usuarios activos e inactivos
- Información: nombre, email, fecha de registro
- Enlaces a editar y ver detalle

#### Editar Usuarios
**Endpoint:** `POST /usuarios/editar/{id}`

#### Ver Detalle de Usuario
**Endpoint:** `GET /usuarios/detalle/{id}`

**Muestra:**
- Información completa del usuario
- Historial de préstamos activos
- Historial de préstamos pasados
- Multas pendientes

---

### 6.6 Gestión de Préstamos

#### Registrar Préstamos
**Endpoint:** `POST /prestamos/registrar`

**Campos:**
- Usuario (selección de lista)
- Ejemplar (selección de ejemplares disponibles)
- Fecha de préstamo (automática: hoy)
- Fecha de devolución esperada (calculada: +14 días)

**Validaciones:**
- Ejemplar debe estar disponible
- Usuario debe estar activo
- No permite préstamos si el usuario tiene multas pendientes

**Efectos:**
- Cambia el estado del ejemplar a "Prestado"
- Registra la fecha de préstamo

#### Listar Préstamos
**Endpoint:** `GET /prestamos`

**Características:**
- Muestra préstamos activos y devueltos
- Filtros: Por usuario, por estado (activo/devuelto)
- Información: usuario, libro, fechas, estado de retraso
- Indicador visual de préstamos vencidos (en rojo)

#### Devolver Préstamos
**Endpoint:** `POST /prestamos/devolver/{id}`

**Funcionamiento:**
- Registra la fecha real de devolución
- Cambia el estado del ejemplar a "Disponible"
- Si hay retraso, genera automáticamente una multa
- Cálculo de multa: días de retraso × $3.00 por día

**Vista:** `prestamos-devolver.html`

#### Ver Detalle de Préstamo
**Endpoint:** `GET /prestamos/detalle/{id}`

**Muestra:**
- Información completa del préstamo
- Datos del usuario
- Datos del ejemplar y libro
- Fechas (préstamo, devolución esperada, devolución real)
- Estado de multa (si existe)
- Días de retraso (si aplica)

---

### 6.7 Gestión de Multas

#### Listar Multas
**Endpoint:** `GET /multas`

**Características:**
- Muestra todas las multas generadas
- Filtros: Pagadas, Pendientes
- Información: usuario, préstamo, monto, estado de pago
- Indicador visual de multas pendientes

#### Ver Detalle de Multa
**Endpoint:** `GET /multas/detalle/{id}`

**Muestra:**
- Información de la multa
- Datos del préstamo asociado
- Datos del usuario
- Libro y ejemplar involucrados
- Cálculo del monto (días × tarifa)

#### Pagar Multas
**Endpoint:** `POST /multas/pagar/{id}`

**Funcionamiento:**
- Cambia el estado `pagado` a `true`
- Registra la fecha de pago (opcional)
- Permite al usuario realizar nuevos préstamos

**Vista:** `multas-pagar.html`

---

## ⚙️ 7. CONFIGURACIÓN Y DESPLIEGUE

### 7.1 Requisitos Previos

**Software necesario:**
- Java JDK 21 o superior
- Maven 3.9+ (incluido en el proyecto con Maven Wrapper)
- SQL Server 2019 o superior
- IDE recomendado: IntelliJ IDEA, Eclipse o VS Code

### 7.2 Configuración de Base de Datos

**Paso 1: Crear la base de datos**

```sql
CREATE DATABASE dsw_Bibblioteca;
GO

USE dsw_Bibblioteca;
GO
```

**Paso 2: Configurar application.properties**

El archivo `src/main/resources/application.properties` contiene:

```properties
spring.application.name=evc3-Biblioteca

# SQL SERVER CONFIG
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=dsw_Bibblioteca;encrypt=false
spring.datasource.username=sa
spring.datasource.password=123456
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# JPA / HIBERNATE CONFIG
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
```

**Ajustar según tu entorno:**
- `spring.datasource.username`: Tu usuario de SQL Server
- `spring.datasource.password`: Tu contraseña de SQL Server
- `spring.datasource.url`: Si usas otro puerto o servidor remoto

**Paso 3: Hibernate generará automáticamente las tablas**

Con `spring.jpa.hibernate.ddl-auto=update`, las tablas se crean automáticamente al iniciar la aplicación.

### 7.3 Instalación y Ejecución

**Opción 1: Usando Maven Wrapper (Recomendado)**

```bash
# Windows
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw clean install
./mvnw spring-boot:run
```

**Opción 2: Usando IDE**

1. Importar el proyecto como proyecto Maven
2. Esperar a que se descarguen las dependencias
3. Ejecutar la clase `Evc3BibliotecaApplication.java`

**Opción 3: Generando JAR ejecutable**

```bash
# Compilar
.\mvnw.cmd clean package

# Ejecutar
java -jar target/evc3-Biblioteca-0.0.1-SNAPSHOT.jar
```

### 7.4 Acceso a la Aplicación

Una vez iniciada la aplicación:

**URL principal:** http://localhost:8080

**Endpoints disponibles:**
- Home: http://localhost:8080/
- Autores: http://localhost:8080/autores
- Libros: http://localhost:8080/libros
- Categorías: http://localhost:8080/categorias
- Usuarios: http://localhost:8080/usuarios
- Préstamos: http://localhost:8080/prestamos
- Multas: http://localhost:8080/multas

### 7.5 Datos de Prueba

**Script SQL con datos iniciales:**

```sql
-- AUTORES
INSERT INTO Autores (nombre, apellidos, nacionalidad, activo) VALUES
('Mario', 'Vargas Llosa', 'Peruana', 1),
('César', 'Vallejo', 'Peruana', 1),
('José María', 'Arguedas', 'Peruana', 1),
('Ciro', 'Alegría', 'Peruana', 1),
('Alfredo', 'Bryce Echenique', 'Peruana', 1);

-- CATEGORÍAS
INSERT INTO Categorias (nombre, descripcion, activo) VALUES
('Novela', 'Obras de ficción narrativa extensa', 1),
('Poesía', 'Composiciones literarias en verso', 1),
('Cuento', 'Relatos breves de ficción', 1),
('Literatura Indigenista', 'Obras sobre cultura y problemática indígena', 1);

-- LIBROS
INSERT INTO Libros (titulo, año, AutorID, CategoriaID, activo) VALUES
('La ciudad y los perros', 1963, 1, 1, 1),
('Conversación en La Catedral', 1969, 1, 1, 1),
('Los Heraldos Negros', 1919, 2, 2, 1),
('Los ríos profundos', 1958, 3, 4, 1),
('El mundo es ancho y ajeno', 1941, 4, 4, 1);

-- USUARIOS
INSERT INTO Usuarios (nombre, apellido, email, direccion, Fecha_Registro, activo) VALUES
('Juan Carlos', 'Pérez García', 'jperez@email.com', 'Av. Arequipa 1234, Lima', GETDATE(), 1),
('María', 'Rodríguez López', 'mrodriguez@email.com', 'Jr. Huancayo 567, Lima', GETDATE(), 1);

-- EJEMPLARES
INSERT INTO Ejemplares (LibroID, estado, ubicacion) VALUES
(1, 'Disponible', 'Estante A-01'),
(1, 'Disponible', 'Estante A-02'),
(2, 'Disponible', 'Estante A-03'),
(3, 'Disponible', 'Estante B-01');
```

---

## 🧪 8. PRUEBAS Y VALIDACIÓN

### 8.1 Tipos de Pruebas Realizadas

#### 8.1.1 Pruebas Funcionales

**Gestión de Autores:**
- ✅ Registro de autor con todos los campos
- ✅ Registro de autor solo con nombre (campos opcionales vacíos)
- ✅ Listado de autores activos e inactivos
- ✅ Edición de datos de autor existente
- ✅ Inactivación de autor (soft delete)
- ✅ Visualización de detalle con lista de libros asociados

**Gestión de Libros:**
- ✅ Registro de libro asociado a un autor
- ✅ Listado completo de libros
- ✅ Filtrado de libros por autor
- ✅ Búsqueda de libros por título
- ✅ Filtrado por estado (disponible/prestado)
- ✅ Edición de datos de libro
- ✅ Visualización de detalle con información de autor

**Gestión de Préstamos:**
- ✅ Registro de préstamo con ejemplar disponible
- ✅ Validación de disponibilidad de ejemplar
- ✅ Cálculo automático de fecha de devolución
- ✅ Proceso de devolución
- ✅ Generación automática de multa por retraso
- ✅ Actualización de estado de ejemplar

**Gestión de Multas:**
- ✅ Generación automática al devolver con retraso
- ✅ Cálculo correcto del monto (días × tarifa)
- ✅ Proceso de pago de multa
- ✅ Listado de multas pagadas y pendientes

#### 8.1.2 Pruebas de Integración

- ✅ Relación Autor → Libro (1:N) funciona correctamente
- ✅ Relación Libro → Ejemplar (1:N) funciona correctamente
- ✅ Relación Usuario → Préstamo (1:N) funciona correctamente
- ✅ Relación Préstamo → Multa (1:1) funciona correctamente
- ✅ Cascada de operaciones funciona según lo esperado

#### 8.1.3 Pruebas de Interfaz de Usuario

- ✅ Navegación entre módulos es consistente
- ✅ Formularios validan datos correctamente
- ✅ Mensajes de error/éxito se muestran apropiadamente
- ✅ Diseño responsivo funciona en diferentes dispositivos
- ✅ Botones y enlaces funcionan correctamente

### 8.2 Casos de Prueba Principales

#### CP-001: Registrar Autor Completo

| **Campo** | **Valor** |
|-----------|-----------|
| Nombre | Mario |
| Apellidos | Vargas Llosa |
| Nacionalidad | Peruana |
| **Resultado Esperado** | Autor registrado exitosamente con estado activo |
| **Estado** | ✅ APROBADO |

#### CP-002: Registrar Libro Asociado a Autor

| **Campo** | **Valor** |
|-----------|-----------|
| Título | La ciudad y los perros |
| Año | 1963 |
| Autor | Mario Vargas Llosa |
| Categoría | Novela |
| **Resultado Esperado** | Libro registrado y asociado al autor |
| **Estado** | ✅ APROBADO |

#### CP-003: Proceso Completo de Préstamo

| **Paso** | **Acción** | **Resultado** |
|----------|------------|---------------|
| 1 | Seleccionar usuario y ejemplar disponible | Formulario cargado correctamente |
| 2 | Registrar préstamo | Préstamo creado, ejemplar marcado como "Prestado" |
| 3 | Verificar fecha límite | Fecha calculada correctamente (+14 días) |
| **Estado** | ✅ APROBADO |

#### CP-004: Generación Automática de Multa

| **Paso** | **Acción** | **Resultado** |
|----------|------------|---------------|
| 1 | Crear préstamo con fecha límite vencida | Préstamo activo |
| 2 | Devolver ejemplar con retraso de 5 días | Devolución registrada |
| 3 | Verificar multa generada | Multa creada con monto $15.00 (5 días × $3) |
| **Estado** | ✅ APROBADO |

---

## 📈 9. CONCLUSIONES Y RECOMENDACIONES

### 9.1 Conclusiones

1. **Arquitectura Sólida:** Se implementó exitosamente una arquitectura en capas que garantiza la separación de responsabilidades y facilita el mantenimiento del código.

2. **Uso de Patrones de Diseño:** La aplicación de patrones como DTO, Repository y Mapper mejora la calidad del código y su escalabilidad.

3. **Funcionalidades Completas:** Se cumplieron todos los requerimientos funcionales (RF8.1 - RF8.10), proporcionando un sistema completo de gestión bibliotecaria.

4. **Integración de Tecnologías Modernas:** El uso de Spring Boot, JPA, MapStruct y Thymeleaf permite un desarrollo ágil y mantenible.

5. **Experiencia de Usuario:** La interfaz desarrollada con Bootstrap proporciona una experiencia intuitiva y responsiva.

6. **Integridad de Datos:** Las relaciones entre entidades y las validaciones implementadas garantizan la consistencia de los datos.

### 9.2 Logros Alcanzados

✅ Sistema completamente funcional con todas las operaciones CRUD
✅ Relaciones entre entidades correctamente implementadas
✅ Interfaz de usuario intuitiva y responsiva
✅ Validaciones de datos en backend y frontend
✅ Eliminación lógica para preservar datos históricos
✅ Generación automática de multas por retrasos
✅ Búsqueda y filtrado de información
✅ Documentación técnica completa

### 9.3 Recomendaciones para Futuras Mejoras

#### 9.3.1 Seguridad

- **Implementar autenticación y autorización:** Usar Spring Security para proteger endpoints y gestionar roles (administrador, bibliotecario, usuario).
- **Encriptación de contraseñas:** Si se implementa login de usuarios.
- **Protección CSRF:** Habilitar protección contra Cross-Site Request Forgery.

#### 9.3.2 Funcionalidades Adicionales

- **Sistema de Reservas:** Permitir a los usuarios reservar libros que están prestados.
- **Notificaciones:** Enviar correos electrónicos de recordatorio antes de la fecha de devolución.
- **Historial de Préstamos:** Visualización completa del historial de cada usuario.
- **Reportes:** Generar reportes en PDF de préstamos, multas y estadísticas.
- **Dashboard Administrativo:** Panel con métricas y estadísticas del sistema.

#### 9.3.3 Mejoras Técnicas

- **Paginación:** Implementar paginación en listados con muchos registros.
- **Caché:** Utilizar Redis o Spring Cache para mejorar el rendimiento.
- **API REST:** Exponer endpoints REST para consumo de aplicaciones móviles.
- **Logs:** Implementar sistema de logs con Logback para auditoría.
- **Tests Unitarios:** Agregar tests con JUnit y Mockito para garantizar calidad.

#### 9.3.4 Base de Datos

- **Optimización de Consultas:** Agregar índices en campos frecuentemente consultados.
- **Auditoría:** Implementar campos `createdAt`, `updatedAt`, `createdBy` en todas las entidades.
- **Respaldos Automáticos:** Configurar backups automáticos de la base de datos.

#### 9.3.5 Interfaz de Usuario

- **Internacionalización (i18n):** Soporte para múltiples idiomas.
- **Modo Oscuro:** Implementar tema claro/oscuro.
- **Accesibilidad:** Mejorar accesibilidad siguiendo estándares WCAG.
- **Progressive Web App (PWA):** Convertir la aplicación en PWA para uso offline.

#### 9.3.6 Despliegue y DevOps

- **Dockerización:** Crear contenedores Docker para facilitar el despliegue.
- **CI/CD:** Implementar pipelines de integración y despliegue continuo.
- **Monitoreo:** Usar Spring Boot Actuator para monitoreo en producción.
- **Escalabilidad:** Preparar la aplicación para escalado horizontal.

---

## 📚 10. REFERENCIAS

### 10.1 Documentación Oficial

1. **Spring Framework**
   - Spring Boot Documentation: https://docs.spring.io/spring-boot/docs/current/reference/html/
   - Spring Data JPA: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

2. **MapStruct**
   - MapStruct Reference Guide: https://mapstruct.org/documentation/stable/reference/html/

3. **Thymeleaf**
   - Thymeleaf Documentation: https://www.thymeleaf.org/documentation.html

4. **Bootstrap**
   - Bootstrap Documentation: https://getbootstrap.com/docs/5.3/getting-started/introduction/

### 10.2 Recursos Adicionales

- Baeldung: Spring Boot Tutorials - https://www.baeldung.com/spring-boot
- Spring Academy - https://spring.academy/
- SQL Server Documentation - https://learn.microsoft.com/en-us/sql/sql-server/

### 10.3 Herramientas Utilizadas

| **Herramienta** | **Propósito** | **URL** |
|-----------------|---------------|---------|
| IntelliJ IDEA | IDE de desarrollo | https://www.jetbrains.com/idea/ |
| SQL Server Management Studio | Gestión de BD | https://learn.microsoft.com/en-us/sql/ssms/ |
| Postman | Pruebas de API | https://www.postman.com/ |
| Git | Control de versiones | https://git-scm.com/ |
| Maven | Gestión de dependencias | https://maven.apache.org/ |

---

## 📝 ANEXOS

### Anexo A: Estructura de Tablas SQL

```sql
-- Tabla Autores
CREATE TABLE Autores (
    autorId INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100),
    nacionalidad VARCHAR(50),
    activo BIT NOT NULL DEFAULT 1
);

-- Tabla Categorias
CREATE TABLE Categorias (
    categoriaId INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    activo BIT NOT NULL DEFAULT 1
);

-- Tabla Libros
CREATE TABLE Libros (
    libroId INT IDENTITY(1,1) PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    año INT,
    AutorID INT NOT NULL,
    CategoriaID INT,
    activo BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (AutorID) REFERENCES Autores(autorId),
    FOREIGN KEY (CategoriaID) REFERENCES Categorias(categoriaId)
);

-- Tabla Usuarios
CREATE TABLE Usuarios (
    usuarioId INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    direccion VARCHAR(200),
    Fecha_Registro DATE NOT NULL,
    activo BIT NOT NULL DEFAULT 1
);

-- Tabla Ejemplares
CREATE TABLE Ejemplares (
    ejemplarId INT IDENTITY(1,1) PRIMARY KEY,
    LibroID INT NOT NULL,
    estado VARCHAR(50),
    ubicacion VARCHAR(100),
    FOREIGN KEY (LibroID) REFERENCES Libros(libroId)
);

-- Tabla Prestamos
CREATE TABLE Prestamos (
    prestamoId INT IDENTITY(1,1) PRIMARY KEY,
    EjemplarID INT NOT NULL,
    UsuarioID INT NOT NULL,
    Fecha_Prestamo DATE NOT NULL,
    Fecha_Devolucion_Esperada DATE NOT NULL,
    Fecha_Devolucion_Real DATE,
    FOREIGN KEY (EjemplarID) REFERENCES Ejemplares(ejemplarId),
    FOREIGN KEY (UsuarioID) REFERENCES Usuarios(usuarioId)
);

-- Tabla Multas
CREATE TABLE Multas (
    multaId INT IDENTITY(1,1) PRIMARY KEY,
    PrestamoID INT NOT NULL UNIQUE,
    monto DECIMAL(10,2) NOT NULL,
    fecha_generacion DATE NOT NULL,
    pagado BIT NOT NULL DEFAULT 0,
    FOREIGN KEY (PrestamoID) REFERENCES Prestamos(prestamoId)
);
```

### Anexo B: Comandos Maven Útiles

```bash
# Limpiar y compilar
mvn clean compile

# Ejecutar tests
mvn test

# Empaquetar sin ejecutar tests
mvn package -DskipTests

# Ejecutar la aplicación
mvn spring-boot:run

# Generar documentación JavaDoc
mvn javadoc:javadoc

# Ver dependencias del proyecto
mvn dependency:tree

# Actualizar dependencias
mvn versions:display-dependency-updates
```

### Anexo C: Configuraciones Adicionales

**application.properties para Producción:**

```properties
# Producción
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
logging.level.root=WARN
logging.level.com.idat.evc3_Biblioteca=INFO

# Pool de conexiones
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
```

---

## 🏆 RECONOCIMIENTOS

Este proyecto fue desarrollado como parte del curso de **Desarrollo de Software Web** del 4to ciclo de la carrera de [Completar carrera], demostrando la aplicación práctica de tecnologías modernas de desarrollo web Java.

**Tecnologías dominadas:**
- ✅ Spring Boot Framework
- ✅ Spring Data JPA
- ✅ Thymeleaf Template Engine
- ✅ MapStruct Object Mapping
- ✅ SQL Server Database
- ✅ Bootstrap Frontend Framework
- ✅ Maven Build Tool
- ✅ Arquitectura en Capas
- ✅ Patrón MVC
- ✅ Patrón Repository
- ✅ Patrón DTO

---

**Documento generado el:** 26 de Noviembre de 2025
**Versión del documento:** 1.0.0
**Estado:** ✅ COMPLETO

---

**Fin del Documento Técnico**

