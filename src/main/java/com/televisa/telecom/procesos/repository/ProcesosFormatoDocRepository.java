package com.televisa.telecom.procesos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.televisa.telecom.procesos.persistence.ProcesosFormatoDoc;

public interface ProcesosFormatoDocRepository extends
		PagingAndSortingRepository<ProcesosFormatoDoc, Long> {
	
	 @Query("from ProcesosFormatoDoc p where p.mimeType = ?1")
	 public ProcesosFormatoDoc findTipoFormatoByMimeType(String mimeType);
	
}
