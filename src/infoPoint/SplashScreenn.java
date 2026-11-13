package infoPoint;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class SplashScreenn extends JDialog{

	private JProgressBar pb;
	
	public SplashScreenn() {
	setSize(400,250);
	setLocationRelativeTo(null);
	setLayout(new BorderLayout());
	setUndecorated(true);
	
	JPanel panel = new JPanel(new BorderLayout());
	panel.setBackground(Color.WHITE);
	
	
	
	}
}
