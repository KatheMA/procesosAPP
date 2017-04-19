package com.televisa.telecom.procesos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.televisa.telecom.procesos.config.PersitenceConfig;
import com.televisa.telecom.procesos.persistence.ProcesosArchivo;
import com.televisa.telecom.procesos.persistence.ProcesosCatalogoDoc;
import com.televisa.telecom.procesos.repository.ProcesosArchivoRepository;
import com.televisa.telecom.procesos.repository.ProcesosCatalogoDocRepository;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ProcesosApplication.class,PersitenceConfig.class})
@SpringBootTest
public class WritingFilesTests {
	
	@Autowired
	PdfFilesManager filesManager;
	
	@Autowired
	ProcesosArchivoRepository rep;
	
	@Autowired
	ProcesosCatalogoDocRepository docRep;
	
	@Test
	public void readWritePdfFile(){
		//filesManager.saveFile("xx", "Control_gastos_1", 1, 1);
		
		ProcesosArchivo archivoBack = rep.findOne(new Long(3));
		
		System.out.println("FECHA: "+archivoBack.getFechaCreacion()+" | "+archivoBack.getIdFormato());
		
		//filesManager.byteArrayToFile(archivoBack.getBytesArchivo());
		
	}
	
	//@Test
	public void queryFiles(){
		System.out.println("INICIO BUSQUEDA");
		//List<ProcesosCatalogoDoc> listaArchivos = docRep.findByFilialAndBranchLike("STO");
		
		List<ProcesosCatalogoDoc> listaArchivos = filesManager.findPDFsFilesFromMonth();
		
		for(ProcesosCatalogoDoc pa : listaArchivos){
			System.out.println("Archivo: "+pa.getIdArchivo());
		}
		
		System.out.println("LISTO");
		
	}

	//@Test
	public void testMethods(){
		filesManager.getDocumentosPorCategoria(new Long(1));
	}
	
}
