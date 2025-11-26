package com.idat.evc3_Biblioteca.Mapper;

import com.idat.evc3_Biblioteca.Dtos.PrestamoDTO;
import com.idat.evc3_Biblioteca.Entity.Prestamo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PrestamoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "usuario.nombre", target = "usuarioNombre")
    @Mapping(source = "ejemplar.id", target = "ejemplarId")
    @Mapping(source = "ejemplar.libro.titulo", target = "ejemplarLibroTitulo")
    PrestamoDTO toDTO(Prestamo prestamo);

    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(target = "usuario.nombre", ignore = true)
    @Mapping(target = "usuario.apellido", ignore = true)
    @Mapping(target = "usuario.email", ignore = true)
    @Mapping(target = "usuario.direccion", ignore = true)
    @Mapping(target = "usuario.fechaRegistro", ignore = true)
    @Mapping(target = "usuario.activo", ignore = true)
    @Mapping(source = "ejemplarId", target = "ejemplar.id")
    @Mapping(target = "ejemplar.libro", ignore = true)
    @Mapping(target = "ejemplar.estado", ignore = true)
    @Mapping(target = "ejemplar.ubicacion", ignore = true)
    Prestamo toEntity(PrestamoDTO prestamoDTO);
}

