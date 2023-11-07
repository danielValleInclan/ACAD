package boletinfichxml.tarea5b;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.Scanner;

public class EjA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("""
                    Elije una opción:
                     [1] Introducir datos por teclado
                     [2] Los datos se introducen por código
                     [3] Los datos se leen de un archivo de texto""");
            System.out.print("--> ");
            op = sc.nextInt();
            if (op < 1 || op > 3){
                System.out.println("*** Elije una opción correcta ***");
            }
        } while (op < 1 || op > 3);

        switch (op){
            case 1:
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                int numero, victorias;
                String escuderia, nombre;

                DocumentBuilder builder;
                try {
                    builder = factory.newDocumentBuilder();

                    DOMImplementation implementation = builder.getDOMImplementation();
                    Document document = implementation.createDocument(null, "Pilotos", null);
                    document.setXmlVersion("1.0");
                    System.out.println("Número de pilotos ha introducir -> ");
                    int numPilotos = sc.nextInt();
                    for (int i = 0; i < numPilotos; i++) {
                        System.out.println("Nombre del piloto -> ");
                        nombre = sc.next();
                        System.out.println("Escudería del piloto -> ");
                        escuderia = sc.next();
                        System.out.println("Número del piloto -> ");
                        numero = sc.nextInt();
                        System.out.println("Victorias del piloto -> ");
                        victorias = sc.nextInt();
                        if (numero > 0){
                            Element raiz = document.createElement("piloto");
                            document.getDocumentElement().appendChild(raiz);
                            //añadir numero
                            crearElemento("numero", Integer.toString(numero), raiz, document);
                            //añadir nombre
                            crearElemento("nombre", nombre, raiz, document);
                            //añadir escuderia
                            crearElemento("escuderia", escuderia, raiz, document);
                            //añadir victorias
                            crearElemento("victorias", Integer.toString(victorias), raiz, document);
                        }
                    }
                    Source source = new DOMSource(document);
                    Result result = new StreamResult(new java.io.File("Pilotos.xml"));
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(source, result);
                } catch (ParserConfigurationException | TransformerException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                break;
        }
    }
    static void crearElemento(String datoPiloto, String valor, Element raiz, Document document){
        Element element = document.createElement(datoPiloto);
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(element); // pegamos el elemento hijo a la raiz
        element.appendChild(text); // pegamos el valor
    }
}
