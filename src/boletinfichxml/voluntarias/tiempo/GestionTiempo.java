package boletinfichxml.voluntarias.tiempo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionTiempo extends DefaultHandler {
    private boolean isDia, isProb_precipitacion, ;
    private String fehca;
    public GestionTiempo(){
        super();
    }
    public void startDocument(){
        System.out.println("Comienzo del documento xml");
    }

    public void endDocument(){
        System.out.println("Final del documento xml");
    }

    public void startElement(String uri, String nombre, String nombreC, Attributes atts){
        if (nombreC.equals("dia")) {
            isDia = true;
            fehca = atts.getValue("fecha");
        } else if (nombreC.equals("prob_precipitacion")) {
            isProb_precipitacion = true;
        }
    }

    public void characters(char[] ch, int inicio , int longitud) throws SAXException {
        if (isDia){
            System.out.println("Día : " + fehca);
        } else if (prob_precipitacion) {

        }

        System.out.println();
        String car = new String(ch, inicio, longitud);
        car = car.replaceAll("[\t\n]", "");
        System.out.printf("\t Caracteres: %s %n", car);
    }
}
