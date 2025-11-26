package com.idat.evc3_Biblioteca.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Ejemplares")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EjemplarID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LibroID", nullable = false)
    private Libro libro;

    @Column(nullable = false, length = 20)
    private String estado; // Disponible, Prestado, Reparación, Perdido

    @Column(length = 50)
    private String ubicacion; // Estante o pasillo
}

