package infoPoint;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainApp extends JFrame {

    private List<Libro> libros;
    private List<Aviso> avisos;
    private PanelUsuario panelUsuario;

    public MainApp(String rol) {
        setTitle("InfoPoint (" + rol + ")");
        setSize(800, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        libros = new ArrayList<>();
        avisos = new ArrayList<>();

        PanelAdmin panelAdmin = new PanelAdmin(libros, avisos);
        panelUsuario = new PanelUsuario(libros, avisos);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Admin", panelAdmin);
        tabs.addTab("Usuario", panelUsuario);

        if ("USUARIO".equals(rol)) {
            tabs.setSelectedIndex(1);
        }

        add(tabs, BorderLayout.CENTER);
        setJMenuBar(crearMenu());
    }

    private JMenuBar crearMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu mArchivo = new JMenu("Archivo");
        mArchivo.setMnemonic(KeyEvent.VK_A);

        JMenuItem iRefrescar = new JMenuItem("Refrescar");
        iRefrescar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        iRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { panelUsuario.refrescarDatos(); }
        });

        JMenuItem iSalir = new JMenuItem("Salir");
        iSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        iSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { System.exit(0); }
        });

        mArchivo.add(iRefrescar);
        mArchivo.addSeparator();
        mArchivo.add(iSalir);

        JMenu mEstilo = new JMenu("Estilo");
        mEstilo.setMnemonic(KeyEvent.VK_E);

        JMenuItem iMetal = new JMenuItem("Metal");
        iMetal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarTema(UIManager.getCrossPlatformLookAndFeelClassName());
            }
        });

        JMenuItem iNimbus = new JMenuItem("Nimbus");
        iNimbus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarTema("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            }
        });

        mEstilo.add(iMetal);
        mEstilo.add(iNimbus);

        bar.add(mArchivo);
        bar.add(mEstilo);
        return bar;
    }

    private void cambiarTema(String clase) {
        try {
            UIManager.setLookAndFeel(clase);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo cambiar el tema.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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