package com.euge.initialload.configuration;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "entityManagerFactoryStagging",
  basePackages = { "com.euge.initialload.repository.stagging" }
)
public class StaggingDbConfig {

	@Autowired
	private Config configuracion;

	@Primary
	@Bean(name = "dataSourceStagging")
	public DataSource dataSource() throws SQLException {
	    OracleDataSource dataSource = new OracleDataSource();
	    dataSource.setUser(configuracion.usuStagging());
	    dataSource.setPassword(configuracion.passStagging());
	    dataSource.setURL(configuracion.urlStagging());
	    
	    dataSource.setImplicitCachingEnabled(true);
	    dataSource.setFastConnectionFailoverEnabled(true);
	    
	    return dataSource;
	}
		  
	@Primary
	@Bean(name = "entityManagerFactoryStagging")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
				@Qualifier("dataSourceStagging") DataSource dataSource) {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		Properties properties = new Properties();
        properties.setProperty("hibernate.jdbc.fetch_size", String.valueOf(configuracion.getSizeUnitWork()));
        properties.setProperty("hibernate.jdbc.batch_size", String.valueOf(configuracion.getSizeUnitWork()));
        properties.setProperty("hibernate.order_inserts", "true");
        properties.setProperty("hibernate.order_updates", "true");
        //Set dialect
        properties.setProperty("hibernate.dialect", configuracion.dialectStagging());
        //Show SQL
        properties.setProperty("hibernate.show_sql", configuracion.show_sqlStagging());	
        //remove warnings on start up about clobs
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setJpaProperties(properties);
		emf.setPackagesToScan("com.euge.initialload.model.stagging");
		emf.setPersistenceUnitName("stagging");
		emf.afterPropertiesSet();	
		
        return emf;
	}
	
	@Primary
	@Bean(name = "transactionManagerStagging")
	public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactoryStagging") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}


