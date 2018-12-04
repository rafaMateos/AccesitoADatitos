import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestoraXML extends DefaultHandler {

    //Instanciamos el objeto que vayamos a insertar cambiandole
    //los datos reecorriendo el archivo XML.
    Album albumAInsertar = new Album();

    /*
    * Contructor de la calse
    * */
    public GestoraXML(){

        super();
    }

    /*
    * Nombre: startDocument
    * Descripcion:
    * Entradas:
    * Salidas:
    * */
    @Override
    public void startDocument(){
        System.out.println("Comienzo del documento XML");
    }

    /*
     * Nombre:
     * Descripcion:
     * Entradas:
     * Salidas:
     * */
    @Override
    public void endDocument(){
        System.out.println("Fin del fichero XML");
    }

    /*
     * Nombre:
     * Descripcion:
     * Entradas:
     * Salidas:
     *
     * */
    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes att){
        System.out.println(nombre + "\n");
    }


    @Override
    public void characters (char[] ch, int inicio, int longitud)
            throws SAXException {



    }
}
