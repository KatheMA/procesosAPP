package com.televisa.telecom.procesos.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the PROCESOS_ARCHIVOS database table.
 * 
 */
@Entity
@Table(name="PROCESOS_ARCHIVOS")
@NamedQuery(name="ProcesosArchivo.findAll", query="SELECT p FROM ProcesosArchivo p")
@SequenceGenerator(name="SEQ_GEN", sequenceName="PROCESOS_DOCS", allocationSize =1)
public class ProcesosArchivo implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProcesosArchivo() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	@Column(name="ID_ARCHIVO", unique=true, nullable=false)
	private long idArchivo;
	
	@Column(name="VERSION_ARCHIVO", nullable=false)
	private String versionArchivo;
	
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name="ID_FORMATO", nullable=false)
	private long idFormato;
	
	@Column(name="BYTES_ARCHIVO", nullable=false)
	private byte[] bytesArchivo;

	public long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(long idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getVersionArchivo() {
		return versionArchivo;
	}

	public void setVersionArchivo(String versionArchivo) {
		this.versionArchivo = versionArchivo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public long getIdFormato() {
		return idFormato;
	}

	public void setIdFormato(long idFormato) {
		this.idFormato = idFormato;
	}

	public byte[] getBytesArchivo() {
		return bytesArchivo;
	}

	public void setBytesArchivo(byte[] bytesArchivo) {
		this.bytesArchivo = bytesArchivo;
	}
	
}