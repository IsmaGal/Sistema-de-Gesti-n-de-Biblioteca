package com.idat.evc3_Biblioteca.Service;

import com.idat.evc3_Biblioteca.Dtos.EjemplarDTO;
import com.idat.evc3_Biblioteca.Entity.Ejemplar;
import com.idat.evc3_Biblioteca.Entity.Libro;
import com.idat.evc3_Biblioteca.Mapper.EjemplarMapper;
import com.idat.evc3_Biblioteca.Repository.EjemplarRepository;
import com.idat.evc3_Biblioteca.Repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;
    private final LibroRepository libroRepository;
    private final EjemplarMapper ejemplarMapper;

    @Transactional(readOnly = true)
    public List<EjemplarDTO> findAll() {
        return ejemplarRepository.findAll()
                .stream()
                .map(ejemplarMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EjemplarDTO findById(Long id) {
        return ejemplarRepository.findById(id)
                .map(ejemplarMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<EjemplarDTO> findByLibroId(Long libroId) {
        return ejemplarRepository.findByLibroId(libroId)
                .stream()
                .map(ejemplarMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EjemplarDTO> findByEstado(String estado) {
        return ejemplarRepository.findByEstado(estado)
                .stream()
                .map(ejemplarMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EjemplarDTO> findByUbicacion(String ubicacion) {
        return ejemplarRepository.findByUbicacion(ubicacion)
                .stream()
                .map(ejemplarMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EjemplarDTO> findByLibroIdAndEstado(Long libroId, String estado) {
        return ejemplarRepository.findByLibroIdAndEstado(libroId, estado)
                .stream()
                .map(ejemplarMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EjemplarDTO save(EjemplarDTO ejemplarDTO) {
        Libro libro = libroRepository.findById(ejemplarDTO.getLibroId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + ejemplarDTO.getLibroId()));

        Ejemplar ejemplar = ejemplarMapper.toEntity(ejemplarDTO);
        ejemplar.setLibro(libro);

        Ejemplar savedEjemplar = ejemplarRepository.save(ejemplar);
        return ejemplarMapper.toDTO(savedEjemplar);
    }

    @Transactional
    public EjemplarDTO update(Long id, EjemplarDTO ejemplarDTO) {
        Ejemplar existingEjemplar = ejemplarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con ID: " + id));

        if (ejemplarDTO.getLibroId() != null && !ejemplarDTO.getLibroId().equals(existingEjemplar.getLibro().getId())) {
            Libro libro = libroRepository.findById(ejemplarDTO.getLibroId())
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + ejemplarDTO.getLibroId()));
            existingEjemplar.setLibro(libro);
        }

        existingEjemplar.setEstado(ejemplarDTO.getEstado());
        existingEjemplar.setUbicacion(ejemplarDTO.getUbicacion());

        Ejemplar updatedEjemplar = ejemplarRepository.save(existingEjemplar);
        return ejemplarMapper.toDTO(updatedEjemplar);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!ejemplarRepository.existsById(id)) {
            throw new RuntimeException("Ejemplar no encontrado con ID: " + id);
        }
        ejemplarRepository.deleteById(id);
    }
}

