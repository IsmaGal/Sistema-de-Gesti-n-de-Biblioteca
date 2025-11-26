package com.idat.evc3_Biblioteca.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Autores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AutorID")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(length = 70)
    private String nacionalidad;

    @Builder.Default
    private boolean activo=true;
}
