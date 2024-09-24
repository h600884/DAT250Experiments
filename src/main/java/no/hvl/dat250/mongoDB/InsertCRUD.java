package no.hvl.dat250.mongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class InsertCRUD {

    public static void main(String[] args) {
        MongoDBService service = new MongoDBService("testdb", "inventory");
        MongoCollection<Document> collection = service.getCollection();

        // Insert a single document
        Document canvas = new Document("item", "canvas")
                .append("qty", 100)
                .append("tags", Collections.singletonList("cotton"));

        Document size = new Document("h", 28)
                .append("w", 35.5)
                .append("uom", "cm");
        canvas.put("size", size);

        // Insert the document and get the inserted ID
        InsertOneResult result = collection.insertOne(canvas);
        System.out.println("Inserted document ID: " + result.getInsertedId());

        // Retrieve the inserted document
        FindIterable<Document> findIterable = collection.find(eq("item", "canvas"));
        System.out.println("Inserted document: " + Objects.requireNonNull(findIterable.first()).toJson());

        // Insert multiple documents
        Document journal = new Document("item", "journal")
                .append("qty", 25)
                .append("tags", Arrays.asList("blank", "red"));

        Document journalSize = new Document("h", 14)
                .append("w", 21)
                .append("uom", "cm");
        journal.put("size", journalSize);

        Document mat = new Document("item", "mat")
                .append("qty", 85)
                .append("tags", Collections.singletonList("gray"));

        Document matSize = new Document("h", 27.9)
                .append("w", 35.5)
                .append("uom", "cm");
        mat.put("size", matSize);

        Document mousePad = new Document("item", "mousePad")
                .append("qty", 25)
                .append("tags", Arrays.asList("gel", "blue"));

        Document mousePadSize = new Document("h", 19)
                .append("w", 22.85)
                .append("uom", "cm");
        mousePad.put("size", mousePadSize);

        // Insert multiple documents
        collection.insertMany(Arrays.asList(journal, mat, mousePad));

        // Retrieve and print all inserted documents
        FindIterable<Document> allDocuments = collection.find();
        System.out.println("All inserted documents:");
        for (Document doc : allDocuments) {
            System.out.println(doc.toJson());
        }

        service.close();
    }
}

