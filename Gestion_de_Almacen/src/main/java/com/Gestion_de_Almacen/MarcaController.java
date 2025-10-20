package com.Gestion_de_Almacen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("/nueva")
    public String nuevaMarca(Model model) {
        model.addAttribute("marca", new Marca());
        return "Marca";
    }

    @PostMapping("/guardar")
    public String guardarMarca(@ModelAttribute Marca marca) {
        marcaRepository.save(marca);
        return "redirect:/marca/nueva";
    }
}