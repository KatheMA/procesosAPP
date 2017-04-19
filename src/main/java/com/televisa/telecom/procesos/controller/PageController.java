package com.televisa.telecom.procesos.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.televisa.telecom.procesos.AuthenticationManager;
import com.televisa.telecom.procesos.CategoryManager;
import com.televisa.telecom.procesos.FilesService;
import com.televisa.telecom.procesos.PdfFilesManager;
import com.televisa.telecom.procesos.persistence.ProcesosCatalogoDoc;
import com.televisa.telecom.procesos.persistence.ProcesosCategoria;

@Controller
public class PageController {
	
	@Autowired
	private FilesService files;
	
	@Autowired
	PdfFilesManager filesManager;
	
	@Autowired
	CategoryManager categorias;
	
	@Autowired
	AuthenticationManager auth;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request){
		System.out.println("INICIO BUSQUEDA");
		
		List<ProcesosCatalogoDoc> listaArchivos = filesManager.findPDFsFilesFromMonth();
		
		model.addAttribute("mesActual", Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es", "ES")));
		
		System.out.println("Cantidad de archivos del mes: "+listaArchivos.size());
		
		Map<Integer, List<ProcesosCatalogoDoc>> mapa = new HashMap<>();
		
		if(request.getSession().getAttribute("usuarioLogged") != null){
			model.addAttribute("usuarioLogged", true);
		}
		
		List<ProcesosCatalogoDoc> lista = new ArrayList<>();
		int i = 1;
		/*for(ProcesosCatalogoDoc doc : listaArchivos){
			lista.add(doc);
			if(i==6){
				mapa.put(new Integer(i),lista);
				model.addAttribute("archivos1", lista);
				lista = new ArrayList<>();
			}
			else if(i==12){
				mapa.put(new Integer(i),lista);
				model.addAttribute("archivos2", lista);
				lista = new ArrayList<>();
			}
			i++;
		}*/
		
		for(ProcesosCatalogoDoc doc : listaArchivos){
			lista.add(doc);
			if(i%6==0){
				mapa.put(new Integer(i),lista);
				//model.addAttribute("archivos1", lista);
				lista = new ArrayList<>();
			}
			i++;
		}
		mapa.put(new Integer(i), lista);
		
		model.addAttribute("archivosListas", mapa);
		
		
		List<ProcesosCategoria> listaCategorias = categorias.getCategoriasCompletas();
		
		model.addAttribute("listaCategorias", listaCategorias);
		
		return "indexNVO";
	}
	
	@RequestMapping(value = "/downloadFile")
	public ResponseEntity<byte[]> descargaArchivo(@RequestParam(value = "idArchivo", required = true) String idArchivo) {	
		
		Map <String, Object> map = filesManager.downloadFile(new Long(idArchivo));
		
		if(map == null){
			return null;
		}
		
		try{
			ByteArrayOutputStream baos = map.get("objectoArchivo")!=null ? (ByteArrayOutputStream)map.get("objectoArchivo") : null;
			byte[] binaryDataValue = baos.toByteArray();
			HttpHeaders headers = new HttpHeaders();
			
			String mediaType= (String)map.get("tipoArchivo");
			
			headers.setContentType(MediaType.parseMediaType(mediaType));
			String filename = (String)map.get("nombre");

			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			
			ResponseEntity<byte[]> rpdf = new ResponseEntity<byte[]>(binaryDataValue, headers, HttpStatus.OK);
			return rpdf;
		}
		catch(Exception e){
			System.out.println("error al descargar la tarjeta");
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/findFilesPerCategory")
	public String filesPanel(@RequestParam(value = "idCategoria",required = true)int idCategoria, Model model, HttpServletRequest request){
		
		
		Map<String, List<ProcesosCatalogoDoc>> listaDocsCategoria = filesManager.getDocumentosPorCategoria(new 	Long(idCategoria));
		
		model.addAttribute("listaProcesos",listaDocsCategoria);
		
		Map<String, String> mapaCats = categorias.obtenerNombrePadreHijo(idCategoria);
		
		model.addAttribute("padre",mapaCats.get("padre"));
		model.addAttribute("hijo",mapaCats.get("hijo"));
		
		model.addAttribute("categoria",idCategoria);
		//revisar si hay archivos para cumplir con las 5 tabs (formatos, procesos...) para armar lista y desplegar
		
		model.addAttribute("usuarioLogged",request.getSession().getAttribute("usuarioLogged"));
		
		
		return "views/panel_docs"; //regresa vista de archivos por categoria
	}
	
	@RequestMapping("/searchFiles")
	public String filesSearch(@RequestParam(value = "word",required = true)String fileSearched, Model model){
		
		
		List<ProcesosCatalogoDoc> listaDocsCategoria = filesManager.buscaArchivos(fileSearched);
		
		model.addAttribute("listaDocs",listaDocsCategoria);
		
		model.addAttribute("palabraClave",fileSearched);
		//revisar si hay archivos para cumplir con las 5 tabs (formatos, procesos...) para armar lista y desplegar
		
		
		
		return "views/panel_busqueda"; //regresa vista de archivos por categoria
	}
	
	@RequestMapping(value= {"/uploadFile","/uploadFile/"},  method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {                 
		 System.out.println("REC, FILES");
	     //0. notice, we have used MultipartHttpServletRequest
		 
		 System.out.println("A: "+request.getParameter("idCategoria"));
		 
		 int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		 String tipoDoc = request.getParameter("tipoDoc");
		 
		 
		 MultipartFile file = request.getFile("file");
		 
		 System.out.println("ZZZ: "+file.getOriginalFilename()+" |YYY: "+file.getContentType()+" |XXX: "+file.getSize());
		 
		 //QUITAR PRUEBA
		 //meter logica para ver si hay ultima version o no
		 String version = "0";
		 
		 filesManager.saveFile(file.getOriginalFilename(), idCategoria, file.getContentType(), file.getBytes(), tipoDoc, version);
		 
	     return "OK!";
	 
	  }
	
	@RequestMapping(value ="/deleteFile")
	public @ResponseBody String delete(@RequestParam(value="idArchivo")String idArchivo){
		String resp = null;
		
		resp = filesManager.deleteFile(new Long(idArchivo));
		
		return resp;
	}
	
	@RequestMapping(value="/loggin", method = RequestMethod.POST)
	public String logUser (Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, @RequestParam("usuario")String username, @RequestParam("password")String password){
		boolean logUser = auth.loggin(username, password);
		if(logUser){
			request.getSession().setAttribute("usuarioLogged", username);
		}else{
			redirectAttributes.addFlashAttribute("errorLog", "Usuario o contraseña incorrectos");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/loggout")
	public String loggoutUser(HttpServletRequest request){
		request.getSession().removeAttribute("usuarioLogged");
		return "redirect:/";
	}
}
