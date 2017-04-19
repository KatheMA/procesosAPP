package com.televisa.telecom.procesos.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.televisa.telecom.procesos.CategoryManager;
import com.televisa.telecom.procesos.PdfFilesManager;
import com.televisa.telecom.procesos.persistence.ProcesosArchivo;
import com.televisa.telecom.procesos.persistence.ProcesosCatalogoDoc;
import com.televisa.telecom.procesos.persistence.ProcesosCategoria;
import com.televisa.telecom.procesos.persistence.ProcesosFormatoDoc;
import com.televisa.telecom.procesos.repository.ProcesosArchivoRepository;
import com.televisa.telecom.procesos.repository.ProcesosCatalogoDocRepository;
import com.televisa.telecom.procesos.repository.ProcesosCategoriaRepository;
import com.televisa.telecom.procesos.repository.ProcesosFormatoDocRepository;

@Service
public class CategoryManagerImpl implements CategoryManager {
	
	@Autowired
	private ProcesosArchivoRepository repoArchivos;
	
	@Autowired
	private ProcesosCatalogoDocRepository repoCatalogoDocs;
	
	@Autowired
	private ProcesosCategoriaRepository categoriaRepo;
	
	@Override
	public List<ProcesosCategoria> getCategoriasCompletas(){
		
		List<ProcesosCategoria> categoriasPadre = categoriaRepo.findParents();
		List<ProcesosCategoria> subcategorias = new ArrayList<>();
		for(ProcesosCategoria cats : categoriasPadre){
			subcategorias = categoriaRepo.findSubcategories(cats.getIdCategoria());
			cats.setSubcategorias(subcategorias);
		}
		
		return categoriasPadre;
		
	}
	
	@Override
	public Map<String,String> obtenerNombrePadreHijo(int idCategoria){
		Map<String, String> mapa = new HashMap<>();
		
		ProcesosCategoria cat = categoriaRepo.findOne(new Long(idCategoria));
		if(cat!=null){
			if(cat.getPadre()!=0){
				
				ProcesosCategoria catP = categoriaRepo.findOne(cat.getPadre());
				mapa.put("padre", catP.getNombreCategoria());
				mapa.put("hijo", cat.getNombreCategoria());
				
				
			}else{
				mapa.put("padre", cat.getNombreCategoria());
				mapa.put("hijo", "");
			}
		}
		
		
		return mapa;
		
	}
	
}
