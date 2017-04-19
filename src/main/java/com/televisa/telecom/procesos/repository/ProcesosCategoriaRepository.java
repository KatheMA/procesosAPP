package com.televisa.telecom.procesos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.televisa.telecom.procesos.persistence.ProcesosArchivo;
import com.televisa.telecom.procesos.persistence.ProcesosCategoria;

@Repository
public interface ProcesosCategoriaRepository  extends PagingAndSortingRepository<ProcesosCategoria, Long> {	
	
	@Query("from ProcesosCategoria p where p.padre=0")
	public List<ProcesosCategoria> findParents();
	
	@Query("from ProcesosCategoria p where p.padre = ?1")
	public List<ProcesosCategoria> findSubcategories(Long idPadres);
	
}
