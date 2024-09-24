package no.hvl.dat250.mongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static java.util.Arrays.asList;

public class DeleteCRUD {

    public static void main(String[] args) {
        MongoDBService service = new MongoDBService("testdb", "inventory");
        MongoCollection<Document> collection = service.getCollection();

        collection.insertMany(asList(
                Document.parse("{ item: 'journal', qty: 25, size: { h: 14, w: 21, uom: 'cm' }, status: 'A' }"),
                Document.parse("{ item: 'notebook', qty: 50, size: { h: 8.5, w: 11, uom: 'in' }, status: 'A' }"),
                Document.parse("{ item: 'paper', qty: 100, size: { h: 8.5, w: 11, uom: 'in' }, status: 'D' }"),
                Document.parse("{ item: 'planner', qty: 75, size: { h: 22.85, w: 30, uom: 'cm' }, status: 'D' }"),
                Document.parse("{ item: 'postcard', qty: 45, size: { h: 10, w: 15.25, uom: 'cm' }, status: 'A' }")
        ));

        System.out.println("All documents before deletion:");
        FindIterable<Document> allDocsBeforeDelete = collection.find(new Document());
        for (Document doc : allDocsBeforeDelete) {
            System.out.println(doc.toJson());
        }

        collection.deleteMany(new Document());

        //collection.deleteMany(eq("status", "A"));

        //collection.deleteOne(eq("status", "D"));

        System.out.println("All documents after deletion:");
        FindIterable<Document> allDocs = collection.find(new Document());
        for (Document doc : allDocs) {
            System.out.println(doc.toJson());
        }
    }

}
