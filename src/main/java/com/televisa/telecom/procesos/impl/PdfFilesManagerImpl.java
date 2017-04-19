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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.televisa.telecom.procesos.PdfFilesManager;
import com.televisa.telecom.procesos.persistence.ProcesosArchivo;
import com.televisa.telecom.procesos.persistence.ProcesosCatalogoDoc;
import com.televisa.telecom.procesos.persistence.ProcesosFormatoDoc;
import com.televisa.telecom.procesos.persistence.ProcesosTipoDoc;
import com.televisa.telecom.procesos.repository.ProcesosArchivoRepository;
import com.televisa.telecom.procesos.repository.ProcesosCatalogoDocRepository;
import com.televisa.telecom.procesos.repository.ProcesosFormatoDocRepository;
import com.televisa.telecom.procesos.repository.ProcesosTipoDocRepository;

@Service
public class PdfFilesManagerImpl implements PdfFilesManager {

	@Autowired
	private ProcesosArchivoRepository repoArchivos;
	
	@Autowired
	private ProcesosCatalogoDocRepository repoCatalogoDocs;
	
	@Autowired
	private ProcesosFormatoDocRepository formatoRepo;
	
	@Autowired
	private ProcesosTipoDocRepository procesosRepo;
	
	private byte[] convertDocToByteArray(String sourcePath) {
		
		Path path = Paths.get(sourcePath);
		
		byte[] byteArray = null;
		
		try{
			byteArray = Files.readAllBytes(path);
		}catch (IOException e) {
		      System.out.println(e);
	    }
		
		return byteArray;
	}
	
	@Override
	public Map<String, Object> downloadFile(long id) {  
		
		Map<String, Object> response = new HashMap<>();
	    try {  
	    	ProcesosArchivo archivo = repoArchivos.findOne(new Long(id));
	    	
	    	byte[] bArray = archivo!=null?archivo.getBytesArchivo():null;
	        // Create file 
	        ByteArrayOutputStream outS = null;  
	        
	        if(bArray!=null){
	        	outS = new ByteArrayOutputStream();  
	        	outS.write(bArray);  
	        	outS.close();  
	        }
	        ProcesosFormatoDoc formato = formatoRepo.findOne(archivo.getIdFormato());
	        
	        response.put("objectoArchivo", outS!=null ? outS : null);
	        response.put("tipoArchivo",formato!=null?formato.getMimeType():"application/pdf");
	        
	        ProcesosCatalogoDoc doc = repoCatalogoDocs.findFilesPerIdArchivo(id);
	        
	        response.put("nombre", doc!=null ? doc.getNombreDoc() : "Documento "+formato!=null ? formato.getExtension():"");
	        
	        return response;
	    } catch (Exception e) {  
	        System.err.println("Error: " + e.getMessage());  
	    }  
	    
	    return null;
	}
	
	/*@Override
	public void downloadFile(long id) {  
	    try {  
	    	ProcesosArchivo archivo = repoArchivos.findOne(new Long(id));
	    	
	    	byte[] bArray = archivo.getBytesArchivo();
	        // Create file 
	        OutputStream outS = new FileOutputStream("C:\\portweb01\\documentacion\\archivos\\portabilidad\\archivo11.pdf");  
	        outS.write(bArray);  
	        outS.close();  
	    } catch (Exception e) {  
	        System.err.println("Error: " + e.getMessage());  
	    }  
	}*/
	
	/*public void uploadFile(){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = decoder.decodeBuffer(encodedBytes);
	}*/
	
