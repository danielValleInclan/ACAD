import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;

import com.mongodb.client.model.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Create {
    public static void main(String[] args) {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase db = mongoClient.getDatabase("F1DB");

        // Eliminar datos existentes
        db.getCollection("pilotos").deleteMany(new Document());
        db.getCollection("equipos").deleteMany(new Document());

        // Creación de colecciones
        MongoCollection<Document> pilotosCollection = db.getCollection("pilotos");
        MongoCollection<Document> equiposCollection = db.getCollection("equipos");

        // Insercción de piloto
        List<Document> documentsPilotos = new ArrayList<>();
        List<Document> documentsEquipos = new ArrayList<>();

        documentsPilotos.add(new Document("nombre", "Lewis Hamilton")
                .append("nacionalidad", "Británico")
                .append("edad", 37)
                .append("equipo_actual", "Mercedes")
                .append("puntos", 347)
                .append("activo", true)
                .append("campeon", true)
                .append("historial_equipos", Arrays.asList("McLaren", "Mercedes")));

        documentsPilotos.add(new Document("nombre", "Fernando Alonso")
                .append("nacionalidad", "Español")
                .append("edad", "42")
                .append("equipo-actual", "Astón Martin")
                .append("puntos", 400)
                .append("activo", true)
                .append("campeon", true)
                .append("historial_equipos", Arrays.asList("McLaren", "Renault", "Alpine", "Ferrari")));

        documentsPilotos.add(new Document("nombre", "Max Verstappen")
                .append("nacionalidad", "Holandes")
                .append("edad", "26")
                .append("equipo-actual", "Red-Bull")
                .append("puntos", 380)
                .append("activo", true)
                .append("campeon", true)
                .append("historial_equipos", Arrays.asList("Red Bull Racing")));

        documentsPilotos.add(new Document("nombre", "Carlos Sainz")
                .append("nacionalidad", "Español")
                .append("edad", "42")
                .append("equipo-actual", "Ferrari")
                .append("puntos", 300)
                .append("activo", true)
                .append("campeon", false)
                .append("historial_equipos", Arrays.asList("McLaren", "Ferrari")));

        documentsPilotos.add(new Document("nombre", "Valtteri Bottas")
                .append("nacionalidad", "Finlandés")
                .append("edad", "34")
                .append("equipo-actual", "Mercedes")
                .append("puntos", 100)
                .append("activo", false)
                .append("campeon", false)
                .append("historial_equipos", Arrays.asList("Mercedes", "Ferrari")));

        pilotosCollection.insertMany(documentsPilotos);

        // Insercción de equipo

        documentsEquipos.add( new Document("nombre", "Mercedes")
                .append("pais", "Alemania")
                .append("fundacion", 1954)
                .append("numero_titulos", 8)
                .append("pilotos_actuales", Arrays.asList("Lewis Hamilton", "Valtteri Bottas"))
                .append("sede", new Document("ciudad", "Brackley").append("pais", "Reino Unido")));

        documentsEquipos.add( new Document("nombre", "Astón Martin")
                .append("pais", "Reino Unido")
                .append("fundacion", 1913)
                .append("numero_titulos", 0)
                .append("pilotos_actuales", Arrays.asList("Fernando Alonso", "Lance Stroll"))
                .append("sede", new Document("ciudad", "Silverstone").append("pais", "Reino Unido")));

        documentsEquipos.add(new Document("nombre", "Red Bull Racing")
                .append("pais", "Reino Unido")
                .append("fundacion", 2005)
                .append("numero_titulos", 4)
                .append("pilotos_actuales", Arrays.asList("Max Verstappen", "Sergio Pérez"))
                .append("sede", new Document("ciudad", "Milton Keynes").append("pais", "Reino Unido")));

        documentsEquipos.add(new Document("nombre", "Ferrari")
                .append("pais", "Italia")
                .append("fundacion", 1929)
                .append("numero_titulos", 16)
                .append("pilotos_actuales", Arrays.asList("Carlos Sainz", "Charles Leclerc"))
                .append("sede", new Document("ciudad", "Maranello").append("pais", "Italia")));


        equiposCollection.insertMany(documentsEquipos);

        // Consultas

        MongoCursor<Document> cursor; // Cursor
        Document filtro; // Filtro
        Document actualizacion; // Actualización

        // Buscar pilotos de nacionalidad británica
        filtro = new Document("nacionalidad", "Británico");
        cursor = pilotosCollection.find(filtro).iterator();
        System.out.println("Nacionlidad Britanica");
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        // Buscar pilotos de nacionalidad británica
        filtro = new Document("nacionalidad", "Español");
        cursor = pilotosCollection.find(filtro).iterator();
        System.out.println("Nacionlidad Español");
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }


        cursor = pilotosCollection.find(new Document("str", "hello")).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        // Actualizaciones

        // Actualizar puntos de Lewis Hamilton
        filtro = new Document("nombre", "Lewis Hamilton");
        actualizacion = new Document("$set", new Document("puntos", 400));
        pilotosCollection.updateOne(filtro, actualizacion);

        // Añadir un nuevo piloto al array de pilotos_actuales de Mercedes
        filtro = new Document("nombre", "Mercedes");
        actualizacion = new Document("$push", new Document("pilotos_actuales", "George Russell"));
        equiposCollection.updateOne(filtro, actualizacion);


        // Eliminación
        // Borrar el equipo Red Bull Racing
        filtro = new Document("nombre", "Red Bull Racing");
        equiposCollection.deleteOne(filtro);


        // Agregación pipeline
        List<Document> pipeline = Arrays.asList(
                new Document("$match", new Document("nacionalidad", "Británico")),
                new Document("$group", new Document("_id", null).append("total", new Document("$sum", 1)))
        );
        AggregateIterable<Document> resultado = pilotosCollection.aggregate(pipeline);
        System.out.println(resultado.first().toJson());

        // Consultas utilizando funciones para arrays
        // Buscar equipos que tengan a Lewis Hamilton como piloto actual
        filtro = new Document("pilotos_actuales", "Lewis Hamilton");
        cursor = equiposCollection.find(filtro).iterator();
        System.out.println("Buscar equipos que tengan a Lewis Hamilton como piloto actual");
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        // Consultas sobre documentos enlazados
        // Obtener todos los pilotos que pertenecieron al equipo McLaren
        filtro = new Document("historial_equipos", "McLaren");
        cursor = pilotosCollection.find(filtro).iterator();
        System.out.println("Obtener todos los pilotos que pertenecieron al equipo McLaren");
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
}
