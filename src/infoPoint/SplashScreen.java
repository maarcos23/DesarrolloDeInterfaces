package infoPoint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class SplashScreen extends JDialog {

    private JProgressBar barra;
    private JLabel lblEstado;

    public SplashScreen() {
        setUndecorated(true);
        setSize(420, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        JLabel lblTitulo = new JLabel("InfoPoint - Biblioteca", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        lblEstado = new JLabel("Iniciando aplicación...", SwingConstants.CENTER);

        barra = new JProgressBar(0, 100);
        barra.setStringPainted(true);

        panel.add(lblTitulo, BorderLayout.CENTER);
        panel.add(lblEstado, BorderLayout.NORTH);
        panel.add(barra, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    public void iniciarCarga() {
        setVisible(true);
        for (int i = 0; i <= 100; i++) {
            barra.setValue(i);
            if (i == 30) lblEstado.setText("Cargando módulos...");
            if (i == 70) lblEstado.setText("Preparando datos...");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dispose();
    }
}