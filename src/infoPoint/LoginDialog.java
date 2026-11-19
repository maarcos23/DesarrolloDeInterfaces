package infoPoint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private boolean accesoConcedido = false;
    private String rol = "";

    public LoginDialog(JFrame parent) {
        super(parent, "Acceso a la Biblioteca", true);
        setSize(320, 190);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2, 8, 8));

        txtUsuario = new JTextField();
        txtUsuario.setToolTipText("Introduce 'admin' o 'usuario'");
        txtPassword = new JPasswordField();
        txtPassword.setToolTipText("Introduce la contraseña ('1234')");

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setToolTipText("Iniciar sesión");
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setToolTipText("Salir de la aplicación");

        getRootPane().setDefaultButton(btnEntrar);

        add(new JLabel("  Usuario:"));
        add(txtUsuario);
        add(new JLabel("  Contraseña:"));
        add(txtPassword);
        add(btnEntrar);
        add(btnCancelar);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText().trim();
                String pass = String.valueOf(txtPassword.getPassword());

                if (usuario.equals("admin") && pass.equals("1234")) {
                    accesoConcedido = true;
                    rol = "ADMIN";
                    dispose();
                } else if (usuario.equals("usuario") && pass.equals("1234")) {
                    accesoConcedido = true;
                    rol = "USUARIO";
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                        LoginDialog.this,
                        "Usuario o contraseña incorrectos.",
                        "Error de acceso",
                        JOptionPane.ERROR_MESSAGE
                    );
                    txtUsuario.setText("");
                    txtPassword.setText("");
                    txtUsuario.requestFocusInWindow();
                }
            }
        });
    }

    public boolean isAccesoConcedido() {
        return accesoConcedido;
    }

    public String getRol() {
        return rol;
    }
}