package ra1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ej3MarcosFdez extends JFrame {

    // Casillas de verificación para las marcas de coches
    private JCheckBox chkToyota;
    private JCheckBox chkHonda;
    private JCheckBox chkTesla;
    
    // Botón y etiqueta
    private JButton btnComprobar;
    private JLabel lblResultado;

    public Ej3MarcosFdez() {
        setTitle("Ej3 Marcas de coches");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usamos FlowLayout para que los componentes se alineen de forma sencilla
        setLayout(new FlowLayout());

        // 1. Crear las casillas de verificación
        chkToyota = new JCheckBox("Toyota");
        chkHonda = new JCheckBox("Honda");
        chkTesla = new JCheckBox("Tesla");

        // 2. Crear el botón
        btnComprobar = new JButton("Comprobar Seleccion");

        // 3. Crear el JLabel vacío
        lblResultado = new JLabel("");

        // 4. Añadir los componentes a la ventana
        add(chkToyota);
        add(chkHonda);
        add(chkTesla);
        add(btnComprobar);
        add(lblResultado);

        // 5. Evento del botón para comprobar la selección
        btnComprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean toyota = chkToyota.isSelected();
                boolean honda = chkHonda.isSelected();
                boolean tesla = chkTesla.isSelected();

                // Caso 1: Las tres seleccionadas
                if (toyota && honda && tesla) {
                    lblResultado.setText("Has seleccionado Toyota, Honda y Tesla");
                } 
                // Caso 2: Dos seleccionadas
                else if (toyota && honda) {
                    lblResultado.setText("Has seleccionado Toyota y Honda");
                } else if (toyota && tesla) {
                    lblResultado.setText("Has seleccionado Toyota y Tesla");
                } else if (honda && tesla) {
                    lblResultado.setText("Has seleccionado Honda y Tesla");
                } 
                // Caso 3: Solo una seleccionada
                else if (toyota) {
                    lblResultado.setText("Has seleccionado Toyota");
                } else if (honda) {
                    lblResultado.setText("Has seleccionado Honda");
                } else if (tesla) {
                    lblResultado.setText("Has seleccionado Tesla");
                } 
                // Caso 4: Ninguna seleccionada
                else {
                    lblResultado.setText("No has seleccionado ninguna marca");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Ej3MarcosFdez();
    }
}