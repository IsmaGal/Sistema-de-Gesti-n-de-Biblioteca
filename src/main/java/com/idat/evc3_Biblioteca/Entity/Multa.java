package com.idat.evc3_Biblioteca.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Multas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MultaID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PrestamoID", nullable = false)
    private Prestamo prestamo;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;

    @Builder.Default
    private boolean pagado = false;

    @Column(name = "FechaGeneracion")
    @Builder.Default
    private LocalDateTime fechaGeneracion = LocalDateTime.now();
}

