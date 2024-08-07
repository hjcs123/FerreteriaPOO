package org.example.Beans;

public class Producto {

    private int id_producto;
    private String nombre_producto;
    private int cantidad;
    private double precio_pro;

    public Producto() {
    }

    public Producto(String nombre_producto, int cantidad, double precio_pro) {
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.precio_pro = precio_pro;
    }

    public Producto(int id_producto, String nombre_producto, int cantidad, double precio_pro) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.precio_pro = precio_pro;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public double getPrecio_pro() {
        return precio_pro;
    }

    public void setPrecio_pro(double precio_pro) {
        this.precio_pro = precio_pro;
    }

    @Override
    public String toString() {
        return this.nombre_producto;
    }

}
