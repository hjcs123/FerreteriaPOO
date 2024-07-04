package org.example.DAO;

import org.example.Beans.Boleta;
import org.example.Beans.Cliente;
import org.example.Beans.Producto;
import org.example.Config.ConnectionBD;

import java.io.IOException;
import java.sql.*;

public class BoletaDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public BoletaDAO() throws SQLException, IOException {
        this.connection = new ConnectionBD();
    }

    public Boleta[] getBoleta() throws SQLException {
        Boleta[] boletas = new Boleta[10];
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM tab_boleta LIMIT 10;";
            call = conn.prepareCall(query);
            result = call.executeQuery();
            int i = 0;
            while (result.next() && i < boletas.length) {
                Boleta boleta = new Boleta();
                boleta.setNumero_boleta(result.getInt("id_boleta"));
                boleta.setFecha(result.getDate("f_boleta"));
                boleta.setFecha(result.getDate("f_boleta"));
                boletas[i] = boleta;
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return boletas;
    }

    public String agregarBoleta(Boleta boleta, Cliente cliente, Producto producto) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO tab_boleta (id_boleta, f_boleta, cod_usuario, cod_Producto) VALUES (?, ?, ?, ?)";
            call = conn.prepareCall(query);
            call.setInt(1, boleta.getNumero_boleta());
            call.setDate(2, boleta.getFecha());
            call.setString(3, cliente.getId_cliente());
            call.setInt(4, producto.getId_producto());
            call.execute();
            conn.close();
            return "Se agregó la boleta correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarBoleta(Boleta boleta, Cliente cliente, Producto producto) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "UPDATE tab_boleta SET f_boleta = ?, cod_usuario = ?, cod_Producto = ? WHERE id_boleta = ?";
            call = conn.prepareCall(query);
            call.setDate(1, boleta.getFecha());
            call.setString(2, cliente.getId_cliente());
            call.setInt(3, producto.getId_producto());
            call.setInt(4, boleta.getNumero_boleta());
            call.execute();
            conn.close();
            return "Se editó la boleta correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public void eliminarBoleta(int idBoleta) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "DELETE FROM tab_boleta WHERE id_boleta = ?";
            call = conn.prepareCall(query);
            call.setInt(1, idBoleta);
            call.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public Boleta buscarBoletaPorNumero(int idBoleta) throws SQLException {
        Boleta boleta = null;
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM tab_boleta WHERE id_boleta = ?";
            call = conn.prepareCall(query);
            call.setInt(1, idBoleta);
            result = call.executeQuery();
            if (result.next()) {
                boleta = new Boleta();
                boleta.setNumero_boleta(result.getInt("id_boleta"));
                boleta.setFecha(result.getDate("f_boleta"));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return boleta;
    }
}