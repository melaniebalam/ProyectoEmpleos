package com.melaniebalam.service;

import java.util.List;

import org.springframework.data.domain.Example;

import com.melaniebalam.model.Vacante;

/*Es nuestra interfaz*/
public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	// metodo para guardar el objeto del formulario
	void guardar(Vacante vacante); 
	List<Vacante> buscarDestacadas();
	void eliminar(Integer idVacante);
	// ejecutara querybyexample, nos permite crear consultas SQL tipo select, las diferentes condificones se forman de forma dinamica
	List<Vacante> buscarByExample(Example<Vacante> example);
}
