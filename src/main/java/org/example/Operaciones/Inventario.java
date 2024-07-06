package org.example.Operaciones;

import org.example.CLASES.Producto;

import java.util.HashMap;

public class Inventario {
    private HashMap<String, Producto> productos;

    public Inventario() {
        this.productos = new HashMap<>();
    }

    public void agregarProducto(Producto producto) {
        productos.put(producto.getId(), producto);
    }

    public Producto buscarProductoPorId(String id) {
        return productos.get(id);
    }

    public void mostrarProductos() {
        for (Producto p : productos.values()) {
            System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio() + ", Categoría: " + p.getCategoria().getNombre());
        }
    }
}