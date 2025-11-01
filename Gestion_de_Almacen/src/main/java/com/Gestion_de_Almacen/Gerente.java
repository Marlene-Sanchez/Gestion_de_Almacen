package com.Gestion_de_Almacen;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Table(name = "Gerente")
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String contrasena;

    public Gerente() {}

    public Gerente(String usuario, String contrasena) {
        this.usuario = usuario;
        setContrasena(contrasena);
    }

    public void setContrasena(String contrasena) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.contrasena = encoder.encode(contrasena);
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public boolean validarContrasena(String contrasenaIngresada) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(contrasenaIngresada, this.contrasena);
    }
}
