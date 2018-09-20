package com.euge.initialload.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.euge.initialload.model.Estado;
import com.euge.initialload.model.fda.individualFDA;
import com.euge.initialload.model.stagging.Individual;
import com.euge.initialload.services.fda.individualFdaService;
import com.euge.initialload.services.stagging.inidvidualService;

@RestController
@RequestMapping(value="v1/initialload")
public class status {

	@Autowired
	inidvidualService serv;
	
	@Autowired
	individualFdaService servFDA;
	
	@RequestMapping(value="/stagging/individual/{scn}",method = RequestMethod.GET)
    public Individual getStaggingIndividual( @PathVariable("scn") String scn) {
        return serv.buscaSttagingConSCN(scn);
    }
	
	@RequestMapping(value="/fda/individual/{scn}",method = RequestMethod.GET)
    public individualFDA getFdaIndividual( @PathVariable("scn") Long scn) {
        return servFDA.getIndividual(scn);
    }
	@RequestMapping(value="/fda/individual1/{scn}",method = RequestMethod.GET)
    public individualFDA getFdaIndividual1( @PathVariable("scn") Long scn) {
        return servFDA.getIndividual(scn);
    }
	@RequestMapping(value="/fda/individuals/{name}",method = RequestMethod.GET)
    public List<individualFDA> getFdaIndividuals( @PathVariable("name") String nombre) {
        return servFDA.getIndividuals(nombre);
    }
    @RequestMapping(value="/fda/individuals1/{name}",method = RequestMethod.GET)
    public List<individualFDA> getFdaIndividuals1( @PathVariable("name") String nombre) {
        return servFDA.getIndividuals(nombre);
    }
    
	@RequestMapping(value="/job/{nombre}",method = RequestMethod.GET)
    public List<Individual> getIndividualsenJob( @PathVariable("nombre") String name) {
        return serv.buscaConJob(name);
    }
	
	@Autowired
	private Estado est;
	
	@RequestMapping(value="/job/estado",method = RequestMethod.GET)
    public Estado getEstado( ) {
	        return est;
    }
}
