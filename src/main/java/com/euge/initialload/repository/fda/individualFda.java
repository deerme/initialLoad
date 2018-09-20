package com.euge.initialload.repository.fda;

import java.util.List;

import com.euge.initialload.model.fda.individualFDA;

public interface individualFda {
	individualFDA getIndividual(long scn);
	individualFDA getIndividual1(long scn);
	List<individualFDA> getIndividuals(String surname);
	List<individualFDA> getIndividuals1(String surname);
	
	int getTotIndividuals(String surname);
	String getNameIndividual(long scn);
	
	int insertIndividual(individualFDA indv);
}
