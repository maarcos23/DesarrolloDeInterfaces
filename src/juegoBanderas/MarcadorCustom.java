package juegoBanderas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MarcadorCustom extends JLabel {

	
	public MarcadorCustom(String texto) {
		super(texto, SwingConstants.CENTER);
		
		setOpaque(true);
		
		setBackground(Color.DARK_GRAY);
		setForeground(Color.YELLOW);
		setFont(new Font("Arial", Font.BOLD, 18));
	}
}
