package infoPoint;

public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private int anio;

    public Libro(String titulo, String autor, String isbn, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anio = anio;
    }

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + anio + ")";
    }
}