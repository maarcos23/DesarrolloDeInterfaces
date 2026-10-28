package gestionNotas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class SplashScreen extends JDialog {

    private JProgressBar barraProgreso;

    public SplashScreen() {
        setUndecorated(true); 
        setSize(450, 300);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblImagen;
        File archivoImagen = new File("Lib/splash.png");
        if (archivoImagen.exists()) {
            ImageIcon iconoOriginal = new ImageIcon("Lib/splash.png");
            Image escalada = iconoOriginal.getImage().getScaledInstance(440, 220, Image.SCALE_SMOOTH);
            lblImagen = new JLabel(new ImageIcon(escalada), SwingConstants.CENTER);
        } else {
            lblImagen = new JLabel("GESTOR DE NOTAS PRO", SwingConstants.CENTER);
            lblImagen.setFont(new Font("Arial", Font.BOLD, 24));
            lblImagen.setForeground(new Color(0, 102, 204));
            lblImagen.setPreferredSize(new Dimension(440, 220));
        }

        barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true);
        barraProgreso.setForeground(new Color(0, 102, 204));
        
        mainPanel.add(lblImagen, BorderLayout.CENTER);
        mainPanel.add(barraProgreso, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    public void updateProgress(int value) {
        barraProgreso.setValue(value);
        barraProgreso.setString("Cargando componentes... " + value + "%");
    }
}
