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

public class Act1 {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder =  factory.newDocumentBuilder();
            Document document = builder.parse(new File("peliculas.xml"));
            document.getDocumentElement().normalize();

            System.out.printf("Elemento raíz: %s %n", document.getDocumentElement().getNodeName());
            // crea una lista con todos los nodos peliculas
            NodeList peliculas = document.getElementsByTagName("Pelicula");
            System.out.printf("Nodos pelicula a recorrer: %d %n", peliculas.getLength());

            for (int i = 0; i < peliculas.getLength(); i++) {
                Node pelicula = peliculas.item(i); // obtener un nodo empleado
                if (pelicula.getNodeType() == Node.ELEMENT_NODE){ // tipo de nodo
                    Element elemento = (Element) pelicula;
                    System.out.printf("Titulo = %s %n", elemento.getElementsByTagName("Titulo").item(0)
                            .getTextContent());
                    System.out.printf(" * Duracion = %s %n", elemento.getElementsByTagName("Duracion").item(0)
                            .getTextContent());
                    System.out.printf(" * Genero = %s %n", elemento.getElementsByTagName("Genero")
                            .item(0).getTextContent());
                    System.out.printf(" * Sinopsis = %s %n", elemento.getElementsByTagName("sinopsis").item(0)
                            .getTextContent());

                    // crea una lista con todos los nodos actores
                    NodeList actores = elemento.getElementsByTagName("Actor");
                    for (int j = 0; j < actores.getLength(); j++) {
                        Node actor = actores.item(j);
                        if (actor.getNodeType() == Node.ELEMENT_NODE){
                            Element element2 = (Element) actor;
                            System.out.println(" ** Actor = " + element2.getTextContent());
                        }
                    }
                    System.out.printf(" * Fecha = %s %n", elemento.getElementsByTagName("Fecha").item(0)
                            .getTextContent());
                    System.out.printf(" * Director = %s %n", elemento.getElementsByTagName("Director").item(0)
                            .getTextContent());
                }
            }


        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
