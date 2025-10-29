package com.Gestion_de_Almacen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReporteController {

    @GetMapping("/reporte")
    public String mostrarReporte(Model model) {
        model.addAttribute("fechaInicio", "01/09/2025");
        model.addAttribute("fechaFin", "07/09/2025");
        model.addAttribute("paresVendidos", 124);
        model.addAttribute("totalIngresos", "$6,820");

        Marca nike = new Marca();
        nike.setNombre("Nike");

        Marca adidas = new Marca();
        adidas.setNombre("Adidas");

        List<Tenis> productos = List.of(
                new Tenis(nike, "Air Max 270", 42, "Negro", 120.0, 10),
                new Tenis(adidas, "Ultraboost 23", 41, "Blanco", 150.0, 5)
        );

        model.addAttribute("productos", productos);

        return "reporte";
    }

    @GetMapping("/reporte/nuevo")
    public String nuevoReporte(Model model) {
        model.addAttribute("fechaInicio", "");
        model.addAttribute("fechaFin", "");
        model.addAttribute("paresVendidos", "");
        model.addAttribute("totalIngresos", "");
        model.addAttribute("productos", List.of());

        return "reporte";
    }
}
