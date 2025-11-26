package com.idat.evc3_Biblioteca.Controller;

import com.idat.evc3_Biblioteca.Dtos.AutorDTO;
import com.idat.evc3_Biblioteca.Service.AutorService;
import com.idat.evc3_Biblioteca.Service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;
    private final LibroService libroService;

    // RF8.2 Listar autores registrados
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autores", autorService.findAll());
        return "autores-listar";
    }

    // RF8.1 Registrar autores - Mostrar formulario
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("autor", new AutorDTO());
        return "autores-registrar";
    }

    // RF8.1 Registrar autores - Procesar formulario
    @PostMapping("/registrar")
    public String registrar(@ModelAttribute AutorDTO autorDTO, RedirectAttributes redirectAttributes) {
        try {
            autorService.save(autorDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Autor registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/autores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar autor: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/autores/registrar";
        }
    }

    // RF8.10 Mostrar detalle de un autor con información de sus libros
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        try {
            AutorDTO autor = autorService.findById(id);
            model.addAttribute("autor", autor);
            model.addAttribute("libros", libroService.findByAutorId(id));
            return "autores-detalle";
        } catch (Exception e) {
            return "redirect:/autores";
        }
    }

    // RF8.3 Editar datos de autores - Mostrar formulario
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        try {
            AutorDTO autor = autorService.findById(id);
            model.addAttribute("autor", autor);
            return "autores-editar";
        } catch (Exception e) {
            return "redirect:/autores";
        }
    }

    // RF8.3 Editar datos de autores - Procesar formulario
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute AutorDTO autorDTO,
                        RedirectAttributes redirectAttributes) {
        try {
            autorService.update(id, autorDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Autor actualizado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/autores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar autor: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/autores/editar/" + id;
        }
    }

    // RF8.4 Inactivar autores (eliminación lógica) - Mostrar confirmación
    @GetMapping("/inactivar/{id}")
    public String mostrarConfirmacionInactivar(@PathVariable Long id, Model model) {
        try {
            AutorDTO autor = autorService.findById(id);
            model.addAttribute("autor", autor);
            return "autores-inactivar";
        } catch (Exception e) {
            return "redirect:/autores";
        }
    }

    // RF8.4 Inactivar autores (eliminación lógica) - Procesar
    @PostMapping("/inactivar/{id}")
    public String inactivar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            autorService.deactivate(id);
            redirectAttributes.addFlashAttribute("mensaje", "Autor inactivado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/autores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al inactivar autor: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/autores";
        }
    }
}
