package org.example.Beans;

import java.sql.Date;
import java.time.LocalDate;

public class Boleta {

    private int numero_boleta;
    private LocalDate fecha;

    public Boleta() {
    }

    public Boleta(int numero_boleta, LocalDate fecha) {
        this.numero_boleta = numero_boleta;
        this.fecha = fecha;

    }

    public int getNumero_boleta() {
        return numero_boleta;
    }

    public void setNumero_boleta(int numero_boleta) {
        this.numero_boleta = numero_boleta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
