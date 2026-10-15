package ra1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * 
 */
public class EJ6MarcosFdez extends JFrame {
	
	
	private JSpinner sp;
	private JSlider sl;
	private JTextArea ta;
	private JScrollPane cp;
	/**
	 * @throws HeadlessException
	 */
	
	public EJ6MarcosFdez(){
		
		setTitle("ejercicio 6");
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		ta = new JTextArea("tamaño de la letra");
		cp = new JScrollPane(ta);				
		sp = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1));
		sl = new JSlider(1, 100, 10);
		
		sp.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
				int valor = (int) sp.getValue();
				if (sl.getValue() != valor) {
					sl.setValue(valor);
					ta.setFont(new Font("Arial", Font.PLAIN, valor));
				}
				
			}
			
		});
		
		sl.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
				int valor = (int) sl.getValue();
				if ((int)sp.getValue() != valor) {
					sp.setValue(valor);
					ta.setFont(new Font("Arial", Font.PLAIN, valor));
				}
				
			}
			
		});
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.add(sl);
		panelSuperior.add(sp);
		
		add(panelSuperior, BorderLayout.NORTH);
		add(cp, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new EJ6MarcosFdez();
		
	}

}
