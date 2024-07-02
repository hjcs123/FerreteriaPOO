package org.example.Beans;

public class Inventario {

    private String codProducto;
    private String nombreProducto;
    private double precioU;

    public Inventario() {
    }

    public Inventario(String nombreProducto, String codProducto, double precioU) {
        this.nombreProducto = nombreProducto;
        this.codProducto = codProducto;
        this.precioU = precioU;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioU() {
        return precioU;
    }

    public void setPrecioU(double precioU) {
        this.precioU = precioU;
    }
}
