package com.idat.evc3_Biblioteca.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Libros")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LibroID")
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "nvarchar(MAX)")
    private String descripcion;

    @Column(name = "AnioPublicacion")
    private Integer anioPublicacion;

    @ManyToOne
    @JoinColumn(name = "AutorID", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "CategoriaID")
    private Categoria categoria;

    @Builder.Default
    private boolean activo = true;
}

