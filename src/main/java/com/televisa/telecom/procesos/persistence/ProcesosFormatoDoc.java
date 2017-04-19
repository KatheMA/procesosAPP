package com.televisa.telecom.procesos.persistence;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PROCESOS_FORMATO_DOC database table.
 * 
 */
@Entity
@Table(name="PROCESOS_FORMATO_DOC")
@NamedQuery(name="ProcesosFormatoDoc.findAll", query="SELECT p FROM ProcesosFormatoDoc p")
@SequenceGenerator(name="SEQ_GEN", sequenceName="PROCESOS_DOCS", allocationSize =1)
public class ProcesosFormatoDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProcesosFormatoDoc() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	@Column(name="ID_FORMATO", unique=true, nullable=false)
	private long idFormato;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="EXTENSION")
	private String extension;

	@Column(name="MIME_TYPE")
	private String mimeType;
	
	public long getIdFormato() {
		return idFormato;
	}

	public void setIdFormato(long idFormato) {
		this.idFormato = idFormato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
}