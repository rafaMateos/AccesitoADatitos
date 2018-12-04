
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;;


public class SAX {


    XMLReader procesadorXML;
    GestoraXML gestor;
    InputSource archivoXML;

    public SAX (String nombreArchivo){
        try {

            procesadorXML = XMLReaderFactory.createXMLReader();

        }catch (SAXException e){}


        gestor = new GestoraXML();
        procesadorXML.setContentHandler(gestor);
        archivoXML = new InputSource(nombreArchivo);
    }


   public void andale(){

        try{
            procesadorXML.parse(archivoXML);
        }catch (SAXException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

   }

}
