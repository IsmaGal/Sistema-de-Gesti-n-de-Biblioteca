# 📚 Sistema de Gestión de Biblioteca

Sistema backend completo para la gestión de una biblioteca desarrollado con Spring Boot, Spring Data JPA y MapStruct.

---

## 🎯 Estado del Proyecto
---

## 📋 Entidades del Sistema

| Entidad | DTO | Mapper | Repository | Service |
|---------|-----|--------|------------|---------|
| **Autor** | ✅ | ✅ | ✅ | ✅ |
| **Categoria** | ✅ | ✅ | ✅ | ✅ |
| **Libro** | ✅ | ✅ | ✅ | ✅ |
| **Ejemplar** | ✅ | ✅ | ✅ | ✅ |
| **Usuario** | ✅ | ✅ | ✅ | ✅ |
| **Prestamo** | ✅ | ✅ | ✅ | ✅ |
| **Multa** | ✅ | ✅ | ✅ | ✅ |

---

## 🚀 Inicio Rápido

### Compilar el Proyecto

```bash
# Windows
.\mvnw.cmd clean install

# Linux/Mac
./mvnw clean install
```

### Ejecutar la Aplicación

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

---

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 4.0.0** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **MapStruct 1.5.5** - Mapeo automático Entity ↔ DTO
- **Lombok** - Reducción de código boilerplate
- **SQL Server** - Base de datos
- **Jakarta Persistence (JPA)** - ORM

---

## 📁 Estructura del Proyecto

```
src/main/java/com/idat/evc3_Biblioteca/
├── Dtos/          → Data Transfer Objects (7)
├── Entity/        → Entidades JPA (7)
├── Mapper/        → Interfaces MapStruct (7)
├── Repository/    → Repositories Spring Data (7)
├── Service/       → Servicios con lógica de negocio (7)
└── Example/       → Ejemplos de uso
```

---

## 📖 Documentación

El proyecto incluye documentación detallada en formato Markdown:

### 📘 [RESUMEN.md](RESUMEN.md)
**Resumen ejecutivo** con estadísticas y estado del proyecto.

### 📗 [IMPLEMENTACION.md](IMPLEMENTACION.md)
**Documentación técnica completa** con todos los métodos disponibles por cada servicio y repository.

### 📕 [GUIA_USO.md](GUIA_USO.md)
**Guía práctica** con ejemplos de código para usar los servicios.

---

## 💡 Ejemplos de Uso

### Crear un Autor

```java
@Autowired
private AutorService autorService;

public void crearAutor() {
    AutorDTO autorDTO = AutorDTO.builder()
        .nombre("Gabriel")
        .apellidos("García Márquez")
        .nacionalidad("Colombiana")
        .activo(true)
        .build();
    
    AutorDTO guardado = autorService.save(autorDTO);
}
```

### Buscar Libros

```java
@Autowired
private LibroService libroService;

public void buscarLibros() {
    // Buscar todos los libros activos
    List<LibroDTO> activos = libroService.findAllActive();
    
    // Buscar por título
    List<LibroDTO> libros = libroService.findByTitulo("Cien");
    
    // Buscar por autor
    List<LibroDTO> librosAutor = libroService.findByAutorId(1L);
}
```

### Crear un Préstamo

```java
@Autowired
private PrestamoService prestamoService;

public void crearPrestamo() {
    PrestamoDTO prestamoDTO = PrestamoDTO.builder()
        .usuarioId(1L)
        .ejemplarId(1L)
        .fechaPrestamo(LocalDateTime.now())
        .fechaDevolucionEsperada(LocalDateTime.now().plusDays(14))
        .estado("Activo")
        .build();
    
    PrestamoDTO prestamo = prestamoService.save(prestamoDTO);
}
```

Ver más ejemplos en [`ServiceUsageExample.java`](src/main/java/com/idat/evc3_Biblioteca/Example/ServiceUsageExample.java)

---

## 🔑 Características Principales

### ✨ MapStruct - Conversión Automática
Los mappers convierten automáticamente entre entidades y DTOs sin código manual:

```java
// Entity → DTO (automático)
AutorDTO dto = autorMapper.toDTO(autorEntity);

// DTO → Entity (automático)
Autor entity = autorMapper.toEntity(autorDTO);
```

### 🔍 Búsquedas Personalizadas
Cada repository incluye múltiples métodos de búsqueda:

- Búsqueda por nombre 
- Filtrado por estado activo
- Búsquedas por relaciones
- Consultas combinadas

### 🛡️ Validaciones
Los servicios incluyen validaciones de negocio:

- Email único en usuarios
- Validación de existencia de entidades relacionadas
- Manejo de excepciones con mensajes descriptivos

### 📊 Transacciones
Todos los métodos de escritura están marcados con `@Transactional` para garantizar la consistencia de datos.

---

## 👥 Autor

**Ismael** - Estudiante 5to Ciclo  
**Curso:** DSW (Desarrollo de Software Web)  
**Proyecto:** EVC3 - Sistema de Biblioteca

---

## 📄 Licencia

Este proyecto es de uso educativo.

---

## 🙏 Agradecimientos

Proyecto desarrollado como parte del curso de Desarrollo de Software Web en IDAT.

---

**Fecha de implementación:** 25 de Noviembre de 2025

---

<div align="center">

### 🎓 Proyecto Educativo - IDAT


</div>

