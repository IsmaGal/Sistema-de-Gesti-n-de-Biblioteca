package com.idat.evc3_Biblioteca.Dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class AutorDTO {

    private Long id;

    private String nombre;

    private String apellidos;

    private String nacionalidad;

    @Builder.Default
    private boolean activo = true;
}

