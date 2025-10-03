package com.Gestion_de_Almacen;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;

@Controller
public class PaginaController {
    private final VentaRepository ventaRepo;
    private final TenisRepository tenisRepo;
    private Gerente gerente = new Gerente("admin", "123");

    public PaginaController(VentaRepository ventaRepo, TenisRepository tenisRepo) {
        this.ventaRepo = ventaRepo;
        this.tenisRepo = tenisRepo;
    }
    @GetMapping("/producto")
    public String producto(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }
        return "producto";
    }

    @RequestMapping("/tenis")
    public class TenisController {

        @Autowired
        private TenisRepository tenisRepository;

        @GetMapping("/search")
        public String buscarTenis(Model model,HttpSession session) {
            if (session.getAttribute("usuario") == null) {
                return "redirect:/Login";
            }
            model.addAttribute("tenisList", tenisRepository.findAll());
            return "search";
        }

        @GetMapping("/detalle/{id}")
        public String detalleTenis(@PathVariable Long id, Model model) {
            Tenis tenis = tenisRepository.findById(Math.toIntExact(id))
                    .orElseThrow(() -> new RuntimeException("No se encontró el tenis"));
            model.addAttribute("tenis", tenis);
            return "Tenis";
        }
    }
    @GetMapping("/Login")
    public String mostrarLogin() {
        return "Login";
    }

    @GetMapping("/venta")
    public String venta(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }
        return "venta";
    }
    @GetMapping("/Dashboard")
    public String mostrarInicio(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/Login";
        }
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        String fechaFormateada = hoy.format(formato);
        model.addAttribute("fechaHoy", fechaFormateada);
        List<Venta> ventas = ventaRepo.findAll();

        int paresVendidosHoy = ventas.size();
        double ingresosHoy = ventas.stream()
                .mapToDouble(v -> v.getTenis().getPrecio())
                .sum();

        model.addAttribute("paresVendidosHoy", paresVendidosHoy);
        model.addAttribute("ingresosHoy", ventas.stream()
                                                            .mapToDouble(v -> v.getTenis().getPrecio())
                                                            .sum());
        model.addAttribute("ventas", ventas);

        return "dashboard";
    }

    @PostMapping("/procesarLogin")
    public String procesarLogin(@RequestParam String usuario,
                                @RequestParam String password,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        if (gerente.iniciarSesion(usuario, password)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/Dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/Login";
        }
    }
}
