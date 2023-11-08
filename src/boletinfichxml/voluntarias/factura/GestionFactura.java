package boletinfichxml.voluntarias.factura;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionFactura extends DefaultHandler {

    private boolean isName, isFirstSurname, isSecondSurname, isProvince;
    public GestionFactura(){
        super();
    }
    public void startDocument(){
        System.out.println("Comienzo del documento xml");
    }

    public void endDocument(){
        System.out.println("Final del documento xml");
    }

    public void startElement(String uri, String nombre, String nombreC, Attributes atts){
        if (nombreC.equals("Name")){
            isName = true;
        }else if (nombreC.equals("FirstSurname")){
            isFirstSurname = true;
        }else if (nombreC.equals("SecondSurname")){
            isSecondSurname = true;
        }else if (nombreC.equals("Province")){
            isProvince = true;
        }
    }

    public void characters(char[] ch, int inicio , int longitud) throws SAXException {

        if (isName){
            String car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Nombre: %s %n", car);
            isName = false;
        }else if (isFirstSurname){
            String car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Apellido: %s %n", car);
            isFirstSurname = false;
        }else if (isSecondSurname){
            String car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Segundo Apellido: %s %n", car);
            isSecondSurname = false;
        }else if (isProvince){
            String car = new String(ch, inicio, longitud);
            car = car.replaceAll("[\t\n]", "");
            System.out.printf("\t Provincia: %s %n", car);
            isProvince = false;
        }
    }
}
