package com.idat.evc3_Biblioteca.Dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class EjemplarDTO {

    private Long id;

    private Long libroId;

    private String libroTitulo;

    @Builder.Default
    private String estado = "Disponible";

    private String ubicacion;
}

