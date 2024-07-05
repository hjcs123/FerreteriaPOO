package org.example.DAO;

import org.example.Beans.Cliente;
import org.example.Config.ConnectionBD;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class ClienteDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public ClienteDAO() throws SQLException, IOException {
        this.connection = new ConnectionBD();
    }

    public HashMap<String, Cliente> getClientes() throws SQLException {
        HashMap<String, Cliente> clientes = new HashMap<>();
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM tab_cliente;";
            call = conn.prepareCall(query);
            result = call.executeQuery();

            while (result.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(result.getString(1));
                cliente.setNombre_cliente(result.getString(2));
                cliente.setDireccion(result.getString(3));
                cliente.setDni(result.getInt(4));
                clientes.put(cliente.getId_cliente(), cliente);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return clientes;
    }

    public String agregarCliente(Cliente cliente) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO tab_cliente (nombre_cliente, direccion, dni) VALUES (?, ?, ?)";
            call = conn.prepareCall(query);
            call.setString(1, cliente.getNombre_cliente());
            call.setString(2, cliente.getDireccion());
            call.setInt(3, cliente.getDni());
            call.execute();
            conn.close();
            return "Se agregó el cliente correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarCliente(Cliente cliente) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "UPDATE tab_cliente SET nombre_cliente = ?, direccion = ?, dni = ? WHERE id_cliente = ?";
            call = conn.prepareCall(query);
            call.setString(1, cliente.getNombre_cliente());
            call.setString(2, cliente.getDireccion());
            call.setInt(3, cliente.getDni());
            call.setString(4, cliente.getId_cliente());
            call.execute();
            conn.close();
            return "Se editó el cliente correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String eliminarCliente(int id_cliente) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "DELETE FROM tab_cliente WHERE id_cliente = ?";
            call = conn.prepareCall(query);
            call.setInt(1, id_cliente);
            call.execute();
            conn.close();
            return "Se eliminó el cliente correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
}
