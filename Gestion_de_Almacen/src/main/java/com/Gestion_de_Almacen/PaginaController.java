package com.Gestion_de_Almacen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/Producto")
    public String agregarProducto() {
        return "producto";
    }

    @GetMapping("/Login")
    public String mostrarLogin() {
        return "Login";
    }
    @GetMapping("/Dashboard")
    public String mostrarInicio() {
        return "dashboard";
    }
}
