package com.idat.evc3_Biblioteca.Controller;

import com.idat.evc3_Biblioteca.Dtos.UsuarioDTO;
import com.idat.evc3_Biblioteca.Service.MultaService;
import com.idat.evc3_Biblioteca.Service.PrestamoService;
import com.idat.evc3_Biblioteca.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PrestamoService prestamoService;
    private final MultaService multaService;

    @GetMapping
    public String listar(@RequestParam(required = false) String buscar,
                        @RequestParam(required = false) String estado,
                        Model model) {
        if (buscar != null && !buscar.isEmpty()) {
            model.addAttribute("usuarios", usuarioService.findByNombreOrApellido(buscar));
        } else if (estado != null && !estado.isEmpty()) {
            boolean activo = estado.equalsIgnoreCase("activo");
            model.addAttribute("usuarios", usuarioService.findByActivo(activo));
        } else {
            model.addAttribute("usuarios", usuarioService.findAll());
        }
        return "usuarios-listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "usuarios-registrar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute UsuarioDTO usuarioDTO, RedirectAttributes redirectAttributes) {
        try {
            if (usuarioDTO.getId() != null) {
                usuarioService.update(usuarioDTO.getId(), usuarioDTO);
                redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado exitosamente");
            } else {
                usuarioService.save(usuarioDTO);
                redirectAttributes.addFlashAttribute("mensaje", "Usuario registrado exitosamente");
            }
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/usuarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar usuario: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/usuarios/nuevo";
        }
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        try {
            UsuarioDTO usuario = usuarioService.findById(id);
            model.addAttribute("usuario", usuario);
            model.addAttribute("prestamosActivos", prestamoService.findByUsuarioIdAndDevuelto(id, false));
            model.addAttribute("historialPrestamos", prestamoService.findByUsuarioId(id));
            model.addAttribute("multas", multaService.findByUsuarioId(id));
            return "usuarios-detalle";
        } catch (Exception e) {
            return "redirect:/usuarios";
        }
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        try {
            UsuarioDTO usuario = usuarioService.findById(id);
            model.addAttribute("usuario", usuario);
            return "usuarios-editar";
        } catch (Exception e) {
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/{id}/inactivar")
    public String inactivar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.deactivate(id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario inactivado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/usuarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al inactivar usuario: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/usuarios";
        }
    }
}

