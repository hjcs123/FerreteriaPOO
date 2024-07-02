package org.example.DAO;

import org.example.Beans.Categoria;

import org.example.Config.ConnectionBD;

import java.sql.*;

public class CategoriaDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public Categoria[] getCategorias() throws SQLException {
//        DECLARAR UN ARREGLO DE CATEGORIAS VACIO
        Categoria[] categorias = new Categoria[10];
        try {

//        CREAR LA CONEXION
            Connection conn = (new ConnectionBD()).getConnection();

//        PREPARAR LA CONSULTA
            String query = "SELECT * FROM tab_categoria LIMIT 10;";
            call = conn.prepareCall(query);

//        EJECUTAR LA CONSULTA
            result = call.executeQuery();

//        CONTADOR PARA EL ARREGLO
            int i = 0;
//        RECORRER EL RESULTADO
            while (result.next()) {
//            CREAR UN OBJETO CATEGORIA
                Categoria categoria = new Categoria();

//            LLENAR EL OBJETO CATEGORIA
                categoria.setId(result.getInt(1));
                categoria.setNombre(result.getString(2));

//            AGREGAR LA CATEGORIA AL ARREGLO
                categorias[i] = categoria;
                i++;
            }
//        CERRAR LA CONEXION
            conn.close();

//       RETORNAR EL RESULTADO
            return categorias;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }


    public String agregarCategoria(Categoria categoria) throws SQLException{
        try{
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "INSERT INTO tab_categoria (nombre_categoria) VALUES (?)";
            call = conn.prepareCall(query);
            call.setString(1, categoria.getNombre());
            call.execute();
            conn.close();
            return "Se agrego la categoria correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }



    public String editarCategoria(Categoria categoria) throws SQLException {
        try{
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "UPDATE tab_categoria SET nombre_categoria = ? WHERE id_categoria = ?";
            call = conn.prepareCall(query);
            call.setString(1, categoria.getNombre());
            call.setInt(2, categoria.getId());
            call.execute();
            conn.close();
            return "Se edito la categoria correctamente";
        }catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public void eliminarCategoria(int idCategoria) throws SQLException {
        try{
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "DELETE FROM tab_categoria WHERE id_categoria = ?";
            call = conn.prepareCall(query);
            call.setInt(1, idCategoria);
            call.execute();
            conn.close();
        }catch (SQLException e) {
            System.out.println(e);
            throw e;
        }

    }

    public Categoria buscarCategoriaPorNombre(String nombre) throws SQLException {
        Categoria categoria = null;
        try {

            Connection conn = (new ConnectionBD()).getConnection();

            // Preparar la consulta
            String query = "SELECT * FROM tab_categoria WHERE nombre_categoria = ?";
            call = conn.prepareCall(query);
            call.setString(1, nombre);

            // Ejecutar la consulta
            result = call.executeQuery();

            // Si se encuentra una categoría, llenar el objeto Categoria
            if (result.next()) {
                categoria = new Categoria();
                categoria.setId(result.getInt("id_categoria"));
                categoria.setNombre(result.getString("nombre_categoria"));
            }

            // Cerrar la conexión
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }

        return categoria;
    }
}