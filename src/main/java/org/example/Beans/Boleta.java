package org.example.Beans;

import java.sql.Date;
import java.time.LocalDate;

public class Boleta {

    private int numero_boleta;
    private Date fecha;

    public Boleta() {
    }

    public Boleta(int numero_boleta, Date fecha) {
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

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
