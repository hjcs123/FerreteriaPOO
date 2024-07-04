package org.example.DAO;

import org.example.Beans.Proveedor;
import org.example.Config.ConnectionBD;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedorDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public ProveedorDAO() throws SQLException, IOException {
        this.connection = new ConnectionBD();
    }

    public Proveedor[] getProveedores() throws SQLException {

        Proveedor[] proveedores = new Proveedor[10];
        try {
            // Crear la conexión
            Connection conn = connection.getConnection();
            // Preparar la consulta
            String query = "SELECT * FROM tab_proveedor LIMIT 10;";
            call = conn.prepareCall(query);

            // Ejecutar la consulta
            result = call.executeQuery();

            // Contador para el arreglo
            int i = 0;
            // Recorrer el resultado
            while (result.next()) {
                // Crear un objeto Proveedor
                Proveedor proveedor = new Proveedor();

                // Llenar el objeto Proveedor
                proveedor.setNombre_proveedor(result.getString("nombre_proveedor"));
                proveedor.setId_proveedor(result.getInt("ID_Proveedor"));
                proveedor.setRuc(result.getInt("ruc"));
                proveedor.setEmail(result.getString("email"));
                proveedor.setTelefono(result.getInt("telefono"));
                proveedor.setDireccion(result.getString("direccion"));

                // Agregar el proveedor al arreglo
                proveedores[i] = proveedor;
                i++;
            }
            // Cerrar la conexión
            conn.close();

            // Retornar el resultado
            return proveedores;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String agregarProveedor(Proveedor proveedor) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO tab_proveedor (nombre_proveedor, ID_Proveedor, ruc, email, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?)";

            call = conn.prepareCall(query);
            call.setString(1, proveedor.getNombre_proveedor());
            call.setInt(2, proveedor.getId_proveedor());
            call.setInt(3, proveedor.getRuc());
            call.setString(4, proveedor.getEmail());
            call.setInt(5, proveedor.getTelefono());
            call.setString(6, proveedor.getDireccion());

            call.execute();
            conn.close();
            return "Se agregó el proveedor correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarProveedor(Proveedor proveedor) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "UPDATE tab_proveedor SET nombre_proveedor = ?, ruc = ?, email = ?, telefono = ?, direccion = ? WHERE ID_Proveedor = ?";

            call = conn.prepareCall(query);
            call.setString(1, proveedor.getNombre_proveedor());
            call.setInt(2, proveedor.getRuc());
            call.setString(3, proveedor.getEmail());
            call.setInt(4, proveedor.getTelefono());
            call.setString(5, proveedor.getDireccion());
            call.setInt(6, proveedor.getId_proveedor());

            call.execute();
            conn.close();
            return "Se editó el proveedor correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String eliminarProveedor(int id_proveedor) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "DELETE FROM tab_proveedor WHERE ID_Proveedor = ?";

            call = conn.prepareCall(query);
            call.setInt(1, id_proveedor);

            call.execute();
            conn.close();
            return "Se eliminó el proveedor correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
}
