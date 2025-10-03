package com.Gestion_de_Almacen;
import jakarta.persistence.*;
@Entity
@Table(name = "tenis")
public class Tenis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private int talla;
    private String color;
    private double precio;
    private int stock;


    public Tenis() {}

    public Tenis(String marca, String modelo, int talla, String color, double precio, int stock) {
        this.marca = marca;
        this.modelo = modelo;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
        this.stock = stock;
    }
    public void actualizarStock(int cantidad) {
        this.stock = cantidad;
    }


    public String mostrarInfo() {
        return String.format("%s %s | Talla: %d | Color: %s | Precio: %.2f | Stock: %d",
                marca, modelo, talla, color, precio, stock);
    }


    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
