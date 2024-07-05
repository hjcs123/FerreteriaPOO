package org.example;

import VISTAS.CLIENTE;
import VISTAS.FERRETERIA;
import VISTAS.LOGIN;
import org.example.Beans.Categoria;
import org.example.Beans.Cliente;
import org.example.Beans.Producto;
import org.example.Beans.Proveedor;
import org.example.Config.ConnectionBD;
import org.example.DAO.CategoriaDAO;
import org.example.DAO.ClienteDAO;
import org.example.DAO.ProductoDAO;
import org.example.DAO.ProveedorDAO;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        /*CategoriaDAO categoriaDAO;

        try {

            ConnectionBD connectionBD = new ConnectionBD();
            connectionBD.eliminarDatosTables();

            for (int i = 1; i <= 5; i++) {
                Categoria categoria = new Categoria();
                categoria.setNombre("Categoria " + i);
                categoriaDAO = new CategoriaDAO();
                categoriaDAO.agregarCategoria(categoria);
            }

            System.out.println("Categorias creadas");

//            CREAR 5 PROVEEDORES
            for (int i = 1; i <= 5; i++) {
                Proveedor proveedor = new Proveedor();
                proveedor.setRuc(123456789 + i);
                proveedor.setNombre_proveedor("Proveedor " + i);
                proveedor.setDireccion("Direccion " + i);
                proveedor.setTelefono(123456789);
                proveedor.setEmail("proveedor" + i + "@gmail.com");
                ProveedorDAO proveedorDAO = new ProveedorDAO();
                proveedorDAO.agregarProveedor(proveedor);
            }

            System.out.println("Proveedores creados");

//            CREAR 5 PRODUCTOS
            for (int i = 1; i <= 5; i++) {
                Producto producto = new Producto();
                producto.setNombre_producto("Producto " + i);
                producto.setCantidad(10);
                producto.setPrecio_pro(10.0);
                ProductoDAO productoDAO = new ProductoDAO();
                productoDAO.agregarProducto(producto);
            }

            System.out.println("Productos creados");

//            CREAR 5 CLIENTES
            for (int i = 0; i < 5; i++) {
                Cliente cliente = new Cliente();
                cliente.setDni(12345678 + i);
                cliente.setNombre_cliente("Cliente " + i);
                cliente.setDireccion("Direccion " + i);
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.agregarCliente(cliente);
            }

            System.out.println("Clientes creados");

            CategoriaDAO categoriaDAO1 = new CategoriaDAO();
            Map<Integer, Categoria> categorias = categoriaDAO1.buscarCategoriaPorNombre("Categoria");
            for (Categoria categoria : categorias.values()) {
                System.out.println(categoria.getId());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


 */

//        public static void main(String[] args) throws SQLException, IOException {
        JFrame frame = new JFrame("LOGIN");
        frame.setContentPane(new LOGIN().panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//        }

    }
}