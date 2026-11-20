package infoPoint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {

    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private boolean accesoConcedido = false;
    private String rol = "";

    public LoginDialog(JFrame parent) {
        super(parent, "Login InfoPoint", true);
        setSize(300, 160);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2, 8, 8));

        txtUsuario = new JTextField();
        txtUsuario.setToolTipText("Usuario: admin o usuario");

        txtPass = new JPasswordField();
        txtPass.setToolTipText("Contraseña: 1234");

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setMnemonic(KeyEvent.VK_E);
        btnEntrar.setToolTipText("Validar acceso");

        JButton btnSalir = new JButton("Salir");
        btnSalir.setMnemonic(KeyEvent.VK_S);
        btnSalir.setToolTipText("Cerrar programa");

        getRootPane().setDefaultButton(btnEntrar);

        add(new JLabel("  Usuario:"));
        add(txtUsuario);
        add(new JLabel("  Contraseña:"));
        add(txtPass);
        add(btnEntrar);
        add(btnSalir);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u = txtUsuario.getText().trim();
                String p = String.valueOf(txtPass.getPassword());

                if (u.equals("admin") && p.equals("1234")) {
                    accesoConcedido = true;
                    rol = "ADMIN";
                    dispose();
                } else if (u.equals("usuario") && p.equals("1234")) {
                    accesoConcedido = true;
                    rol = "USUARIO";
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                        LoginDialog.this,
                        "Credenciales incorrectas (usa admin/1234 o usuario/1234).",
                        "Error de acceso",
                        JOptionPane.ERROR_MESSAGE
                    );
                    txtUsuario.setText("");
                    txtPass.setText("");
                    txtUsuario.requestFocusInWindow();
                }
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public boolean isAccesoConcedido() { return accesoConcedido; }
    public String getRol() { return rol; }
}