package com.idat.evc3_Biblioteca.Controller;

import com.idat.evc3_Biblioteca.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LibroService libroService;
    private final AutorService autorService;
    private final UsuarioService usuarioService;
    private final PrestamoService prestamoService;

    @GetMapping("/")
    public String index(Model model) {
        // Agregar estadísticas al modelo para el dashboard
        model.addAttribute("totalLibros", libroService.findAll().size());
        model.addAttribute("totalAutores", autorService.findAll().size());
        model.addAttribute("totalUsuarios", usuarioService.findAll().size());
        model.addAttribute("prestamosActivos", prestamoService.findPrestamosActivos().size());

        return "index";
    }

    @GetMapping("/index")
    public String indexAlternative(Model model) {
        return index(model);
    }
}

