package org.example.DAO;

import org.example.Beans.Usuario;
import org.example.Config.ConnectionBD;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public UsuarioDAO() throws SQLException, IOException {
        this.connection = new ConnectionBD();
    }

    public Usuario[] getUsuario() throws SQLException {

        Usuario[] usuarios = new Usuario[10];
        try {
            // Crear la conexión
            Connection conn = connection.getConnection();
            // Preparar la consulta
            String query = "SELECT * FROM tab_usuario LIMIT 10;";
            call = conn.prepareCall(query);

            // Ejecutar la consulta
            result = call.executeQuery();

            // Contador para el arreglo
            int i = 0;
            // Recorrer el resultado
            while (result.next()) {
                // Crear un objeto Usuario
                Usuario usuario = new Usuario();

                // Llenar el objeto Usuario
                usuario.setId_usuario(result.getInt("id_usuario"));
                usuario.setNombre_usuario(result.getString("nombre_usuario"));
                usuario.setEdad(result.getInt("edad"));

                // Agregar el usuario al arreglo
                usuarios[i] = usuario;
                i++;
            }
            // Cerrar la conexión
            conn.close();

            // Retornar el resultado
            return usuarios;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String agregarUsuario(Usuario usuario) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO tab_usuario (id_usuario, nombre_usuario, edad) VALUES (?, ?, ?)";

            call = conn.prepareCall(query);
            call.setInt(1, usuario.getId_usuario());
            call.setString(2, usuario.getNombre_usuario());
            call.setInt(3, usuario.getEdad());

            call.execute();
            conn.close();
            return "Se agregó el usuario correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarUsuario(Usuario usuario) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "UPDATE tab_usuario SET nombre_usuario = ?, edad = ? WHERE id_usuario = ?";

            call = conn.prepareCall(query);
            call.setString(1, usuario.getNombre_usuario());
            call.setInt(2, usuario.getEdad());
            call.setInt(3, usuario.getId_usuario());

            call.execute();
            conn.close();
            return "Se editó el usuario correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String eliminarUsuario(int id_usuario) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "DELETE FROM tab_usuario WHERE id_usuario = ?";

            call = conn.prepareCall(query);
            call.setInt(1, id_usuario);

            call.execute();
            conn.close();
            return "Se eliminó el usuario correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
}
