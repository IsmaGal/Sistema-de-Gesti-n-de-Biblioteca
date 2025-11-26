package com.idat.evc3_Biblioteca.Service;

import com.idat.evc3_Biblioteca.Dtos.UsuarioDTO;
import com.idat.evc3_Biblioteca.Entity.Usuario;
import com.idat.evc3_Biblioteca.Mapper.UsuarioMapper;
import com.idat.evc3_Biblioteca.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAllActive() {
        return usuarioRepository.findByActivoTrue()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findByNombreOrApellido(String searchTerm) {
        return usuarioRepository.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(searchTerm, searchTerm)
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findByActivo(boolean activo) {
        if (activo) {
            return usuarioRepository.findByActivoTrue()
                    .stream()
                    .map(usuarioMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return usuarioRepository.findByActivoFalse()
                    .stream()
                    .map(usuarioMapper::toDTO)
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        // Verificar si el email ya existe
        usuarioRepository.findByEmail(usuarioDTO.getEmail()).ifPresent(usuario -> {
            throw new RuntimeException("Ya existe un usuario con el email: " + usuarioDTO.getEmail());
        });

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(savedUsuario);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Verificar si el email ya existe en otro usuario
        if (!existingUsuario.getEmail().equals(usuarioDTO.getEmail())) {
            usuarioRepository.findByEmail(usuarioDTO.getEmail()).ifPresent(usuario -> {
                throw new RuntimeException("Ya existe un usuario con el email: " + usuarioDTO.getEmail());
            });
        }

        existingUsuario.setNombre(usuarioDTO.getNombre());
        existingUsuario.setApellido(usuarioDTO.getApellido());
        existingUsuario.setEmail(usuarioDTO.getEmail());
        existingUsuario.setDireccion(usuarioDTO.getDireccion());
        existingUsuario.setActivo(usuarioDTO.isActivo());

        Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
        return usuarioMapper.toDTO(updatedUsuario);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public void deactivate(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }
}

