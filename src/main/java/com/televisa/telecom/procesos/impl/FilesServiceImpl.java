package com.televisa.telecom.procesos.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.televisa.telecom.procesos.FilesService;
@Service
public class FilesServiceImpl implements FilesService {
	
	
	public String toString(){
		return "at FileService class";
	}
	
	@Override
	public String seeCategories(){
		System.out.println("loggin repo");
		/*for(CvCategory cat : repo.findAll()){
			System.out.println(cat.getCatName());
		}*/
		return "Done";
	}
}
