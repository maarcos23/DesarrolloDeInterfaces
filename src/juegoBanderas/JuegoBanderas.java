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
		setSize(650, 550);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(15, 15));

		// 1. EXTRA: Confirmación al intentar salir cerrando con la "X"
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

		// 2. EXTRA: Marcador personalizado en la parte superior
		lblMarcador = new MarcadorCustom("Puntuación: " + puntuacion + " pts");
		add(lblMarcador, BorderLayout.NORTH);

		// 3. Panel central con las 3 filas de banderas
		JPanel panelCentral = new JPanel(new GridLayout(3, 1, 10, 10));

		// Fila 1: España (índice 0 es España)
		panelCentral.add(crearFila(
			"Lib/España.jpg",
			new String[] { "España", "México", "Colombia" },
			0
		));

		// Fila 2: Brasil (índice 1 es Brasil)
		panelCentral.add(crearFila(
			"Lib/brasil.png",
			new String[] { "Argentina", "Brasil", "Portugal" },
			1
		));

		// Fila 3: Estados Unidos (índice 2 es EE.UU.)
		panelCentral.add(crearFila(
			"Lib/bandera-eeuu.jpg",
			new String[] { "Reino Unido", "Australia", "EE.UU." },
			2
		));

		add(panelCentral, BorderLayout.CENTER);

		setVisible(true);
	}

	/**
	 * Crea un panel con la imagen de la bandera en un JLabel y su BotonCustom al lado
	 */
	private JPanel crearFila(String rutaImagen, String[] opciones, int respuestaCorrecta) {
		JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

		// Cargar y escalar la imagen
		ImageIcon icono = new ImageIcon(rutaImagen);
		Image escalada = icono.getImage().getScaledInstance(120, 75, Image.SCALE_SMOOTH);
		JLabel lblBandera = new JLabel(new ImageIcon(escalada));

		// Botón con tu clase BotonCustom
		BotonCustom btnAdivinar = new BotonCustom("¿Qué Bandera es?");

		// Oyente de acción
		btnAdivinar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Abrimos tu diálogo customizado
				JDialogCustom dialogo = new JDialogCustom(JuegoBanderas.this, opciones, respuestaCorrecta);
				dialogo.setVisible(true);

				// Al cerrarse el diálogo, comprobamos si acertó
				if (dialogo.isAcertado()) {
					puntuacion += 10;
					lblMarcador.setText("Puntuación: " + puntuacion + " pts");

					JOptionPane.showMessageDialog(
						JuegoBanderas.this,
						"Has acertado",
						"¡Correcto!",
						JOptionPane.INFORMATION_MESSAGE
					);

					// Desactivar el botón para que no repita puntos
					btnAdivinar.setEnabled(false);
					btnAdivinar.setText("Acertada");
				} else {
					// Mensaje de fallo con Aspa Roja (ERROR_MESSAGE)
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