package com.idat.evc3_Biblioteca.Dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class PrestamoDTO {

    private Long id;

    private Long usuarioId;

    private String usuarioNombre;

    private Long ejemplarId;

    private String ejemplarLibroTitulo;

    @Builder.Default
    private LocalDateTime fechaPrestamo=LocalDateTime.now();

    private LocalDateTime fechaDevolucionEsperada;

    private LocalDateTime fechaDevolucionReal;

    @Builder.Default
    private String estado = "Activo";
}

