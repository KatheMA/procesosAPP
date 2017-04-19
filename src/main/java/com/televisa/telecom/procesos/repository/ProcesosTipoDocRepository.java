package com.televisa.telecom.procesos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.televisa.telecom.procesos.persistence.ProcesosArchivo;
import com.televisa.telecom.procesos.persistence.ProcesosCatalogoDoc;
import com.televisa.telecom.procesos.persistence.ProcesosCategoria;
import com.televisa.telecom.procesos.persistence.ProcesosTipoDoc;

@Repository
public interface ProcesosTipoDocRepository  extends PagingAndSortingRepository<ProcesosTipoDoc, Long> {	
	
	 @Query("from ProcesosTipoDoc p where p.nombreProceso = ?1")
	 public ProcesosTipoDoc findTipoDocByName(String nombreProceso);
}
