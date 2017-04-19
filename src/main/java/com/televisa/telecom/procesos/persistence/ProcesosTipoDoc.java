package com.televisa.telecom.procesos.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PROCESOS_TIPO_DOC database table.
 * 
 */
@Entity
@Table(name="PROCESOS_TIPO_DOC")
@NamedQuery(name="ProcesosTipoDoc.findAll", query="SELECT p FROM ProcesosTipoDoc p")
@SequenceGenerator(name="SEQ_GEN", sequenceName="PROCESOS_DOCS", allocationSize =1)
public class ProcesosTipoDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProcesosTipoDoc() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	@Column(name="ID_TPO", unique=true, nullable=false)
	private long idTpo;
	
	@Column(name="NOMBRE_PROCESO", nullable=false)
	private String nombreProceso;
	
//	@OneToMany(mappedBy="idTipoDoc")
//	private List<ProcesosCatalogoDoc> procesoDoc;
//	
	public long getIdTpo() {
		return idTpo;
	}

	public void setIdTpo(long idTpo) {
		this.idTpo = idTpo;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "nombreTipoDoc")
	public ProcesosCatalogoDoc getProcesoDoc() {
		return procesoDoc;
	}

	public void setProcesoDoc(ProcesosCatalogoDoc procesoDoc) {
		this.procesoDoc = procesoDoc;
	}*/
}