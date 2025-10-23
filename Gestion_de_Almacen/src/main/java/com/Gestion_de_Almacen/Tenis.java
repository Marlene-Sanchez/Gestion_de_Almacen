package com.Gestion_de_Almacen;
import jakarta.persistence.*;
@Entity
@Table(name = "tenis")
public class Tenis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modelo;
    private float talla;
    private String color;
    private double precio;
    private int stock;
    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;

    public Tenis() {}

    public Tenis(Marca marca, String modelo, float talla, String color, double precio, int stock) {
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


    public Integer getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = (modelo != null) ? modelo.toUpperCase() : null;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
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

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Marca getMarca() {
        return marca ;
    }

    public Tenis(Marca marca) {
        this.marca = marca;
    }
    public String nombreMarca(){
        return this.marca.getNombre();
    }
}
