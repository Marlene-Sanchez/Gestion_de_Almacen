package com.Gestion_de_Almacen;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

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
    public String guardarVenta(@ModelAttribute Venta venta, Model model) {
        Tenis tenisSeleccionado = venta.getTenis();

        Tenis tenisBD = tenisRepository.findById(tenisSeleccionado.getId())
                .orElseThrow(() -> new RuntimeException("El tenis no existe"));
        if (tenisBD.getStock() <= 0) {
            model.addAttribute("errorStock", "No hay stock disponible para este tenis");
            model.addAttribute("venta", new Venta());
            model.addAttribute("tenisList", tenisRepository.findAll());
            return "venta";
        }

        tenisBD.setStock(tenisBD.getStock() - 1);
        tenisRepository.save(tenisBD);

        ventaRepository.save(venta);

        return "redirect:/Dashboard";
    }


}