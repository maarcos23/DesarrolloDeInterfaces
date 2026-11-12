package gestionNotas;


import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
 
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(40); 
                splash.updateProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      
        splash.dispose();

       
        JFrame frameInvisible = new JFrame(); 
        LoginDialog login = new LoginDialog(frameInvisible);
        login.setVisible(true); 
        
        if (login.isAutenticado()) {
         
            NotasApp mainApp = new NotasApp();
            mainApp.setVisible(true);
        } else {
            System.exit(0);
        }
    }
}