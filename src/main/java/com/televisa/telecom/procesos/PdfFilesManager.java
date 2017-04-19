package com.televisa.telecom.procesos;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import com.televisa.telecom.procesos.persistence.ProcesosArchivo;
import com.televisa.telecom.procesos.persistence.ProcesosCatalogoDoc;

public interface PdfFilesManager {

	List<ProcesosCatalogoDoc> findPDFsFilesFromMonth();

	//void saveFile(String path, String name, int idCategoria, int idTipoDoc);

	Map<String, Object> downloadFile(long id);

	Map<String, List<ProcesosCatalogoDoc>> getDocumentosPorCategoria(Long idCategoria);
	
	void saveFile(String name, int idCategoria, String contentType,
			byte[] array, String tipoDoc, String version);

	List<ProcesosCatalogoDoc> buscaArchivos(String nombre);

	String deleteFile(Long idArchivo);

}
