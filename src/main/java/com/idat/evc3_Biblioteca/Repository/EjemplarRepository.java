package com.idat.evc3_Biblioteca.Repository;

import com.idat.evc3_Biblioteca.Entity.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

    List<Ejemplar> findByLibroId(Long libroId);

    List<Ejemplar> findByEstado(String estado);

    List<Ejemplar> findByUbicacion(String ubicacion);

    List<Ejemplar> findByLibroIdAndEstado(Long libroId, String estado);
}

