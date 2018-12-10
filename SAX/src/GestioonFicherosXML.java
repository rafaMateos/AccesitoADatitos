/**
 *
 * @author Leo
 */
import Clases.Disco;
import Gestoras.GestoraDiscos;
import org.xml.sax.helpers.*;
import org.xml.sax.*;

import java.sql.SQLException;

public class GestioonFicherosXML extends DefaultHandler {

    private String elementoActual;
    private String autor = "", titulo = "", formato = "", localizacion = "";
    private GestoraDiscos gestoraDiscos;
    private boolean esStartElement, agregado;

    public GestioonFicherosXML() {
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
        elementoActual = nombre;
        esStartElement = true;
    }
    @Override
    public void endElement(String uri, String nombre, String nombreC)
    {
        esStartElement = false;
        if(nombre.equals("album"))
        {
            try {
                gestoraDiscos = new GestoraDiscos();
                Disco disco = new Disco(autor, titulo, formato, localizacion);
                agregado = gestoraDiscos.agregarDisco(disco);
                if(agregado)
                {
                    System.out.println("¡Disco "+disco.getTitulo()+" de "+disco.getAutor()+" agregado!");
                    limpiarDatosDisco();
                }
                else
                {
                    System.out.println("Algo fue mal :(");
                    limpiarDatosDisco();
                }
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void characters (char[] ch, int inicio, int longitud) throws SAXException
    {
        if(esStartElement)
        {
            switch(elementoActual)
            {
                case "autor": autor = new String(ch, inicio, longitud); break;
                case "titulo": titulo = new String(ch, inicio, longitud); break;
                case "formato": formato = new String(ch, inicio, longitud); break;
                case "localizacion": localizacion = new String(ch, inicio, longitud); break;
            }
        }
    }

    private void limpiarDatosDisco()
    {
        autor = "";
        titulo = "";
        formato = "";
        localizacion = "";
    }
}
// FIN GestioonFicherosXML
