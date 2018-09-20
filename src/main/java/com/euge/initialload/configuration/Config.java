package com.euge.initialload.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Config {
	
	@Value("${initialload.jobname}")
	private String jobName;

	public String getJobName(){
		return jobName;
	}

	@Value("${initialload.migracion}")
	private boolean migracion=false;

	public boolean getMigracion(){
		return migracion;
	}
	
	@Value("${initialload.freqCommit}")
	private int freqCommit;
	
	public int getFreqCommit(){
		return freqCommit;
	}
	
	@Value("${initialload.tries}")
	private int tries;
	
	public int getTries(){
		return tries;
	}
	
	@Value("${initialload.freq}")
	private String frequency;
	
	public String getFreq(){
		return frequency;
	}
	
	@Value("${initialload.sizeUnitWork}")
	private long sizeUnitWork;
	
	public long getSizeUnitWork(){
		return sizeUnitWork;
	}
	
	@Value("${stagging.usuario}")
	private String userStagging;
	@Value("${stagging.pass}")
	private String passStagging;
	@Value("${stagging.url}")
	private String urlStagging;
	@Value("${stagging.show-sql}")
	private String show_sql1;
	@Value("${stagging.dialect}")
	private String dialect1;
	
	
	public String usuStagging(){
		return userStagging;
	}
	public String passStagging(){
		return passStagging;
	}
	public String urlStagging(){
		return urlStagging;
	}
	public String show_sqlStagging(){
		return show_sql1;
	}
	public String dialectStagging(){
		return dialect1;
	}
	
	
	@Value("${fda.usuario}")
	private String userFda;
	@Value("${fda.pass}")
	private String passFda;
	@Value("${fda.url}")
	private String urlFda;
	@Value("${fda.show-sql}")
	private String show_sql2;
	@Value("${fda.dialect}")
	private String dialect2;
	@Value("${fda.driver}")
	private String driver2;
	
	public String usuFda(){
		return userFda;
	}
	public String passFda(){
		return passFda;
	}
	public String urlFda(){
		return urlFda;
	}
	public String show_sqlFda(){
		return show_sql2;
	}
	public String dialectFda(){
		return dialect2;
	}
	public String driverFda(){
		return driver2;
	}
	
}
