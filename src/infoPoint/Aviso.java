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

    // Getters y Setters
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    @Override
    public String toString() {
        return "[" + prioridad + "] " + mensaje + " (" + fecha + ")";
    }
}