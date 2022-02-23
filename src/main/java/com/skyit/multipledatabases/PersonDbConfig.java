package com.skyit.multipledatabases;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "personEntityManagerFactory",
        transactionManagerRef = "personTransactionManager",
        basePackages = {"com.skyit.multipledatabases.person"}
)
public class PersonDbConfig {

    @Bean(name = "personDataSource")
    @ConfigurationProperties(prefix = "token.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "personEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean personEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("personDataSource") DataSource dataSource
    ){
        return builder.dataSource(dataSource)
                .packages("com.skyit.multipledatabases.persondomain")
                .persistenceUnit("person")
                .build();
    }

    @Bean(name = "personTransactionManager")
    public PlatformTransactionManager personTransactionManager(
            @Qualifier("personEntityManagerFactory") EntityManagerFactory personEntityManagerFactory
            ){
        return new JpaTransactionManager(personEntityManagerFactory);
    }
}
