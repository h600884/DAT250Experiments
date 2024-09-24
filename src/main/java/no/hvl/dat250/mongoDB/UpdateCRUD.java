package no.hvl.dat250.mongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;

public class UpdateCRUD {

    public static void main(String[] args) {
        MongoDBService service = new MongoDBService("testdb", "inventory");
        MongoCollection<Document> collection = service.getCollection();

        collection.insertMany(asList(
                Document.parse("{ item: 'canvas', qty: 100, size: { h: 28, w: 35.5, uom: 'cm' }, status: 'A' }"),
                Document.parse("{ item: 'journal', qty: 25, size: { h: 14, w: 21, uom: 'cm' }, status: 'A' }"),
                Document.parse("{ item: 'mat', qty: 85, size: { h: 27.9, w: 35.5, uom: 'cm' }, status: 'A' }"),
                Document.parse("{ item: 'mousepad', qty: 25, size: { h: 19, w: 22.85, uom: 'cm' }, status: 'P' }"),
                Document.parse("{ item: 'notebook', qty: 50, size: { h: 8.5, w: 11, uom: 'in' }, status: 'P' }"),
                Document.parse("{ item: 'paper', qty: 100, size: { h: 8.5, w: 11, uom: 'in' }, status: 'D' }"),
                Document.parse("{ item: 'planner', qty: 75, size: { h: 22.85, w: 30, uom: 'cm' }, status: 'D' }"),
                Document.parse("{ item: 'postcard', qty: 45, size: { h: 10, w: 15.25, uom: 'cm' }, status: 'A' }"),
                Document.parse("{ item: 'sketchbook', qty: 80, size: { h: 14, w: 21, uom: 'cm' }, status: 'A' }"),
                Document.parse("{ item: 'sketch pad', qty: 95, size: { h: 22.85, w: 30.5, uom: 'cm' }, status: 'A' }")
        ));

        System.out.println("All documents before update:");
        FindIterable<Document> allDocsBeforeUpdate = collection.find(new Document());
        for (Document doc : allDocsBeforeUpdate) {
            System.out.println(doc.toJson());
        }

        collection.updateOne(eq("item", "paper"),
                combine(set("size.uom", "cm"), set("status", "P"), currentDate("lastModified")));

        collection.updateMany(lt("qty", 50),
                combine(set("size.uom", "in"), set("status", "P"), currentDate("lastModified")));

        collection.replaceOne(eq("item", "paper"),
                Document.parse("{ item: 'paper', instock: [ { warehouse: 'A', qty: 60 }, { warehouse: 'B', qty: 40 } ] }"));

        System.out.println("All documents after update:");
        FindIterable<Document> allDocsAfterUpdate = collection.find(new Document());
        for (Document doc : allDocsAfterUpdate) {
            System.out.println(doc.toJson());
        }
    }
}
