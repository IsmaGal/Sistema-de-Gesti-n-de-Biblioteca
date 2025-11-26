package com.idat.evc3_Biblioteca.Controller;

import com.idat.evc3_Biblioteca.Dtos.PrestamoDTO;
import com.idat.evc3_Biblioteca.Service.EjemplarService;
import com.idat.evc3_Biblioteca.Service.PrestamoService;
import com.idat.evc3_Biblioteca.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/prestamos")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final UsuarioService usuarioService;
    private final EjemplarService ejemplarService;

    @GetMapping
    public String listar(@RequestParam(required = false) String estado, Model model) {
        if (estado != null && !estado.isEmpty()) {
            if ("atrasados".equalsIgnoreCase(estado)) {
                model.addAttribute("prestamos", prestamoService.findPrestamosAtrasados());
            } else if ("activos".equalsIgnoreCase(estado)) {
                model.addAttribute("prestamos", prestamoService.findPrestamosActivos());
            } else {
                model.addAttribute("prestamos", prestamoService.findByEstado(estado));
            }
        } else {
            model.addAttribute("prestamos", prestamoService.findAll());
        }
        return "prestamos-listar";
    }

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("prestamo", new PrestamoDTO());
        model.addAttribute("usuarios", usuarioService.findAllActive());
        model.addAttribute("ejemplares", ejemplarService.findByEstado("Disponible"));
        return "prestamos-registrar";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute PrestamoDTO prestamoDTO, RedirectAttributes redirectAttributes) {
        try {
            // Establecer fechas por defecto
            if (prestamoDTO.getFechaPrestamo() == null) {
                prestamoDTO.setFechaPrestamo(LocalDateTime.now());
            }
            if (prestamoDTO.getFechaDevolucionEsperada() == null) {
                prestamoDTO.setFechaDevolucionEsperada(LocalDateTime.now().plusDays(14));
            }
            if (prestamoDTO.getEstado() == null || prestamoDTO.getEstado().isEmpty()) {
                prestamoDTO.setEstado("Activo");
            }

            prestamoService.save(prestamoDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Préstamo registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/prestamos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar préstamo: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/prestamos/registrar";
        }
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        try {
            PrestamoDTO prestamo = prestamoService.findById(id);
            model.addAttribute("prestamo", prestamo);
            return "prestamos-detalle";
        } catch (Exception e) {
            return "redirect:/prestamos";
        }
    }

    @GetMapping("/devolver/{id}")
    public String mostrarFormularioDevolucion(@PathVariable Long id, Model model) {
        try {
            PrestamoDTO prestamo = prestamoService.findById(id);
            model.addAttribute("prestamo", prestamo);
            return "prestamos-devolver";
        } catch (Exception e) {
            return "redirect:/prestamos";
        }
    }

    @PostMapping("/devolver/{id}")
    public String devolver(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            prestamoService.registrarDevolucion(id);
            redirectAttributes.addFlashAttribute("mensaje", "Devolución registrada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/prestamos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar devolución: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/prestamos/devolver/" + id;
        }
    }
}

