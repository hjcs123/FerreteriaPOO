package org.example.DAO;

import org.example.Beans.Producto;
import org.example.Config.ConnectionBD;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductoDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public ProductoDAO() throws SQLException, IOException {
        this.connection = new ConnectionBD();
    }

    public HashMap<Integer, Producto> getProductos() throws SQLException {
        HashMap<Integer, Producto> productos = new HashMap<>();
        try {
            // Crear la conexión
            Connection conn = connection.getConnection();
            // Preparar la consulta
            String query = "SELECT * FROM tab_producto LIMIT 10;";
            call = conn.prepareCall(query);

            // Ejecutar la consulta
            result = call.executeQuery();

            // Recorrer el resultado
            while (result.next()) {
                // Crear un objeto Producto
                Producto producto = new Producto();

                // Llenar el objeto Producto
                producto.setId_producto(result.getInt("id_producto"));
                producto.setNombre_producto(result.getString("nombre_producto"));
                producto.setCantidad(result.getInt("cantidad"));
                producto.setPrecio_pro(result.getDouble("precio"));

                // Agregar el producto al HashMap
                productos.put(producto.getId_producto(), producto);
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
            Connection conn = connection.getConnection();
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
            Connection conn = connection.getConnection();
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
            Connection conn = connection.getConnection();
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

    public HashMap<Integer, Producto> ordenarProductosPorNombreBurbuja(HashMap<Integer, Producto> productos) {

        List<Producto> listaProductos = new ArrayList<>(productos.values());

        int n = listaProductos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (listaProductos.get(j).getNombre_producto().compareTo(listaProductos.get(j + 1).getNombre_producto()) > 0) {
                    Producto temp = listaProductos.get(j);
                    listaProductos.set(j, listaProductos.get(j + 1));
                    listaProductos.set(j + 1, temp);
                }
            }
        }
        productos.clear();
        for (Producto producto : listaProductos) {
            productos.put(producto.getId_producto(), producto);
        }

        return productos;
    }


}
