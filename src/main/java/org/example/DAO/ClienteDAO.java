package org.example.DAO;

import org.example.Beans.Cliente;
import org.example.Config.ConnectionBD;

import java.io.IOException;
import java.sql.*;

public class ClienteDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public ClienteDAO() throws SQLException, IOException {
        this.connection = new ConnectionBD();
    }

    public Cliente[] getCliente() throws SQLException {
        Cliente[] clientes = new Cliente[10];
        try {

            //        CREAR LA CONEXION
            Connection conn = connection.getConnection();
            //        PREPARAR LA CONSULTA
            String query = "SELECT * FROM tab_cliente LIMIT 10;";
            call = conn.prepareCall(query);

//        EJECUTAR LA CONSULTA
            result = call.executeQuery();

//        CONTADOR PARA EL ARREGLO
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
            //        CERRAR LA CONEXION
            conn.close();

//       RETORNAR EL RESULTADO
            return clientes;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return clientes;
    }

    public String agregarCliente(Cliente cliente) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO tab_cliente (id_cliente, nombre_cliente, direccion, dni) VALUES (?, ?, ?, ?)";

            call = conn.prepareCall(query);
            call.setString(1, cliente.getId_cliente());
            call.setString(2, cliente.getNombre_cliente());
            call.setString(3, cliente.getDireccion());
            call.setInt(4, cliente.getDni());

            call.execute();
            conn.close();
            return "Se agrego el cliente correctamente";
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
            call = conn.prepareCall(query);
            call.setString(1, cliente.getId_cliente());
            call.setString(2, cliente.getNombre_cliente());
            call.setString(3, cliente.getDireccion());
            call.setInt(4, cliente.getDni());
            call.execute();
            conn.close();
            return "Se edito la boleta correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String eliminarCliente(Cliente cliente) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "DELETE FROM tab_cliente WHERE id_cliente = ?";
            call = conn.prepareCall(query);
            call.setString(1, cliente.getId_cliente());
            call.execute();
            conn.close();
            return "Se elimino el cliente correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
}
