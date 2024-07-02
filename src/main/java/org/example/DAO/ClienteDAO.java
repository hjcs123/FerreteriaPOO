package org.example.DAO;


import org.example.Beans.Cliente;
import org.example.Config.ConnectionBD;
import java.sql.*;

public class ClienteDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public Cliente[] getCliente() throws SQLException {
//        DECLARAR UN ARREGLO DE CLIENTE VACIO
        Cliente[] clientes = new Cliente[10];
        try {

            //        CREAR LA CONEXION
        Connection conn = (new ConnectionBD()).getConnection();
            //        PREPARAR LA CONSULTA
            String query = "SELECT * FROM tab_cliente LIMIT 10;";
            call = conn.prepareCall(query);

//        EJECUTAR LA CONSULTA
            result = call.executeQuery();

//        CONTADOR PARA EL ARREGLO
            int i = 0;
//        RECORRER EL RESULTADO
            while (result.next()) {
//            CREAR UN OBJETO CLIENTE
                Cliente cliente = new Cliente();

//            LLENAR EL OBJETO BOLETA
                cliente.setId_cliente(result.getString(1));
                cliente.setNombre_cliente(result.getString(2));
                cliente.setDireccion(result.getString(3));
                cliente.setDni(result.getInt(4));


//            AGREGAR LA BOLETA AL ARREGLO
                clientes[i] = cliente;
                i++;

            }
            //        CERRAR LA CONEXION
            conn.close();

//       RETORNAR EL RESULTADO
            return clientes;
            }

        catch (SQLException e) {
            System.out.println(e);
            throw e;
        }

    }
    public String agregarCliente( Cliente cliente) throws SQLException{
        try {
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "INSERT INTO clientes (id_cliente, nombre_cliente, direccion, dni) VALUES (?, ?, ?, ?)";

            call = conn.prepareCall(query);
            call.setInt(1, cliente.getId_cliente());
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
        try{
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "INSERT INTO boletas (id_cliente, nombre_cliente, direccion, dni) VALUES (?, ?, ?, ?, ?)";

            call = conn.prepareCall(query);
            call = conn.prepareCall(query);
            call.setInt(1, cliente.getId_cliente());
            call.setString(2, cliente.getNombre_cliente());
            call.setString(3, cliente.getDireccion());
            call.setInt(4, cliente.getDni());
            call.execute();
            conn.close();
            return "Se edito la boleta correctamente";
        }
        catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String eliminarCliente(Cliente cliente) throws SQLException {
        try{
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "INSERT INTO boletas (id_cliente, nombre_cliente, direccion, dni) VALUES (?, ?, ?, ?, ?)";

            call = conn.prepareCall(query);
            call = conn.prepareCall(query);
            call.setInt(1, cliente.getId_cliente());
            call.setString(2, cliente.getNombre_cliente());
            call.setString(3, cliente.getDireccion());
            call.setInt(4, cliente.getDni());
            call.execute();
            conn.close();
            return "Se edito la boleta correctamente";
        }
        catch (SQLException e) {
            System.out.println(e);
            throw e;

        }

    }
}
