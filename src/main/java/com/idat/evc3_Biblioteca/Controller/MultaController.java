package com.idat.evc3_Biblioteca.Controller;

import com.idat.evc3_Biblioteca.Dtos.MultaDTO;
import com.idat.evc3_Biblioteca.Service.MultaService;
import com.idat.evc3_Biblioteca.Service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/multas")
@RequiredArgsConstructor
public class MultaController {

    private final MultaService multaService;
    private final PrestamoService prestamoService;

    @GetMapping
    public String listar(@RequestParam(required = false) Boolean pagado, Model model) {
        if (pagado != null) {
            model.addAttribute("multas", multaService.findByPagado(pagado));
        } else {
            model.addAttribute("multas", multaService.findAll());
        }
        return "multas-listar";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        try {
            MultaDTO multa = multaService.findById(id);
            model.addAttribute("multa", multa);
            return "multas-detalle";
        } catch (Exception e) {
            return "redirect:/multas";
        }
    }

    @GetMapping("/pagar/{id}")
    public String mostrarFormularioPago(@PathVariable Long id, Model model) {
        try {
            MultaDTO multa = multaService.findById(id);
            model.addAttribute("multa", multa);
            return "multas-pagar";
        } catch (Exception e) {
            return "redirect:/multas";
        }
    }

    @PostMapping("/pagar/{id}")
    public String pagar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            multaService.marcarComoPagada(id);
            redirectAttributes.addFlashAttribute("mensaje", "Multa pagada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/multas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al pagar multa: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/multas/pagar/" + id;
        }
    }
}

