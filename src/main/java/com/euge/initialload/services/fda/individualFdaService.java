package com.euge.initialload.services.fda;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.euge.initialload.model.fda.individualFDA;
import com.euge.initialload.model.fda.individualFdaRowMapper;
import com.euge.initialload.repository.fda.individualFda;

@Service
public class individualFdaService implements individualFda{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
    
	@Override
	public int insertIndividual(individualFDA person) {
		
		return transactionTemplate.execute(new TransactionCallback<Integer>() {

			@Override
			public Integer doInTransaction(TransactionStatus ts) {
				try {
		            String inserQuery = "INSERT INTO individual(IntegrationID, Name, Scn, Surname)"
		                    + " VALUES(?, ?, ?, ?)";
		                Object[] params = new Object[] { person.getIntegrationID(),
		                    person.getName(),
		                    person.getScn(), person.getSurname()};
		                int[] types = new int[] { Types.NUMERIC, Types.VARCHAR,Types.NUMERIC,Types.VARCHAR};
		                int value = jdbcTemplate.update(inserQuery, params,types);
		                return value;
				}
				catch(Exception ex) {
					ts.setRollbackOnly();
				}
				return 0;
			}
			
		});

	}

	@Override
	public individualFDA getIndividual(long scn) {
		String sql = "select id,IntegrationID, Name, Scn, Surname from individual " +
		        "where scn=?";
		individualFDA respuesta=null;
		try {
			respuesta= jdbcTemplate.queryForObject(sql, new Object[] { scn }, new individualFdaRowMapper());
		}
		catch(EmptyResultDataAccessException ex) {
			respuesta=new individualFDA(-1, "--", -1, "--");
		}
		return respuesta;
		    
	}
	
	@Override
	public individualFDA getIndividual1(long scn) {
		String sql = "select id,IntegrationID, Name, Scn, Surname from individual " +
		        "where scn=?";

		individualFDA respuesta=null;
		try{
			respuesta= jdbcTemplate.queryForObject(sql, new Object[] { scn }, new BeanPropertyRowMapper<individualFDA>(individualFDA.class));
		}
		catch(EmptyResultDataAccessException ex) {
			respuesta=new individualFDA(-1, "--", -1, "--");
		}
		return respuesta;   
	}

	@Override
	public List<individualFDA> getIndividuals(String surname) {
		String sql = "SELECT id,IntegrationID, Name, Scn, Surname from individual "+
						"where Surname like ?";
		List<individualFDA> customers = new ArrayList<individualFDA>();
		surname="%"+surname+"%";
		List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql,new Object[] { surname });
		for (Map<String,Object> row : rows) {
			individualFDA customer = new individualFDA();
			
			customer.setId((int)row.get("id"));
			customer.setIntegrationID((int)row.get("IntegrationID"));
			customer.setName((String)row.get("Name"));
			customer.setScn((int)row.get("Scn"));
			customer.setSurname((String)row.get("Surname"));
			customers.add(customer);
		}
		return customers;
	}

	@Override
	public List<individualFDA> getIndividuals1(String surname) {
		String sql = "SELECT id,IntegrationID, Name, Scn, Surname from individual "+
				"where Surname like ?";
		
		surname="%"+surname+"%";
		List<individualFDA> customers  = jdbcTemplate.query(sql,new Object[] { surname },
				new BeanPropertyRowMapper<individualFDA>(individualFDA.class));
		
		return customers;
	}

	@Override
	public int getTotIndividuals(String surname) {
		String sql = "SELECT count(*) from individual FROM individual "+
				"where Surname like ?";
		
		int total = jdbcTemplate.queryForObject(sql,new Object[] { surname },int.class);
	
		return total;
	}

	@Override
	public String getNameIndividual(long scn) {
		String sql = "SELECT Name from individual FROM individual "+
				"where Surname like ?";

		String name = (String)jdbcTemplate.queryForObject(sql, new Object[] { scn }, String.class);

		return name;
	}

}
