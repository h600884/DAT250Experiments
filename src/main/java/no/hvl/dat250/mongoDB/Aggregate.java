package no.hvl.dat250.mongoDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static com.mongodb.client.model.Sorts.ascending;

public class Aggregate {

    public static void main(String[] args) {
        MongoDBService service = new MongoDBService("testdb", "orders");
        MongoCollection<Document> collection = service.getCollection();

        // Insert test data
        collection.insertMany(Arrays.asList(
                Document.parse("{ _id: 1, cust_id: 'Ant O. Knee', ord_date: '2020-03-01', price: 25, " +
                        "items: [ { sku: 'oranges', qty: 5, price: 2.5 }, { sku: 'apples', qty: 5, price: 2.5 } ], status: 'A' }"),
                Document.parse("{ _id: 2, cust_id: 'Ant O. Knee', ord_date: '2020-03-08', price: 70, " +
                        "items: [ { sku: 'oranges', qty: 8, price: 2.5 }, { sku: 'chocolates', qty: 5, price: 10 } ], status: 'A' }"),
                Document.parse("{ _id: 3, cust_id: 'Busby Bee', ord_date: '2020-03-08', price: 50, " +
                        "items: [ { sku: 'oranges', qty: 10, price: 2.5 }, { sku: 'pears', qty: 10, price: 2.5 } ], status: 'A' }"),
                Document.parse("{ _id: 4, cust_id: 'Busby Bee', ord_date: '2020-03-18', price: 25, " +
                        "items: [ { sku: 'oranges', qty: 10, price: 2.5 } ], status: 'A' }"),
                Document.parse("{ _id: 5, cust_id: 'Busby Bee', ord_date: '2020-03-19', price: 50, " +
                        "items: [ { sku: 'chocolates', qty: 5, price: 10 } ], status: 'A' }"),
                Document.parse("{ _id: 6, cust_id: 'Cam Elot', ord_date: '2020-03-19', price: 35, " +
                        "items: [ { sku: 'carrots', qty: 10, price: 1.0 }, { sku: 'apples', qty: 10, price: 2.5 } ], status: 'A' }"),
                Document.parse("{ _id: 7, cust_id: 'Cam Elot', ord_date: '2020-03-20', price: 25, " +
                        "items: [ { sku: 'oranges', qty: 10, price: 2.5 } ], status: 'A' }"),
                Document.parse("{ _id: 8, cust_id: 'Don Quis', ord_date: '2020-03-20', price: 75, " +
                        "items: [ { sku: 'chocolates', qty: 5, price: 10 }, { sku: 'apples', qty: 10, price: 2.5 } ], status: 'A' }"),
                Document.parse("{ _id: 9, cust_id: 'Don Quis', ord_date: '2020-03-20', price: 55, " +
                        "items: [ { sku: 'carrots', qty: 5, price: 1.0 }, { sku: 'apples', qty: 10, price: 2.5 }, { sku: 'oranges', qty: 10, price: 2.5 } ], status: 'A' }"),
                Document.parse("{ _id: 10, cust_id: 'Don Quis', ord_date: '2020-03-23', price: 25, " +
                        "items: [ { sku: 'oranges', qty: 10, price: 2.5 } ], status: 'A' }")
        ));

        // Mapreduce operasjon før ekstra funksjoner
        /* String mapFunction = "function() { emit(this.cust_id, this.price); }";
        String reduceFunction = "function(keyCustId, valuesPrices) { return Array.sum(valuesPrices); }";

        collection.mapReduce(mapFunction, reduceFunction).collectionName("map_reduce_example").toCollection();

        MongoCollection<Document> mapReduceResults = service.getDatabase().getCollection("map_reduce_example");

        Bson sort = ascending("_id");
        MongoCursor<Document> cursor = mapReduceResults.find().sort(sort).iterator();

        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }*/

        // Oppdatert map-funksjon: Emit både 'price' og 'ord_date'
        String mapFunction = "function() { emit(this.cust_id, {price: this.price, ord_date: this.ord_date}); }";

        // Oppdatert reduce-funksjon: Summer 'price' og velg en ord_date (her tar vi den siste datoen i settet)
        String reduceFunction = "function(keyCustId, values) { " +
                "let totalPrice = 0; " +
                "let latestDate = null; " +
                "values.forEach(function(value) { " +
                "    totalPrice += value.price; " +
                "    if (!latestDate || value.ord_date > latestDate) { " +
                "        latestDate = value.ord_date; " +
                "    }" +
                "}); " +
                "return {price: totalPrice, ord_date: latestDate}; " +
                "}";

        // Utfør mapReduce-operasjonen med oppdatert map- og reduce-funksjon
        collection.mapReduce(mapFunction, reduceFunction).collectionName("map_reduce_example").toCollection();

        // Hent resultatene
        MongoCollection<Document> mapReduceResults = service.getDatabase().getCollection("map_reduce_example");

        // Sortér resultatene
        Bson sort = ascending("_id");
        MongoCursor<Document> cursor = mapReduceResults.find().sort(sort).iterator();

        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
}
