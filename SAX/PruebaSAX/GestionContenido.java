package probandoSAX;


/**
 *
 * @author Leo
 */
import org.xml.sax.helpers.*;
import org.xml.sax.*;
public class GestionContenido extends DefaultHandler {

    public GestionContenido() {
        super();
    }
    @Override
    public void startDocument(){
        System.out.println("Comienzo del documento XML");
    }
    @Override
    public void endDocument(){
        System.out.println("Fin del documento XML");
    }
    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes att){
        System.out.println("\t< "+nombre +">");
    }
    @Override
    public void endElement(String uri, String nombre, String nombreC){
        System.out.println("\t</ "+nombre +">");
    }
    @Override
    public void characters (char[] ch, int inicio, int longitud)
            throws SAXException{
        String cad = new String(ch, inicio, longitud);
        cad = cad.replaceAll("[\t\n]",""); // Quitamos tabuladores y saltos de linea
        System.out.println("\t\t" + cad);
    }
}
// FIN GestioonFicherosXML
