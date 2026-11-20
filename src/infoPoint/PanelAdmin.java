package infoPoint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PanelAdmin extends JPanel {

    private List<Libro> libros;
    private List<Aviso> avisos;

    private JTextField txtId;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAnio;
    private JButton btnGuardarLibro;

    private JTextField txtMensaje;
    private JTextField txtFecha;
    private JComboBox<String> cbPrioridad;
    private JButton btnGuardarAviso;

    public PanelAdmin(List<Libro> libros, List<Aviso> avisos) {
        this.libros = libros;
        this.avisos = avisos;

        setLayout(new GridLayout(1, 2, 12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        add(crearPanelLibros());
        add(crearPanelAvisos());
    }

    private JPanel crearPanelLibros() {
        JPanel p = new JPanel(new GridLayout(5, 2, 6, 6));
        p.setBorder(BorderFactory.createTitledBorder("Alta de Libro"));

        txtId = new JTextField();
        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtAnio = new JTextField();

        btnGuardarLibro = new JButton("Guardar Libro");
        btnGuardarLibro.setMnemonic(KeyEvent.VK_G);
        btnGuardarLibro.setToolTipText("Guardar libro (Enter)");

        p.add(new JLabel("ID:"));
        p.add(txtId);
        p.add(new JLabel("Título:"));
        p.add(txtTitulo);
        p.add(new JLabel("Autor:"));
        p.add(txtAutor);
        p.add(new JLabel("Año:"));
        p.add(txtAnio);
        p.add(new JLabel(""));
        p.add(btnGuardarLibro);

        btnGuardarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarLibro();
            }
        });

        return p;
    }

    private JPanel crearPanelAvisos() {
        JPanel p = new JPanel(new GridLayout(4, 2, 6, 6));
        p.setBorder(BorderFactory.createTitledBorder("Nuevo Aviso"));

        txtMensaje = new JTextField();
        txtFecha = new JTextField();
        cbPrioridad = new JComboBox<>(new String[]{"Baja", "Media", "Alta"});

        btnGuardarAviso = new JButton("Guardar Aviso");
        btnGuardarAviso.setMnemonic(KeyEvent.VK_V);
        btnGuardarAviso.setToolTipText("Publicar aviso");

        p.add(new JLabel("Mensaje:"));
        p.add(txtMensaje);
        p.add(new JLabel("Fecha:"));
        p.add(txtFecha);
        p.add(new JLabel("Prioridad:"));
        p.add(cbPrioridad);
        p.add(new JLabel(""));
        p.add(btnGuardarAviso);

        btnGuardarAviso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarAviso();
            }
        });

        return p;
    }

    private void guardarLibro() {
        String id = txtId.getText().trim();
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();
        String anioStr = txtAnio.getText().trim();

        if (id.isEmpty() || titulo.isEmpty() || autor.isEmpty() || anioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena todos los campos del libro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int anio = Integer.parseInt(anioStr);
            libros.add(new Libro(id, titulo, autor, anio));
            JOptionPane.showMessageDialog(this, "Libro guardado.");
            txtId.setText("");
            txtTitulo.setText("");
            txtAutor.setText("");
            txtAnio.setText("");
            txtId.requestFocusInWindow();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            txtAnio.setText("");
            txtAnio.requestFocusInWindow();
        }
    }

    private void guardarAviso() {
        String msg = txtMensaje.getText().trim();
        String fecha = txtFecha.getText().trim();
        String prio = (String) cbPrioridad.getSelectedItem();

        if (msg.isEmpty() || fecha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena mensaje y fecha.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        avisos.add(new Aviso(msg, fecha, prio));
        JOptionPane.showMessageDialog(this, "Aviso guardado.");
        txtMensaje.setText("");
        txtFecha.setText("");
        txtMensaje.requestFocusInWindow();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (SwingUtilities.getRootPane(this) != null) {
            SwingUtilities.getRootPane(this).setDefaultButton(btnGuardarLibro);
        }
    }
}