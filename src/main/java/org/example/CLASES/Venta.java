package org.example.CLASES;

import java.util.Date;
import java.util.List;

public class Venta {
    private String id;
    private Date fecha;
    private List<Producto> productos;

    public Venta() {
    }

    public Venta(String id, Date fecha, List<Producto> productos) {
        this.id = id;
        this.fecha = fecha;
        this.productos = productos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
