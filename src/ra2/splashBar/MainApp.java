package ra2.splashBar;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainApp extends JFrame {

    public MainApp() {
        // Configuración de la ventana principal
        setTitle("Aplicación Principal");
        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Contenido de la ventana principal
        JLabel welcomeLabel = new JLabel("¡Bienvenido a la Aplicación Principal!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel);
    }
}