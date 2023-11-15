package boletinfichxml.ejemplos;

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

public class LeerXMl {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder =  factory.newDocumentBuilder();
            Document document = builder.parse(new File("Empleados.xml"));
            document.getDocumentElement().normalize();

            System.out.printf("Elemento raíz: %s %n", document.getDocumentElement().getNodeName());
            // crea una lista con todos los nodos empleados
            NodeList empleados = document.getElementsByTagName("empleado");
            System.out.printf("Nodos empleado a recorrer: %d %n", empleados.getLength());

            //recorrer la lista
            for (int i = 0; i < empleados.getLength(); i++) {
                Node emple = empleados.item(i); // obtener un nodo empleado
                if (emple.getNodeType() == Node.ELEMENT_NODE){ // tipo de nodo
                    Element elemento = (Element) emple;
                    System.out.printf("ID = %s %n", elemento.getElementsByTagName("id").item(0).getTextContent());
                    System.out.printf(" * Apellido = %s %n", elemento.getElementsByTagName("apellido").item(0)
                            .getTextContent());
                    System.out.printf(" * Departamento = %s %n", elemento.getElementsByTagName("dep")
                            .item(0).getTextContent());
                    System.out.printf(" * Salario = %s %n", elemento.getElementsByTagName("salario").item(0)
                            .getTextContent());
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
