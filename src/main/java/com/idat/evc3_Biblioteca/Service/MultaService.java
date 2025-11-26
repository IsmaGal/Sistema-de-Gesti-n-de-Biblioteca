package com.idat.evc3_Biblioteca.Service;

import com.idat.evc3_Biblioteca.Dtos.MultaDTO;
import com.idat.evc3_Biblioteca.Entity.Multa;
import com.idat.evc3_Biblioteca.Entity.Prestamo;
import com.idat.evc3_Biblioteca.Mapper.MultaMapper;
import com.idat.evc3_Biblioteca.Repository.MultaRepository;
import com.idat.evc3_Biblioteca.Repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MultaService {

    private final MultaRepository multaRepository;
    private final PrestamoRepository prestamoRepository;
    private final MultaMapper multaMapper;

    @Transactional(readOnly = true)
    public List<MultaDTO> findAll() {
        return multaRepository.findAll()
                .stream()
                .map(multaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MultaDTO findById(Long id) {
        return multaRepository.findById(id)
                .map(multaMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Multa no encontrada con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<MultaDTO> findByPrestamoId(Long prestamoId) {
        return multaRepository.findByPrestamoId(prestamoId)
                .stream()
                .map(multaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MultaDTO> findByPagado(boolean pagado) {
        return multaRepository.findByPagado(pagado)
                .stream()
                .map(multaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MultaDTO> findByUsuarioId(Long usuarioId) {
        return multaRepository.findByPrestamoUsuarioId(usuarioId)
                .stream()
                .map(multaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MultaDTO> findByUsuarioIdAndPagado(Long usuarioId, boolean pagado) {
        return multaRepository.findByPrestamoUsuarioIdAndPagado(usuarioId, pagado)
                .stream()
                .map(multaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public MultaDTO save(MultaDTO multaDTO) {
        Prestamo prestamo = prestamoRepository.findById(multaDTO.getPrestamoId())
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + multaDTO.getPrestamoId()));

        Multa multa = multaMapper.toEntity(multaDTO);
        multa.setPrestamo(prestamo);

        Multa savedMulta = multaRepository.save(multa);
        return multaMapper.toDTO(savedMulta);
    }

    @Transactional
    public MultaDTO update(Long id, MultaDTO multaDTO) {
        Multa existingMulta = multaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa no encontrada con ID: " + id));

        if (multaDTO.getPrestamoId() != null && !multaDTO.getPrestamoId().equals(existingMulta.getPrestamo().getId())) {
            Prestamo prestamo = prestamoRepository.findById(multaDTO.getPrestamoId())
                    .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + multaDTO.getPrestamoId()));
            existingMulta.setPrestamo(prestamo);
        }

        existingMulta.setMonto(multaDTO.getMonto());
        existingMulta.setPagado(multaDTO.isPagado());
        existingMulta.setFechaGeneracion(multaDTO.getFechaGeneracion());

        Multa updatedMulta = multaRepository.save(existingMulta);
        return multaMapper.toDTO(updatedMulta);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!multaRepository.existsById(id)) {
            throw new RuntimeException("Multa no encontrada con ID: " + id);
        }
        multaRepository.deleteById(id);
    }

    @Transactional
    public void marcarComoPagada(Long id) {
        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa no encontrada con ID: " + id));
        multa.setPagado(true);
        multaRepository.save(multa);
    }
}

