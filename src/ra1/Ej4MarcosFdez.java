
package ra1;

import java.awt.FlowLayout;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 */
public class Ej4MarcosFdez extends JFrame {
	
	private JTextField tf;
	private JPasswordField pf;
	
	private JButton btnComprobar;
	private JLabel lblMensaje;
	

	public Ej4MarcosFdez () {
		setTitle("Ej4 Usuario y contraseña");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		btnComprobar = new JButton("Iniciar Sesión");
		
		
		tf = new JTextField(15);
		
		pf = new JPasswordField(15);
		lblMensaje = new JLabel("");
		
		
		add(new JLabel("Usuario"));
		add(tf);
		add(new JLabel("Contraseña"));
		add(pf);
		add(btnComprobar);
		add(lblMensaje);
		
		//accion del boton
		btnComprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String usuario = tf.getText();
            	String pas = String.valueOf(pf.getPassword());
            	
            	boolean us = usuario.equals("marcos");
            	boolean ps = pas.equals("1234");
            	
            	if (!us && !ps) {
                    lblMensaje.setText("El usuario y la contraseña son incorrectos.");
                } else if (!us) {
                    lblMensaje.setText("El usuario no es correcto");
                } else if (!ps) {
                    lblMensaje.setText("La contraseña  no es correcta.");
                } else {
                    lblMensaje.setText("Correcto");
                }
            }
            	
			
		});
		
		
		
		setVisible(true);
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ej4MarcosFdez();

	}

}
