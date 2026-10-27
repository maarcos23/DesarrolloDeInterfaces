package gestionNotas;

import javax.swing.JFrame;

public class Main {

	    public static void main(String[] args) {
	        
	        JFrame frameInvisible = new JFrame(); // Requerido para lanzar un JDialog modal
	        
	        // 1. Lanzamos el Login
	        LoginDialog login = new LoginDialog(frameInvisible);
	        login.setVisible(true); // El programa se detiene aquí hasta que se cierre el diálogo
	        
	        // 2. Comprobamos si acertó la clave
	        if (login.isAutenticado()) {
	            // Si es correcto, abrimos el Bloc de Notas
	            NotasApp app = new NotasApp();
	            app.setVisible(true);
	        } else {
	            // Si pulsó cancelar o cerró la ventana, salimos
	            System.exit(0);
	        }
	    }
	}