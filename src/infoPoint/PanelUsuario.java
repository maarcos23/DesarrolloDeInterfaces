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
    private DefaultListModel<Libro> modLibros;
    private DefaultListModel<Aviso> modAvisos;

    private JTextArea areaTexto;
    private UndoManager undoManager = new UndoManager();

    public PanelUsuario(List<Libro> libros, List<Aviso> avisos) {
        this.libros = libros;
        this.avisos = avisos;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnRefrescar = new JButton(iconoCirculo(new Color(0, 102, 204)));
        btnRefrescar.setToolTipText("Actualizar libros y avisos");
        btnRefrescar.setMnemonic(KeyEvent.VK_R);

        JButton btnUndo = new JButton(iconoFlecha(true));
        btnUndo.setToolTipText("Deshacer (Ctrl+Z)");
        btnUndo.setMnemonic(KeyEvent.VK_Z);

        JButton btnRedo = new JButton(iconoFlecha(false));
        btnRedo.setToolTipText("Rehacer (Ctrl+Y)");
        btnRedo.setMnemonic(KeyEvent.VK_Y);

        JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTop.add(btnRefrescar);
        pnlTop.add(btnUndo);
        pnlTop.add(btnRedo);
        add(pnlTop, BorderLayout.NORTH);

        modLibros = new DefaultListModel<>();
        modAvisos = new DefaultListModel<>();

        JPanel pnlListas = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel pLib = new JPanel(new BorderLayout());
        pLib.setBorder(BorderFactory.createTitledBorder("Libros"));
        pLib.add(new JScrollPane(new JList<>(modLibros)), BorderLayout.CENTER);

        JPanel pAvi = new JPanel(new BorderLayout());
        pAvi.setBorder(BorderFactory.createTitledBorder("Avisos"));
        pAvi.add(new JScrollPane(new JList<>(modAvisos)), BorderLayout.CENTER);

        pnlListas.add(pLib);
        pnlListas.add(pAvi);

        areaTexto = new JTextArea(4, 30);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setToolTipText("Notas o comentarios del usuario");
        areaTexto.getDocument().addUndoableEditListener(undoManager);

        JPanel pNotas = new JPanel(new BorderLayout());
        pNotas.setBorder(BorderFactory.createTitledBorder("Notas / Comentarios"));
        pNotas.add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        JPanel centro = new JPanel(new GridLayout(2, 1, 10, 10));
        centro.add(pnlListas);
        centro.add(pNotas);
        add(centro, BorderLayout.CENTER);

        btnRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarDatos();
            }
        });

        btnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshacer();
            }
        });

        btnRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rehacer();
            }
        });

        InputMap im = areaTexto.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am = areaTexto.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo");
        am.put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { deshacer(); }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "redo");
        am.put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { rehacer(); }
        });

        refrescarDatos();
    }

    public void refrescarDatos() {
        modLibros.clear();
        for (Libro l : libros) modLibros.addElement(l);

        modAvisos.clear();
        for (Aviso a : avisos) modAvisos.addElement(a);
    }

    public void deshacer() {
        if (undoManager.canUndo()) undoManager.undo();
    }

    public void rehacer() {
        if (undoManager.canRedo()) undoManager.redo();
    }

    private Icon iconoCirculo(final Color c) {
        return new Icon() {
            @Override
            public void paintIcon(Component comp, Graphics g, int x, int y) {
                g.setColor(c);
                g.drawOval(x + 2, y + 2, 12, 12);
            }
            @Override public int getIconWidth() { return 16; }
            @Override public int getIconHeight() { return 16; }
        };
    }

    private Icon iconoFlecha(final boolean izq) {
        return new Icon() {
            @Override
            public void paintIcon(Component comp, Graphics g, int x, int y) {
                g.setColor(Color.DARK_GRAY);
                if (izq) {
                    g.drawLine(x + 12, y + 8, x + 4, y + 8);
                    g.drawLine(x + 4, y + 8, x + 8, y + 4);
                    g.drawLine(x + 4, y + 8, x + 8, y + 12);
                } else {
                    g.drawLine(x + 4, y + 8, x + 12, y + 8);
                    g.drawLine(x + 12, y + 8, x + 8, y + 4);
                    g.drawLine(x + 12, y + 8, x + 8, y + 12);
                }
            }
            @Override public int getIconWidth() { return 16; }
            @Override public int getIconHeight() { return 16; }
        };
    }
}