package com.euge.initialload.demon;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.euge.initialload.configuration.Config;
import com.euge.initialload.model.Estado;
import com.euge.initialload.model.stagging.VistaInd;
import com.euge.initialload.repository.fda.pushDataFDA;
import com.euge.initialload.services.initialLoadUtilities;


@Component
public class work {
	
	@Autowired
	private Estado est;
	
	@Autowired
	private Config configuracion;
	
	@Autowired
	private TransactionTemplate transactionTemplateFDA;

	@Autowired
	pushDataFDA pushDataFDAservice;
	
	@Autowired
	initialLoadUtilities utils;

	//record index
	private int record_i=0;

	public boolean procesaUnitWork() {
		long duration=System.currentTimeMillis();
		try {
			
			//Updates status and stops the chronograph
			est.setDaemonRunning(true);
	
			//gets the frequency to commit
			int freqCommit=configuracion.getFreqCommit();
			//gests the job name
			String job_name=est.getJobName();
			
			//Reserves records to be processed by the job
			utils.reserveRecordsStagging(job_name);
			record_i=0;
			
			//Reads the records reserved
			List<VistaInd> registros=utils.retrieveRecordsJob(job_name);
	
			//Number of records to process
			//total of records to process
			final int total_records=(registros!=null?registros.size():0);
			int records_updated=0;
			
			//Loops through the records
			while(record_i<total_records) {
	
	
				try {
					//Process transaction in FDA
					records_updated=transactionTemplateFDA.execute(new TransactionCallback<Integer>() {
		
						@Override
						public Integer doInTransaction(TransactionStatus ts) {
						
							//record index within the unit of work, and error index
							int unit_of_work_i=0;
								
							try {
								//record being processed
								VistaInd reg;
								//Error collection
								HashMap<BigDecimal, String> errors=new HashMap<BigDecimal, String>();
								
								while((record_i+unit_of_work_i)<total_records && unit_of_work_i<freqCommit) 
								{
									//Fetch the record to process
									reg=registros.get(record_i+unit_of_work_i);
		
									//Process the record
									try {		
										//Updates FDA
										pushDataFDAservice.pushIndividualDataFDA(reg);
										//Updates statistics
										est.incNumRecords();						
									}
									catch(Exception ex) {
										//Stores the record and the error it triggered
										errors.put(reg.getId(), ex.toString());
										//Updates statistics
										est.incNumRecordsError();
									}
									//Move to next record
									unit_of_work_i++;
								}
								
								//Update records in error
								utils.updateStatusErrInRecordsStagging(errors);
							}
							catch(Exception ex) {
								System.out.println(ex.getMessage());
							}
							return unit_of_work_i;
						}
					});
					//Update the index of records processed
					record_i+=records_updated;
				}
				catch(Exception ex) {
					//AQUI
					//Poner los registros en error y actualizar estadistica?
					System.out.println(ex.getMessage());
					//Updates status and stops the chronograph
					est.setDaemonRunning(false);
					est.setLastExecutionRunTime(System.currentTimeMillis()-duration);
					return false;
				}
				
	
			}
		
			boolean respuesta;
			//Checks if any record was processed
			if(total_records==0) respuesta=false;
			else
			{
				//Updates status of records in stagging
				utils.updateStatusRecordsStagging(job_name);
				//Updates statistics
				est.setRecordsUnitWork(total_records);
				est.incNumWorkUnits();
				respuesta=true;
			}
			
			//Updates status and stops the chronograph
			est.setDaemonRunning(false);
			est.setLastExecutionRunTime(System.currentTimeMillis()-duration);
			return respuesta;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			//Updates status and stops the chronograph
			est.setDaemonRunning(false);
			est.setLastExecutionRunTime(System.currentTimeMillis()-duration);
			return false;
		}		
		
	}
}
