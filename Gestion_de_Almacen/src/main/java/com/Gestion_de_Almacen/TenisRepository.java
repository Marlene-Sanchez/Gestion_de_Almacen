package com.Gestion_de_Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TenisRepository extends JpaRepository<Tenis, Integer> {
    boolean existsByMarcaAndModeloAndTallaAndColor(Marca marca, String modelo, float talla, String color);
    @Query("SELECT t FROM Tenis t WHERE UPPER(t.marca.nombre) LIKE CONCAT('%', UPPER(:marca), '%')")
    List<Tenis> findByMarca(@Param("marca") String marca);

    @Query("SELECT t FROM Tenis t WHERE UPPER(t.modelo) LIKE CONCAT('%', UPPER(:modelo), '%')")
    List<Tenis> findByModelo(@Param("modelo") String modelo);

    @Query("SELECT t FROM Tenis t WHERE UPPER(t.marca.nombre) LIKE CONCAT('%', UPPER(:marca), '%') " +
            "AND UPPER(t.modelo) LIKE CONCAT('%', UPPER(:modelo), '%')")
    List<Tenis> findByMarcaAndModelo(@Param("marca") String marca, @Param("modelo") String modelo);

    @Query("SELECT t FROM Tenis t WHERE t.talla = :talla")
    List<Tenis> findByTalla(@Param("talla") float talla);

    @Query("SELECT t FROM Tenis t WHERE " +
            "(:marca IS NULL OR UPPER(t.marca.nombre) LIKE CONCAT('%', UPPER(:marca), '%')) AND " +
            "(:modelo IS NULL OR UPPER(t.modelo) LIKE CONCAT('%', UPPER(:modelo), '%')) AND " +
            "(:talla IS NULL OR t.talla = :talla)")
    List<Tenis> buscarPorFiltros(@Param("marca") String marca,
                                 @Param("modelo") String modelo,
                                 @Param("talla") Float talla);

    @Query("SELECT DISTINCT t.modelo FROM Tenis t ORDER BY t.modelo ASC")
    List<String> findDistinctModelos();

}
