package boletinfichxml.sax.ej;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido extends DefaultHandler {
    public GestionContenido(){
        super();
    }
    public void startDocument(){
        System.out.println("Comienzo del documento xml");
    }

    public void endDocument(){
        System.out.println("Final del documento xml");
    }

    public void startElement(String uri, String nombre, String nombreC, Attributes atts){
        System.out.printf("\t Principio Elemento: %s %n", nombre);
    }

    public void characters(char[] ch, int inicio , int longitud) throws SAXException {
        String car = new String(ch, inicio, longitud);
        car = car.replaceAll("[\t\n]", "");
        System.out.printf("\t Caracteres: %s %n", car);
    }
}
