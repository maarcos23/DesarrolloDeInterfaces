package gestionNotas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private boolean autenticado = false;

    public LoginDialog(JFrame parent) {
        super(parent, "Iniciar Sesión", true); 
        setSize(320, 180);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel pnlCampos = new JPanel(new GridLayout(2, 2, 10, 10));
        txtUsuario = new JTextField();
        txtUsuario.setToolTipText("Introduce tu nombre de usuario (ej: admin)"); 

        txtPassword = new JPasswordField();
        txtPassword.setToolTipText("Introduce tu contraseña (ej: 1234)");

        pnlCampos.add(new JLabel("  Usuario:"));
        pnlCampos.add(txtUsuario);
        pnlCampos.add(new JLabel("  Contraseña:"));
        pnlCampos.add(txtPassword);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usr = txtUsuario.getText().trim();
                String pwd = String.valueOf(txtPassword.getPassword()); 

                if (usr.equals("marcos") && pwd.equals("1234")) {
                    autenticado = true;
                    dispose(); 
                } else {

                    JOptionPane.showMessageDialog(LoginDialog.this, 
                        "Usuario o contraseña incorrectos.", 
                        "Error de acceso", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBotones.add(btnEntrar);
        pnlBotones.add(btnCancelar);

        add(pnlCampos, BorderLayout.CENTER);
        add(pnlBotones, BorderLayout.SOUTH);
    }

    public boolean isAutenticado() {
        return autenticado;
    }
}