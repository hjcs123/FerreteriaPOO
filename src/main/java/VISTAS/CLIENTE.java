package VISTAS;

import org.example.Beans.Cliente;
import org.example.DAO.ClienteDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CLIENTE extends JFrame {
    public JPanel jCliente;
    private JTextField txtIdCliente;
    private JTextField txtNombre;
    private JTextField txtCliente;
    private JTextField txtDni;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnOrdenar;
    private JLabel TITLE;
    private JLabel CLIENTE;
    private JLabel NOMBRE;
    private JLabel DIRECCION;
    private JLabel DNI;
    private JButton btnAgregar;
    private JTable tblCliente;

    public CLIENTE() throws SQLException, IOException {
        createTable();
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String direccion = txtCliente.getText();
                int dni = Integer.parseInt(txtDni.getText());
                Cliente cliente = new Cliente(nombre, direccion, dni);
                try {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    clienteDAO.agregarCliente(cliente);
                    createTable();
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ROW SELECTED
                int row = tblCliente.getSelectedRow();
                String id = tblCliente.getValueAt(row, 0).toString();
                String nombre = txtNombre.getText() == null ? tblCliente.getValueAt(row, 1).toString() : txtNombre.getText();
                String direccion = txtCliente.getText() == null ? tblCliente.getValueAt(row, 2).toString() : txtCliente.getText();
                int dni = txtDni.getText() == null ? Integer.parseInt(tblCliente.getValueAt(row, 3).toString()) : Integer.parseInt(txtDni.getText());

//                LOAD TXT
                txtIdCliente.setText(id);
                txtNombre.setText(nombre);
                txtCliente.setText(direccion);
                txtDni.setText(String.valueOf(dni));

                Cliente cliente = new Cliente(id, nombre, direccion, dni);
//
                try {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    clienteDAO.editarCliente(cliente);
                    createTable();
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        tblCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblCliente.getSelectedRow();

                if (row != -1) {
                    txtIdCliente.setText(tblCliente.getValueAt(row, 0).toString());
                    txtNombre.setText(tblCliente.getValueAt(row, 1).toString());
                    txtCliente.setText(tblCliente.getValueAt(row, 2).toString());
                    txtDni.setText(tblCliente.getValueAt(row, 3).toString());
                }

            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblCliente.getSelectedRow();
                if (row != -1) {
                    int id = Integer.parseInt(tblCliente.getValueAt(row, 0).toString());
                    try {
                        ClienteDAO clienteDAO = new ClienteDAO();
                        clienteDAO.eliminarCliente(id);
                        createTable();
                    } catch (SQLException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private void createTable() throws SQLException, IOException {
        ClienteDAO clienteDAO = new ClienteDAO();
        HashMap<String, Cliente> clientes = clienteDAO.getClientes();

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "NOMBRE", "DIRECCION", "DNI"}, 0);

        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            model.addRow(new Object[]{
                    cliente.getId_cliente(),
                    cliente.getNombre_cliente(),
                    cliente.getDireccion(),
                    cliente.getDni()
            });
        }

        tblCliente.setModel(model);
    }


}
