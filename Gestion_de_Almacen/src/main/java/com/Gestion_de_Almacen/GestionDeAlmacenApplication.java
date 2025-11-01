package com.Gestion_de_Almacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestionDeAlmacenApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionDeAlmacenApplication.class, args);
    }

    @Bean
    public CommandLineRunner crearUsuarioInicial(GerenteRepository gerenteRepo) {
        return args -> {
            if (gerenteRepo.findByUsuario("admin").isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String hash = encoder.encode("123"); // contraseña cifrada
                Gerente admin = new Gerente();
                admin.setUsuario("admin");
                admin.setContrasena("123"); // el setter ya la cifrará
                gerenteRepo.save(admin);
                System.out.println("✅ Usuario admin creado con contraseña '123'");
            }
        };
    }
}
