package gestionNotas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.undo.UndoManager;

public class NotasApp extends JFrame {

    private DefaultListModel<String> modeloNotas;
    private JList<String> listaNotas;
    private JTextArea areaTexto;
    
    // Diccionario para guardar el texto de cada nota en la memoria RAM
    private HashMap<String, String> memoriaNotas = new HashMap<>();
    
    // Gestor de Deshacer
    private UndoManager gestorDeshacer = new UndoManager();

    public NotasApp() {
        setTitle("Bloc de Notas Swing");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. ÁREA DE TEXTO (Centro)
        areaTexto = new JTextArea();
        areaTexto.setLineWrap(true);
        // Conectar el deshacer al área de texto
        areaTexto.getDocument().addUndoableEditListener(gestorDeshacer);
        JScrollPane scrollCentro = new JScrollPane(areaTexto);
        add(scrollCentro, BorderLayout.CENTER);

        // 2. LISTA DE NOTAS Y BOTONES (Izquierda)
        modeloNotas = new DefaultListModel<>();
        listaNotas = new JList<>(modeloNotas);
        
        // Evento: Al hacer clic en una nota de la lista, mostrar su texto
        listaNotas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Para evitar que se dispare dos veces
                    String seleccion = listaNotas.getSelectedValue();
                    if (seleccion != null) {
                        areaTexto.setText(memoriaNotas.get(seleccion));
                        gestorDeshacer.discardAllEdits(); // Limpia el historial al cambiar de nota
                    }
                }
            }
        });

        JScrollPane scrollIzquierda = new JScrollPane(listaNotas);
        scrollIzquierda.setPreferredSize(new Dimension(200, 0));

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.add(scrollIzquierda, BorderLayout.CENTER);

        // Botones debajo de la lista
        JPanel pnlBotonesLista = new JPanel(new FlowLayout());
        JButton btnNueva = new JButton("Nueva");
        JButton btnGuardar = new JButton("Guardar");
        
        btnNueva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Título de la nueva nota:");
                if (titulo != null && !titulo.isEmpty()) {
                    modeloNotas.addElement(titulo); // La añade a la vista
                    memoriaNotas.put(titulo, "");   // Le crea un hueco vacío en memoria
                    listaNotas.setSelectedValue(titulo, true); // La selecciona
                    areaTexto.setText("");
                }
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = listaNotas.getSelectedValue();
                if (seleccion != null) {
                    memoriaNotas.put(seleccion, areaTexto.getText()); // Actualiza el texto en memoria
                    JOptionPane.showMessageDialog(NotasApp.this, "Nota guardada");
                }
            }
        });

        pnlBotonesLista.add(btnNueva);
        pnlBotonesLista.add(btnGuardar);
        panelIzquierdo.add(pnlBotonesLista, BorderLayout.SOUTH);

        add(panelIzquierdo, BorderLayout.WEST);

        // 3. BARRA DE MENÚ SUPERIOR
        crearMenu();
    }

    private void crearMenu() {
        JMenuBar barra = new JMenuBar();

        // Menú Edición (Deshacer/Rehacer)
        JMenu menuEdicion = new JMenu("Edición");
        menuEdicion.setMnemonic(KeyEvent.VK_E); // Atajo: Alt + E

        JMenuItem itemDeshacer = new JMenuItem("Deshacer");
        // Acelerador: Ctrl + Z
        itemDeshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        itemDeshacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestorDeshacer.canUndo()) {
                    gestorDeshacer.undo();
                }
            }
        });

        JMenuItem itemRehacer = new JMenuItem("Rehacer");
        // Acelerador: Ctrl + Y
        itemRehacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        itemRehacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestorDeshacer.canRedo()) {
                    gestorDeshacer.redo();
                }
            }
        });

        menuEdicion.add(itemDeshacer);
        menuEdicion.add(itemRehacer);

        // Menú Apariencia (Look & Feel)
        JMenu menuAspecto = new JMenu("Apariencia");
        JMenuItem itemMetal = new JMenuItem("Metal");
        JMenuItem itemWindows = new JMenuItem("Sistema");

        itemMetal.addActionListener(e -> cambiarAspecto("javax.swing.plaf.metal.MetalLookAndFeel"));
        itemWindows.addActionListener(e -> cambiarAspecto(UIManager.getSystemLookAndFeelClassName()));

        menuAspecto.add(itemMetal);
        menuAspecto.add(itemWindows);

        barra.add(menuEdicion);
        barra.add(menuAspecto);
        setJMenuBar(barra);
    }

    private void cambiarAspecto(String clase) {
        try {
            UIManager.setLookAndFeel(clase);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}