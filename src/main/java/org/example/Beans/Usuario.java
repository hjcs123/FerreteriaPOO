package org.example.Beans;

public class Usuario {

    private int id_usuario;
    private String nombre_usuario;
    private int edad;
    private String password;


    public Usuario() {

    }

    public Usuario(String nombre_usuario, int edad, String password) {
        this.nombre_usuario = nombre_usuario;
        this.edad = edad;
        this.password = password;
    }

    public Usuario(int id_usuario, String nombre_usuario, int edad, String password) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.edad = edad;
        this.password = password;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
