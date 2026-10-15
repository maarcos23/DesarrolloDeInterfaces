package juegoBanderas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;


public class BotonCustom extends JButton {
	
	public BotonCustom(String text) {
		super(text);
		setBackground(Color.GREEN);
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 14));
	}
}
