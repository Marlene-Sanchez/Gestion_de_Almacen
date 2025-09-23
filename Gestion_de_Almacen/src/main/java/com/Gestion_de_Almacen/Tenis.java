package com.Gestion_de_Almacen;
import jakarta.persistence.*;
@Entity
@Table(name = "Tenis")
public class Tenis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String marca;
    String modelo;
    int talla;
    String color;
    double precio;
    int stock;

    public Tenis() {}
    public Tenis(String marca, String modelo, int talla, String color, double precio, int stock) {
        this.marca = marca;
        this.modelo = modelo;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
        this.stock = stock;
    }
    public void actualizarStock(int talla) {
        this.talla = talla;
    }

    public String mostrarInfo(){
        return (this.marca+" "+modelo+" Talla: "+talla+"\n");
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
