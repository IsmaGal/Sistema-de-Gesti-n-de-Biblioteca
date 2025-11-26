package com.idat.evc3_Biblioteca.Mapper;

import com.idat.evc3_Biblioteca.Dtos.LibroDTO;
import com.idat.evc3_Biblioteca.Entity.Libro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LibroMapper {

    @Mapping(source = "autor.id", target = "autorId")
    @Mapping(source = "autor.nombre", target = "autorNombre")
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    LibroDTO toDTO(Libro libro);

    @Mapping(source = "autorId", target = "autor.id")
    @Mapping(target = "autor.nombre", ignore = true)
    @Mapping(target = "autor.apellidos", ignore = true)
    @Mapping(target = "autor.nacionalidad", ignore = true)
    @Mapping(target = "autor.activo", ignore = true)
    @Mapping(source = "categoriaId", target = "categoria.id")
    @Mapping(target = "categoria.nombre", ignore = true)
    @Mapping(target = "categoria.descripcion", ignore = true)
    @Mapping(target = "categoria.activo", ignore = true)
    @Mapping(target = "categoria.libros", ignore = true)
    Libro toEntity(LibroDTO libroDTO);
}

