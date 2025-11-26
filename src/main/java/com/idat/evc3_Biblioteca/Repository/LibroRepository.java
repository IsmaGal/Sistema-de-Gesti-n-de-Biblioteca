package com.idat.evc3_Biblioteca.Repository;

import com.idat.evc3_Biblioteca.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByActivoTrue();

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByAutorId(Long autorId);

    List<Libro> findByCategoriaId(Long categoriaId);

    List<Libro> findByAnioPublicacion(Integer anioPublicacion);
}

