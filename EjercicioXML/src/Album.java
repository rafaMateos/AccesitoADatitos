public class Album {

    private String _autor;
    private String _formato;
    private String _titulo;
    private String _localizacion;

    public Album(){
        //Constructor por defecto.
    }

    public String get_autor() {
        return _autor;
    }

    public void set_autor(String _autor) {
        this._autor = _autor;
    }

    public String get_formato() {
        return _formato;
    }

    public void set_formato(String _formato) {
        this._formato = _formato;
    }

    public String get_titulo() {
        return _titulo;
    }

    public void set_titulo(String _titulo) {
        this._titulo = _titulo;
    }

    public String get_localizacion() {
        return _localizacion;
    }

    public void set_localizacion(String _localizacion) {
        this._localizacion = _localizacion;
    }
}
