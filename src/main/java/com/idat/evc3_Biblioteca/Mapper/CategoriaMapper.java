package com.idat.evc3_Biblioteca.Mapper;

import com.idat.evc3_Biblioteca.Dtos.CategoriaDTO;
import com.idat.evc3_Biblioteca.Entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {
    
    CategoriaDTO toDTO(Categoria categoria);
    
    @Mapping(target = "libros", ignore = true)
    Categoria toEntity(CategoriaDTO categoriaDTO);
}

