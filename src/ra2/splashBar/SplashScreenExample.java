package ra2.splashBar;
public class SplashScreenExample {

    public static void main(String[] args) {
        // 1. Mostrar Splash Screen
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);

        // 2. Simular carga de recursos (de 0 a 100%)
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50); // Simula 50 ms por cada punto (unos 5 segundos en total)
                splash.updateProgress(i); // Actualiza la barra de progreso
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 3. Cerrar y destruir el Splash Screen
        splash.dispose();

        // 4. Abrir la ventana principal
        MainApp mainApp = new MainApp();
        mainApp.setVisible(true);
    }
}