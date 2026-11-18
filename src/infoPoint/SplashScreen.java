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

    private JProgressBar barraProgreso;
    private JLabel lblMensaje;

    public SplashScreen() {
        // setUndecorated(true) elimina la barra de título y bordes (Requisito obligatorio)
        setUndecorated(true);
        setSize(450, 220);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 3));
        panelPrincipal.setBackground(Color.WHITE);

        // Título principal de la aplicación
        JLabel lblTitulo = new JLabel("Suite Biblioteca Municipal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 102, 204));

        // Mensaje de estado dinámico
        lblMensaje = new JLabel("Iniciando sistema...", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));

        // Barra de progreso real (0 a 100)
        barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true);
        barraProgreso.setForeground(new Color(0, 102, 204));

        JPanel panelSur = new JPanel(new BorderLayout(5, 5));
        panelSur.setOpaque(false);
        panelSur.add(lblMensaje, BorderLayout.NORTH);
        panelSur.add(barraProgreso, BorderLayout.SOUTH);

        panelPrincipal.add(lblTitulo, BorderLayout.CENTER);
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    // Método con hilos (Thread.sleep) para simular la carga y cambiar mensajes de estado
    public void iniciarCarga() {
        setVisible(true);

        for (int i = 0; i <= 100; i++) {
            barraProgreso.setValue(i);

            // Mensajes de estado cambiantes según el progreso
            if (i == 20) {
                lblMensaje.setText("Conectando con el servidor...");
            } else if (i == 50) {
                lblMensaje.setText("Cargando estilos y componentes...");
            } else if (i == 80) {
                lblMensaje.setText("Sincronizando datos de biblioteca...");
            } else if (i == 95) {
                lblMensaje.setText("¡Listo!");
            }

            try {
                Thread.sleep(90); // Pausa de 30ms por cada incremento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        dispose(); // Cierra el splash al terminar el bucle
    }
}