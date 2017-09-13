package test.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by mmamula on 26.4.2017.
 */
public class MongoTest {

    public static void test() {
        MongoClient client = new MongoClient("localhost", 27017);

        MongoDatabase db = client.getDatabase("java");

        MongoCollection<Document> c = db.getCollection("test");

        Document d = new Document("name", "test");

        c.insertOne(d);

        System.out.println(123);

    }

}
