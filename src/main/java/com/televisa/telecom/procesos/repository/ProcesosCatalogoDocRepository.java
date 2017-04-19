package com.televisa.telecom.procesos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.televisa.telecom.procesos.persistence.ProcesosArchivo;
import com.televisa.telecom.procesos.persistence.ProcesosCatalogoDoc;
import com.televisa.telecom.procesos.persistence.ProcesosCategoria;

@Repository
public interface ProcesosCatalogoDocRepository  extends PagingAndSortingRepository<ProcesosCatalogoDoc, Long> {	
	
	@Query("from ProcesosCatalogoDoc p where p.fechaCreacion between ?1 and ?2 and idTipoDoc NOT in (2)  order by nombreDoc")
	public List<ProcesosCatalogoDoc> findBetweenDates(Date start, Date end);
	
	 static final String QUERY_NOMBRE_DOCS = "select p from ProcesosCatalogoDoc p"
		       + " where p.nombreDocInterno like CONCAT('%', ?1, '%') order by nombreDoc";
	 
	 @Query(QUERY_NOMBRE_DOCS)
	 List<ProcesosCatalogoDoc> findByFilialAndBranchLike(String nombre);
	 
	 @Query("from ProcesosCatalogoDoc p where p.idCategoria = ?1 order by p.nombreDoc asc")
	 public List<ProcesosCatalogoDoc> findFilesPerCategory(Long idCategoria);
	 
	 @Query("from ProcesosCatalogoDoc p where p.idCategoria = ?1 and p.idTipoDoc = ?2 order by p.nombreDoc asc")
	 public List<ProcesosCatalogoDoc> findFilesPerCategoryAndTipoDoc(Long idCategoria, Long idTipoDoc);
	 
	 @Query("from ProcesosCatalogoDoc p where p.idArchivo = ?1")
	 public ProcesosCatalogoDoc findFilesPerIdArchivo(Long idArchivo);
	 
}
