package com.idat.evc3_Biblioteca.Service;

import com.idat.evc3_Biblioteca.Dtos.LibroDTO;
import com.idat.evc3_Biblioteca.Entity.Autor;
import com.idat.evc3_Biblioteca.Entity.Categoria;
import com.idat.evc3_Biblioteca.Entity.Libro;
import com.idat.evc3_Biblioteca.Mapper.LibroMapper;
import com.idat.evc3_Biblioteca.Repository.AutorRepository;
import com.idat.evc3_Biblioteca.Repository.CategoriaRepository;
import com.idat.evc3_Biblioteca.Repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final LibroMapper libroMapper;

    @Transactional(readOnly = true)
    public List<LibroDTO> findAll() {
        return libroRepository.findAll()
                .stream()
                .map(libroMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LibroDTO> findAllActive() {
        return libroRepository.findByActivoTrue()
                .stream()
                .map(libroMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LibroDTO findById(Long id) {
        return libroRepository.findById(id)
                .map(libroMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<LibroDTO> findByTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(libroMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LibroDTO> findByAutorId(Long autorId) {
        return libroRepository.findByAutorId(autorId)
                .stream()
                .map(libroMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LibroDTO> findByCategoriaId(Long categoriaId) {
        return libroRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(libroMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LibroDTO> findByAnioPublicacion(Integer anioPublicacion) {
        return libroRepository.findByAnioPublicacion(anioPublicacion)
                .stream()
                .map(libroMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public LibroDTO save(LibroDTO libroDTO) {
        Autor autor = autorRepository.findById(libroDTO.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + libroDTO.getAutorId()));

        Categoria categoria = null;
        if (libroDTO.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(libroDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + libroDTO.getCategoriaId()));
        }

        Libro libro = libroMapper.toEntity(libroDTO);
        libro.setAutor(autor);
        libro.setCategoria(categoria);

        Libro savedLibro = libroRepository.save(libro);
        return libroMapper.toDTO(savedLibro);
    }

    @Transactional
    public LibroDTO update(Long id, LibroDTO libroDTO) {
        Libro existingLibro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));

        if (libroDTO.getAutorId() != null && !libroDTO.getAutorId().equals(existingLibro.getAutor().getId())) {
            Autor autor = autorRepository.findById(libroDTO.getAutorId())
                    .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + libroDTO.getAutorId()));
            existingLibro.setAutor(autor);
        }

        if (libroDTO.getCategoriaId() != null &&
            (existingLibro.getCategoria() == null || !libroDTO.getCategoriaId().equals(existingLibro.getCategoria().getId()))) {
            Categoria categoria = categoriaRepository.findById(libroDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + libroDTO.getCategoriaId()));
            existingLibro.setCategoria(categoria);
        }

        existingLibro.setTitulo(libroDTO.getTitulo());
        existingLibro.setDescripcion(libroDTO.getDescripcion());
        existingLibro.setAnioPublicacion(libroDTO.getAnioPublicacion());
        existingLibro.setActivo(libroDTO.isActivo());

        Libro updatedLibro = libroRepository.save(existingLibro);
        return libroMapper.toDTO(updatedLibro);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con ID: " + id);
        }
        libroRepository.deleteById(id);
    }

    @Transactional
    public void deactivate(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
        libro.setActivo(false);
        libroRepository.save(libro);
    }
}

