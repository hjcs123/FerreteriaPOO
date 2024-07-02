package org.example.DAO;

import org.example.Beans.Inventario;
import org.example.Config.ConnectionBD;

import java.sql.*;

public class InventarioDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public Inventario[] getInventarios() throws SQLException {
        Inventario[] inventarios = new Inventario[10];
        try {
            // Crear la conexión
            Connection conn = (new ConnectionBD()).getConnection();
            // Preparar la consulta
            String query = "SELECT * FROM tab_inventario LIMIT 10;";
            call = conn.prepareCall(query);

            // Ejecutar la consulta
            result = call.executeQuery();

            // Contador para el arreglo
            int i = 0;
            // Recorrer el resultado
            while (result.next()) {
                // Crear un objeto Inventario
                Inventario inventario = new Inventario();

                // Llenar el objeto Inventario
                inventario.setCodProducto(result.getString("codProducto"));
                inventario.setNombreProducto(result.getString("nombreProducto"));
                inventario.setPrecioU(result.getDouble("precioU"));

                // Agregar el inventario al arreglo
                inventarios[i] = inventario;
                i++;
            }
            // Cerrar la conexión
            conn.close();

            // Retornar el resultado
            return inventarios;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String agregarInventario(Inventario inventario) throws SQLException {
        try {
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "INSERT INTO tab_inventario (codProducto, nombreProducto, precioU) VALUES (?, ?, ?)";

            call = conn.prepareCall(query);
            call.setString(1, inventario.getCodProducto());
            call.setString(2, inventario.getNombreProducto());
            call.setDouble(3, inventario.getPrecioU());

            call.execute();
            conn.close();
            return "Se agregó el inventario correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarInventario(Inventario inventario) throws SQLException {
        try {
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "UPDATE tab_inventario SET nombreProducto = ?, precioU = ? WHERE codProducto = ?";

            call = conn.prepareCall(query);
            call.setString(1, inventario.getNombreProducto());
            call.setDouble(2, inventario.getPrecioU());
            call.setString(3, inventario.getCodProducto());

            call.execute();
            conn.close();
            return "Se editó el inventario correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String eliminarInventario(String codProducto) throws SQLException {
        try {
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "DELETE FROM tab_inventario WHERE codProducto = ?";

            call = conn.prepareCall(query);
            call.setString(1, codProducto);

            call.execute();
            conn.close();
            return "Se eliminó el inventario correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public Inventario buscarInventarioPorNombre(String nombreProducto) throws SQLException {
        Inventario inventario = null;
        try {
            Connection conn = (new ConnectionBD()).getConnection();

            // Preparar la consulta
            String query = "SELECT * FROM tab_inventario WHERE nombreProducto = ?";
            call = conn.prepareCall(query);
            call.setString(1, nombreProducto);

            // Ejecutar la consulta
            result = call.executeQuery();

            // Si se encuentra un inventario, llenar el objeto Inventario
            if (result.next()) {
                inventario = new Inventario();
                inventario.setCodProducto(result.getString("codProducto"));
                inventario.setNombreProducto(result.getString("nombreProducto"));
                inventario.setPrecioU(result.getDouble("precioU"));
            }

            // Cerrar la conexión
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }

        return inventario;
    }
}
