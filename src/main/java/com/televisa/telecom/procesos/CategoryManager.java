package com.televisa.telecom.procesos;

import java.util.List;
import java.util.Map;

import com.televisa.telecom.procesos.persistence.ProcesosCategoria;

public interface CategoryManager {

	List<ProcesosCategoria> getCategoriasCompletas();

	Map<String, String> obtenerNombrePadreHijo(int idCategoria);

}
