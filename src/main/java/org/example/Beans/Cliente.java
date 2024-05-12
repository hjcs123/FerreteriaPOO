package org.example.Beans;

public class Cliente {

    private String id_cliente;
    private String nombre_cliente;
    private String direccion;
    private String dni;


    public Cliente() {
    }

    public Cliente(String id_cliente, String nombre_cliente, String direccion, String dni) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.direccion = direccion;
        this.dni = dni;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return this.nombre_cliente;
    }
}

