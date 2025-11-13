package com.Gestion_de_Almacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GestionDeAlmacenApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionDeAlmacenApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(MarcaRepository marcaRepo, TenisRepository tenisRepo, GerenteRepository gerenteRepo) {
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
            if (marcaRepo.count() == 0 && tenisRepo.count() == 0) {
                Marca nike = new Marca("NIKE");
                Marca adidas = new Marca("ADIDAS");
                Marca puma = new Marca("PUMA");
                Marca dc = new Marca("DC");
                Marca jordan = new Marca("JORDAN");
                Marca vans = new Marca("VANS");
                Marca converse = new Marca("CONVERSE");

                marcaRepo.saveAll(List.of(nike, adidas, puma, dc, jordan, vans, converse));

                List<Tenis> tenisList = new ArrayList<>();

                //ADIDAS CAMPUS
                float[] tallasCampusGris = {23.5F, 24, 24.5F, 25, 25.5F, 26};
                for (float talla : tallasCampusGris) {
                    tenisList.add(new Tenis(adidas, "CAMPUS", talla, "GRIS/NEGRO/BLANCO", 450.0, 6));
                }

                float[] tallasCampusNegro = {25.5F, 26, 26.5F, 27, 27.5F, 28};
                for (float talla : tallasCampusNegro) {
                    tenisList.add(new Tenis(adidas, "CAMPUS", talla, "NEGRO", 450.0, 6));
                }

                //ADIDAS CONCHA
                float[] tallasConcha = {25.5F, 26, 26.5F, 27, 27.5F, 28.5F};
                for (float talla : tallasConcha) {
                    tenisList.add(new Tenis(adidas, "CONCHA", talla, "BLANCO", 400.0, 6));
                }

                //ADIDAS SUPER-NOVA
                float[] tallasspn = {25, 26, 26.5F, 27, 27.5F, 28};
                for (float talla : tallasspn) {
                    tenisList.add(new Tenis(adidas, "SUPER-NOVA", talla, "BLANCO/NEGRO", 450.0, 6));
                }

                //VANS 1981
                float[] tallasvans1981 = {26.5F, 27, 27.5F, 29, 28.5F,29.5F};
                for (float talla : tallasvans1981) {
                    tenisList.add(new Tenis(vans, "1981", talla, "NEGRO/BLANCO", 400.0, 6));
                }

                //JORDAN RETRO 4
                float[] tallasjrretro = {25.5F, 26, 26.5F, 27, 27.5F, 28.5F};
                for (float talla : tallasjrretro) {
                    tenisList.add(new Tenis(jordan, "RETRO 4", talla, "BLANCO/GRIS/VERDE", 450.0, 6));
                }

                tenisRepo.saveAll(tenisList);

                System.out.println("Datos iniciales cargados correctamente en la base");
            } else {
                System.out.println("Ya existen datos, no se insertaron duplicados");
            }
        };
    }
}

