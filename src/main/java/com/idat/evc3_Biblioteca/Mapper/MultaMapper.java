package com.idat.evc3_Biblioteca.Mapper;

import com.idat.evc3_Biblioteca.Dtos.MultaDTO;
import com.idat.evc3_Biblioteca.Entity.Multa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MultaMapper {

    @Mapping(source = "prestamo.id", target = "prestamoId")
    MultaDTO toDTO(Multa multa);

    @Mapping(source = "prestamoId", target = "prestamo.id")
    @Mapping(target = "prestamo.usuario", ignore = true)
    @Mapping(target = "prestamo.ejemplar", ignore = true)
    @Mapping(target = "prestamo.fechaPrestamo", ignore = true)
    @Mapping(target = "prestamo.fechaDevolucionEsperada", ignore = true)
    @Mapping(target = "prestamo.fechaDevolucionReal", ignore = true)
    @Mapping(target = "prestamo.estado", ignore = true)
    Multa toEntity(MultaDTO multaDTO);
}

