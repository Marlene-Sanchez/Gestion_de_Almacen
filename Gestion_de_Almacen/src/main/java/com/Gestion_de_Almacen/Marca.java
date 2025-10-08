package com.Gestion_de_Almacen;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="Marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarca;
    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tenis> tenis;

    public String getMarca() {
        return nombre;
    }

    public void setMarca(String nombre) {
        this.nombre = nombre;
    }
    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public List<Tenis> getTenis() {
        return tenis;
    }

    public void setTenis(List<Tenis> tenis) {
        this.tenis = tenis;
    }
}
