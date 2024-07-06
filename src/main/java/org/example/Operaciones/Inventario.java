package org.example.Operaciones;

import org.example.CLASES.Producto;

import java.util.HashMap;
import java.util.Stack;

public class Inventario {
    private HashMap<String, Producto> productos;
    private Stack<String> operaciones;
    private Stack<Producto> historialProductos;

    public Inventario() {
        this.productos = new HashMap<>();
        this.operaciones = new Stack<>();
        this.historialProductos = new Stack<>();
    }

    public void agregarProducto(Producto producto) {
        productos.put(producto.getId(), producto);
        operaciones.push("agregar");
        historialProductos.push(producto);
    }

    public Producto buscarProductoPorId(String id) {
        return productos.get(id);
    }

    public Producto eliminarProductoPorId(String id) {
        Producto eliminado = productos.remove(id);
        if (eliminado != null) {
            operaciones.push("eliminar");
            historialProductos.push(eliminado);
        }
        return eliminado;
    }

    public void deshacerUltimaOperacion() {
        if (!operaciones.isEmpty()) {
            String ultimaOperacion = operaciones.pop();
            Producto producto = historialProductos.pop();
            if (ultimaOperacion.equals("agregar")) {
                productos.remove(producto.getId());
                System.out.println("Deshacer: Producto eliminado - " + producto.getNombre());
            } else if (ultimaOperacion.equals("eliminar")) {
                productos.put(producto.getId(), producto);
                System.out.println("Deshacer: Producto agregado - " + producto.getNombre());
            }
        } else {
            System.out.println("No hay operaciones para deshacer.");
        }
    }

    public void mostrarProductos() {
        for (Producto p : productos.values()) {
            System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio() + ", Categoría: " + p.getCategoria().getNombre());
        }
    }
}