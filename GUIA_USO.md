# Guía de Uso - Sistema de Biblioteca

## 📁 Estructura del Proyecto

```
evc3-Biblioteca/
├── src/main/java/com/idat/evc3_Biblioteca/
│   ├── Dtos/                    ✅ 7 DTOs creados
│   │   ├── AutorDTO.java
│   │   ├── CategoriaDTO.java
│   │   ├── EjemplarDTO.java
│   │   ├── LibroDTO.java
│   │   ├── MultaDTO.java
│   │   ├── PrestamoDTO.java
│   │   └── UsuarioDTO.java
│   │
│   ├── Entity/                  📦 Entidades existentes
│   │   ├── Autor.java
│   │   ├── Categoria.java
│   │   ├── Ejemplar.java
│   │   ├── Libro.java
│   │   ├── Multa.java
│   │   ├── Prestamo.java
│   │   └── Usuario.java
│   │
│   ├── Mapper/                  🔄 7 Mappers de MapStruct
│   │   ├── AutorMapper.java
│   │   ├── CategoriaMapper.java
│   │   ├── EjemplarMapper.java
│   │   ├── LibroMapper.java
│   │   ├── MultaMapper.java
│   │   ├── PrestamoMapper.java
│   │   └── UsuarioMapper.java
│   │
│   ├── Repository/              💾 7 Repositories
│   │   ├── AutorRepository.java
│   │   ├── CategoriaRepository.java
│   │   ├── EjemplarRepository.java
│   │   ├── LibroRepository.java
│   │   ├── MultaRepository.java
│   │   ├── PrestamoRepository.java
│   │   └── UsuarioRepository.java
│   │
│   ├── Service/                 ⚙️ 7 Services
│   │   ├── AutorService.java
│   │   ├── CategoriaService.java
│   │   ├── EjemplarService.java
│   │   ├── LibroService.java
│   │   ├── MultaService.java
│   │   ├── PrestamoService.java
│   │   └── UsuarioService.java
│   │
│   ├── Example/                 📖 Ejemplos de uso
│   │   └── ServiceUsageExample.java
│   │
│   └── Evc3BibliotecaApplication.java
│
└── target/generated-sources/
    └── annotations/com/idat/evc3_Biblioteca/Mapper/
        ├── AutorMapperImpl.java      🤖 Generado por MapStruct
        ├── CategoriaMapperImpl.java  🤖 Generado por MapStruct
        ├── EjemplarMapperImpl.java   🤖 Generado por MapStruct
        ├── LibroMapperImpl.java      🤖 Generado por MapStruct
        ├── MultaMapperImpl.java      🤖 Generado por MapStruct
        ├── PrestamoMapperImpl.java   🤖 Generado por MapStruct
        └── UsuarioMapperImpl.java    🤖 Generado por MapStruct
```

---

## 🚀 Cómo Usar los Servicios

### 1️⃣ Inyectar el servicio en tu componente

```java
@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
public class AutorController {
    
    private final AutorService autorService;
    
    // Tus métodos aquí
}
```

### 2️⃣ Crear un nuevo registro

```java
// Crear DTO
AutorDTO autorDTO = AutorDTO.builder()
    .nombre("Gabriel")
    .apellidos("García Márquez")
    .nacionalidad("Colombiana")
    .activo(true)
    .build();

// Guardar (MapStruct convierte automáticamente DTO ↔ Entity)
AutorDTO autorGuardado = autorService.save(autorDTO);
```

### 3️⃣ Buscar registros

```java
// Buscar todos
List<AutorDTO> todos = autorService.findAll();

// Buscar por ID
AutorDTO autor = autorService.findById(1L);

// Buscar solo activos
List<AutorDTO> activos = autorService.findAllActive();

// Búsquedas personalizadas
List<AutorDTO> autores = autorService.findByNombre("Gabriel");
List<AutorDTO> colombianos = autorService.findByNacionalidad("Colombiana");
```

### 4️⃣ Actualizar registros

```java
// Obtener registro actual
AutorDTO autor = autorService.findById(1L);

// Modificar
autor.setNacionalidad("Colombia");

// Guardar cambios
AutorDTO actualizado = autorService.update(1L, autor);
```

