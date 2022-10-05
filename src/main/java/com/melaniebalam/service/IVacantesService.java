package com.melaniebalam.service;

import java.util.List;
import com.melaniebalam.model.Vacante;

/*Es nuestra interfaz*/
public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	// metodo para guardar el objeto del formulario
	void guardar(Vacante vacante); 
}
