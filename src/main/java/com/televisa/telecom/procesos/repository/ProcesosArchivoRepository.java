package com.televisa.telecom.procesos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.televisa.telecom.procesos.persistence.ProcesosArchivo;
import com.televisa.telecom.procesos.persistence.ProcesosCategoria;

@Repository
public interface ProcesosArchivoRepository  extends PagingAndSortingRepository<ProcesosArchivo, Long> {	
	
	@Query("from ProcesosArchivo p where p.fechaCreacion between ?1 and ?2")
	public List<ProcesosArchivo> findBetweenDates(Date start, Date end);
	
	@Query("from ProcesosArchivo p where p.idArchivo = ?1")
	public ProcesosArchivo findArchivo(Long idArchivo);
	
}
