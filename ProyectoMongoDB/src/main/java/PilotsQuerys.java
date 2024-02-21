import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;


public class PilotsQuerys {
    private MongoCursor<Document> cursor; // Cursor
    private Document filtro; // Filtro
    private Document actualizacion; // Actualización

    public void findByNationality (MongoCollection<Document> pilotosCollection, String nationality) {
        filtro = new Document("nacionalidad", nationality);
        cursor = pilotosCollection.find(filtro).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    public void findByName (MongoCollection<Document> pilotosCollection, String name) {
        filtro = new Document("nombre", name);
        cursor = pilotosCollection.find(filtro).iterator();
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }

    public void updatePoints (MongoCollection<Document> collection, String name, int points) {
        filtro = new Document("nombre", name);
        actualizacion = new Document("$set", new Document("puntos", points));
        collection.updateOne(filtro, actualizacion);
    }

    public void pipelineGroupByNationality(MongoCollection<Document> collection, String nationality) {
        List<Document> pipeline = Arrays.asList(
                new Document("$match", new Document("nacionalidad", nationality)),
                new Document("$group", new Document("_id", null).append("total", new Document("$sum", 1)))
        );
                AggregateIterable<Document> resultado = collection.aggregate(pipeline);
                System.out.println(resultado.first().toJson());
    }

    public void findByTeam(MongoCollection<Document> collection, String team){
        filtro = new Document("historial_equipos", team);
        cursor = collection.find(filtro).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
}
