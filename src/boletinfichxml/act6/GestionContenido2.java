package boletinfichxml.act6;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido2 extends DefaultHandler {

    private boolean isTitle;
    private boolean isDate;

    public GestionContenido2(){
        super();
    }
    public void startDocument(){
        System.out.println("Comienzo del documento xml");
    }

    public void endDocument(){
        System.out.println("Final del documento xml");
    }

    public void startElement(String uri, String nombre, String nombreC, Attributes atts){
        if (nombreC.equals("title")){
            isTitle = true;
        } else if (nombreC.equals("pubDate")) {
            isDate = true;
        }
    }

    public void characters(char[] ch, int inicio , int longitud) throws SAXException {
        if (isTitle){
            String car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Titulo: %s %n", car);
            isTitle = false;
        } else if (isDate) {
            String car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Fecha: %s %n", car);
            isDate = false;
        }
    }
}
