package pl.jangrot.lnksmgmt;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
@ComponentScan(basePackageClasses = LinkRepository.class)
public class TestApplicationConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "db-name";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new Fongo("in-memory").getMongo();
    }
}