package com.idat.evc3_Biblioteca.Service;

import com.idat.evc3_Biblioteca.Dtos.PrestamoDTO;
import com.idat.evc3_Biblioteca.Entity.Ejemplar;
import com.idat.evc3_Biblioteca.Entity.Prestamo;
import com.idat.evc3_Biblioteca.Entity.Usuario;
import com.idat.evc3_Biblioteca.Mapper.PrestamoMapper;
import com.idat.evc3_Biblioteca.Repository.EjemplarRepository;
import com.idat.evc3_Biblioteca.Repository.PrestamoRepository;
import com.idat.evc3_Biblioteca.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EjemplarRepository ejemplarRepository;
    private final PrestamoMapper prestamoMapper;

    @Transactional(readOnly = true)
    public List<PrestamoDTO> findAll() {
        return prestamoRepository.findAll()
                .stream()
                .map(prestamoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PrestamoDTO findById(Long id) {
        return prestamoRepository.findById(id)
                .map(prestamoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<PrestamoDTO> findByUsuarioId(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(prestamoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PrestamoDTO> findByEjemplarId(Long ejemplarId) {
        return prestamoRepository.findByEjemplarId(ejemplarId)
                .stream()
                .map(prestamoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PrestamoDTO> findByEstado(String estado) {
        return prestamoRepository.findByEstado(estado)
                .stream()
                .map(prestamoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PrestamoDTO> findByUsuarioIdAndEstado(Long usuarioId, String estado) {
        return prestamoRepository.findByUsuarioIdAndEstado(usuarioId, estado)
                .stream()
                .map(prestamoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PrestamoDTO> findPrestamosAtrasados() {
        return prestamoRepository.findByFechaDevolucionEsperadaBefore(LocalDateTime.now())
                .stream()
                .filter(prestamo -> prestamo.getFechaDevolucionReal() == null)
                .map(prestamoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PrestamoDTO> findPrestamosActivos() {
        return prestamoRepository.findByFechaDevolucionRealIsNull()
                .stream()
                .map(prestamoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PrestamoDTO> findByUsuarioIdAndDevuelto(Long usuarioId, boolean devuelto) {
        List<Prestamo> prestamos;
        if (devuelto) {
            prestamos = prestamoRepository.findByUsuarioIdAndFechaDevolucionRealIsNotNull(usuarioId);
        } else {
            prestamos = prestamoRepository.findByUsuarioIdAndFechaDevolucionRealIsNull(usuarioId);
        }
        return prestamos.stream()
                .map(prestamoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PrestamoDTO save(PrestamoDTO prestamoDTO) {
        Usuario usuario = usuarioRepository.findById(prestamoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + prestamoDTO.getUsuarioId()));

        Ejemplar ejemplar = ejemplarRepository.findById(prestamoDTO.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con ID: " + prestamoDTO.getEjemplarId()));

        Prestamo prestamo = prestamoMapper.toEntity(prestamoDTO);
        prestamo.setUsuario(usuario);
        prestamo.setEjemplar(ejemplar);

        Prestamo savedPrestamo = prestamoRepository.save(prestamo);
        return prestamoMapper.toDTO(savedPrestamo);
    }

    @Transactional
    public PrestamoDTO update(Long id, PrestamoDTO prestamoDTO) {
        Prestamo existingPrestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));

        if (prestamoDTO.getUsuarioId() != null && !prestamoDTO.getUsuarioId().equals(existingPrestamo.getUsuario().getId())) {
            Usuario usuario = usuarioRepository.findById(prestamoDTO.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + prestamoDTO.getUsuarioId()));
            existingPrestamo.setUsuario(usuario);
        }

        if (prestamoDTO.getEjemplarId() != null && !prestamoDTO.getEjemplarId().equals(existingPrestamo.getEjemplar().getId())) {
            Ejemplar ejemplar = ejemplarRepository.findById(prestamoDTO.getEjemplarId())
                    .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con ID: " + prestamoDTO.getEjemplarId()));
            existingPrestamo.setEjemplar(ejemplar);
        }

        existingPrestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        existingPrestamo.setFechaDevolucionEsperada(prestamoDTO.getFechaDevolucionEsperada());
        existingPrestamo.setFechaDevolucionReal(prestamoDTO.getFechaDevolucionReal());
        existingPrestamo.setEstado(prestamoDTO.getEstado());

        Prestamo updatedPrestamo = prestamoRepository.save(existingPrestamo);
        return prestamoMapper.toDTO(updatedPrestamo);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new RuntimeException("Préstamo no encontrado con ID: " + id);
        }
        prestamoRepository.deleteById(id);
    }

    @Transactional
    public PrestamoDTO registrarDevolucion(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));

        prestamo.setFechaDevolucionReal(LocalDateTime.now());
        prestamo.setEstado("Finalizado");

        Prestamo updatedPrestamo = prestamoRepository.save(prestamo);
        return prestamoMapper.toDTO(updatedPrestamo);
    }
}

