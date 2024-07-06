package org.example.Utils;

import org.example.CLASES.Producto;

import java.util.List;

public class Ordenamiento {
    public static void ordenarPorNombre(List<Producto> productos) {
        int n = productos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (productos.get(j).getNombre().compareTo(productos.get(j + 1).getNombre()) > 0) {
                    // Intercambiar productos[j+1] y productos[j]
                    Producto temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);
                }
            }
        }
    }
}
