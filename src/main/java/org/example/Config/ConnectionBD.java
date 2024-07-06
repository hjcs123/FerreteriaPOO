package org.example.Config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionBD {

    private static String BD_NAME = "dbferreteria";  //nombre como esta en mysql
    private static String USER = "root";
    private static String PASSWORD = "123456"; //si no no funciona.
    private static String URL = "jdbc:mysql://localhost:3306/" + BD_NAME;
    private Connection CONN;


    public ConnectionBD() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            CONN = DriverManager.getConnection(URL, USER, PASSWORD);
            if (CONN != null) {
//                System.out.println("Conexion Exitosa");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public Connection getConnection() {
        return CONN;  //retornar el objeto que contiene la conexión
    }

    public void eliminarDatosTables() {
        try {
            deleteTable("tab_categoria");
            deleteTable("tab_cliente");
            deleteTable("tab_inventario");
            deleteTable("tab_producto");
            deleteTable("tab_proveedor");
            deleteTable("tab_usuario");
            System.out.println("Datos eliminados");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void deleteTable(String table) {
        try {
            Connection conn = new ConnectionBD().getConnection();
            String query = "DELETE FROM " + table;
            conn.createStatement().execute(query);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
