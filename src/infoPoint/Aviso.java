package infoPoint;

public class Aviso {
    private String mensaje;
    private String fecha;
    private String prioridad;

    public Aviso(String mensaje, String fecha, String prioridad) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.prioridad = prioridad;
    }

    public String getMensaje() { return mensaje; }
    public String getFecha() { return fecha; }
    public String getPrioridad() { return prioridad; }

    @Override
    public String toString() {
        return "[" + prioridad + "] " + mensaje + " (" + fecha + ")";
    }
}