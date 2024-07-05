package VISTAS;

import org.example.Beans.Cliente;
import org.example.Beans.Producto;
import org.example.DAO.ClienteDAO;
import org.example.DAO.ProductoDAO;

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

public class PRODUCTO extends JFrame {
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnOrdenar;
    private JTable tableProductos;
    public JPanel panel;
    private JLabel lblTitle;
    private JLabel lblTitulo;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblCantidad;
    private JLabel lblPrecio;

    public PRODUCTO() throws SQLException, IOException {
        createTable();


        tableProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableProductos.getSelectedRow();

                if (row != -1) {
                    txtID.setText(tableProductos.getValueAt(row, 0).toString());
                    txtNombre.setText(tableProductos.getValueAt(row, 1).toString());
                    txtCantidad.setText(tableProductos.getValueAt(row, 2).toString());
                    txtPrecio.setText(tableProductos.getValueAt(row, 3).toString());
                }
            }
        });


        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableProductos.getSelectedRow();
                if (row != -1) {
                    int id = Integer.parseInt(tableProductos.getValueAt(row, 0).toString());
                    try {
                        ProductoDAO productoDAO = new ProductoDAO();
                        productoDAO.eliminarProducto(id);
                        createTable();
                    } catch (SQLException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductoDAO productoDAO = new ProductoDAO();
                    HashMap<Integer, Producto> productos = productoDAO.getProductos();
                    orderTable();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableProductos.getSelectedRow();
                if (row != -1) {
                    int id = Integer.parseInt(tableProductos.getValueAt(row, 0).toString());
                    String nombre = txtNombre.getText() == null ? tableProductos.getValueAt(row, 1).toString() : txtNombre.getText();
                    int cantidad = txtCantidad.getText() == null ? Integer.parseInt(tableProductos.getValueAt(row, 2).toString()) : Integer.parseInt(txtCantidad.getText());
                    Double precio = txtPrecio.getText() == null ? Double.parseDouble(tableProductos.getValueAt(row, 3).toString()) : Double.parseDouble(txtPrecio.getText());

//                LOAD TXT
                    txtID.setText(String.valueOf(id));
                    txtNombre.setText(nombre);
                    txtCantidad.setText(String.valueOf(cantidad));
                    txtPrecio.setText(String.valueOf(precio));

                    Producto producto = new Producto(id, nombre, cantidad, precio);
//
                    try {
                        ProductoDAO productoDAO = new ProductoDAO();
                        productoDAO.editarProducto(producto);
                        createTable();
                    } catch (SQLException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private void createTable() throws SQLException, IOException {
        ProductoDAO productoDAO = new ProductoDAO();
        HashMap<Integer, Producto> productos = productoDAO.getProductos();
        productoDAO = new ProductoDAO();
        productos = productoDAO.ordenarProductosPorNombreBurbuja(productos);

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "NOMBRE", "CANTIDAD", "PRECIO"}, 0);

        for (Map.Entry<Integer, Producto> entry : productos.entrySet()) {
            Producto producto = entry.getValue();
            model.addRow(new Object[]{
                    producto.getId_producto(),
                    producto.getNombre_producto(),
                    producto.getCantidad(),
                    producto.getPrecio_pro(),
            });
        }

        tableProductos.setModel(model);
    }

    private void orderTable() throws SQLException, IOException {
        ProductoDAO productoDAO = new ProductoDAO();
        HashMap<Integer, Producto> productos = productoDAO.ordenarProductosPorNombreBurbuja(
                productoDAO.getProductos());

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "NOMBRE", "CANTIDAD", "PRECIO"}, 0);

        for (Map.Entry<Integer, Producto> entry : productos.entrySet()) {
            Producto producto = entry.getValue();
            model.addRow(new Object[]{
                    producto.getId_producto(),
                    producto.getNombre_producto(),
                    producto.getCantidad(),
                    producto.getPrecio_pro(),
            });
        }

        tableProductos.setModel(model);
    }

}
