package com.euge.initialload.repository.stagging;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.euge.initialload.model.stagging.VistaInd;

@Repository
public interface vistaIndRepository extends CrudRepository<VistaInd,Long>   {
	public List<VistaInd> findByJob(String name);
}
