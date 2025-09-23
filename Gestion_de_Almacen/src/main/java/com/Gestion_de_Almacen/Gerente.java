package com.Gestion_de_Almacen;

public class Gerente {
    private String usuario;
    private String contraseña;

    public Gerente(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    public boolean iniciarSesion(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contraseña);
    }
    public void registrarVenta(Venta x){

    }
    public void actualizarProducto(Tenis x){

    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }
}
