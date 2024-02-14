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
        MongoDatabase db = mongoClient.getDatabase("test");

        // Creación de colecciones

        MongoCollection<Document> collection1 = db.getCollection("collection1");
        MongoCollection<Document> collection2 = db.getCollection("collection2");

        // Insertar documentos en la colección 1
        Document doc1 = new Document("num", 123)
                .append("str", "hello")
                .append("bool", true)
                .append("nullVal", null)
                .append("subDoc", new Document("subField", "value"))
                .append("array", Arrays.asList("a", "b", "c"))
                .append("dependencies", new Document("dependency1", "value1")
                        .append("dependency2", "value2"));
        collection1.insertOne(doc1);

        // Insertar documentos en la colección 2
        Document doc2 = new Document("num", 456)
                .append("str", "world")
                .append("bool", false)
                .append("nullVal", null)
                .append("subDoc", new Document("subField", "value"))
                .append("array", Arrays.asList("x", "y", "z"))
                .append("dependencies", new Document("dependency3", "value3")
                        .append("dependency4", "value4"));
        collection2.insertOne(doc2);

        // Consultas básicas
        MongoCursor<Document> cursor = collection1.find(new Document("str", "hello")).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        // Actualizaciones
        collection1.updateOne(new Document("str", "hello"), new Document("$set", new Document("str", "updated")));

        // Eliminación
        collection1.deleteOne(new Document("str", "hello"));

        // Consultas de agregación
        List<Document> results = collection1.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("num", 123)), // Etapa de filtrado
                        Aggregates.project(Projections.fields(Projections.include("str", "num"), Projections.excludeId())), // Etapa de proyección
                        Aggregates.sort(Sorts.ascending("num")), // Etapa de ordenación
                        Aggregates.limit(5), // Etapa de límite
                        Aggregates.group("$num", Accumulators.sum("total", 1)) // Etapa de agrupación
                )
        ).into(new ArrayList<>());

        System.out.println(results);


    }
}
