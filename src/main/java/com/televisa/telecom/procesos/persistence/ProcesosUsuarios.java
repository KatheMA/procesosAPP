package com.televisa.telecom.procesos.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the PROCESOS_ARCHIVOS database table.
 * 
 */
@Entity
@Table(name="PROCESOS_USUARIOS")
@NamedQuery(name="ProcesosUsuarios.findAll", query="SELECT p FROM ProcesosUsuarios p")
public class ProcesosUsuarios implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProcesosUsuarios() {
	}
	
	@Id
	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="PASSWORD")
	private String password;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}