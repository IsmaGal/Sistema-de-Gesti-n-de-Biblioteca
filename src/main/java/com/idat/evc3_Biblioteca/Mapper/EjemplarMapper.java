package com.idat.evc3_Biblioteca.Mapper;

import com.idat.evc3_Biblioteca.Dtos.EjemplarDTO;
import com.idat.evc3_Biblioteca.Entity.Ejemplar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EjemplarMapper {

    @Mapping(source = "libro.id", target = "libroId")
    @Mapping(source = "libro.titulo", target = "libroTitulo")
    EjemplarDTO toDTO(Ejemplar ejemplar);

    @Mapping(source = "libroId", target = "libro.id")
    @Mapping(target = "libro.titulo", ignore = true)
    @Mapping(target = "libro.descripcion", ignore = true)
    @Mapping(target = "libro.anioPublicacion", ignore = true)
    @Mapping(target = "libro.autor", ignore = true)
    @Mapping(target = "libro.categoria", ignore = true)
    @Mapping(target = "libro.activo", ignore = true)
    Ejemplar toEntity(EjemplarDTO ejemplarDTO);
}

