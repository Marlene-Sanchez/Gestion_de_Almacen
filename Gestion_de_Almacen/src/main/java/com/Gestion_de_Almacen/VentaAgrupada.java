package com.Gestion_de_Almacen;

public class VentaAgrupada {
    private Tenis tenis;
    private int cantidad;

    public VentaAgrupada(Tenis tenis, int cantidad) {
        this.tenis = tenis;
        this.cantidad = cantidad;
    }

    public void incrementarCantidad() {
        this.cantidad++;
    }

    public Tenis getTenis() {
        return tenis;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return tenis.getPrecio() * cantidad;
    }
}
