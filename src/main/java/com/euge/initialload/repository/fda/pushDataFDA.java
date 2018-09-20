package com.euge.initialload.repository.fda;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.euge.initialload.model.stagging.VistaInd;

@Service
public class pushDataFDA {

	@Autowired
	private JdbcTemplate jdbcTemplateFDA;
	
	private SimpleJdbcCall pushdataFDA;
	
	@Autowired
	void pushDataFDA () {
		pushdataFDA=new SimpleJdbcCall(jdbcTemplateFDA).withProcedureName("pushIndividual");
		pushdataFDA.addDeclaredParameter(new SqlParameter("integrationID", Types.NUMERIC));
		pushdataFDA.addDeclaredParameter(new SqlParameter("name", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("surname", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("scn", Types.NUMERIC));
		pushdataFDA.addDeclaredParameter(new SqlParameter("segment", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("custIntegrationID", Types.NUMERIC));
		pushdataFDA.addDeclaredParameter(new SqlParameter("email", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("phone", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("Gaia", Types.NUMERIC));
		pushdataFDA.addDeclaredParameter(new SqlParameter("AddrIntegrationID", Types.NUMERIC));
		pushdataFDA.addDeclaredParameter(new SqlParameter("Street", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("Number", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("City", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("PostalCode", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("Operation", Types.VARCHAR));
		pushdataFDA.addDeclaredParameter(new SqlParameter("phone", Types.VARCHAR));
		
	}
	
	public boolean pushIndividualDataFDA(VistaInd reg) {
		//service to push data
	
		Map<String, Object> params=new HashMap<String, Object>();
	    params.put("integrationID", reg.getIntegrationid());
	    params.put("name", reg.getName());
	    params.put("surname", reg.getSurname());
	    params.put("scn", reg.getScn());
	    params.put("segment", reg.getSegment());
	    params.put("custIntegrationID", reg.getCustIntegrationid());
	    params.put("email", reg.getEmail());
	    params.put("Gaia", reg.getGaia());
	    params.put("AddrIntegrationID", reg.getAddr_integrationid());
	    params.put("Street", reg.getStreet());
	    params.put("Number", reg.getStreetnum());
	    params.put("City", reg.getCity());
	    params.put("PostalCode", reg.getPostalcode());
	    params.put("Operation", reg.getOperation());
	    params.put("phone", reg.getPhone());
	    
	    //Executes the api
	    pushdataFDA.execute(params);
	    
	    return true;
	}
}