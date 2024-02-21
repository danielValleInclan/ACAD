import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

public class TeamsQuerys {
    private MongoCursor<Document> cursor; // Cursor
    private Document filtro; // Filtro
    private Document actualizacion; // Actualización

    public void addCurrentPilot(MongoCollection<Document> collection, String team, String pilot){
        filtro = new Document("nombre", team);
        actualizacion = new Document("$push", new Document("pilotos_actuales", pilot));
        collection.updateOne(filtro, actualizacion);
    }

    public void delete(MongoCollection<Document> collection, String team) {
        filtro = new Document("nombre", team);
        collection.deleteOne(filtro);
    }

    public void findByCurrentPilot(MongoCollection<Document> collection, String pilot){
        filtro = new Document("pilotos_actuales", pilot);
        cursor = collection.find(filtro).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

}
