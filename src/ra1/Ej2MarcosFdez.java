/**
 * 
 */
package ra1;

import javax.swing.JFrame;
import javax.swing.*;

/**
 * 
 */
public class Ej2MarcosFdez extends JFrame {
	
	public Ej2MarcosFdez() {
		
		setTitle("Ejercicio 2 JMenuBar");
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creamos la barra de menus
		JMenuBar menuBar = new JMenuBar();
		
		//creamos los menus
		JMenu Archivo = new JMenu("Archivo");
		JMenu Editar = new JMenu("Editar");
		JMenu Formato = new JMenu("Formato");
		
		//creamos los elementos de cada menu
		JMenuItem Nuevo = new JMenuItem("Nuevo");
		JMenuItem Abrir = new JMenuItem("Abrir");
		JMenuItem Salir = new JMenuItem("Salir");
		
		Salir.addActionListener(e -> System.exit(0));
		
		Archivo.add(Nuevo);
		Archivo.add(Abrir);
		Archivo.add(Salir);
		
		JMenuItem Copiar = new JMenuItem("Copiar");
		JMenuItem Pegar = new JMenuItem("Pegar");
		
		Editar.add(Copiar);
		Editar.add(Pegar);
		
		JMenuItem Negrita = new JMenuItem("Negrita");
		JMenuItem Cursiva = new JMenuItem("Cursiva");
		
		Formato.add(Negrita);
		Formato.add(Cursiva);
		
		menuBar.add(Archivo);
		menuBar.add(Editar);
		menuBar.add(Formato);
		
		setJMenuBar(menuBar);
		
		setVisible(true);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ej2MarcosFdez();
	}

}