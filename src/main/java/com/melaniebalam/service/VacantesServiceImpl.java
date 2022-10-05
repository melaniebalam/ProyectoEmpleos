package com.melaniebalam.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.melaniebalam.model.Vacante;

@Service
public class VacantesServiceImpl implements IVacantesService{ /*esta clase esta usando la interfaz IVacantesService*/

	private List<Vacante> lista = null; /*Esta declarando la lista como un atributo a nivel de la clase*/
			
	public VacantesServiceImpl() { /*Este es nuestro contructor*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Vacante>();
		try {
			//Creamos la oferta de trabajo 1 
			Vacante vacante1 = new Vacante(); /*objeto*/
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil"); // Titulo de la vacante
			vacante1.setDescripcion("Solicitamos Ing. Civil para diseñar un puente peatonal.");
			vacante1.setFecha(sdf.parse("08-02-2019"));
			vacante1.setSalario(14000.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
			vacante1.setEstatus("Aprobada");
			
			//Creamos la oferta de trabajo 2
			Vacante vacante2 = new Vacante(); /*objeto*/
			vacante2.setId(2);
			vacante2.setNombre("Contador Publico"); // Titulo de la vacante
			vacante2.setDescripcion("Empresa importante solicita contador con 5 años de experiencia titulado.");
			vacante2.setFecha(sdf.parse("09-02-2019"));
			vacante2.setSalario(12500.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
			vacante2.setEstatus("Creada");
			
			//Creamos la oferta de trabajo 3
			Vacante vacante3 = new Vacante(); /*objeto*/
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Electrico"); // Titulo de la vacante
			vacante3.setDescripcion("Empresa internacional solicita ingeniero mecanico para manteniemiento de la instalacion electrica");
			vacante3.setFecha(sdf.parse("10-02-2019"));
			vacante3.setSalario(10500.0);
			vacante3.setDestacado(0);
			vacante3.setEstatus("Eliminada");
			
			//Creamos la oferta de trabajo 4
			Vacante vacante4 = new Vacante(); /*objeto*/
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Grafico"); // Titulo de la vacante
			vacante4.setDescripcion("Solicitamos Diseñador Grafico titulado para diseñar publicidad de la empresa.");
			vacante4.setFecha(sdf.parse("11-02-2019"));
			vacante4.setSalario(7500.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("empresa3.png");
			vacante4.setEstatus("Aprobada");
			
			/**
			 * Agregamos los 4 objetos de tipo Vacante a la lista...
			 */
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
		} catch (ParseException e) {
			System.out.print("Error: " + e.getMessage());
		}
		/*return lista; en un constructor no se regresa ningun objeto*/
	}
	@Override
	public List<Vacante> buscarTodas() { /*Este es nuestro metodo*/
		return lista;
	}
	/*Este metodo sirve para saber si el ID existe o  no*/
	@Override
	public Vacante buscarPorId(Integer idVacante) {
		
		for(Vacante v : lista) {
			if(v.getId()==idVacante) {
				return v;
			}
		}
		return null; /*En dado caso de que el ID no exista nos dira que no fue encontrado*/
	}
	// es el metodo que implementamos en nuestra interfaz IVacantesService
	// por el momento se guardan los datos en una lista, no en una base de datos
	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante); // este objeto va mandar al contralador en nuestra clase de servicio
		
	}

}
