package org.example.DAO;

import org.example.Beans.Producto;
import org.example.Config.ConnectionBD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public Producto[] getProductos() throws SQLException {
        Producto[] productos = new Producto[10];
        try {
            // Crear la conexión
            Connection conn = (new ConnectionBD()).getConnection();
            // Preparar la consulta
            String query = "SELECT * FROM tab_producto LIMIT 10;";
            call = conn.prepareCall(query);

            // Ejecutar la consulta
            result = call.executeQuery();

            // Contador para el arreglo
            int i = 0;
            // Recorrer el resultado
            while (result.next()) {
                // Crear un objeto Producto
                Producto producto = new Producto();

                // Llenar el objeto Producto
                producto.setId_producto(result.getInt("id_producto"));
                producto.setNombre_producto(result.getString("nombre_producto"));
                producto.setCantidad(result.getInt("cantidad"));
                producto.setPrecio_pro(result.getDouble("precio"));

                // Agregar el producto al arreglo
                productos[i] = producto;
                i++;
            }
            // Cerrar la conexión
            conn.close();

            // Retornar el resultado
            return productos;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String agregarProducto(Producto producto) throws SQLException {
        try {
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "INSERT INTO tab_producto (id_producto, nombre_producto, cantidad, precio) VALUES (?, ?, ?, ?)";

            call = conn.prepareCall(query);
            call.setInt(1, producto.getId_producto());
            call.setString(2, producto.getNombre_producto());
            call.setInt(3, producto.getCantidad());
            call.setDouble(4, producto.getPrecio_pro());

            call.execute();
            conn.close();
            return "Se agregó el producto correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarProducto(Producto producto) throws SQLException {
        try {
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "UPDATE tab_producto SET nombre_producto = ?, cantidad = ?, precio = ? WHERE id_producto = ?";

            call = conn.prepareCall(query);
            call.setString(1, producto.getNombre_producto());
            call.setInt(2, producto.getCantidad());
            call.setDouble(3, producto.getPrecio_pro());
            call.setInt(4, producto.getId_producto());

            call.execute();
            conn.close();
            return "Se editó el producto correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String eliminarProducto(int id_producto) throws SQLException {
        try {
            Connection conn = (new ConnectionBD()).getConnection();
            String query = "DELETE FROM tab_producto WHERE id_producto = ?";

            call = conn.prepareCall(query);
            call.setInt(1, id_producto);

            call.execute();
            conn.close();
            return "Se eliminó el producto correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
}
