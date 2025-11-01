package com.Gestion_de_Almacen;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PaginaController {
    @Autowired
    private GerenteRepository gerenteRepo;

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private TenisRepository tenisRepo;

    @GetMapping("/Login")
    public String mostrarLogin() {
        return "Login";
    }
    @PostMapping("/procesarLogin")
    public String procesarLogin(@RequestParam String usuario,
                                @RequestParam String password,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        Gerente gerente = gerenteRepo.findByUsuario(usuario).orElse(null);

        if (gerente != null && gerente.validarContrasena( password)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/Dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/Login";
        }
    }

    @GetMapping("/Dashboard")
    public String mostrarDashboard(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }

        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        model.addAttribute("fechaHoy", hoy.format(formato));

        List<Venta> ventas = ventaRepo.findAll();

        model.addAttribute("paresVendidosHoy", ventas.size());
        model.addAttribute("ingresosHoy", ventas.stream().mapToDouble(v -> v.getTenis().getPrecio()).sum());
        model.addAttribute("ventas", ventas);

        return "dashboard";
    }
    @GetMapping("/cambiarContrasena")
    public String mostrarCambioContrasena(Model model) {
        model.addAttribute("mensaje", null);
        return "gerente";
    }

    @PostMapping("/actualizarContrasena")
    public String actualizarContrasena(@RequestParam String usuario,
                                       @RequestParam String actual,
                                       @RequestParam String nueva,
                                       Model model) {

        Gerente gerente = gerenteRepo.findByUsuario(usuario).orElse(null);
        if (gerente == null) {
            model.addAttribute("mensaje", "Usuario no encontrado");
            return "gerente";
        }

        if (!gerente.validarContrasena(actual)) {
            model.addAttribute("mensaje", "La contraseña actual es incorrecta");
            return "gerente";
        }

        gerente.setContrasena(nueva);
        gerenteRepo.save(gerente);
        model.addAttribute("mensaje", "Contraseña actualizada correctamente");
        return "gerente";
    }

}
