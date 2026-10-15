package ra1;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class PaintAyuda extends JFrame {

	 private Color colorActual = Color.BLACK;
	private JPanel lienzo;
	private JComboBox<String>cb;
	
	private Graphics2D gr;
	private BufferedImage bf;
	
	private Point inicio;
	private Point fin;
	
	private JSlider sl;
	
	public PaintAyuda() {
		//Primero lo basico
		setTitle("Paint");
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Para tener el menu
		JMenuBar mb = new JMenuBar();
		JMenu color = new JMenu("Color");
		JMenu formas = new JMenu("Formas");
		
		//para no reescribir las formas
		String[]form = {"Dibujo libre", "Linea recta", "Circulo", "Cuadrado"};
		cb = new JComboBox<String>(form); 
		//bucle for para no tener q hacerlo por cada forma
		 for (String f : form) {
	            JMenuItem item = new JMenuItem(f);
	            item.addActionListener(e -> cb.setSelectedItem(f)); // Sincroniza menú con combo
	            formas.add(item);
	        }
		 JMenuItem colo = new JMenuItem("Elige color");
		 colo.addActionListener(e -> losColores());
		 
		 color.add(colo);
		 
		mb.add(color);
		mb.add(formas);
		setJMenuBar(mb);
		
		//para la barra de herramientas
		JPanel pnl = new JPanel();
		JButton colors = new JButton("Colores");
		colors.addActionListener(e -> losColores());
		sl = new JSlider(1, 50, 5);
		JLabel lb = new JLabel(sl.getValue()+ "px");
		
		pnl.add(cb);
		pnl.add(colors);
		pnl.add(sl);
		pnl.add(lb);
		
		add(pnl, BorderLayout.NORTH);
		
		//Lienzo(Donde se va a pintar)
		lienzo = new JPanel() {
		
		 protected void paintComponent(Graphics g) {
             super.paintComponent(g);
		//como ajustarlo
		if( bf == null || bf.getWidth() != getWidth() || bf.getHeight() != getHeight()) {
			bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bf.createGraphics();
			gr.setColor(Color.white);
			gr.fillRect(0, 0, getWidth(), getHeight());
		}
		//se muestra la memoria guardada
		g.drawImage(bf, 0, 0, null);
		
		String forma = (String) cb.getSelectedItem();
		if(inicio != null && fin != null && forma.equals("Dibujo libre")) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(colorActual);
			g2.setStroke(new BasicStroke(sl.getValue()));
		}
		
		 }
		};
		
		setVisible(true);
	}
	
	public void losColores() {
		Color c = JColorChooser.showDialog(this, "Elige Color", colorActual);
		if (c != null) {
			c = colorActual;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PaintAyuda();
	}

}
