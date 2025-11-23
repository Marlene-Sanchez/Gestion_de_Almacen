package com.Gestion_de_Almacen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

            // Agrupar ventas por Tenis (modelo, talla, marca, precio)
            Map<String, VentaAgrupada> agrupadas = new HashMap<>();

            for (Venta v : ventas) {
                Tenis t = v.getTenis();
                String clave = t.getMarca().getNombre() + "_" + t.getModelo() + "_" + t.getTalla() + "_" + t.getPrecio();

                agrupadas.computeIfAbsent(clave, k -> new VentaAgrupada(t, 0));
                agrupadas.get(clave).incrementarCantidad();
            }

            model.addAttribute("fechaInicio", inicio);
            model.addAttribute("fechaFin", fin);
            model.addAttribute("paresVendidos", paresVendidos);
            model.addAttribute("totalIngresos", totalIngresos);
            model.addAttribute("ventasAgrupadas", agrupadas.values());
        } else {
            model.addAttribute("ventasAgrupadas", List.of());
        }

        return "reporte";
    }
}

