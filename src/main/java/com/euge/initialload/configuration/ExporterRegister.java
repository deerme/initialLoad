package com.euge.initialload.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;

@Configuration
public class ExporterRegister {
	private List<Collector> collectors; 
	 
	@Autowired
	CollectorRegistry registro;
	
    public ExporterRegister(List<Collector> collectors) {
         for (Collector collector : collectors) {
             collector.register();
         }
         this.collectors = collectors;
    }

    public List<Collector> getCollectors() {
         return collectors;
    }
}
