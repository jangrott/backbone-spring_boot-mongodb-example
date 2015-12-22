package pl.jangrot.lnksmgmt;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

public class MongoConfig extends AbstractMongoConfiguration {

    private static final String DB_URL = System.getenv("OPENSHIFT_MONGODB_DB_URL");

    @Override
    protected String getDatabaseName() {
        return "lnksmgmt";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        MongoClientURI mongoClientURI = new MongoClientURI(DB_URL);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        return mongoClient;
    }
}