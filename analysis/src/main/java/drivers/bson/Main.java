package drivers.bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {

    public static void main(String[] args) {

        Document doc = Document.parse("{\"k1\":\"v1\"}");
        doc.toJson();

    }
}
