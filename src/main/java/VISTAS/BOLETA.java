package VISTAS;

import org.example.Beans.Boleta;
import org.example.DAO.BoletaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class BOLETA extends JFrame {
    public JPanel panel1;
    private JTextField txtNumero;
    private JTextField txtFecha;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JLabel lblTitle;
    private JLabel lblNumero;
    private JLabel lblFecha;
    private JTable tblBoletas;
    private JTextField txtID;
    private JLabel lblID;

    public BOLETA() throws SQLException, IOException {
        createTable();
        tblBoletas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tblBoletas.getSelectedRow();
                txtID.setText(tblBoletas.getValueAt(row, 0).toString());
                txtNumero.setText(tblBoletas.getValueAt(row, 1).toString());
                txtFecha.setText(tblBoletas.getValueAt(row, 2).toString());
            }
        });
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = Integer.parseInt(txtNumero.getText());
                String fechaString = txtFecha.getText();
                if (validarFecha(fechaString)) {
                    try {
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecha = new Date(formatoFecha.parse(fechaString).getTime());
                        Boleta boleta = new Boleta(numero, fecha);
                        BoletaDAO boletaDAO = new BoletaDAO();
                        boletaDAO.agregarBoleta(boleta);
                        createTable();
                    } catch (SQLException | IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Fecha inválida. Por favor, ingrese la fecha en el formato dd/MM/yyyy.");
                }
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtID.getText());
                int numero = Integer.parseInt(txtNumero.getText());
                String fechaString = txtFecha.getText();
                if (validarFecha(fechaString)) {
                    try {
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecha = new Date(formatoFecha.parse(fechaString).getTime());
                        Boleta boleta = new Boleta(id, numero, fecha);
                        BoletaDAO boletaDAO = new BoletaDAO();
                        boletaDAO.editarBoleta(boleta);
                        createTable();
                    } catch (SQLException | IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Fecha inválida. Por favor, ingrese la fecha en el formato dd/MM/yyyy.");
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtID.getText());
                try {
                    BoletaDAO boletaDAO = new BoletaDAO();
                    boletaDAO.eliminarBoleta(id);
                    createTable();
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void createTable() throws SQLException, IOException {
        BoletaDAO boletaDAO = new BoletaDAO();
        HashMap<Integer, Boleta> boletas;
        boletas = boletaDAO.getBoletas();
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Numero", "Fecha"}, 0);
        for (Boleta boleta : boletas.values()) {
            model.addRow(new Object[]{boleta.getId(), boleta.getNumero_boleta(),
                    new SimpleDateFormat("dd/MM/yyyy").format(boleta.getFecha())});
        }
        tblBoletas.setModel(model);
    }

    public boolean validarFecha(String fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);
        try {
            formatoFecha.parse(fecha);
            return true; // La fecha es válida
        } catch (ParseException e) {
            return false; // La fecha no es válida
        }
    }

}
