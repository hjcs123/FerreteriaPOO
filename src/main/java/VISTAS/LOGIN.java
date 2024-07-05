package VISTAS;

import org.example.DAO.UsuarioDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class LOGIN {
    private JTextField txtUsuario;
    private JTextField txtPassword;
    private JButton btnLogin;
    private JLabel lblUsuario;
    private JLabel lblPass;
    public JPanel panelLogin;

    public LOGIN() {

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String password = txtPassword.getText();

                UsuarioDAO usuarioDAO = null;
                try {
                    usuarioDAO = new UsuarioDAO();
                    if (usuarioDAO.autenticarUsuario(usuario, password)) {
                        JFrame frame = new JFrame("FERRETERIA");
                        frame.setContentPane(new FERRETERIA().panelMain);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }

}
