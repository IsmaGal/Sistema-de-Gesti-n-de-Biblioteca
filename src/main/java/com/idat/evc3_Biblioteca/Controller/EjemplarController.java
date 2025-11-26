package com.idat.evc3_Biblioteca.Controller;

import com.idat.evc3_Biblioteca.Dtos.EjemplarDTO;
import com.idat.evc3_Biblioteca.Service.EjemplarService;
import com.idat.evc3_Biblioteca.Service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ejemplares")
@RequiredArgsConstructor
public class EjemplarController {

    private final EjemplarService ejemplarService;
    private final LibroService libroService;

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(@RequestParam(required = false) Long libroId, Model model) {
        EjemplarDTO ejemplar = new EjemplarDTO();
        if (libroId != null) {
            ejemplar.setLibroId(libroId);
        }
        model.addAttribute("ejemplar", ejemplar);
        model.addAttribute("libros", libroService.findAllActive());
        return "ejemplares-registrar";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute EjemplarDTO ejemplarDTO, RedirectAttributes redirectAttributes) {
        try {
            ejemplarService.save(ejemplarDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Ejemplar registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");

            // Redirigir al detalle del libro
            if (ejemplarDTO.getLibroId() != null) {
                return "redirect:/libros/detalle/" + ejemplarDTO.getLibroId();
            }
            return "redirect:/libros";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar ejemplar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/ejemplares/registrar";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        try {
            EjemplarDTO ejemplar = ejemplarService.findById(id);
            model.addAttribute("ejemplar", ejemplar);
            model.addAttribute("libros", libroService.findAllActive());
            return "ejemplares-editar";
        } catch (Exception e) {
            return "redirect:/libros";
        }
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute EjemplarDTO ejemplarDTO,
                        RedirectAttributes redirectAttributes) {
        try {
            ejemplarService.update(id, ejemplarDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Ejemplar actualizado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");

            // Redirigir al detalle del libro
            if (ejemplarDTO.getLibroId() != null) {
                return "redirect:/libros/detalle/" + ejemplarDTO.getLibroId();
            }
            return "redirect:/libros";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar ejemplar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/ejemplares/editar/" + id;
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, @RequestParam(required = false) Long libroId,
                          RedirectAttributes redirectAttributes) {
        try {
            ejemplarService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Ejemplar eliminado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");

            if (libroId != null) {
                return "redirect:/libros/detalle/" + libroId;
            }
            return "redirect:/libros";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar ejemplar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/libros";
        }
    }
}

