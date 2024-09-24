package no.hvl.dat250.mongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBService {

    private final MongoDatabase database;
    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    public MongoDBService(String dbName, String collectionName) {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase(dbName);
        this.collection = database.getCollection(collectionName);;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }
    public MongoDatabase getDatabase() {
        return this.database;
    }

    public void close() {
        mongoClient.close();
    }
}
