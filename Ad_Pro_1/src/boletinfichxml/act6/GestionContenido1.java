package boletinfichxml.act6;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido1 extends DefaultHandler {

    private boolean isTitle;
    private boolean isDate;
    private boolean isDirector;
    private boolean isActor;

    public GestionContenido1(){
        super();
    }
    public void startDocument(){
        System.out.println("Comienzo del documento xml");
    }

    public void endDocument(){
        System.out.println("Final del documento xml");
    }

    public void startElement(String uri, String nombre, String nombreC, Attributes atts){
        if (nombreC.equals("Titulo")){
            isTitle = true;
        } else if (nombreC.equals("Fecha")) {
            isDate = true;
        } else if (nombreC.equals("Actor")) {
            isActor = true;
        }
    }

    public void characters(char[] ch, int inicio , int longitud) throws SAXException {
        String car;
        if (isTitle){
            car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Titulo: %s %n", car);
            isTitle = false;
        } else if (isDate) {
            car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Fecha: %s %n", car);
            isDate = false;
        } else if (isActor) {
            car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Actor: %s %n", car);
            isActor = false;
        } else if (isDirector) {
            car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Actor: %s %n", car);
            isDirector = false;
        }
    }
}
