package org.example.DAO;

import org.example.Beans.Categoria;
import org.example.Config.ConnectionBD;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CategoriaDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public CategoriaDAO() throws SQLException, IOException {
        this.connection = new ConnectionBD();
    }

    public Map<Integer, Categoria> getCategorias() throws SQLException {
        Map<Integer, Categoria> categorias = new HashMap<>();
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM tab_categoria;";
            call = conn.prepareCall(query);
            result = call.executeQuery();
            while (result.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(result.getInt("id_categoria"));
                categoria.setNombre(result.getString("nombre_categoria"));
                categorias.put(categoria.getId(), categoria);
            }
            conn.close();
            return categorias;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String agregarCategoria(Categoria categoria) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO tab_categoria (nombre_categoria) VALUES (?)";
            call = conn.prepareCall(query);
            call.setString(1, categoria.getNombre());
            call.execute();
            conn.close();
            return "Se agregó la categoría correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarCategoria(Categoria categoria) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "UPDATE tab_categoria SET nombre_categoria = ? WHERE id_categoria = ?";
            call = conn.prepareCall(query);
            call.setString(1, categoria.getNombre());
            call.setInt(2, categoria.getId());
            call.execute();
            conn.close();
            return "Se editó la categoría correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public void eliminarCategoria(int idCategoria) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "DELETE FROM tab_categoria WHERE id_categoria = ?";
            call = conn.prepareCall(query);
            call.setInt(1, idCategoria);
            call.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public Map<Integer, Categoria> buscarCategoriaPorNombre(String nombre) throws SQLException {
        Map<Integer, Categoria> categorias = new HashMap<>();
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM tab_categoria WHERE nombre_categoria LIKE '%' || ? || '%';";
            call = conn.prepareCall(query);
            call.setString(1, nombre);
            result = call.executeQuery();
            while (result.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(result.getInt("id_categoria"));
                categoria.setNombre(result.getString("nombre_categoria"));
                categorias.put(categoria.getId(), categoria);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return categorias;
    }
}