### 5️⃣ Eliminar o desactivar

```java
// Desactivar (soft delete - recomendado)
autorService.deactivate(1L);

// Eliminar permanentemente
autorService.deleteById(1L);
```

---

## 🔗 Trabajar con Relaciones

### Crear un Libro con Autor y Categoría

```java
LibroDTO libroDTO = LibroDTO.builder()
    .titulo("Cien Años de Soledad")
    .descripcion("Obra cumbre del realismo mágico")
    .anioPublicacion(1967)
    .autorId(1L)        // Solo necesitas el ID
    .categoriaId(1L)    // Solo necesitas el ID
    .activo(true)
    .build();

// El servicio carga las entidades relacionadas automáticamente
LibroDTO libroGuardado = libroService.save(libroDTO);

// El DTO de respuesta incluye nombres para mostrar
System.out.println(libroGuardado.getAutorNombre());      // "Gabriel"
System.out.println(libroGuardado.getCategoriaNombre());  // "Ficción"
```

### Crear un Préstamo

```java
PrestamoDTO prestamoDTO = PrestamoDTO.builder()
    .usuarioId(1L)
    .ejemplarId(1L)
    .fechaPrestamo(LocalDateTime.now())
    .fechaDevolucionEsperada(LocalDateTime.now().plusDays(14))
    .estado("Activo")
    .build();

PrestamoDTO prestamo = prestamoService.save(prestamoDTO);
```

### Registrar Devolución

```java
// Un solo método actualiza fecha y estado
PrestamoDTO devuelto = prestamoService.registrarDevolucion(1L);
```

---

## 🔍 Búsquedas Avanzadas

### Libros

```java
// Por título (búsqueda parcial, case-insensitive)
List<LibroDTO> libros = libroService.findByTitulo("cien");

// Por autor
List<LibroDTO> librosAutor = libroService.findByAutorId(1L);

// Por categoría
List<LibroDTO> librosCategoria = libroService.findByCategoriaId(1L);

// Por año
List<LibroDTO> libros1967 = libroService.findByAnioPublicacion(1967);
```

### Préstamos

```java
// Préstamos de un usuario
List<PrestamoDTO> prestamos = prestamoService.findByUsuarioId(1L);

// Préstamos activos (no devueltos)
List<PrestamoDTO> activos = prestamoService.findPrestamosActivos();

// Préstamos atrasados
List<PrestamoDTO> atrasados = prestamoService.findPrestamosAtrasados();

// Por estado
List<PrestamoDTO> finalizados = prestamoService.findByEstado("Finalizado");
```

### Multas

```java
// Multas de un usuario
List<MultaDTO> multas = multaService.findByUsuarioId(1L);

// Multas pendientes de un usuario
List<MultaDTO> pendientes = multaService.findByUsuarioIdAndPagado(1L, false);

// Marcar multa como pagada
multaService.marcarComoPagada(1L);
```

### Ejemplares

```java
// Ejemplares de un libro
List<EjemplarDTO> ejemplares = ejemplarService.findByLibroId(1L);

// Ejemplares disponibles
List<EjemplarDTO> disponibles = ejemplarService.findByEstado("Disponible");

// Ejemplares disponibles de un libro específico
List<EjemplarDTO> disponiblesLibro = 
    ejemplarService.findByLibroIdAndEstado(1L, "Disponible");
```

---

## ⚡ MapStruct en Acción

### ¿Qué hace MapStruct?

MapStruct **genera automáticamente** el código de conversión entre Entity y DTO durante la compilación.

**Sin MapStruct (manual):**
```java
// Tendrías que escribir esto manualmente
public AutorDTO toDTO(Autor autor) {
    AutorDTO dto = new AutorDTO();
    dto.setId(autor.getId());
    dto.setNombre(autor.getNombre());
    dto.setApellidos(autor.getApellidos());
    dto.setNacionalidad(autor.getNacionalidad());
    dto.setActivo(autor.isActivo());
    return dto;
}
```

**Con MapStruct (automático):**
```java
// Solo defines la interfaz
@Mapper(componentModel = "spring")
public interface AutorMapper {
    AutorDTO toDTO(Autor autor);
    Autor toEntity(AutorDTO autorDTO);
}
// MapStruct genera toda la implementación automáticamente!
```

