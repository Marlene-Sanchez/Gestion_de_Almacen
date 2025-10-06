package com.Gestion_de_Almacen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReporteController {

    @GetMapping("/reporte")
    public String mostrarReporte(Model model) {

        return "reporte";
    }
}