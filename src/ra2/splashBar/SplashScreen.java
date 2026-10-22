package ra2.splashBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class SplashScreen extends JDialog {

    private JProgressBar progressBar;

    public SplashScreen() {
        // Configuración de la ventana Splash
        setSize(400, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setLayout(new BorderLayout());
        setUndecorated(true); // Quitamos la barra de título y botones al cuadro de diálogo

        // Panel principal con logo y mensaje
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Título o Logo
        JLabel logoLabel = new JLabel("SPLASH SCREEN", JLabel.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setForeground(new Color(0, 102, 204));

        // Mensaje de estado
        JLabel messageLabel = new JLabel("Cargando recursos, por favor espere...", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(Color.GRAY);

        // Barra de progreso
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Muestra el porcentaje en texto
        progressBar.setForeground(new Color(0, 102, 204));

        // Agregar componentes al panel principal
        mainPanel.add(logoLabel, BorderLayout.CENTER);
        mainPanel.add(messageLabel, BorderLayout.SOUTH);

        // Agregar panel principal y barra de progreso al JDialog
        add(mainPanel, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
    }

    // Método para actualizar el valor de la barra desde el bucle
    public void updateProgress(int value) {
        progressBar.setValue(value);
    }
}