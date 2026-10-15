package juegoBanderas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class JDialogCustom extends JDialog{
	
	private boolean acertado;
	private String[]opciones;
	private JRadioButton[] radioButtons;
	
	public JDialogCustom(JFrame parent, String[] opciones, int respuesta) {
	
		super(parent, "¿Que bandera es?", true);
		this.opciones = opciones;
		
		setSize(600, 500);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout(10, 10));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel pnlOp = new JPanel(new GridLayout(opciones.length, 1, 5, 5));
		ButtonGroup grupo = new ButtonGroup();
		radioButtons = new JRadioButton[opciones.length];
	
		for(int i = 0; i<opciones.length; i++) {
			
			radioButtons[i] = new JRadioButton(opciones[i]);
			grupo.add(radioButtons[i]);
			pnlOp.add(radioButtons[i]);
			
		}
	
	}
	
}
