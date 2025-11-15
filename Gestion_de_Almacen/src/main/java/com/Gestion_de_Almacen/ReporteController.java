package com.Gestion_de_Almacen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReporteController {

    @Autowired
    private VentaRepository ventaRepo;

    @GetMapping("/reporte")
    public String mostrarFormularioOReporte(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
            Model model) {

        if (inicio != null && fin != null) {
            List<Venta> ventas = ventaRepo.findByFechaBetween(inicio, fin);

            int paresVendidos = ventas.size();
            double totalIngresos = ventas.stream()
                    .mapToDouble(v -> v.getTenis().getPrecio())
                    .sum();

            model.addAttribute("fechaInicio", inicio);
            model.addAttribute("fechaFin", fin);
            model.addAttribute("paresVendidos", paresVendidos);
            model.addAttribute("totalIngresos", totalIngresos);
            model.addAttribute("productos", ventas.stream().map(Venta::getTenis).toList());
        } else {
            model.addAttribute("productos", List.of()); // vacía la lista si aún no hay fechas
        }

        return "reporte";
    }
}

