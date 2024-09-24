package no.hvl.dat250.mongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class BulkWrite {

    public static void main(String[] args) {
        MongoDBService service = new MongoDBService("testdb", "pizza");
        MongoCollection<Document> collection = service.getCollection();

        try {
            collection.bulkWrite(Arrays.asList(
                    // Insert new pizza types
                    new InsertOneModel<>(new Document("_id", 3).append("type", "beef").append("size", "medium").append("price", 6)),
                    new InsertOneModel<>(new Document("_id", 4).append("type", "sausage").append("size", "large").append("price", 10)),

                    // Update price of cheese pizza
                    new UpdateOneModel<>(eq("type", "cheese"), set("price", 8)),

                    // Delete pepperoni pizza
                    new DeleteOneModel<>(eq("type", "pepperoni")),

                    // Replace vegan pizza with tofu pizza
                    new ReplaceOneModel<>(eq("type", "vegan"), new Document("type", "tofu").append("size", "small").append("price", 4))
            ), new BulkWriteOptions().ordered(true));

            System.out.println("Bulk write operations executed successfully.");
        } catch (Exception e) {
            System.err.println("Error during bulk operations: " + e.getMessage());
        } finally {
            System.out.println("All pizzas:");
            FindIterable<Document> allDocs = collection.find(new Document());
            for (Document doc : allDocs) {
                System.out.println(doc.toJson());
            }
        service.close();
        }

    }
}
