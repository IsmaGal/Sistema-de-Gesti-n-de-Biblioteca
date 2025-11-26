package com.idat.evc3_Biblioteca.Repository;

import com.idat.evc3_Biblioteca.Entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByActivoTrue();

    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    List<Autor> findByNacionalidad(String nacionalidad);
}

