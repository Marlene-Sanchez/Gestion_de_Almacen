package com.Gestion_de_Almacen;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private TenisRepository tenisRepository;

    @GetMapping("/nueva")
    public String nuevaVenta(Model model, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }

        model.addAttribute("venta", new Venta());
        model.addAttribute("tenisList", tenisRepository.findAll() );
        return "venta";
    }


    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Venta venta, RedirectAttributes redirectAttrs) {
        Tenis tenisSeleccionado = venta.getTenis();

        Tenis tenisBD = tenisRepository.findById(tenisSeleccionado.getId())
                .orElseThrow(() -> new RuntimeException("El tenis no existe"));

        if (tenisBD.getStock() <= 0) {
            redirectAttrs.addFlashAttribute("error",
                    "No hay stock disponible para este tenis.");
            return "redirect:/venta/nueva";

        }

        tenisBD.setStock(tenisBD.getStock() - 1);
        tenisRepository.save(tenisBD);

        ventaRepository.save(venta);

        redirectAttrs.addFlashAttribute("success",
                "Venta registrada correctamente.");
        return "redirect:/Dashboard";
    }
    @GetMapping("/crear/{idTenis}")
    public String crearVentaDirecta(@PathVariable Integer idTenis, RedirectAttributes redirectAttrs) {

        Tenis tenis = tenisRepository.findById(idTenis)
                .orElseThrow(() -> new RuntimeException("No se encontró el tenis con id: " + idTenis));

        if (tenis.getStock() <= 0) {
            redirectAttrs.addFlashAttribute("error", "No hay stock disponible para este tenis.");
            return "redirect:/tenis/detalle/" + idTenis;
        }

        tenis.setStock(tenis.getStock() - 1);
        tenisRepository.save(tenis);

        Venta nuevaVenta = new Venta();
        nuevaVenta.setTenis(tenis);
        nuevaVenta.setFecha(java.sql.Date.valueOf(java.time.LocalDate.now()));

        ventaRepository.save(nuevaVenta);
        redirectAttrs.addFlashAttribute("success", "Venta registrada correctamente.");
        return "redirect:/Dashboard";
    }



}