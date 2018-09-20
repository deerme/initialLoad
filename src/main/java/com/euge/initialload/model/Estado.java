package com.euge.initialload.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.stereotype.Component;

import com.euge.initialload.configuration.Config;
import com.euge.initialload.demon.migracion;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Summary;

@Component
public class Estado {

	private boolean daemonRunning=false;
	 
	@Autowired
	private Config configuracion;
	
	@Autowired(required=false)
	private ApplicationInstanceInfo appInfo;

	private long lastExecutionRunTime=0;
	static final Summary elapsed= Summary.build()
		     .name("Elapsed")
		     .help("Last Execution RunTime")
		     .register();
	 
	public long getLastExecutionRunTime() {
		return lastExecutionRunTime/1000;
	}
	public void setLastExecutionRunTime(long lastExecutionRunTime) {
		this.lastExecutionRunTime = lastExecutionRunTime;
		elapsed.observe(lastExecutionRunTime);
	}
		
	
	public long getTotalExecutionRunTime() {
		return migracion.getRunningTime();
	}	

		
	private long numRecords=0;
	private long numRecordsError=0;
    private static final Counter numRecordsTotal = Counter.build()
    		//especifica el nombre
    		.name("NumberOfRecords")
    		//etiquetas
    		.labelNames("Status")
    		//y la ayuda
    		.help("Total number records processed")
    		.register();

	public long getNumRecords() {
		return numRecords;
	}
	
	public void incNumRecords() {
		this.numRecords ++;
		numRecordsTotal.labels("Processed").inc();
	}
	
	public long getNumRecordsError() {
		return numRecordsError;
	}
	
	public void incNumRecordsError() {
		this.numRecordsError ++;
		numRecordsTotal.labels("Error").inc();
	}

		
	private long numWorkUnits=0;
	public long getNumWorkUnits() {
		return numWorkUnits;
	}
	public void incNumWorkUnits() {
		this.numWorkUnits ++;
	}
	
	
    private double recordsUnitWork=0;
    private static final Summary numRecordsWU = Summary.build()
    		//especifica el nombre
    		.name("NumberOfRecordsWU")
    		//y la ayuda
    		.help("Total number records per Work Unit")
    		.register();
	
	public double getRecordsUnitWork() {
		return recordsUnitWork;
	}
	public void setRecordsUnitWork(double recordsUnitWork) {
		this.recordsUnitWork = recordsUnitWork;
		numRecordsWU.observe(recordsUnitWork);
	}
	
	
	public boolean isEsMigracion() {
		return configuracion.getMigracion();
	}
	
    private static final Gauge numProcs = Gauge.build()
    		//especifica el nombre
    		.name("NumberOfProcesses")
    		//y la ayuda
    		.help("Total number of processes running")
    		.register();
    
	public boolean isDaemonRunning() {
		return daemonRunning ;
	}
	
	public void setDaemonRunning(boolean daemonRunning) {
		this.daemonRunning = daemonRunning;
		if(this.daemonRunning) {
			numProcs.inc();
		}
		else {
			numProcs.dec();
		}
	}
	
	public boolean isMigracionRunning() {
		return migracion.getEstado();
	}

	//gests the job name
	public String getJobName() {
		String job_name;
		if(appInfo!=null){
			job_name=configuracion.getJobName()+"_"+appInfo.getInstanceId(); 
		}
		else{
			job_name=configuracion.getJobName();
		}
		return job_name.substring(0, Math.min(job_name.length(), 40));
	}
}
