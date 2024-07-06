package org.example.Operaciones;

import org.example.CLASES.Categoria;

import java.util.TreeMap;

public class GestionCategorias {
    private TreeMap<String, Categoria> categorias;

    public GestionCategorias() {
        this.categorias = new TreeMap<>();
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.put(categoria.getId(), categoria);
    }

    public Categoria buscarCategoriaPorId(String id) {
        return categorias.get(id);
    }

    public void mostrarCategorias() {
        for (Categoria c : categorias.values()) {
            System.out.println("ID: " + c.getId() + ", Nombre: " + c.getNombre());
        }
    }
}
