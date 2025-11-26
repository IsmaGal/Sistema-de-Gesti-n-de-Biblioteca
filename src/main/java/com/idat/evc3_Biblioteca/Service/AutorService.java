package com.idat.evc3_Biblioteca.Service;

import com.idat.evc3_Biblioteca.Dtos.AutorDTO;
import com.idat.evc3_Biblioteca.Entity.Autor;
import com.idat.evc3_Biblioteca.Mapper.AutorMapper;
import com.idat.evc3_Biblioteca.Repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;

    @Transactional(readOnly = true)
    public List<AutorDTO> findAll() {
        return autorRepository.findAll()
                .stream()
                .map(autorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AutorDTO> findAllActive() {
        return autorRepository.findByActivoTrue()
                .stream()
                .map(autorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AutorDTO findById(Long id) {
        return autorRepository.findById(id)
                .map(autorMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<AutorDTO> findByNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(autorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AutorDTO> findByNacionalidad(String nacionalidad) {
        return autorRepository.findByNacionalidad(nacionalidad)
                .stream()
                .map(autorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AutorDTO save(AutorDTO autorDTO) {
        Autor autor = autorMapper.toEntity(autorDTO);
        Autor savedAutor = autorRepository.save(autor);
        return autorMapper.toDTO(savedAutor);
    }

    @Transactional
    public AutorDTO update(Long id, AutorDTO autorDTO) {
        Autor existingAutor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));

        existingAutor.setNombre(autorDTO.getNombre());
        existingAutor.setApellidos(autorDTO.getApellidos());
        existingAutor.setNacionalidad(autorDTO.getNacionalidad());
        existingAutor.setActivo(autorDTO.isActivo());

        Autor updatedAutor = autorRepository.save(existingAutor);
        return autorMapper.toDTO(updatedAutor);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor no encontrado con ID: " + id);
        }
        autorRepository.deleteById(id);
    }

    @Transactional
    public void deactivate(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));
        autor.setActivo(false);
        autorRepository.save(autor);
    }
}

