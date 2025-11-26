package com.idat.evc3_Biblioteca.Mapper;

import com.idat.evc3_Biblioteca.Dtos.UsuarioDTO;
import com.idat.evc3_Biblioteca.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);
}

