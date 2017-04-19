package com.televisa.telecom.procesos.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the PROCESOS_AREA database table.
 * 
 */
@Entity
@Table(name="PROCESOS_AREA")
@NamedQuery(name="ProcesosArea.findAll", query="SELECT p FROM ProcesosArea p")
@SequenceGenerator(name="SEQ_GEN", sequenceName="PROCESOS_DOCS", allocationSize =1)
public class ProcesosArea implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProcesosArea() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	@Column(name="ID_AREA", unique=true, nullable=false)
	private long idArea;
	
	@Column(name="NOMBRE_AREA", nullable=false)
	private String nombreArea;

	/*@OneToMany(mappedBy = "idArea")
	private List<ProcesosCatalogoDoc> docs;*/

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	
}