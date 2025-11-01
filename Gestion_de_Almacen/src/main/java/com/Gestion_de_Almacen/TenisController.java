package com.Gestion_de_Almacen;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tenis")
public class TenisController {
    @Autowired
    private MarcaRepository marcaRepository;
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

        return "Search";
    }

    @GetMapping("/detalle/{id}")
    public String detalleTenis(@PathVariable Integer id, Model model) {
        Tenis tenis = tenisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el tenis"));
        model.addAttribute("tenis", tenis);
        return "Tenis";
    }


    @GetMapping("/nuevo")
        public String producto(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }
        model.addAttribute("tenis", new Tenis());

        model.addAttribute("marcas", marcaRepository.findAll());
        return "producto";
    }

    @PostMapping("/guardar")
    public String guardarTenis(@ModelAttribute Tenis tenis, RedirectAttributes redirectAttributes) {
        Marca marca = marcaRepository.findById(tenis.getMarca().getIdMarca())
                .orElseThrow(() -> new IllegalArgumentException("Marca no encontrada"));

        tenis.setMarca(marca);
        tenis.setModelo(tenis.getModelo().toUpperCase());
        boolean existe = tenisRepository.existsByMarcaAndModeloAndTallaAndColor(
                marca, tenis.getModelo(), tenis.getTalla(), tenis.getColor()
        );

        if (existe) {
            redirectAttributes.addFlashAttribute("error",
                    "Ya existe un tenis con la misma marca, modelo, talla y color.");
            return "redirect:/tenis/nuevo";
        }
        tenisRepository.save(tenis);
        redirectAttributes.addFlashAttribute("mensaje", "Tenis agregado correctamente.");
        return "redirect:/tenis/lista";
    }
    @GetMapping("/lista")
    public String listarTenis(Model model) {
        model.addAttribute("tenisList", tenisRepository.findAll());
        return "lista_tenis";
    }
    @GetMapping("/editar/{id}")
    public String editarTenis(@PathVariable Integer id, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }

        Tenis tenis = tenisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el tenis con id: " + id));

        model.addAttribute("tenis", tenis);
        model.addAttribute("marcas", marcaRepository.findAll());
        return "producto_editar";
    }

    @PostMapping("/actualizar")
    public String actualizarTenis(@ModelAttribute Tenis tenisActualizado) {
        Tenis tenis = tenisRepository.findById(tenisActualizado.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró el tenis"));

        tenis.setModelo(tenisActualizado.getModelo().toUpperCase());
        tenis.setColor(tenisActualizado.getColor().toUpperCase());
        tenis.setTalla(tenisActualizado.getTalla());
        tenis.setPrecio(tenisActualizado.getPrecio());
        tenis.setStock(tenisActualizado.getStock());
        tenis.setMarca(tenisActualizado.getMarca());

        tenisRepository.save(tenis);
        return "redirect:/tenis/detalle/" + tenis.getId();
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTenis(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            tenisRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "El tenis se eliminó correctamente.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error",
                    "No se puede eliminar este tenis porque está asociado a una o más ventas.");
        }
        return "redirect:/tenis/search";
    }

}