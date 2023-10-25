package boletinfichxml.tarea5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Act2 {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse((new File("Productos.xml")));
            document.getDocumentElement().normalize();

            // crea una lista con todos los nodos
            NodeList productos = document.getElementsByTagName("Producto");
            System.out.printf("Nodos empleado a recorrer: %d %n", productos.getLength());

            for (int i = 0; i < productos.getLength(); i++) {
                Node producto = productos.item(i); // obtener un nodo empleado
                if (producto.getNodeType() == Node.ELEMENT_NODE) { // tipo de nodo
                    Element element = (Element) producto;
                    int stock = Integer.parseInt(element.getElementsByTagName("Stock").toString());
                    if (stock < 5){
                        element.setAttribute("aLaVenta", "false");
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
