package com.Gestion_de_Almacen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class PaginaController {
    private final VentaRepository ventaRepo;
    private final TenisRepository tenisRepo;
    private Gerente gerente = new Gerente("admin", "1234");

    public PaginaController(VentaRepository ventaRepo, TenisRepository tenisRepo) {
        this.ventaRepo = ventaRepo;
        this.tenisRepo = tenisRepo;
    }
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
    public String mostrarInicio(Model model) {

        List<Venta> ventas = ventaRepo.findAll();

        int paresVendidosHoy = ventas.size();
        double ingresosHoy = ventas.stream()
                .mapToDouble(v -> v.getTenis().getPrecio())
                .sum();

        model.addAttribute("paresVendidosHoy", paresVendidosHoy);
        model.addAttribute("ingresosHoy", ingresosHoy);
        model.addAttribute("ventas", ventas);

        return "dashboard"; // tu HTML dashboard.html
    }

    @PostMapping("/procesarLogin")
    public String procesarLogin(@RequestParam String usuario,
                                @RequestParam String password,
                                RedirectAttributes redirectAttributes) {

        if (gerente.iniciarSesion(usuario, password)) {
            redirectAttributes.addFlashAttribute("nombreUsuario", usuario);
            return "redirect:/Dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/Login";
        }
    }


}
