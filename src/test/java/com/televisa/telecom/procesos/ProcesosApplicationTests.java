package com.televisa.telecom.procesos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.televisa.telecom.procesos.config.PersitenceConfig;
import com.televisa.telecom.procesos.impl.FilesServiceImpl;
import com.televisa.telecom.procesos.persistence.ProcesosCategoria;
import com.televisa.telecom.procesos.repository.ProcesosCategoriaRepository;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ProcesosApplication.class,PersitenceConfig.class})
@SpringBootTest
public class ProcesosApplicationTests {

	@Autowired
	private FilesService servicio;
	
	@Autowired
	private ProcesosCategoriaRepository repCat;
	
	@Test
	public void contextLoads() {
		System.out.println("TEST");
		//servicio.seeCategories();
		
		ProcesosCategoria cat = new ProcesosCategoria();
		cat.setNombreCategoria("Ventas");
		cat.setPadre(new Long(0));
		
		repCat.save(cat);
		
		System.out.println("Persisted");
	}

}
