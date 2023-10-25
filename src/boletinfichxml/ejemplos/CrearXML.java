package boletinfichxml.ejemplos;

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

public class CrearXML {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        int id, dep;
        double salario;
        String apellido;

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");
            System.out.print("Número de empleados: ");
            int numEmpleados = sc.nextInt();
            for (int i = 0; i < numEmpleados; i++) {
                System.out.print("Introduce id empleado: ");
                id = sc.nextInt(); // obtengo id empleado
                System.out.println("Introduce apellido, número del departamento y salario del empleado:");
                apellido = sc.next();
                dep = sc.nextInt();
                salario = sc.nextDouble();
                if (id > 0){ // id válidos a partir de 1
                    Element raiz = document.createElement("empleado"); // nodo empleado
                    document.getDocumentElement().appendChild(raiz);

                    //añadir id
                    crearElemento("id", Integer.toString(id), raiz, document);
                    //Apellido
                    crearElemento("apellido", apellido.trim(), raiz, document);
                    //añadir DEP
                    crearElemento("dep", Integer.toString(dep), raiz, document);
                    //añadir salario
                    crearElemento("salario", Double.toString(salario), raiz, document);
                }
            }
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Empleados.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        } catch (TransformerFactoryConfigurationError e){
            throw new RuntimeException();
        }
    }

    //Inserción de datos de empleado
    static void crearElemento(String datoEmple, String valor, Element raiz, Document document){
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo al raíz
        elem.appendChild(text); //pegamos el valor
    }
}
