package kw.kng.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "kw.kng.repo.oracle",
    entityManagerFactoryRef = "oracleEMF",
    transactionManagerRef = "oracleTxMgmr"
)
public class OracleDBConfig {

    @Bean
    @ConfigurationProperties(prefix = "oracle.ds")
    public DataSource createOracleDS()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleEMF")
    public LocalContainerEntityManagerFactoryBean createOracleEntityManagerFactoryBean(EntityManagerFactoryBuilder builder)
    {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
       // props.put("hibernate.default_schema", "starter");
        
        return builder
            .dataSource(createOracleDS())
            .packages("kw.kng.entities.oracle")
            .properties(props)
            .build();
    }

    @Bean(name = "oracleTxMgmr")
    public PlatformTransactionManager createOracleTxMgmr(@Qualifier("oracleEMF") EntityManagerFactory factory) 
    {
        return new JpaTransactionManager(factory);
    }
}