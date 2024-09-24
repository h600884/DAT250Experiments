package no.hvl.dat250.mongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;

public class QueryCRUD {

    public static void main(String[] args) {
        MongoDBService service = new MongoDBService("testdb", "inventory");
        MongoCollection<Document> collection = service.getCollection();

        // Insert multiple documents into the inventory collection
        collection.insertMany(Arrays.asList(
                Document.parse("{ item: 'journal', qty: 25, size: { h: 14, w: 21, uom: 'cm' }, status: 'A' }"),
                Document.parse("{ item: 'notebook', qty: 50, size: { h: 8.5, w: 11, uom: 'in' }, status: 'A' }"),
                Document.parse("{ item: 'paper', qty: 100, size: { h: 8.5, w: 11, uom: 'in' }, status: 'D' }"),
                Document.parse("{ item: 'planner', qty: 75, size: { h: 22.85, w: 30, uom: 'cm' }, status: 'D' }"),
                Document.parse("{ item: 'postcard', qty: 45, size: { h: 10, w: 15.25, uom: 'cm' }, status: 'A' }")
        ));

        // 1. Select all documents in the collection
        System.out.println("All documents:");
        FindIterable<Document> allDocs = collection.find(new Document());
        for (Document doc : allDocs) {
            System.out.println(doc.toJson());
        }

        // 2. Select all documents where status equals "D"
        System.out.println("\nDocuments with status 'D':");
        FindIterable<Document> statusDDocs = collection.find(eq("status", "D"));
        for (Document doc : statusDDocs) {
            System.out.println(doc.toJson());
        }

        // 3. Select documents where status is either "A" or "D"
        System.out.println("\nDocuments with status 'A' or 'D':");
        FindIterable<Document> statusADDocs = collection.find(in("status", "A", "D"));
        for (Document doc : statusADDocs) {
            System.out.println(doc.toJson());
        }

        // 4. Select documents where status is "A" and qty is less than 30
        System.out.println("\nDocuments with status 'A' and qty < 30:");
        FindIterable<Document> statusAQtyLessThan30Docs = collection.find(and(eq("status", "A"), lt("qty", 30)));
        for (Document doc : statusAQtyLessThan30Docs) {
            System.out.println(doc.toJson());
        }

        // 5. Select documents where status is "A" or qty is less than 30
        System.out.println("\nDocuments with status 'A' or qty < 30:");
        FindIterable<Document> statusAOrQtyLessThan30Docs = collection.find(or(eq("status", "A"), lt("qty", 30)));
        for (Document doc : statusAOrQtyLessThan30Docs) {
            System.out.println(doc.toJson());
        }

        // 6. Select documents where status is "A" and (qty < 30 or item starts with "p")
        System.out.println("\nDocuments with status 'A' and (qty < 30 or item starts with 'p'):");
        FindIterable<Document> complexQueryDocs = collection.find(and(eq("status", "A"), or(lt("qty", 30), regex("item", "^p"))));
        for (Document doc : complexQueryDocs) {
            System.out.println(doc.toJson());
        }

        service.close();
    }
}
