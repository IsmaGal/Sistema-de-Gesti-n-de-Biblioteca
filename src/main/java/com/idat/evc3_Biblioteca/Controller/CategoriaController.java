package com.idat.evc3_Biblioteca.Controller;

import com.idat.evc3_Biblioteca.Dtos.CategoriaDTO;
import com.idat.evc3_Biblioteca.Service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categorias-listar";
    }

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("categoria", new CategoriaDTO());
        return "categorias-registrar";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute CategoriaDTO categoriaDTO, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.save(categoriaDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría registrada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/categorias";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar categoría: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/categorias/registrar";
        }
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        try {
            CategoriaDTO categoria = categoriaService.findById(id);
            model.addAttribute("categoria", categoria);
            return "categorias-detalle";
        } catch (Exception e) {
            return "redirect:/categorias";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        try {
            CategoriaDTO categoria = categoriaService.findById(id);
            model.addAttribute("categoria", categoria);
            return "categorias-editar";
        } catch (Exception e) {
            return "redirect:/categorias";
        }
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute CategoriaDTO categoriaDTO,
                        RedirectAttributes redirectAttributes) {
        try {
            categoriaService.update(id, categoriaDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría actualizada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/categorias";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar categoría: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/categorias/editar/" + id;
        }
    }

    @PostMapping("/inactivar/{id}")
    public String inactivar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.deactivate(id);
            redirectAttributes.addFlashAttribute("mensaje", "Categoría inactivada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/categorias/listar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al inactivar categoría: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/categorias/listar";
        }
    }
}

