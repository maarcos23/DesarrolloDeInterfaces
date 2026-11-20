package infoPoint;

public class Libro {
    private String id;
    private String titulo;
    private String autor;
    private int anio;

    public Libro(String id, String titulo, String autor, int anio) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnio() { return anio; }

    @Override
    public String toString() {
        return "[" + id + "] " + titulo + " - " + autor + " (" + anio + ")";
    }
}