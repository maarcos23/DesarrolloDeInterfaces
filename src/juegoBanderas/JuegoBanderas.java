package juegoBanderas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JuegoBanderas extends JFrame {

	private int puntuacion = 0;
	private MarcadorCustom lblMarcador;

	public JuegoBanderas() {
		setTitle("Juego de Banderas");
		setSize(650, 700);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(15, 15));

		// 1. EXTRA: Confirmación al intentar salir con la "X"
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showConfirmDialog(
					JuegoBanderas.this,
					"¿Estás seguro de que quieres salir?",
					"Confirmar salida",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
				);
				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		// 2. EXTRA: Marcador personalizado en la parte superior (NORTH)
		lblMarcador = new MarcadorCustom("Puntuación: " + puntuacion + " pts");
		add(lblMarcador, BorderLayout.NORTH);

		// 3. Panel central con las 5 filas de banderas
		JPanel panelCentral = new JPanel(new GridLayout(5, 1, 10, 10));

		// Fila 1: España (índice 0)
		panelCentral.add(crearFila(
			"Lib/banderaEspaña.jpg",
			new String[] { "España", "México", "Colombia" },
			0
		));

		// Fila 2: Brasil (índice 1)
		panelCentral.add(crearFila(
			"Lib/banderaBrasil.jpg",
			new String[] { "Argentina", "Brasil", "Portugal" },
			1
		));

		// Fila 3: Portugal (índice 2)
		panelCentral.add(crearFila(
			"Lib/banderaPortugal.jpg",
			new String[] { "Italia", "Francia", "Portugal" },
			2
		));

		// Fila 4: Italia (índice 0)
		panelCentral.add(crearFila(
			"Lib/banderaItalia.jpg",
			new String[] { "Italia", "Irlanda", "México" },
			0
		));

		// Fila 5: Colombia (índice 1)
		panelCentral.add(crearFila(
			"Lib/banderaColombia.jpg",
			new String[] { "Ecuador", "Colombia", "Venezuela" },
			1
		));

		add(panelCentral, BorderLayout.CENTER);

		setVisible(true);
	}

	/**
	 * Crea un panel con la imagen de la bandera en un JLabel y su BotonCustom al lado
	 */
	private JPanel crearFila(String rutaImagen, String[] opciones, int respuestaCorrecta) {
		JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

		// Cargar y escalar la imagen proporcionalmente
		ImageIcon icono = new ImageIcon(rutaImagen);
		Image escalada = icono.getImage().getScaledInstance(120, 75, Image.SCALE_SMOOTH);
		JLabel lblBandera = new JLabel(new ImageIcon(escalada));

		// Botón de la clase BotonCustom
		BotonCustom btnAdivinar = new BotonCustom("¿Qué Bandera es?");

		// Oyente de acción sin lambdas
		btnAdivinar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Abrimos el JDialog modal personalizado
				JDialogCustom dialogo = new JDialogCustom(JuegoBanderas.this, opciones, respuestaCorrecta);
				dialogo.setVisible(true);

				// Al cerrarse el diálogo, consultamos si ha acertado
				if (dialogo.isAcertado()) {
					puntuacion += 10;
					lblMarcador.setText("Puntuación: " + puntuacion + " pts");

					JOptionPane.showMessageDialog(
						JuegoBanderas.this,
						"Has acertado",
						"¡Correcto!",
						JOptionPane.INFORMATION_MESSAGE
					);

					// Ampliación: deshabilitar botón para evitar sumar puntos dobles
					btnAdivinar.setEnabled(false);
					btnAdivinar.setText("Acertada");
				} else {
					// Mensaje de fallo con el aspa roja de Swing (ERROR_MESSAGE)
					JOptionPane.showMessageDialog(
						JuegoBanderas.this,
						"Has Fallado",
						"Incorrecto",
						JOptionPane.ERROR_MESSAGE
					);
				}
			}
		});

		fila.add(lblBandera);
		fila.add(btnAdivinar);
		return fila;
	}

	public static void main(String[] args) {
		new JuegoBanderas();
	}
}