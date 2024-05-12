package org.example.Beans;

public class Proveedor {
    private String nombre_proveedor;
    private int id_proveedor;
    private int ruc;
    private String email;
    private int telefono;
    private String direccion;

    public Proveedor() {
    }

    public Proveedor(String nombre_proveedor, String direccion, int telefono, String email, int ruc, int id_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.ruc = ruc;
        this.id_proveedor = id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return this.nombre_proveedor;
    }
}
