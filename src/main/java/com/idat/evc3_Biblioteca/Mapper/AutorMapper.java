package com.idat.evc3_Biblioteca.Mapper;

import com.idat.evc3_Biblioteca.Dtos.AutorDTO;
import com.idat.evc3_Biblioteca.Entity.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AutorMapper {

    AutorDTO toDTO(Autor autor);

    Autor toEntity(AutorDTO autorDTO);
}

