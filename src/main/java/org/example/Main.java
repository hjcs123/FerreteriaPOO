package org.example;

import org.example.Beans.Categoria;
import org.example.DAO.CategoriaDAO;

public class Main {
    public static void main(String[] args) {

        CategoriaDAO categoriaDAO;

        try {
//            Categoria categoriaPrueba = new Categoria();
//            categoriaPrueba.setId(3);
//            categoriaPrueba.setNombre("Categoria 3");

            categoriaDAO = new CategoriaDAO();
//            categoriaDAO.agregarCategoria(categoria1);
//            categoriaDAO.editarCategoria(categoriaPrueba);
            categoriaDAO.eliminarCategoria(7);

            categoriaDAO = new CategoriaDAO();
            Categoria[] categorias = categoriaDAO.getCategorias();

            for (Categoria categoria : categorias) {
                if (categoria != null) {
                    System.out.println(categoria.getId() + " " + categoria.getNombre());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}