import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBController {

    private MongoDatabase db;

    public MongoDBController(String connection, String dbName){

        ConnectionString connectionString = new ConnectionString(connection);
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        MongoClient mongoClient = MongoClients.create(settings);
        this.db = mongoClient.getDatabase(dbName);

    }

    public MongoCollection<Document> createCollection(String collectionName) {
        return db.getCollection(collectionName);
    }

    public void deleteCollection(String collectionName){
        db.getCollection(collectionName).deleteMany(new Document());
    }
}
