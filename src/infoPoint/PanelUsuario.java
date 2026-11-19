package infoPoint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.undo.UndoManager;

public class PanelUsuario extends JPanel {

    private List<Libro> libros;
    private List<Aviso> avisos;
    private DefaultListModel<Libro> modeloLibros;
    private DefaultListModel<Aviso> modeloAvisos;

    private UndoManager undoManager = new UndoManager();
    private JTextArea areaTexto;
    private JScrollPane sp;
    private JButton btnActualizar;
    private JButton btnDeshacer;
    private JButton btnRehacer;
    private JButton btnLimpiar;

    public PanelUsuario(List<Libro> libros, List<Aviso> avisos) {
        this.libros = libros;
        this.avisos = avisos;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnActualizar = new JButton(crearIconoActualizar());
        btnActualizar.setToolTipText("Actualizar libros y avisos");
        btnActualizar.setMnemonic(KeyEvent.VK_A);

        btnDeshacer = new JButton(crearIconoFlecha(true));
        btnDeshacer.setToolTipText("Deshacer último cambio (Ctrl+Z)");
        btnDeshacer.setMnemonic(KeyEvent.VK_Z);

        btnRehacer = new JButton(crearIconoFlecha(false));
        btnRehacer.setToolTipText("Rehacer cambio (Ctrl+Y)");
        btnRehacer.setMnemonic(KeyEvent.VK_Y);

        btnLimpiar = new JButton(crearIconoLimpiar());
        btnLimpiar.setToolTipText("Borrar texto de comentarios");
        btnLimpiar.setMnemonic(KeyEvent.VK_L);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarDatos();
            }
        });

        btnDeshacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshacer();
            }
        });

        btnRehacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rehacer();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaTexto.setText("");
            }
        });

        panelBotones.add(btnActualizar);
        panelBotones.add(btnDeshacer);
        panelBotones.add(btnRehacer);
        panelBotones.add(btnLimpiar);
        add(panelBotones, BorderLayout.NORTH);

        modeloLibros = new DefaultListModel<>();
        modeloAvisos = new DefaultListModel<>();

        JPanel panelListas = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel pLibros = new JPanel(new BorderLayout());
        pLibros.setBorder(BorderFactory.createTitledBorder("Libros disponibles"));
        pLibros.add(new JScrollPane(new JList<>(modeloLibros)), BorderLayout.CENTER);

        JPanel pAvisos = new JPanel(new BorderLayout());
        pAvisos.setBorder(BorderFactory.createTitledBorder("Avisos publicados"));
        pAvisos.add(new JScrollPane(new JList<>(modeloAvisos)), BorderLayout.CENTER);

        panelListas.add(pLibros);
        panelListas.add(pAvisos);

        areaTexto = new JTextArea(4, 30);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setToolTipText("Escribe aquí tus comentarios o búsqueda (Ctrl+Z deshacer, Ctrl+Y rehacer)");
        areaTexto.getDocument().addUndoableEditListener(undoManager);
        sp = new JScrollPane(areaTexto);

        JPanel panelComentarios = new JPanel(new BorderLayout());
        panelComentarios.setBorder(BorderFactory.createTitledBorder("Comentarios / Búsqueda del Usuario"));
        panelComentarios.add(sp, BorderLayout.CENTER);

        JPanel centro = new JPanel(new GridLayout(2, 1, 10, 10));
        centro.add(panelListas);
        centro.add(panelComentarios);
        add(centro, BorderLayout.CENTER);

        configurarAtajosTeclado();
        refrescarDatos();
    }

    public void refrescarDatos() {
        modeloLibros.clear();
        for (Libro l : libros) {
            modeloLibros.addElement(l);
        }
        modeloAvisos.clear();
        for (Aviso a : avisos) {
            modeloAvisos.addElement(a);
        }
    }

    private void configurarAtajosTeclado() {
        InputMap im = areaTexto.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am = areaTexto.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "deshacer");
        am.put("deshacer", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshacer();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "rehacer");
        am.put("rehacer", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rehacer();
            }
        });
    }

    public void deshacer() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    public void rehacer() {
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }

    public void limpiarHistorial() {
        undoManager.discardAllEdits();
    }

    public String getTexto() {
        return areaTexto.getText();
    }

    public void setTexto(String texto) {
        areaTexto.setText(texto);
        undoManager.discardAllEdits();
    }

    private Icon crearIconoActualizar() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(new Color(0, 102, 204));
                g.drawArc(x + 2, y + 2, 12, 12, 30, 300);
            }

            @Override
            public int getIconWidth() { return 16; }

            @Override
            public int getIconHeight() { return 16; }
        };
    }

    private Icon crearIconoFlecha(final boolean izquierda) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(Color.DARK_GRAY);
                if (izquierda) {
                    g.drawLine(x + 12, y + 8, x + 4, y + 8);
                    g.drawLine(x + 4, y + 8, x + 8, y + 4);
                    g.drawLine(x + 4, y + 8, x + 8, y + 12);
                } else {
                    g.drawLine(x + 4, y + 8, x + 12, y + 8);
                    g.drawLine(x + 12, y + 8, x + 8, y + 4);
                    g.drawLine(x + 12, y + 8, x + 8, y + 12);
                }
            }

            @Override
            public int getIconWidth() { return 16; }

            @Override
            public int getIconHeight() { return 16; }
        };
    }

    private Icon crearIconoLimpiar() {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(new Color(180, 40, 40));
                g.drawLine(x + 4, y + 4, x + 12, y + 12);
                g.drawLine(x + 12, y + 4, x + 4, y + 12);
            }

            @Override
            public int getIconWidth() { return 16; }

            @Override
            public int getIconHeight() { return 16; }
        };
    }
}