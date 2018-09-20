package com.euge.initialload.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class FdaDbConfig {
    
	@Autowired
	Config configuracion;
	
	@Bean(name = "dataSourceFda")
	DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(configuracion.driverFda());
		ds.setUrl(configuracion.urlFda());
		ds.setPassword(configuracion.passFda());
		ds.setUsername(configuracion.usuFda());
		return ds;
	}

	@Bean(name = "jdbcTemplateFda")
	JdbcTemplate jdbcTemplateFda(@Qualifier("dataSourceFda") DriverManagerDataSource ds) {
		JdbcTemplate template=new JdbcTemplate();
		template.setDataSource(ds);
		return template;
	}
	
	@Bean(name = "transactionManagerFda")
	DataSourceTransactionManager transactionManagerFda(@Qualifier("dataSourceFda") DriverManagerDataSource ds) {
		DataSourceTransactionManager tm=new DataSourceTransactionManager();
		
		tm.setDataSource(ds);
		return tm;
	}
	
	@Bean(name = "transactionTemplateFda")
	TransactionTemplate transactionTemplateFda(@Qualifier("transactionManagerFda") DataSourceTransactionManager tm) {
		TransactionTemplate tt=new TransactionTemplate();
		tt.setTransactionManager(tm);
		return tt;
	}
}


  
