package pl.jangrot.lnksmgmt;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("pl.jangrot.lnksmgmt")
@Import({ MongoConfig.class})
public class ApplicationConfig {

}
