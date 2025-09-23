package com.Gestion_de_Almacen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaginaController {

    @GetMapping("/Producto")
    public String agregarProducto() {
        return "producto";
    }
    @GetMapping("/Search")
    public String mostrarSearch() {
        return "Search";
    }
    @GetMapping("/Login")
    public String mostrarLogin() {
        return "Login";
    }
    @GetMapping("/Dashboard")
    public String mostrarInicio() {
        return "dashboard";
    }
    @PostMapping("/procesarLogin")
    public String procesarLogin(@RequestParam String usuario,
                                @RequestParam String password,
                                RedirectAttributes redirectAttributes) {

        if (usuario.equals("admin") && password.equals("1234")) {
            redirectAttributes.addFlashAttribute("nombreUsuario", usuario);
            return "redirect:/Dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/Login";
        }
    }

}
