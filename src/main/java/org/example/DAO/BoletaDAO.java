package org.example.DAO;

import org.example.Beans.Boleta;
import org.example.Beans.Cliente;
import org.example.Beans.Producto;
import org.example.Config.ConnectionBD;

import java.sql.*;

public class BoletaDAO {

    public Boleta[] getBoleta() throws SQLException {
        Boleta[] boletas = new Boleta[10];
        String query = "SELECT * FROM tab_boleta LIMIT 10";
        try (Connection conn = (new ConnectionBD()).getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet result = stmt.executeQuery()) {
            int i = 0;
            while (result.next() && i < boletas.length) {
                Boleta boleta = new Boleta();
                boleta.setNumero_boleta(result.getInt("id_boleta"));
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
        String query = "INSERT INTO tab_boleta (id_boleta, f_boleta, cod_usuario, cod_Producto) VALUES (?, ?, ?, ?)";
        try (Connection conn = (new ConnectionBD()).getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, boleta.getNumero_boleta());
            stmt.setDate(2, boleta.getFecha());
            stmt.setInt(3, Integer.parseInt(cliente.getId_cliente()));
            stmt.setInt(4, producto.getId_producto());
            stmt.execute();
            return "Se agregó la boleta correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public String editarBoleta(Boleta boleta, Cliente cliente, Producto producto) throws SQLException {
        String query = "UPDATE tab_boleta SET f_boleta = ?, cod_usuario = ?, cod_Producto = ? WHERE id_boleta = ?";
        try (Connection conn = (new ConnectionBD()).getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, boleta.getNumero_boleta());
            stmt.setDate(2, boleta.getFecha());
            stmt.setInt(3, Integer.parseInt(cliente.getId_cliente()));
            stmt.setInt(4, producto.getId_producto());
            stmt.execute();
            return "Se editó la boleta correctamente";
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public void eliminarBoleta(int idBoleta) throws SQLException {
        String query = "DELETE FROM tab_boleta WHERE id_boleta = ?";
        try (Connection conn = (new ConnectionBD()).getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idBoleta);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public Boleta buscarBoletaPorNumero(int idBoleta) throws SQLException {
        Boleta boleta = null;
        String query = "SELECT * FROM tab_boleta WHERE id_boleta = ?";
        try (Connection conn = (new ConnectionBD()).getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idBoleta);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    boleta = new Boleta();
                    boleta.setNumero_boleta(result.getInt("id_boleta"));
                    boleta.setFecha(result.getDate("f_boleta"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
        return boleta;
    }
}