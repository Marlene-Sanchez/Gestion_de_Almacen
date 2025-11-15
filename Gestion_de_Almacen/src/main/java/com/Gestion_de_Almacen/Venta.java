package com.Gestion_de_Almacen;

import jakarta.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "Ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    private Date fecha;

    @ManyToOne
    private Tenis tenis;

    public Venta() {}
    public Venta(Date fecha, Tenis tenis) {
        this.fecha = fecha;
        this.tenis = tenis;
    }

    public void registrarVenta(Tenis x) {
        this.tenis= x;

    }
    public Integer getId() { return idVenta; }
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
