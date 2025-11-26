package com.idat.evc3_Biblioteca.Dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class LibroDTO {

    private Long id;

    private String titulo;

    private String descripcion;

    private Integer anioPublicacion;

    private Long autorId;

    private String autorNombre;

    private Long categoriaId;

    private String categoriaNombre;

    @Builder.Default
    private boolean activo = true;
}
