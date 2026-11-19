package infoPoint;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainApp extends JFrame {

    private List<Libro> libros;
    private List<Aviso> avisos;

    public MainApp(String rol) {
        setTitle("InfoPoint - Biblioteca Municipal (" + rol + ")");
        setSize(820, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        libros = new ArrayList<>();
        avisos = new ArrayList<>();

        PanelAdmin panelAdmin = new PanelAdmin(libros, avisos);
        PanelUsuario panelUsuario = new PanelUsuario(libros, avisos);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Panel Admin", panelAdmin);
        pestanas.addTab("Terminal Usuario", panelUsuario);

        if ("USUARIO".equals(rol)) {
            pestanas.setSelectedIndex(1);
        }

        add(pestanas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen();
        splash.iniciarCarga();

        LoginDialog login = new LoginDialog(null);
        login.setVisible(true);

        if (login.isAccesoConcedido()) {
            MainApp app = new MainApp(login.getRol());
            app.setVisible(true);
        } else {
            System.exit(0);
        }
    }
}