package com.idat.evc3_Biblioteca.Service;

import com.idat.evc3_Biblioteca.Dtos.CategoriaDTO;
import com.idat.evc3_Biblioteca.Entity.Categoria;
import com.idat.evc3_Biblioteca.Mapper.CategoriaMapper;
import com.idat.evc3_Biblioteca.Repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAllActive() {
        return categoriaRepository.findByActivoTrue()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findByNombre(String nombre) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(savedCategoria);
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));

        existingCategoria.setNombre(categoriaDTO.getNombre());
        existingCategoria.setDescripcion(categoriaDTO.getDescripcion());
        existingCategoria.setActivo(categoriaDTO.isActivo());

        Categoria updatedCategoria = categoriaRepository.save(existingCategoria);
        return categoriaMapper.toDTO(updatedCategoria);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    @Transactional
    public void deactivate(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        categoria.setActivo(false);
        categoriaRepository.save(categoria);
    }
}

