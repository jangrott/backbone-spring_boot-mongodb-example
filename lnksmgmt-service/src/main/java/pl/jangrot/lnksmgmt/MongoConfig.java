package pl.jangrot.lnksmgmt;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongodb.db.name}")
    private String dbName;

    @Value("${mongodb.db.url}")
    private String dbUrl;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        MongoClientURI mongoClientURI = new MongoClientURI(dbUrl);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        return mongoClient;
    }
}