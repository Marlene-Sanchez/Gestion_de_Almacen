package com.Gestion_de_Almacen;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tenis")
public class TenisController {

    @Autowired
    private TenisRepository tenisRepository;

    @GetMapping("/search")
    public String buscarTenis(@RequestParam(required = false) String marca,
                              @RequestParam(required = false) String modelo,
                              Model model,
                              HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }

        if ((marca == null || marca.isEmpty()) && (modelo == null || modelo.isEmpty())) {
            model.addAttribute("tenisList", tenisRepository.findAll());
        } else {
            model.addAttribute("tenisList", tenisRepository.findAll());
        }

        return "search";
    }

    @GetMapping("/detalle/{id}")
    public String detalleTenis(@PathVariable Integer id, Model model) {
        Tenis tenis = tenisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el tenis"));
        model.addAttribute("tenis", tenis);
        return "Tenis";
    }


    @GetMapping("/producto")
        public String producto(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }
        model.addAttribute("tenis", new Tenis());
        return "producto";
    }

    @PostMapping("/guardar")
    public String guardarTenis(@ModelAttribute Tenis tenis) {
        tenisRepository.save(tenis);
        return "redirect:/tenis/lista";
    }
    @GetMapping("/lista")
    public String listarTenis(Model model) {
        model.addAttribute("tenisList", tenisRepository.findAll());
        return "lista_tenis";
    }

}