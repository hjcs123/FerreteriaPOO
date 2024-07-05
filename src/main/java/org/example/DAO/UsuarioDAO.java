package org.example.DAO;

import org.example.Beans.Usuario;
import org.example.Config.ConnectionBD;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UsuarioDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public UsuarioDAO() throws SQLException, IOException {
        this.connection = new ConnectionBD();
    }

    public HashMap<Integer, Usuario> getUsuarios() throws SQLException {
        HashMap<Integer, Usuario> usuarios = new HashMap<>();
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM tab_usuario;";
            call = conn.prepareCall(query);
            result = call.executeQuery();

            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(result.getInt("id_usuario"));
                usuario.setNombre_usuario(result.getString("nombre_usuario"));
                usuario.setEdad(result.getInt("edad"));
                usuarios.put(usuario.getId_usuario(), usuario);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return usuarios;
    }

    public String agregarUsuario(Usuario usuario) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO tab_usuario (nombre_usuario, edad, password) VALUES (?, ?, ?)";

            call = conn.prepareCall(query);
            call.setString(1, usuario.getNombre_usuario());
            call.setInt(2, usuario.getEdad());
            call.setString(3, usuario.getPassword()); // Se asume que has añadido este campo a la clase Usuario

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
            String query = "UPDATE tab_usuario SET nombre_usuario = ?, edad = ?, password = ? WHERE id_usuario = ?";

            call = conn.prepareCall(query);
            call.setString(1, usuario.getNombre_usuario());
            call.setInt(2, usuario.getEdad());
            call.setString(3, usuario.getPassword());
            call.setInt(4, usuario.getId_usuario());

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

    public boolean autenticarUsuario(String nombre_usuario, String password) throws SQLException {
        boolean autenticado = false;
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM tab_usuario WHERE nombre_usuario = ? AND password = ?";

            call = conn.prepareCall(query);
            call.setString(1, nombre_usuario);
            call.setString(2, password);
            result = call.executeQuery();

            if (result.next()) {
                autenticado = true;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return autenticado;
    }
}
