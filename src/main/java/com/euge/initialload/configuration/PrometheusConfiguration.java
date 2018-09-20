package com.euge.initialload.configuration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.euge.initialload.model.Estado;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import io.prometheus.client.hotspot.ThreadExports;
import io.prometheus.client.hotspot.VersionInfoExports;


@Configuration
@ConditionalOnClass(CollectorRegistry.class)
public class PrometheusConfiguration {

	@Autowired
	private Estado est;
	
	//Declara el registro de metricas en caso de que no se haya declarado ya
     @Bean
     @ConditionalOnMissingBean
     CollectorRegistry metricRegistry() {
         return CollectorRegistry.defaultRegistry;
     }

     
     //Declara una Bean que se encargara de registrar los collectors por defeto
     @Bean
     ExporterRegister exporterRegister() {
    	 try {
			pg=new PushGateway(new URL(ruta));
		} catch (MalformedURLException e) {
			System.out.println("Wrong URL for PG");
			e.printStackTrace();
			pg=new PushGateway(ruta);
		}
    	 //Declara los colectores que vamos a capturar
    	 List<Collector> collectors = new ArrayList<Collector>();
    	 //Especifica las metricas a exportar
    	 //Server Statistics
    	 collectors.add(new StandardExports());
		 collectors.add(new MemoryPoolsExports());
		 //JVM Statistics
		 collectors.add(new VersionInfoExports());
		 collectors.add(new ThreadExports());
		 
		 //Los registra 
         ExporterRegister register = new ExporterRegister(collectors);
         return register;
      }
    
     private Logger logger = LoggerFactory.getLogger(getClass());
     
     @Autowired
     CollectorRegistry registro;
     
 	 @Value("${health.pg}")
 	 private String ruta;
 	 private PushGateway pg;
     
 	 @Value("${health.active}")
 	 private boolean active=true;
 	 
     @Scheduled(cron="${health.prometheus}")
 	 public void send() {
    	 if(!active)return;
         try {
 			pg.pushAdd(registro,est.getJobName());

 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			logger.error("Error al enviar al PG");
 			logger.error(e.getMessage());
 		}
 		
 	 }
}