package com.Gestion_de_Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenisRepository extends JpaRepository<Tenis, Integer> {
    boolean existsByMarcaAndModeloAndTallaAndColor(Marca marca, String modelo, float talla, String color);
}
