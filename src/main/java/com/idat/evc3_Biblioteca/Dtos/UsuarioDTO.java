package com.idat.evc3_Biblioteca.Dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String email;

    private String direccion;

    @Builder.Default
    private LocalDateTime fechaRegistro=LocalDateTime.now();

    @Builder.Default
    private boolean activo = true;
}

