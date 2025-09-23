package com.Gestion_de_Almacen;

import java.util.Date;
import java.util.List;

public class ReporteVentas {
    Date fechaIncio;
    Date fechaFin;
    double totalIngresos;
    int paresVendidos;
    List <Venta> ventas;


    public void generarReporteDiario(Date fecha ){

    }

    public void generarReporteSemanal(int mes, int año){

    }
    public void generarReporteMensual(int mes){

    }
    public String mostrarResumen(){
        int v=0, i=0;
        for(Venta x :ventas){
            v++;
            i+= (int) x.getTenis().precio;
        }
        return "Total de Ventas: "+v +"Total de Ingresos: "+ i;
    }
}