	@Override
	public List<ProcesosCatalogoDoc> findPDFsFilesFromMonth(){
		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.set(Calendar.DAY_OF_MONTH, 1);
		
		Calendar fechaFin = Calendar.getInstance();
		fechaFin.set(Calendar.DAY_OF_MONTH, fechaFin.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		System.out.println("FECHAS: "+fechaInicio.getTime()+" | "+fechaFin.getTime());
		
		List<ProcesosCatalogoDoc> listaArchivos = repoCatalogoDocs.findBetweenDates(fechaInicio.getTime(), fechaFin.getTime());
		
		return listaArchivos;
		
	}
	
	@Override
	public void saveFile(String name, int idCategoria, String contentType, byte[] array, String tipoDoc, String version){
		if(name != null && !name.isEmpty()){
			
			/*String sourcePath = path+name;
			sourcePath = "C:\\portweb01\\documentacion\\archivos\\portabilidad\\listado-canales.pdf";
			byte[] array = convertDocToByteArray(sourcePath);*/
			
			Date fechaCreacion = Calendar.getInstance().getTime();
			
//			ProcesosTipoDoc ptipoDoc = new ProcesosTipoDoc();
//			ptipoDoc.setIdTpo(procesosRepo.findTipoDocByName(tipoDoc).getIdTpo());
			
			ProcesosFormatoDoc formato = formatoRepo.findTipoFormatoByMimeType(contentType);
			
			ProcesosArchivo archivo = new ProcesosArchivo();
			//archivo.setIdArchivo(new Long(44));
			archivo.setBytesArchivo(array);
			archivo.setVersionArchivo(version);
			archivo.setFechaCreacion(fechaCreacion);
			archivo.setIdFormato(formato.getIdFormato());
			
			ProcesosArchivo archivoNuevo = repoArchivos.save(archivo);
			
			if(archivoNuevo == null){
				System.out.println("ERROR: Intentar cargar de nuevo: "+name);
				return;
			}
			
			ProcesosCatalogoDoc nuevoDoc = new ProcesosCatalogoDoc();
			nuevoDoc.setNombreDoc(name);
			nuevoDoc.setEsUltimaVersion(true);
			nuevoDoc.setEstado(new Long(1)); //al ser un archivo recientemente agregado tiene estado de activo
			nuevoDoc.setIdCategoria(idCategoria);
			nuevoDoc.setIdArchivo(archivoNuevo.getIdArchivo());
			nuevoDoc.setIdTipoDoc(procesosRepo.findTipoDocByName(tipoDoc).getIdTpo());
			nuevoDoc.setFechaCreacion(fechaCreacion);
			nuevoDoc.setNombreDocInterno(name.toUpperCase());		
			
			String extensionDoc = name.substring(name.lastIndexOf(".")+1);
			
			nuevoDoc.setExtensionDoc(extensionDoc);
			
			repoCatalogoDocs.save(nuevoDoc);
			
			System.out.println("Nuevo archivo guardado: "+name);
			
		}
	}
	
	@Override
	public Map<String, List<ProcesosCatalogoDoc>> getDocumentosPorCategoria(Long idCategoria){
		//List<ProcesosCatalogoDoc> documentos = repoCatalogoDocs.findFilesPerCategory(idCategoria);
		
		Iterable<ProcesosTipoDoc> tiposDocs = procesosRepo.findAll();
		Iterator<ProcesosTipoDoc> iter = tiposDocs.iterator();
		
//		Map<String, List<ProcesosCatalogoDoc>> docs = new HashMap<>();
//		
		Map<String, List<ProcesosCatalogoDoc>> docsFinal = new HashMap<>();
//		
//		//SUSTITUIR POR UN DISTICT QUERY A LA BD?????
//		for(ProcesosCatalogoDoc doc : documentos){
//		System.out.println("Docs tipo: "+doc.getIdTipoDoc().getNombreProceso());
//			if(!docs.containsKey(doc.getIdTipoDoc())){
//				List<ProcesosCatalogoDoc> listaTemp = new ArrayList<>();
//				docs.put(doc.getIdTipoDoc().getNombreProceso(), listaTemp);
//			}
//		}
//		
//		
//		
//		for (Map.Entry<String,  List<ProcesosCatalogoDoc>> entry : docs.entrySet()) {
//			System.out.println("tipoDoc: "+entry.getKey());
//			
//			List<ProcesosCatalogoDoc> docsTemp = new ArrayList<>();
//			for(ProcesosCatalogoDoc doc : documentos){
//				if(doc.getIdTipoDoc().getNombreProceso().equalsIgnoreCase(entry.getKey())){
//					docsTemp.add(doc);
//				}
//			}
//			
//			docsFinal.put(entry.getKey(), docsTemp);
//			
//		}
		
		System.out.println("AAA");
		while(iter.hasNext()){
			ProcesosTipoDoc ptd = iter.next();
			System.out.println("iter: "+ptd.getIdTpo());
			List<ProcesosCatalogoDoc> docsTemp = repoCatalogoDocs.findFilesPerCategoryAndTipoDoc(idCategoria, ptd.getIdTpo());
			docsFinal.put(ptd.getNombreProceso(), docsTemp);
		}
		System.out.println("BBB");
		Map<String, List<ProcesosCatalogoDoc>> reversedMap = new TreeMap(Collections.reverseOrder());
		reversedMap.putAll(docsFinal);
		System.out.println("CCC");
		return reversedMap;
		
	}
	
	@Override
	public List<ProcesosCatalogoDoc> buscaArchivos(String nombre){
		List<ProcesosCatalogoDoc> archivos = repoCatalogoDocs.findByFilialAndBranchLike(nombre.toUpperCase());
		
		return archivos;
	}
	
	@Override
	public String deleteFile(Long idArchivo){
		ProcesosCatalogoDoc doc = repoCatalogoDocs.findFilesPerIdArchivo(idArchivo);
		String resp = null;
		if(doc != null){
			repoCatalogoDocs.delete(doc);
			
			ProcesosArchivo archivo = repoArchivos.findArchivo(idArchivo);
			
			if(archivo!=null){
				repoArchivos.delete(archivo);
				resp = doc.getNombreDoc();
			}
		}
		
		return resp;
	}
}
