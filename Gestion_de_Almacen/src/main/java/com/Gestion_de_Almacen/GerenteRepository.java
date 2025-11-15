package com.Gestion_de_Almacen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GerenteRepository extends JpaRepository<Gerente, Integer> {
    Optional<Gerente> findByUsuario(String usuario);
}
