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
    static int numero, victorias;
    static String escuderia, nombre;
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
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Pilotos", null);
            document.setXmlVersion("1.0");
            insertarTeclado();
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Pilotos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "Pilotos", null);
        document.setXmlVersion("1.0");
        switch (op){
            case 1:
                insertarTeclado(sc);
                break;
            case 2:

                break;
            default:
                break;
        }
    }
    static void medianteCodigo(){

    }
    public static void insertarTeclado(Scanner sc){
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Pilotos", null);
            document.setXmlVersion("1.0");
            // A partir de aqui cambia
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
            // Aqui acaba
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Pilotos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    static void crearElemento(String datoPiloto, String valor, Element raiz, Document document){
        Element element = document.createElement(datoPiloto);
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(element); // pegamos el elemento hijo a la raiz
        element.appendChild(text); // pegamos el valor
    }
}
