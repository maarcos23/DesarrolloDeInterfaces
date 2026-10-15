package ra1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Ej7MarcosFdez extends JFrame {

	
	private JComboBox<Object> cb;
	private JTextArea ta;
	
	public Ej7MarcosFdez(){
		
		setTitle("Ejercicio 7");
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		String[] fuentes = {"Normal", "Negrita", "Cursiva", "Cursiva+Negrita"};
		cb = new JComboBox<Object>(fuentes);
		ta = new JTextArea();
		
		
		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (cb.getSelectedItem() == "Normal") {
					ta.setFont(new Font("Arial", 0, 12));
				}else if (cb.getSelectedItem() == "Negrita") {
					ta.setFont(new Font("Arial", 1, 12));
				}else if(cb.getSelectedItem() == "Cursiva") {
					ta.setFont(new Font("Arial", 2, 12));
				}else if(cb.getSelectedItem() == "Cursiva+Negrita") {
					ta.setFont(new Font("Arial", 3, 12));
				}	
			}
			
		});
		
		JPanel ps = new JPanel();
		ps.add(cb);
		add(ta);
		
		add(ps, BorderLayout.NORTH);
		setVisible(true);
		
	}
	
	public static void main (String[]args) {
		
		new Ej7MarcosFdez();
	}

}
