package boletinfichxml.sax.ej;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class PruebaSax1 {
    public static void main(String[] args) throws SAXException, IOException {
        //se crea un procesador de XML
        XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
        // hay que indicarle al procesador XML que objeto tiene los métodos que van a tratar los eventos
        GestionContenido gestor = new GestionContenido();
        procesadorXML.setContentHandler(gestor);
        InputSource fileXML = new InputSource("Empleados.xml");
        procesadorXML.parse(fileXML);
    }
}
