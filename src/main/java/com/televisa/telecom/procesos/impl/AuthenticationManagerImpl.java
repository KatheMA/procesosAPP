package com.televisa.telecom.procesos.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.televisa.telecom.procesos.AuthenticationManager;
import com.televisa.telecom.procesos.persistence.ProcesosUsuarios;
import com.televisa.telecom.procesos.repository.ProcesosUsuariosRepository;

@Service
public class AuthenticationManagerImpl implements AuthenticationManager {
	
	@Autowired
	private ProcesosUsuariosRepository auth;
	
	@Override
	public boolean loggin(String usuario, String password){
		ProcesosUsuarios loggin = auth.findUser(usuario, password);
		
		if(loggin!=null){
			return true;
		}else{
			return false;
		}
	}
	
}
