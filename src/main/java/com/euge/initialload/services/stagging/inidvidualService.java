package com.euge.initialload.services.stagging;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euge.initialload.model.stagging.Individual;
import com.euge.initialload.model.stagging.VistaInd;
import com.euge.initialload.repository.stagging.individualStaggingRepository;
import com.euge.initialload.repository.stagging.vistaIndRepository;

@Service
public class inidvidualService {
	 @Autowired
	 private individualStaggingRepository indStaggingRepository;
	 
	 @Autowired
	 private vistaIndRepository vistaRepository;
	 
	 
	    public Individual  buscaSttagingConSCN(String scn) {
	    	BigDecimal valor=BigDecimal.valueOf(Long.valueOf(scn));
	        return indStaggingRepository.findByScn(valor);
	    }
	    public List<Individual> buscaConJob(String nombre) {
	    	return indStaggingRepository.findByJob(nombre);
	    }
	    public List<VistaInd> recuperaRegistrosJob(String nombre) {
	    	return vistaRepository.findByJob(nombre);
	    }
}
