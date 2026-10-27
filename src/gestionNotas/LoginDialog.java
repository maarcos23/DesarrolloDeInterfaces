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
    private boolean autenticado = false; // Aquí guardamos si acertó

    public LoginDialog(JFrame parent) {
        super(parent, "Iniciar Sesión", true); // true = Modal (bloquea)
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // Panel central para introducir datos
        JPanel pnlCampos = new JPanel(new GridLayout(2, 2, 5, 5));
        txtUsuario = new JTextField();
        txtUsuario.setToolTipText("Introduce tu usuario"); // Requisito: ToolTip
        
        txtPassword = new JPasswordField();
        txtPassword.setToolTipText("Introduce tu contraseña");
        
        pnlCampos.add(new JLabel("  Usuario:"));
        pnlCampos.add(txtUsuario);
        pnlCampos.add(new JLabel("  Contraseña:"));
        pnlCampos.add(txtPassword);

        // Panel inferior para botones
        JPanel pnlBotones = new JPanel(new FlowLayout());
        JButton btnEntrar = new JButton("Entrar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usr = txtUsuario.getText();
                String pwd = String.valueOf(txtPassword.getPassword());

                if (usr.equals("admin") && pwd.equals("1234")) {
                    autenticado = true;
                    dispose(); // Cierra el diálogo si es correcto
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra sin autenticar
            }
        });

        pnlBotones.add(btnEntrar);
        pnlBotones.add(btnCancelar);

        add(pnlCampos, BorderLayout.CENTER);
        add(pnlBotones, BorderLayout.SOUTH);
    }

    public boolean isAutenticado() {
        return autenticado;
    }
}