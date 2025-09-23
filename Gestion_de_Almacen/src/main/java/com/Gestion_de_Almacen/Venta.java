package com.Gestion_de_Almacen;

import java.sql.Date;

public class Venta {

    private int idVenta;
    private Date fecha;
    private Tenis tenis;


    public Venta(Date fecha, Tenis tenis) {
        this.fecha = fecha;
        this.tenis = tenis;
    }

    public void registrarVenta(Tenis x) {
        this.tenis= x;

    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tenis getTenis() {
        return tenis;
    }

    public void setTenis(Tenis tenis) {
        this.tenis = tenis;
    }


}
