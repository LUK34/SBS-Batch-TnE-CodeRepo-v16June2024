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
						basePackages="kw.kng.repo.mysql.ds2",
						entityManagerFactoryRef="mysqlEMF2",
						transactionManagerRef="mysqlTxMgmr2"
					   )
public class MySqlDb2Config 
{
	// --------------------------------------------------------------------------------------------------------
		// Configure DataSource

		@Bean
		@ConfigurationProperties(prefix = "mysql.ds2")
		public DataSource createMySqlDS2() {
			return DataSourceBuilder.create().build();
		}
	//--------------------------------------------------------------------------------------------------------

	//--------------------------------------------------------------------------------------------------------
	// Configure Entity Manager Factory

		@Bean(name = "mysqlEMF2")
		public LocalContainerEntityManagerFactoryBean createMySqlEntityManagerFactoryBean(
				EntityManagerFactoryBuilder builder) {
	//Create Map Object having Hibernate Properties
			Map<String, Object> props = new HashMap();
			props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			props.put("hibernate.hbm2ddl.auto", "update");

	//create and return LocalContainerEntityManagerFactoryBean class object which 
	//makes EntityManagerFactory as Spring bean
			return builder.dataSource(createMySqlDS2()) // datasource
					.packages("kw.kng.entities.mysql.ds2") // model class pacakage
					.properties(props) // hibernate properties
					.build();
		}
	//--------------------------------------------------------------------------------------------------------

	// Configure Transaction Manager

		@Bean(name = "mysqlTxMgmr2")
		public PlatformTransactionManager createMysqlTxMgmr(@Qualifier("mysqlEMF2") EntityManagerFactory factory) {
			return new JpaTransactionManager(factory);
		}
		/*
		 * To make Container not to use the AutoConfiguration based EntityManagerFactory
		 * object and to use our EntityManageFactory object.
		 */

	//--------------------------------------------------------------------------------------------------------


}
