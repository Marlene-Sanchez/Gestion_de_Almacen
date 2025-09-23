package com.Gestion_de_Almacen;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/Login")
    public String mostrarInicio() {
        return "inicio";
    }

    @GetMapping("/dashboard")
    public String mostrarProductos() {
        return "productos";
    }

}
