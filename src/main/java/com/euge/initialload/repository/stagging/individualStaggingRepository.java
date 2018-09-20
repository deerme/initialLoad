package com.euge.initialload.repository.stagging;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.euge.initialload.model.stagging.Individual;



@Repository
public interface individualStaggingRepository extends CrudRepository<Individual,Long>   {
	public Individual findByScn(BigDecimal scn);
	public List<Individual> findByJob(String name);
}


