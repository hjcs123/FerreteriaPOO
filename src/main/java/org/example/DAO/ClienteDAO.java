package org.example.DAO;

import org.example.Beans.Cliente;
import org.example.Config.ConnectionBD;

import java.sql.*;

public class ClienteDAO {

    public Cliente[] getCliente() throws SQLException {
        Cliente[] clientes = new Cliente[10];
        String query = "SELECT * FROM tab_cliente LIMIT 10";
        try (Connection conn = (new ConnectionBD()).getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet result = stmt.executeQuery()) {
            int i = 0;
            while (result.next() && i < clientes.length) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(result.getString(1));
                cliente.setNombre_cliente(result.getString(2));
                cliente.setDireccion(result.getString(3));
                cliente.setDni(result.getInt(4));
                clientes[i] = cliente;
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return clientes;
    }

    public String agregarCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO tab_cliente (id_cliente, nombre_cliente, direccion, dni) VALUES (?, ?, ?, ?)";
        try (Connection conn = (new ConnectionBD()).getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getId_cliente());
            stmt.setString(2, cliente.getNombre_cliente());
            stmt.setString(3, cliente.getDireccion());
            stmt.setInt(4, cliente.getDni());
            stmt.execute();
            return "Se agregó el cliente correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE tab_cliente SET nombre_cliente = ?, direccion = ?, dni = ? WHERE id_cliente = ?";
        try (Connection conn = (new ConnectionBD()).getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre_cliente());
            stmt.setString(2, cliente.getDireccion());
            stmt.setInt(3, cliente.getDni());
            stmt.setString(4, cliente.getId_cliente());
            stmt.execute();
            return "Se editó el cliente correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String eliminarCliente(String id_cliente) throws SQLException {
        String query = "DELETE FROM tab_cliente WHERE id_cliente = ?";
        try (Connection conn = (new ConnectionBD()).getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id_cliente);
            stmt.execute();
            return "Se eliminó el cliente correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
}
