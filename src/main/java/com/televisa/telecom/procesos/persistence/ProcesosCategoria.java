package com.televisa.telecom.procesos.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the PROCESOS_CATEGORIAS database table.
 * 
 */
@Entity
@Table(name="PROCESOS_CATEGORIAS")
@NamedQuery(name="ProcesosCategoria.findAll", query="SELECT p FROM ProcesosCategoria p")
@SequenceGenerator(name="SEQ_GEN", sequenceName="PROCESOS_DOCS", allocationSize =1)
//@TableGenerator(name="tab", initialValue=0)
public class ProcesosCategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProcesosCategoria() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	@Column(name="ID_CATEGORIA", unique=true, nullable=false)
	private long idCategoria;
	
	@Column(name="NOMBRE_CATEGORIA", nullable=false)
	private String nombreCategoria;
	
	@Column(name="PADRE", nullable=false)
	private long padre;

	@Transient
	private List<ProcesosCategoria> subcategorias;
	
	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public long getPadre() {
		return padre;
	}

	public void setPadre(long padre) {
		this.padre = padre;
	}

	public List<ProcesosCategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<ProcesosCategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
}