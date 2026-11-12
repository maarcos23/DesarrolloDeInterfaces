package gestionNotas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
    private HashMap<String, String> contenidoNotas = new HashMap<>();
    private final File carpetaNotas = new File("mis_notas_guardadas");
    private UndoManager gestorDeshacer = new UndoManager();

    public NotasApp() {
        setTitle("Gestor Interactivo de Notas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(NotasApp.this, "¿Estás seguro de que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        if (!carpetaNotas.exists()) carpetaNotas.mkdirs();

        // 1. ÁREA DE TEXTO (Centro)
        areaTexto = new JTextArea();
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.getDocument().addUndoableEditListener(gestorDeshacer); 
        JScrollPane scrollEditor = new JScrollPane(areaTexto);

        // 2. LISTA LATERAL
        modeloNotas = new DefaultListModel<>();
        listaNotas = new JList<>(modeloNotas);
        listaNotas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String seleccion = listaNotas.getSelectedValue();
                    if (seleccion != null) {
                        areaTexto.setText(contenidoNotas.get(seleccion));
                        gestorDeshacer.discardAllEdits(); 
                    }
                }
            }
        });

        JScrollPane scrollLista = new JScrollPane(listaNotas);
        scrollLista.setPreferredSize(new Dimension(200, 400));

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.add(scrollLista, BorderLayout.CENTER);

        JPanel pnlBotonesLista = new JPanel(new FlowLayout());
        JButton btnNueva = new JButton("Nueva");
        JButton btnGuardar = new JButton("Guardar");
        btnNueva.setMnemonic(KeyEvent.VK_N); 
        btnGuardar.setMnemonic(KeyEvent.VK_G); 

        btnNueva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearNota();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarNota();
            }
        });

        pnlBotonesLista.add(btnNueva);
        pnlBotonesLista.add(btnGuardar);
        panelIzquierdo.add(pnlBotonesLista, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdo, scrollEditor);
        add(splitPane, BorderLayout.CENTER);

        crearMenu();
        cargarNotasDisco();
    }

    private void crearMenu() {
        JMenuBar mb = new JMenuBar();

        JMenu mArchivo = new JMenu("Archivo");
        mArchivo.setMnemonic(KeyEvent.VK_A);

        JMenuItem iImportar = new JMenuItem("Importar .txt");
        iImportar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        iImportar.addActionListener(e -> usarFileChooser(true));

        JMenuItem iExportar = new JMenuItem("Exportar a .txt");
        iExportar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        iExportar.addActionListener(e -> usarFileChooser(false));

        mArchivo.add(iImportar);
        mArchivo.add(iExportar);
        
        JMenu mEdicion = new JMenu("Edición");
        mEdicion.setMnemonic(KeyEvent.VK_E);

        JMenuItem iDeshacer = new JMenuItem("Deshacer");
        iDeshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        iDeshacer.addActionListener(e -> { if (gestorDeshacer.canUndo()) gestorDeshacer.undo(); });

        JMenuItem iRehacer = new JMenuItem("Rehacer");
        iRehacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        iRehacer.addActionListener(e -> { if (gestorDeshacer.canRedo()) gestorDeshacer.redo(); });

        mEdicion.add(iDeshacer);
        mEdicion.add(iRehacer);

        JMenu mApariencia = new JMenu("Apariencia");
        String[] temas = {"Metal", "Nimbus", "Windows (Nativo)"};
        String[] clases = {
            "javax.swing.plaf.metal.MetalLookAndFeel",
            "javax.swing.plaf.nimbus.NimbusLookAndFeel",
            UIManager.getSystemLookAndFeelClassName()
        };

        for (int i = 0; i < temas.length; i++) {
            final String clase = clases[i];
            JMenuItem item = new JMenuItem(temas[i]);
            item.addActionListener(e -> {
                try {
                    UIManager.setLookAndFeel(clase);
                    SwingUtilities.updateComponentTreeUI(this);
                } catch (Exception ex) { ex.printStackTrace(); }
            });
            mApariencia.add(item);
        }

        mb.add(mArchivo);
        mb.add(mEdicion);
        mb.add(mApariencia);
        setJMenuBar(mb);
    }

    private void crearNota() {
        String titulo = JOptionPane.showInputDialog(this, "Título de la nueva nota:");
        if (titulo != null && !titulo.trim().isEmpty() && !contenidoNotas.containsKey(titulo)) {
            modeloNotas.addElement(titulo);
            contenidoNotas.put(titulo, "");
            listaNotas.setSelectedValue(titulo, true);
            areaTexto.setText("");
            guardarNotasDisco();
        }
    }

    private void actualizarNota() {
        String sel = listaNotas.getSelectedValue();
        if (sel != null) {
            contenidoNotas.put(sel, areaTexto.getText());
            guardarNotasDisco();
            JOptionPane.showMessageDialog(this, "Nota '" + sel + "' actualizada correctamente.");
        }
    }

    private void usarFileChooser(boolean esAbrir) {
        JFileChooser fc = new JFileChooser();
        if (esAbrir) {
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
                    areaTexto.read(br, null);
                } catch (IOException ex) { ex.printStackTrace(); }
            }
        } else {
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(fc.getSelectedFile()))) {
                    areaTexto.write(bw);
                } catch (IOException ex) { ex.printStackTrace(); }
            }
        }
    }

    private void guardarNotasDisco() {
        for (String titulo : contenidoNotas.keySet()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(carpetaNotas, titulo + ".txt")))) {
                bw.write(contenidoNotas.get(titulo));
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    private void cargarNotasDisco() {
        File[] archivos = carpetaNotas.listFiles();
        if (archivos != null) {
            for (File f : archivos) {
                if (f.getName().endsWith(".txt")) {
                    String titulo = f.getName().replace(".txt", "");
                    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                        StringBuilder sb = new StringBuilder();
                        String linea;
                        while ((linea = br.readLine()) != null) sb.append(linea).append("\n");
                        modeloNotas.addElement(titulo);
                        contenidoNotas.put(titulo, sb.toString());
                    } catch (IOException e) { e.printStackTrace(); }
                }
            }
        }
    }
}