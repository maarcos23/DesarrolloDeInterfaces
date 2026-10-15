package ra1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ej8MarcosFdez extends JFrame {

    private JTextArea ta;
    private JScrollPane sc;
    private JSpinner sp;
    private JSlider sl;
    private JCheckBox chkNegrita;
    private JCheckBox chkCursiva;
    private JCheckBoxMenuItem menuNegrita;
    private JCheckBoxMenuItem menuCursiva;
    private JComboBox cb;

    public Ej8MarcosFdez() {
        setTitle("Ejercicio 8");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menú
        JMenuBar mb = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
        JMenu estilo = new JMenu("Estilo");

        JMenuItem nuevo = new JMenuItem("Nuevo");
        JMenuItem salir = new JMenuItem("Salir");

        // Limpiar texto
        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.setText("");
            }
        });

        // Salir
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        archivo.add(nuevo);
        archivo.addSeparator();
        archivo.add(salir);

        menuNegrita = new JCheckBoxMenuItem("Negrita");
        menuCursiva = new JCheckBoxMenuItem("Cursiva");

        estilo.add(menuNegrita);
        estilo.add(menuCursiva);

        mb.add(archivo);
        mb.add(estilo);
        setJMenuBar(mb);

        // Área central con Scroll
        ta = new JTextArea("Escribe tu texto aquí...");
        sc = new JScrollPane(ta);

        // Fuentes (usamos el atributo cb sin volver a declararlo)
        String[] fuentes = {"Arial", "Calibri", "Times New Roman", "Georgia"};
        cb = new JComboBox<>(fuentes);

        sp = new JSpinner(new SpinnerNumberModel(14, 10, 60, 1));
        sl = new JSlider(10, 60, 14);

        chkNegrita = new JCheckBox("Negrita");
        chkCursiva = new JCheckBox("Cursiva");

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(cb);
        panelSuperior.add(sp);
        panelSuperior.add(sl);
        panelSuperior.add(chkNegrita);
        panelSuperior.add(chkCursiva);

        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarFuente();
            }
        });

        // Spinner a Slider
        sp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valor = (int) sp.getValue();
                if (sl.getValue() != valor) {
                    sl.setValue(valor);
                }
                actualizarFuente();
            }
        });

        // Slider a Spinner
        sl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valor = sl.getValue();
                if ((int) sp.getValue() != valor) {
                    sp.setValue(valor);
                }
                actualizarFuente();
            }
        });

        // Negrita
        chkNegrita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chkNegrita.isSelected()) {
                    chkCursiva.setSelected(false);
                    menuCursiva.setSelected(false);
                }
                menuNegrita.setSelected(chkNegrita.isSelected());
                actualizarFuente();
            }
        });

        menuNegrita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuNegrita.isSelected()) {
                    chkCursiva.setSelected(false);
                    menuCursiva.setSelected(false);
                }
                chkNegrita.setSelected(menuNegrita.isSelected());
                actualizarFuente();
            }
        });

        // Cursiva
        chkCursiva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chkCursiva.isSelected()) {
                    chkNegrita.setSelected(false);
                    menuNegrita.setSelected(false);
                }
                menuCursiva.setSelected(chkCursiva.isSelected());
                actualizarFuente();
            }
        });

        menuCursiva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuCursiva.isSelected()) {
                    chkNegrita.setSelected(false);
                    menuNegrita.setSelected(false);
                }
                chkCursiva.setSelected(menuCursiva.isSelected());
                actualizarFuente();
            }
        });

        add(panelSuperior, BorderLayout.NORTH);
        add(sc, BorderLayout.CENTER);

        actualizarFuente();
        setVisible(true);
    }

    private void actualizarFuente() {
        String nombreFuente = (String) cb.getSelectedItem();
        int tamano = (int) sp.getValue();

        int estilo = Font.PLAIN;
        if (chkNegrita.isSelected()) {
            estilo = Font.BOLD;
        } else if (chkCursiva.isSelected()) {
            estilo = Font.ITALIC;
        }

        ta.setFont(new Font(nombreFuente, estilo, tamano));
    }

    public static void main(String[] args) {
        new Ej8MarcosFdez();
    }
}