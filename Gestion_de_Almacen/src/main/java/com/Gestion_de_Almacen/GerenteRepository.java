package com.Gestion_de_Almacen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    Gerente findByUsuario(String usuario);
}