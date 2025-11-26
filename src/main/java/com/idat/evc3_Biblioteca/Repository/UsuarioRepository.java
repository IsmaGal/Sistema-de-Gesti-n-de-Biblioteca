package com.idat.evc3_Biblioteca.Repository;

import com.idat.evc3_Biblioteca.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByActivoTrue();

    List<Usuario> findByActivoFalse();

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
}

