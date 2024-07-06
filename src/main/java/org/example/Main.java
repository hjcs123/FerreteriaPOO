package org.example;

import org.example.CLASES.Categoria;
import org.example.CLASES.Producto;
import org.example.Operaciones.GestionCategorias;
import org.example.Operaciones.Inventario;
import org.example.Utils.Ordenamiento;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Categoria electronica = new Categoria("1", "Electrónica");
        Categoria muebles = new Categoria("2", "Muebles");

        Producto prod1 = new Producto("101", "Televisor", 500.0, electronica);
        Producto prod2 = new Producto("102", "Radio", 30.0, electronica);
        Producto prod3 = new Producto("103", "Laptop", 1000.0, electronica);
        Producto prod4 = new Producto("201", "Silla", 45.0, muebles);
        Producto prod5 = new Producto("202", "Mesa", 120.0, muebles);

        Inventario inventario = new Inventario();
        inventario.agregarProducto(prod1);
        inventario.agregarProducto(prod2);
        inventario.agregarProducto(prod3);
        inventario.agregarProducto(prod4);
        inventario.agregarProducto(prod5);

        System.out.println("Inventario de productos:");
        inventario.mostrarProductos();

        GestionCategorias gestionCategorias = new GestionCategorias();
        gestionCategorias.agregarCategoria(electronica);
        gestionCategorias.agregarCategoria(muebles);

        System.out.println("\nCategorías disponibles:");
        gestionCategorias.mostrarCategorias();

        // Prueba de búsqueda
        System.out.println("\nBuscando producto con ID '103':");
        Producto productoBuscado = inventario.buscarProductoPorId("103");
        if (productoBuscado != null) {
            System.out.println("Producto encontrado: " + productoBuscado.getNombre());
        } else {
            System.out.println("Producto no encontrado.");
        }

        System.out.println("\nBuscando categoría con ID '2':");
        Categoria categoriaBuscada = gestionCategorias.buscarCategoriaPorId("2");
        if (categoriaBuscada != null) {
            System.out.println("Categoría encontrada: " + categoriaBuscada.getNombre());
        } else {
            System.out.println("Categoría no encontrada.");
        }

        // Prueba de ordenamiento
        List<Producto> listaProductos = Arrays.asList(prod1, prod2, prod3, prod4, prod5);
        Ordenamiento.ordenarPorNombre(listaProductos);
        System.out.println("\nProductos ordenados por nombre:");
        for (Producto p : listaProductos) {
            System.out.println(p.getNombre());
        }

        // Prueba de pila (deshacer)
        System.out.println("\nEliminando producto con ID '101'");
        inventario.eliminarProductoPorId("101");
        System.out.println("Inventario después de eliminar:");
        inventario.mostrarProductos();

        System.out.println("\nDeshacer última operación:");
        inventario.deshacerUltimaOperacion();
        System.out.println("Inventario después de deshacer:");
        inventario.mostrarProductos();
    }
}