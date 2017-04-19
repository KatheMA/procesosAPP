package com.televisa.telecom.procesos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.televisa.telecom.procesos.persistence.ProcesosUsuarios;

@Repository
public interface ProcesosUsuariosRepository  extends PagingAndSortingRepository<ProcesosUsuarios, Long> {	
	
	@Query("from ProcesosUsuarios p where p.usuario = ?1 and p.password = ?2")
	public ProcesosUsuarios findUser(String usuario, String password);
}

