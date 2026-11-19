package infoPoint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtId;
    private JTextField txtAnio;
    private JButton btnGuardarLibro;

    private JTextField txtMensaje;
    private JTextField txtFecha;
    private JComboBox<String> cbPrioridad;
    private JButton btnGuardarAviso;

    public PanelAdmin(List<Libro> libros, List<Aviso> avisos) {
        this.libros = libros;
        this.avisos = avisos;

        setLayout(new GridLayout(1, 2, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        add(crearPanelLibro());
        add(crearPanelAviso());
    }

    private JPanel crearPanelLibro() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Alta de Libro"));

        txtTitulo = new JTextField();
        txtTitulo.setToolTipText("Título del libro");
        txtAutor = new JTextField();
        txtAutor.setToolTipText("Autor del libro");
        txtId = new JTextField();
        txtId.setToolTipText("Identificador único del libro");
        txtAnio = new JTextField();
        txtAnio.setToolTipText("Año de publicación (numérico)");

        btnGuardarLibro = new JButton("Guardar Libro");
        btnGuardarLibro.setToolTipText("Guardar libro (Enter)");

        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);
        panel.add(new JLabel("Autor:"));
        panel.add(txtAutor);
        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Año (numérico):"));
        panel.add(txtAnio);
        panel.add(new JLabel(""));
        panel.add(btnGuardarLibro);

        btnGuardarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarLibro();
            }
        });

        return panel;
    }

    private JPanel crearPanelAviso() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Nuevo Aviso"));

        txtMensaje = new JTextField();
        txtMensaje.setToolTipText("Texto del aviso");
        txtFecha = new JTextField();
        txtFecha.setToolTipText("Fecha del aviso");
        cbPrioridad = new JComboBox<>(new String[]{"Baja", "Media", "Alta"});
        cbPrioridad.setToolTipText("Prioridad del aviso");

        btnGuardarAviso = new JButton("Guardar Aviso");
        btnGuardarAviso.setToolTipText("Publicar aviso");

        panel.add(new JLabel("Mensaje:"));
        panel.add(txtMensaje);
        panel.add(new JLabel("Fecha:"));
        panel.add(txtFecha);
        panel.add(new JLabel("Prioridad:"));
        panel.add(cbPrioridad);
        panel.add(new JLabel(""));
        panel.add(btnGuardarAviso);

        btnGuardarAviso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarAviso();
            }
        });

        return panel;
    }

    private void guardarLibro() {
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();
        String id = txtId.getText().trim();
        String anioTexto = txtAnio.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty() || id.isEmpty() || anioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena todos los campos del libro.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int anio = Integer.parseInt(anioTexto);
            libros.add(new Libro(id, titulo, autor, anio));
            JOptionPane.showMessageDialog(this, "Libro guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            txtTitulo.setText("");
            txtAutor.setText("");
            txtId.setText("");
            txtAnio.setText("");
            txtTitulo.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero (ej. 2024).", "Error numérico", JOptionPane.ERROR_MESSAGE);
            txtAnio.setText("");
            txtAnio.requestFocus();
        }
    }

    private void guardarAviso() {
        String mensaje = txtMensaje.getText().trim();
        String fecha = txtFecha.getText().trim();
        String prioridad = (String) cbPrioridad.getSelectedItem();

        if (mensaje.isEmpty() || fecha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena el mensaje y la fecha del aviso.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        avisos.add(new Aviso(mensaje, fecha, prioridad));
        JOptionPane.showMessageDialog(this, "Aviso publicado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        txtMensaje.setText("");
        txtFecha.setText("");
        txtMensaje.requestFocus();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (SwingUtilities.getRootPane(this) != null) {
            SwingUtilities.getRootPane(this).setDefaultButton(btnGuardarLibro);
        }
    }
}