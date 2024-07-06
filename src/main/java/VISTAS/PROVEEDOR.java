package VISTAS;

import org.example.Beans.Cliente;
import org.example.Beans.Proveedor;
import org.example.DAO.ClienteDAO;
import org.example.DAO.ProveedorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PROVEEDOR extends JFrame {
    private JTextField txtNombrePro;
    private JTable tableProveedor;
    private JTextField txtIdProv;
    private JTextField txtRucProv;
    private JTextField txtEmailProv;
    private JTextField txtTelefonoPro;
    private JTextField txtDireccionPro;
    private JButton btnAgregarProv;
    private JButton btnModificarProv;
    private JButton btnEliminarProv;
    private JButton btnBuscarProv;
    private JButton btnOrdenarProv;
    private JLabel lblProveedor;
    private JLabel lblRUC;
    private JLabel lblEmail;
    private JLabel lblTelefono;
    private JLabel lblDireccion;
    public JPanel panel;

    public PROVEEDOR() throws SQLException, IOException {
        createTable();

        tableProveedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableProveedor.getSelectedRow();

                if (row != -1) {
                    txtIdProv.setText(tableProveedor.getValueAt(row, 0).toString());
                    txtNombrePro.setText(tableProveedor.getValueAt(row, 1).toString());
                    txtRucProv.setText(tableProveedor.getValueAt(row, 2).toString());
                    txtEmailProv.setText(tableProveedor.getValueAt(row, 3).toString());
                    txtTelefonoPro.setText(tableProveedor.getValueAt(row, 4).toString());
                    txtDireccionPro.setText(tableProveedor.getValueAt(row, 5).toString());
                }

            }
        });
        btnOrdenarProv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createTable() throws SQLException, IOException {
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        HashMap<Integer, Proveedor> clientes = proveedorDAO.getProveedores();

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "NOMBRE", "RUC", "EMAIL", "TELEFONO", "DIRECCION"}, 0);

        for (Map.Entry<Integer, Proveedor> entry : clientes.entrySet()) {
            Proveedor proveedor = entry.getValue();
            model.addRow(new Object[]{
                    proveedor.getId_proveedor(),
                    proveedor.getNombre_proveedor(),
                    proveedor.getRuc(),
                    proveedor.getEmail(),
                    proveedor.getTelefono(),
                    proveedor.getDireccion()
            });
        }

        tableProveedor.setModel(model);
    }

    private void createTable(HashMap<Integer, Proveedor> proveedores) throws SQLException, IOException {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "NOMBRE", "RUC", "EMAIL", "TELEFONO", "DIRECCION"}, 0);

        for (Map.Entry<Integer, Proveedor> entry : proveedores.entrySet()) {
            Proveedor proveedor = entry.getValue();
            model.addRow(new Object[]{
                    proveedor.getId_proveedor(),
                    proveedor.getNombre_proveedor(),
                    proveedor.getRuc(),
                    proveedor.getEmail(),
                    proveedor.getTelefono(),
                    proveedor.getDireccion()
            });
        }

        tableProveedor.setModel(model);
    }
}
