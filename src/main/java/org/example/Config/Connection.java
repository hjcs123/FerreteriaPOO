package org.example.Config;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private static String BD_NAME = "dbFerreteria";  //nombre como esta en mysql
    private static String USER = "root";
    private static String PASSWORD = "12346789"; //si no no funciona.
    private static String URL = "jdbc:mysql://localhost:3306/" + BD_NAME;
    private static java.sql.Connection CONN = null;


    public Connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            CONN = DriverManager.getConnection(URL, USER, PASSWORD);
            if (CONN != null) {
                System.out.println("Conexion Exitosa");

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    public static java.sql.Connection getConnection() {
        return CONN;
    }

    public void closeConnection() {
        try {
            CONN.close();
            System.out.println("Desconexion exitosa");
        } catch (Exception e) {
            System.out.println("error al desconectar");
        }

    }
}
