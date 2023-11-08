package boletinfichxml.voluntarias.tiempo;

import boletinfichxml.sax.ej.GestionContenido;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class LeerTiempoSAX {
    public static void main(String[] args) throws SAXException, IOException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = parserFactory.newSAXParser();
            //se crea un procesador de XML
            XMLReader procesadorXML = parser.getXMLReader();
            // hay que indicarle al procesador XML que objeto tiene los métodos que van a tratar los eventos
            GestionTiempo gestor = new GestionTiempo();
            procesadorXML.setContentHandler(gestor);
            InputSource fileXML = new InputSource("ElTiempo.xml");
            procesadorXML.parse(fileXML);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
