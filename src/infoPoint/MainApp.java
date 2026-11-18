package infoPoint;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame{

	List<Libro> libro = new ArrayList<>();
	List<Aviso> aviso = new ArrayList<>();
	
	public MainApp() {
     
		setTitle("Biblioteca Municipal");
		setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	public static void main(String[]args) {
		
	LoginDialog lg = new LoginDialog(null);
	lg.setVisible(true);
	
	if(lg.isAccesoConcedido()== true) {
		MainApp ventanaPrincipal = new MainApp();
        ventanaPrincipal.setVisible(true);
	}else {
		System.exit(0);
	}
		
	}
	
}
