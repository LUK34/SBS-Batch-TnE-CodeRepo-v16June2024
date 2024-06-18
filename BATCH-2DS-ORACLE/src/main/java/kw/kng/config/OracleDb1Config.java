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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
						basePackages="kw.kng.repo.oracle.ds1",
						entityManagerFactoryRef="oracleEMF1",
						transactionManagerRef="oracleTxMgmr1"
					   )
public class OracleDb1Config 
{
	@Bean
    @ConfigurationProperties(prefix = "oracle.ds1")
	@Primary
    public DataSource createOracleDS1()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleEMF1")
    @Primary
    public LocalContainerEntityManagerFactoryBean createOracleEntityManagerFactoryBean(EntityManagerFactoryBuilder builder)
    {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        //props.put("hibernate.default_schema", "starter1");
        
        return builder
            .dataSource(createOracleDS1())
            .packages("kw.kng.entities.oracle.ds1")
            .properties(props)
            .build();
    }

    @Bean(name = "oracleTxMgmr1")
    @Primary
    public PlatformTransactionManager createOracleTxMgmr(@Qualifier("oracleEMF1") EntityManagerFactory factory) 
    {
        return new JpaTransactionManager(factory);
    }
}
