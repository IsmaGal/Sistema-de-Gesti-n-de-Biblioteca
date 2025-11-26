package com.idat.evc3_Biblioteca.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Prestamos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PrestamoID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UsuarioID", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "EjemplarID", nullable = false)
    private Ejemplar ejemplar;

    @Column(name = "FechaPrestamo")
    @Builder.Default
    private LocalDateTime fechaPrestamo = LocalDateTime.now();

    @Column(name = "FechaDevolucionEsperada", nullable = false)
    private LocalDateTime fechaDevolucionEsperada;

    @Column(name = "FechaDevolucionReal")
    private LocalDateTime fechaDevolucionReal; // Null si aún no se devuelve

    @Column(length = 20)
    private String estado; // Activo, Finalizado, Atrasado
}

