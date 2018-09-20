package com.euge.initialload.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.euge.initialload.configuration.Config;
import com.euge.initialload.model.stagging.VistaInd;

@Service
public class initialLoadUtilities {
	
	@Qualifier("entityManagerFactoryStagging")
	@Autowired
    private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private Config configuracion;
	
	public List<VistaInd> retrieveRecordsJob(String nombre) {
		
        EntityManager session=null;
        List <VistaInd> respuesta;
        
        try {
        	session = entityManagerFactory.createEntityManager();
        	respuesta=session.createNativeQuery("select * "+
        			"from vista_ind where job =:nombre",VistaInd.class)
                    .setParameter("nombre", nombre)
                    .getResultList();
        }
        catch (Exception e){
            return null;
        }
        finally {
            if(session !=null && session.isOpen()) session.close();
        }
        return respuesta;
	}
	
	@Transactional
	public boolean reserveRecordsStagging(String jobName){
        
		EntityManager session =null;
		
        try {
        	session = entityManagerFactory.createEntityManager();
	        StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("RESERVE_IND");
	  	    // set parameters
	  	    storedProcedure.registerStoredProcedureParameter("NAME", String.class, ParameterMode.IN);
	  	    storedProcedure.registerStoredProcedureParameter("NUM", Long.class, ParameterMode.IN);
	  	    storedProcedure.registerStoredProcedureParameter("TAM", Long.class, ParameterMode.IN);
	  	    storedProcedure.setParameter("NAME", jobName);
	  	    storedProcedure.setParameter("NUM", (long)configuracion.getTries());
	  	    storedProcedure.setParameter("TAM", configuracion.getSizeUnitWork());
		    
	  	    // execute SP
	  	    storedProcedure.execute();
            return true;
        }
        catch (Exception e){
        	System.out.println(e.getMessage());
            return false;
        }
        finally {
            if(session!=null && session.isOpen()) session.close();
        }
    }
	
	@Transactional
	public boolean updateStatusRecordsStagging(String jobName){

		EntityManager session =null;

        try {
    		session = entityManagerFactory.createEntityManager();
	        StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("UPD_OK_IND");
	  	    // set parameters
	  	    storedProcedure.registerStoredProcedureParameter("NAME", String.class, ParameterMode.IN);
	  	    storedProcedure.setParameter("NAME", jobName);

	  	    // execute SP
	  	    storedProcedure.execute();
            return true;
        }
        catch (Exception e){
			System.out.println(e.getMessage());

            return false;
        }
        finally {
            if(session!=null && session.isOpen()) session.close();
        }
    }
	
	@Transactional
	public void updateStatusErrInRecordsStagging(HashMap<BigDecimal,String> errors){

		//If there are no records in errror, exit
		if(errors.size()==0)return;
		EntityManager session=null;
		try {
			session=entityManagerFactory.createEntityManager();
			String error_code;
	        StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("UPD_KO_IND");
	  	    storedProcedure.registerStoredProcedureParameter("ID", BigDecimal.class, ParameterMode.IN);
	  	    storedProcedure.registerStoredProcedureParameter("CODE", String.class, ParameterMode.IN);
	
	  	    //Udpates sattus of each record
	  	    for(BigDecimal ID:errors.keySet())
	        {
		  	    // set parameters
	  	    	error_code=errors.get(ID);
	  	    	error_code=error_code.substring(0, Math.min(error_code.length(), 40));
		  	    storedProcedure.setParameter("CODE", error_code);
		  	    storedProcedure.setParameter("ID", ID);
		  	    // execute SP
		  	    storedProcedure.execute();
	        	
	        }

		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		finally {
	  	    //Close session
	  	    if(session!=null && session.isOpen()) session.close();
		}
  	    return;
    }
	

}

