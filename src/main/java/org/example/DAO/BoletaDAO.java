package org.example.DAO;

import org.example.Beans.Boleta;
import org.example.Config.ConnectionBD;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class BoletaDAO {

    ConnectionBD connection;
    CallableStatement call;
    private ResultSet result;

    public BoletaDAO() throws SQLException, IOException {
        this.connection = new ConnectionBD();
    }

    public HashMap<Integer, Boleta> getBoletas() throws SQLException {
        HashMap<Integer, Boleta> boletas = new HashMap<>();
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT id, numero_boleta, fecha FROM tab_boleta;";
            call = conn.prepareCall(query);
            result = call.executeQuery();

            while (result.next()) {
                Boleta boleta = new Boleta();
                boleta.setId(result.getInt("id"));
                boleta.setNumero_boleta(result.getInt("numero_boleta"));
                boleta.setFecha(result.getDate("fecha"));
                boletas.put(boleta.getId(), boleta);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return boletas;
    }

    public String agregarBoleta(Boleta boleta) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO tab_boleta (numero_boleta, fecha) VALUES (?, ?)";
            call = conn.prepareCall(query);
            call.setInt(1, boleta.getNumero_boleta());
            call.setDate(2, boleta.getFecha());
            call.execute();
            conn.close();
            return "Se agregó la boleta correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarBoleta(Boleta boleta) throws SQLException {
        try {
            Connection conn = connection.getConnection();
            String query = "UPDATE tab_boleta SET numero_boleta = ?, fecha = ? WHERE id = ?";
            call = conn.prepareCall(query);
            call.setInt(1, boleta.getNumero_boleta());
            call.setDate(2, boleta.getFecha());
            call.setInt(3, boleta.getId());
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
            String query = "DELETE FROM tab_boleta WHERE id = ?";
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
            String query = "SELECT id, numero_boleta, fecha FROM tab_boleta WHERE id = ?";
            call = conn.prepareCall(query);
            call.setInt(1, idBoleta);
            result = call.executeQuery();
            if (result.next()) {
                boleta = new Boleta();
                boleta.setId(result.getInt("id"));
                boleta.setNumero_boleta(result.getInt("numero_boleta"));
                boleta.setFecha(result.getDate("fecha"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return boleta;
    }
}
