package com.idat.evc3_Biblioteca.Dtos;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class MultaDTO {

    private Long id;

    private Long prestamoId;

    private BigDecimal monto;

    @Builder.Default
    private boolean pagado = false;

    @Builder.Default
    private LocalDateTime fechaGeneracion = LocalDateTime.now();
}

