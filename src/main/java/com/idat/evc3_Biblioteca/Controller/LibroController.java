package com.idat.evc3_Biblioteca.Controller;

import com.idat.evc3_Biblioteca.Dtos.LibroDTO;
import com.idat.evc3_Biblioteca.Service.AutorService;
import com.idat.evc3_Biblioteca.Service.CategoriaService;
import com.idat.evc3_Biblioteca.Service.EjemplarService;
import com.idat.evc3_Biblioteca.Service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;
    private final AutorService autorService;
    private final CategoriaService categoriaService;
    private final EjemplarService ejemplarService;

    // RF8.6 Listar libros (todos o por autor)
    @GetMapping
    public String listar(@RequestParam(required = false) Long autorId,
                        @RequestParam(required = false) String titulo,
                        @RequestParam(required = false) String estado,
                        Model model) {

        if (autorId != null) {
            // RF8.6 Listar libros por autor seleccionado
            model.addAttribute("libros", libroService.findByAutorId(autorId));
        } else if (titulo != null && !titulo.isEmpty()) {
            // RF8.8 Buscar libros por título
            model.addAttribute("libros", libroService.findByTitulo(titulo));
        } else if (estado != null && !estado.isEmpty()) {
            // RF8.9 Filtrar libros por estado
            if ("activo".equalsIgnoreCase(estado)) {
                model.addAttribute("libros", libroService.findAllActive());
            } else {
                model.addAttribute("libros", libroService.findAll());
            }
        } else {
            model.addAttribute("libros", libroService.findAll());
        }

        // Agregar lista de autores para el filtro
        model.addAttribute("autores", autorService.findAllActive());
        return "libros-listar";
    }

    // RF8.5 Registrar libros asociados a un autor - Mostrar formulario
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("libro", new LibroDTO());
        model.addAttribute("autores", autorService.findAllActive());
        model.addAttribute("categorias", categoriaService.findAllActive());
        return "libros-registrar";
    }

    // RF8.5 Registrar libros asociados a un autor - Procesar formulario
    @PostMapping("/registrar")
    public String registrar(@ModelAttribute LibroDTO libroDTO, RedirectAttributes redirectAttributes) {
        try {
            libroService.save(libroDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Libro registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/libros";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar libro: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/libros/registrar";
        }
    }

    // RF8.10 Mostrar detalle de un libro con información de su autor
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        try {
            LibroDTO libro = libroService.findById(id);
            model.addAttribute("libro", libro);
            model.addAttribute("ejemplares", ejemplarService.findByLibroId(id));
            return "libros-detalle";
        } catch (Exception e) {
            return "redirect:/libros";
        }
    }

    // RF8.7 Editar datos de un libro - Mostrar formulario
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        try {
            LibroDTO libro = libroService.findById(id);
            model.addAttribute("libro", libro);
            model.addAttribute("autores", autorService.findAllActive());
            model.addAttribute("categorias", categoriaService.findAllActive());
            return "libros-editar";
        } catch (Exception e) {
            return "redirect:/libros";
        }
    }

    // RF8.7 Editar datos de un libro - Procesar formulario
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute LibroDTO libroDTO,
                        RedirectAttributes redirectAttributes) {
        try {
            libroService.update(id, libroDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Libro actualizado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/libros";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar libro: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/libros/editar/" + id;
        }
    }

    // Inactivar libro
    @PostMapping("/inactivar/{id}")
    public String inactivar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            libroService.deactivate(id);
            redirectAttributes.addFlashAttribute("mensaje", "Libro inactivado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/libros";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al inactivar libro: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/libros";
        }
    }
}

