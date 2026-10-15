package ra1;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class IntentoPaintClase extends JPanel {

	private JPanel lienzo;
	private List<Point>points = new ArrayList<>();
	private List<Color>colors = new ArrayList<>();
	private Color colorActual = Color.black;
	private JSlider sl;
	private JComboBox<String> cb;
	
	public IntentoPaintClase() {
		setLayout (new BorderLayout());
		
		JToolBar tb = new JToolBar();
		tb.setFloatable(false);
		
		JButton colores = new JButton("Colores");
		sl = new JSlider(1, 50, 5);
		JLabel lb = new JLabel("5 px");
		sl.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				lb.setText(sl.getValue()+ "px");
			}
		});
		
		JButton limpiar = new JButton("Limpiar");
		JButton goma= new JButton("Goma de borrar");

		
		tb.add(colores);
		tb.addSeparator();
		tb.add(sl);
		tb.add(lb);
		tb.addSeparator();
		tb.add(limpiar);
		tb.addSeparator();
		tb.add(goma);
		
		add(tb, BorderLayout.NORTH);
		
		lienzo = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(colorActual);
				g2.setStroke(new BasicStroke(sl.getValue()));
				
				for(int i = 1; i<points.size(); i++) {
					Point p1 = points.get(i - 1);
					Point p2 = points.get(i);
					
					if (p1 != null && p2 != null) {
						g2.drawLine(p1.x, p1.y, p2.x, p2.y);
					}
				}
			}
		};
		
		lienzo.setBackground(Color.WHITE);
		lienzo.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				points.add(e.getPoint());
				lienzo.repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				points.add(null);
			}
		});
		lienzo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				points.add(e.getPoint());
				lienzo.repaint();
			}
		});
		
		add(lienzo, BorderLayout.CENTER);
			
		
	}
	
	protected void losColores() {
		
	}
	
	protected void aLimpiar() {
	
	}

    public void lasFiguras(Graphics2D g2, Point p1, Point p2, String forma) {
        
    }
	
	
	public static void main(String[]args) {

		JFrame frame = new JFrame ("Paint");
		IntentoPaintClase paint = new IntentoPaintClase();
		
		frame.setTitle("Paint");
		frame.setSize(600,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mb = new JMenuBar();
		
		JMenu colores = new JMenu("Colores");
		JMenu limpar = new JMenu("Limpiar");
		JMenu formas = new JMenu("Formas");
		
		JMenuItem col = new JMenuItem("Elige color");
		JMenuItem limp = new JMenuItem("Limpiar");
		JMenuItem rect = new JMenuItem("Linea Recta");
		
		colores.add(col);
		limpar.add(limp);
		formas.add(rect);
		
		mb.add(colores);
		mb.add(limpar);
		mb.add(formas);
		frame.setJMenuBar(mb);
		frame.add(paint);
		frame.setVisible(true);
		
	}

}