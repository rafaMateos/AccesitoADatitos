package Clases;

public class Disco {
    private String autor;
    private String titulo;
    private String formato;
    private String localizacion;

    public Disco(String autor, String titulo, String formato, String localizacion)
    {
        this.autor = autor;
        this.titulo = titulo;
        this.formato = formato;
        this.localizacion = localizacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
