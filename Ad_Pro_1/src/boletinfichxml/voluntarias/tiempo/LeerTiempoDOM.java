package boletinfichxml.voluntarias.tiempo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LeerTiempoDOM {
    public static void main(String[] args) {
        try {
            File inputFile = new File("ElTiempo.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("dia");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Fecha: " + eElement.getAttribute("fecha"));
                    System.out.println("Probabilidad de precipitación: " + eElement.getElementsByTagName("prob_precipitacion")
                            .item(0).getTextContent());
                    System.out.println("Estado del cielo: " +
                            eElement.getElementsByTagName("estado_cielo").item(0).getAttributes().getNamedItem("descripcion")
                                    .getTextContent());
                    NodeList vientoList = eElement.getElementsByTagName("viento");
                    for (int i = 0; i < vientoList.getLength(); i++) {
                        Node vientoNode = vientoList.item(i);
                        if (vientoNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element vientoElement = (Element) vientoNode;
                            System.out.println("Viento dirección: " + vientoElement.getElementsByTagName("direccion")
                                    .item(0).getTextContent());
                            System.out.println("Viento velocidad: " + vientoElement.getElementsByTagName("velocidad")
                                    .item(0).getTextContent());
                        }
                    }
                    System.out.println("Temperatura máxima: " + eElement.getElementsByTagName("temperatura").item(0)
                            .getChildNodes().item(0)
                                    .getTextContent());
                    System.out.println("Temperatura mínima: " + eElement.getElementsByTagName("temperatura").item(0)
                            .getChildNodes().item(1)
                                    .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}