package ra1;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ej5MarcosFdez extends JFrame{
	
	private JScrollPane sp;
	private JTextArea ta;
	
	public Ej5MarcosFdez()  {
		
		setTitle("ejercicio 5");
		setSize(600,500);
		
		JMenuBar mb= new JMenuBar();
		ta = new JTextArea("");
		JMenu m = new JMenu("Archivo");
		JMenuItem mi = new JMenuItem("Nuevo");
		sp = new JScrollPane(ta);
		
		mb.add(m);
		m.add(mi);
		
		mi.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add(sp);
				ta.setSize(600,500);
				ta.setVisible(true);
				ta.setText("");
				
				
				/*
				 * Revalidate me lo ha dado la IA pero me he encargado de 
				 * entenderlo antes de ponerlo
				 */
				
				//Reorganiza el espacio y los huecos cuando metes o sacas algo
				revalidate(); 
			}
		});
		
		
		setJMenuBar(mb);
		setVisible(true);
		
	}
	
	public static void main (String[]args){
		
		new Ej5MarcosFdez();
	}

}
