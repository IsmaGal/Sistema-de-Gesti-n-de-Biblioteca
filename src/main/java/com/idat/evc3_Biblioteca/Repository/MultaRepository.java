package com.idat.evc3_Biblioteca.Repository;

import com.idat.evc3_Biblioteca.Entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

    List<Multa> findByPrestamoId(Long prestamoId);

    List<Multa> findByPagado(boolean pagado);

    List<Multa> findByPrestamoUsuarioId(Long usuarioId);

    List<Multa> findByPrestamoUsuarioIdAndPagado(Long usuarioId, boolean pagado);
}

