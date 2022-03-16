package eu.ensup.gestionetablissement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("eu.ensup.gestionetablissement.domain")
@EnableJpaRepositories("eu.ensup.gestionetablissement.repository")
@EnableTransactionManagement
public class DomainConfig {
}
