package com.televisa.telecom.procesos.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the PROCESOS_CATALOGO_DOCS database table.
 * 
 */
@Entity
@Table(name="PROCESOS_CATALOGO_DOCS")
@NamedQuery(name="ProcesosCatalogoDoc.findAll", query="SELECT p FROM ProcesosCatalogoDoc p")
@SequenceGenerator(name="SEQ_GEN", sequenceName="PROCESOS_DOCS", allocationSize =1)
public class ProcesosCatalogoDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProcesosCatalogoDoc() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	@Column(name="ID_DOC", unique=true, nullable=false)
	private long idDoc;
	
	@Column(name="NOMBRE_DOC", nullable=false)
	private String nombreDoc;
	
	@Column(name="ES_ULTIMA_VERSION")
	private boolean esUltimaVersion;
	
	@Column( name = "FECHA_CREACION")
	private Date fechaCreacion;
	/*//foreign key
	@JoinColumn(name="ID_AREA" , nullable = false)
	@ManyToOne(targetEntity = ProcesosArea.class)
	private long idArea;*/

	//foreign key
	/*@Column(name="ID_TIPO_DOC" , nullable = false)
	private long idTipoDoc;*/
	
	
	@Column(name="ID_CATEGORIA" , nullable = false)
	private long idCategoria;
	
	//foreign key
	@Column(name="ID_ARCHIVO" , nullable = false)
	private long idArchivo;
	
	@Column(name="ESTADO" , nullable = false)
	private long estado;

	@Column(name="NOMBRE_DOC_INTERNO")
	private String nombreDocInterno;

	@Column(name="EXTENSION_DOC")
	private String extensionDoc;
	
//	@ManyToOne(fetch=FetchType.EAGER)
	@Column(name="ID_TIPO_DOC")
	private long idTipoDoc;
	
	public long getIdDoc() {
		return idDoc;
	}

	public void setIdDoc(long idDoc) {
		this.idDoc = idDoc;
	}

	public String getNombreDoc() {
		return nombreDoc;
	}

	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}

	public boolean isEsUltimaVersion() {
		return esUltimaVersion;
	}

	public void setEsUltimaVersion(boolean esUltimaVersion) {
		this.esUltimaVersion = esUltimaVersion;
	}

	public long getIdTipoDoc() {
		return idTipoDoc;
	}

	public void setIdTipoDoc(long idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(long idArchivo) {
		this.idArchivo = idArchivo;
	}

	public long getEstado() {
		return estado;
	}

	public void setEstado(long estado) {
		this.estado = estado;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombreDocInterno() {
		return nombreDocInterno;
	}

	public void setNombreDocInterno(String nombreDocInterno) {
		this.nombreDocInterno = nombreDocInterno;
	}

	public String getExtensionDoc() {
		return extensionDoc;
	}

	public void setExtensionDoc(String extensionDoc) {
		this.extensionDoc = extensionDoc;
	}
	
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID", nullable = false)
	public ProcesosTipoDoc getNombreTipoDoc() {
		return nombreTipoDoc;
	}

	public void setNombreTipoDoc(ProcesosTipoDoc nombreTipoDoc) {
		this.nombreTipoDoc = nombreTipoDoc;
	}*/
}