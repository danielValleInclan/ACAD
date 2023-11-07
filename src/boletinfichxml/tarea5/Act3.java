package boletinfichxml.tarea5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Act3 {
    public static void main(String[] args) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.newDocument();
            Element receta = document.createElement("Receta");
            Element titulo = document.createElement("titulo");
            Text textoTitulo = document.createTextNode("Tacos de lengua");

            titulo.appendChild(textoTitulo);
            receta.appendChild(titulo);
            document.appendChild(receta);

            Element ingredientes = document.createElement("ingredientes");
            receta.appendChild(ingredientes);

            Element ingrediente1 = document.createElement("ingrediente");
            ingrediente1.setAttribute("cantidad", "220gr");
            Text textoIngrediente1 = document.createTextNode("Lengua de baca");
            ingrediente1.appendChild(textoIngrediente1);
            ingredientes.appendChild(ingrediente1);

            Element ingrediente2 = document.createElement("ingrediente");
            ingrediente2.setAttribute("cantidad", "2 piezas");
            Text textoIngrediente2 = document.createTextNode("Pan de taco");
            ingrediente2.appendChild(textoIngrediente2);
            ingredientes.appendChild(ingrediente2);

            // Crear el elemento "procedimiento" y agregar un texto
            Element procedimiento = document.createElement("procedimiento");
            Text textoProcedimiento = document.createTextNode("Hacer la lengua y hecharla al taco");
            procedimiento.appendChild(textoProcedimiento);
            receta.appendChild(procedimiento);

            // Crear el elemento "tiempo" y agregar un texto
            Element tiempo = document.createElement("tiempo");
            Text textoTiempo = document.createTextNode("50 min");
            tiempo.appendChild(textoTiempo);
            receta.appendChild(tiempo);

            // Generar el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult("RecetaDOM.xml");
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
}
