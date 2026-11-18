package infoPoint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog{
	
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private boolean accesoConcedido = false;
	
	public LoginDialog(JFrame parent) {
		super(parent, "Acceso a la Biblioteca", true);
		setSize(300, 200);
		setLocationRelativeTo(parent);
		setLayout(new GridLayout(3, 2));
		
		txtUsuario = new JTextField();
		txtPassword = new JPasswordField();
		JButton btnEntrar = new JButton("Entrar");
		JButton btnCancelar = new JButton("Cancelar");
		
		add(new JLabel("Usuario"));
		add(txtUsuario);
		add(new JLabel("Contraseña"));
		add(txtPassword);
		add(btnEntrar);
		add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		btnEntrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(String.valueOf(txtUsuario.getText()).equals("admin") &&
						String.valueOf(txtPassword.getPassword()).equals("1234")) {
					
					accesoConcedido = true;
					dispose();
				}else {
					txtUsuario.requestFocus();
					txtUsuario.setText("");
					txtPassword.setText("");
					
				}
				
			}
		});
	}
	
	public boolean isAccesoConcedido() {
		return accesoConcedido;
	}

}
