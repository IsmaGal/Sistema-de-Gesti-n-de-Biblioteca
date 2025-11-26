package com.idat.evc3_Biblioteca.Repository;

import com.idat.evc3_Biblioteca.Entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByUsuarioId(Long usuarioId);

    List<Prestamo> findByEjemplarId(Long ejemplarId);

    List<Prestamo> findByEstado(String estado);

    List<Prestamo> findByUsuarioIdAndEstado(Long usuarioId, String estado);

    List<Prestamo> findByFechaDevolucionEsperadaBefore(LocalDateTime fecha);

    List<Prestamo> findByFechaDevolucionRealIsNull();

    List<Prestamo> findByUsuarioIdAndFechaDevolucionRealIsNull(Long usuarioId);

    List<Prestamo> findByUsuarioIdAndFechaDevolucionRealIsNotNull(Long usuarioId);
}

