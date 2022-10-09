package com.melaniebalam.controller;

/*import java.text.ParseException;
import java.text.SimpleDateFormat;*/
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.melaniebalam.model.Vacante;
import com.melaniebalam.service.IVacantesService;

/*En este paquete vamos a estar guardando nuestras clases controlador*/
@Controller
public class HomeController {
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@GetMapping("/tabla")/* URL: localhost:8080/tabla */
	public String mostrarTabla(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}
	@GetMapping("/acerca")/* URL: localhost:8080/tabla */
	public String mostrarAcerca(Model model) {
		
		return "acerca";
	}
	/*@GetMapping("/error") 
	public String mostrarError(Model model) {
		 
		return "error";
	}*/
	@GetMapping("/detalle")/* URL: localhost:8080/detalle */
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante(); /*objeto*/
		vacante.setNombre("Ingeniero de Comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a internet");
		vacante.setFecha(new Date());
		vacante.setSalario(9800.0);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/listado") /* URL: localhost:8080/listado */
	public String mostrarListado(Model model) { /*Este metodo sirve para que podemos ingresar lista*/
		List<String> lista = new LinkedList<String>(); /*Con esto podemos usar lista, recuerda importar su extension, el metodo list contiene cadenas*/
		lista.add("Ingeniero de Sistemas");
		lista.add("Auxiliar de contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista); /*Una vez creada nuestra lista debemos importar el modelo, le asignamos un atributo "empleos"*/
		
		return "listado";
	}

	@GetMapping("/") /* URL: localhost:8080/ */
	public String mostrarHome(Model model){/* Es una variable de tipo model */ 
		//List<Vacante> lista = serviceVacantes.buscarTodas(); // con esta linea se esta recuperando los datos de tipo vacante
		//model.addAttribute("vacantes", lista); // Con esta lista agregamos la lista al modelo
		//model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		return "home";
	}
	
	@ModelAttribute // se esta agregando a nivel del metodo, aqui podemos agregar todos los atributos que queramos y estaran disponible para cualquier metodo
	private void setGenericos(Model model) {
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
	}
	
	
}
