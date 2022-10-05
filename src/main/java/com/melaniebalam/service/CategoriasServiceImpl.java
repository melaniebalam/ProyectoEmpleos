package com.melaniebalam.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.melaniebalam.model.Categoria;

@Service
public class CategoriasServiceImpl implements ICategoriasService {

	private List<Categoria> lista = null;

	public CategoriasServiceImpl() {
		lista = new LinkedList<Categoria>();
			// Creamos la primera Categoria
			Categoria categoria1 = new Categoria();
			categoria1.setId(1);
			categoria1.setNombre("Educacion");
			categoria1.setDescripcion("Trabajos relacionados al area de la educacion");
			
			// Creamos la segunda Categoria
			Categoria categoria2 = new Categoria();
			categoria2.setId(2);
			categoria2.setNombre("Contabilidad");
			categoria2.setDescripcion("Trabajos relacionados con contabilidad");
			
			// Creamos la tercera Categoria
			Categoria categoria3 = new Categoria();
			categoria3.setId(3);
			categoria3.setNombre("Recursos Humanos");
			categoria3.setDescripcion("Trabajos relacionados con RH");
			
			// Creamos la cuarta Categoria
			Categoria categoria4 = new Categoria();
			categoria4.setId(4);
			categoria4.setNombre("Arquitectura");
			categoria4.setDescripcion("Area relacionada a la arquitectura");
						
			// Creamos la quinta Categoria
			Categoria categoria5 = new Categoria();
			categoria5.setId(5);
			categoria5.setNombre("Contador Publico");
			categoria5.setDescripcion("Trabajos especificos de contador");
			
			// Creamos la quinta Categoria
			Categoria categoria6 = new Categoria();
			categoria6.setId(6);
			categoria6.setNombre("Desarrollo de Software");
			categoria6.setDescripcion("Trabajos para programadores");
			
			// Creamos la opcion de OTRO
			Categoria categoria7 = new Categoria();
			categoria7.setId(7);
			categoria7.setNombre("Otro");
			categoria7.setDescripcion("Otra categoria");
									
			lista.add(categoria1);			
			lista.add(categoria2);
			lista.add(categoria3);
			lista.add(categoria4);
			lista.add(categoria5);
			lista.add(categoria6);
			lista.add(categoria7);
			
			
	}
	@Override
	public List<Categoria> buscarTodas() {
		return lista;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		
		for(Categoria c : lista) {
			if(c.getId()==idCategoria) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void guardar(Categoria categoria) {
		lista.add(categoria);
		
	}
}
