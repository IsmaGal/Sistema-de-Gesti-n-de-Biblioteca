package com.idat.evc3_Biblioteca.Dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class CategoriaDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    @Builder.Default
    private boolean activo = true;
}

