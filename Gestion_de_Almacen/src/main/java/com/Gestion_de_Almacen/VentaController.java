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
    private TenisRepository tenisRepository;

    @GetMapping("/nueva")
    public String nuevaMarca(Model model, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }

        model.addAttribute("venta", new Venta());
        model.addAttribute("tenisList", tenisRepository.findAll());
        return "venta";
    }


    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Venta venta) {

        Tenis tenis = tenisRepository.findById(venta.getTenis().getId())
                .orElseThrow(() -> new IllegalArgumentException("Tenis no válido"));

        venta.setTenis(tenis);

        ventaRepository.save(venta);
        return "redirect:/Dashboard";
    }


}