### Mapeos Complejos

Para relaciones, MapStruct puede mapear propiedades anidadas:

```java
@Mapper(componentModel = "spring")
public interface LibroMapper {
    
    // Mapea autor.id a autorId
    @Mapping(source = "autor.id", target = "autorId")
    // Mapea autor.nombre a autorNombre
    @Mapping(source = "autor.nombre", target = "autorNombre")
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    LibroDTO toDTO(Libro libro);
    
    // Para el mapeo inverso, ignoramos campos que no necesitamos
    @Mapping(source = "autorId", target = "autor.id")
    @Mapping(target = "autor.nombre", ignore = true)
    // ... otros ignorados ...
    Libro toEntity(LibroDTO libroDTO);
}
```

---

## 🛡️ Validaciones en los Services

### UsuarioService

```java
// Valida que el email sea único al crear
usuarioService.save(usuarioDTO); 
// Lanza RuntimeException si el email ya existe

// Valida que el email sea único al actualizar
usuarioService.update(1L, usuarioDTO);
// Permite el mismo email si es del mismo usuario
```

### LibroService, PrestamoService, EjemplarService

```java
// Validan que las entidades relacionadas existan
libroService.save(libroDTO);
// Lanza RuntimeException si el autorId no existe
// Lanza RuntimeException si el categoriaId no existe
```

---

## 📊 Transacciones

Todos los métodos de escritura están marcados con `@Transactional`:

```java
@Transactional
public AutorDTO save(AutorDTO autorDTO) {
    // Si algo falla, se hace rollback automático
}

@Transactional(readOnly = true)
public List<AutorDTO> findAll() {
    // Optimización para operaciones de solo lectura
}
```

---

## 🎯 Ejemplo Completo: Flujo de Préstamo

```java
// 1. Crear autor
AutorDTO autor = autorService.save(
    AutorDTO.builder()
        .nombre("Gabriel").apellidos("García Márquez")
        .nacionalidad("Colombiana").activo(true).build()
);

// 2. Crear categoría
CategoriaDTO categoria = categoriaService.save(
    CategoriaDTO.builder()
        .nombre("Ficción").activo(true).build()
);

// 3. Crear libro
LibroDTO libro = libroService.save(
    LibroDTO.builder()
        .titulo("Cien Años de Soledad")
        .autorId(autor.getId())
        .categoriaId(categoria.getId())
        .anioPublicacion(1967).activo(true).build()
);

// 4. Crear ejemplar
EjemplarDTO ejemplar = ejemplarService.save(
    EjemplarDTO.builder()
        .libroId(libro.getId())
        .estado("Disponible")
        .ubicacion("Estante A-1").build()
);

// 5. Crear usuario
UsuarioDTO usuario = usuarioService.save(
    UsuarioDTO.builder()
        .nombre("Juan").apellido("Pérez")
        .email("juan@example.com").activo(true).build()
);

// 6. Crear préstamo
PrestamoDTO prestamo = prestamoService.save(
    PrestamoDTO.builder()
        .usuarioId(usuario.getId())
        .ejemplarId(ejemplar.getId())
        .fechaPrestamo(LocalDateTime.now())
        .fechaDevolucionEsperada(LocalDateTime.now().plusDays(14))
        .estado("Activo").build()
);

// 7. Registrar devolución (después de 14 días)
PrestamoDTO devuelto = prestamoService.registrarDevolucion(prestamo.getId());

// 8. Si hay atraso, crear multa
if (devuelto.getFechaDevolucionReal()
    .isAfter(devuelto.getFechaDevolucionEsperada())) {
    
    MultaDTO multa = multaService.save(
        MultaDTO.builder()
            .prestamoId(prestamo.getId())
            .monto(new BigDecimal("10.00"))
            .pagado(false).build()
    );
}
```

---

## ✅ Checklist de Implementación

- [x] DTOs creados para todas las entidades
- [x] Mappers de MapStruct configurados
- [x] Repositories con queries personalizadas
- [x] Services con lógica de negocio
- [x] Validaciones implementadas
- [x] Transacciones configuradas
- [x] MapStruct compilando correctamente
- [x] Ejemplos de uso documentados

---