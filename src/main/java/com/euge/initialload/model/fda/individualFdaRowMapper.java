package com.euge.initialload.model.fda;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.euge.initialload.model.fda.individualFDA;

public class individualFdaRowMapper implements RowMapper<individualFDA>
{
	public individualFDA mapRow(ResultSet rs, int rowNum) throws SQLException {
		individualFDA customer = new individualFDA();

		customer.setId(rs.getInt("id"));
		customer.setIntegrationID(rs.getInt("IntegrationID"));
		customer.setName(rs.getString("Name"));
		customer.setScn(rs.getInt("Scn"));
		customer.setSurname(rs.getString("Surname"));
		return customer;
	}

}
