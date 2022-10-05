package com.melaniebalam.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.melaniebalam.model.Vacante;
import com.melaniebalam.service.ICategoriasService;
import com.melaniebalam.service.IVacantesService;
import com.melaniebalam.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController { 
	
	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	@Autowired //se inyecta la instancia de nuestra clase de servicios
	private IVacantesService serviceVacantes;

	@Autowired 
	private ICategoriasService serviceCategorias; // de esta forma se declara una variable al nivel de la clase y la podemos usar en cualquier metodo
	
	// Ejercicios
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		// 1.Obtener todas las vacantes (recuperarlas con la clase de servicio)

		List<Vacante> lista = serviceVacantes.buscarTodas();

		// 2.Agregar al modelo el listado de vacantes
		model.addAttribute("vacantes", lista);
		// 3.Rederizar las vacantes en la vista (integrar el archivo
		// template-empleos/listVacantes.html)

		// 4.Agregar al menu una opcion llamada Vacantes configurando la URL
		// "vacantes/index"
		return "vacantes/listVacantes";
	}

	// Este metodo es para que se puedan mostrar todas las categorias como opciones en el formulario de vacantes
	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) { // se pasa como parametro un objeto de tipo vacante, asi se vincula la clase
											// modelo, con el formulario que se esta renderizando
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		return "vacantes/formVacante";
	}

	// Carpeta 6 video 1
	@PostMapping("/save") // Es el mismo metodo pero mas corto, esta relacionado con el modelo VACANTE
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage()); // Es para que desplegue en la
																						// consola los errores que pasa
																						// en el formulario
			}
			return "vacantes/formVacante"; 
		}
		 
		// Codigo para poder hacer que el formulario guarde las imagenes
		if (!multiPart.isEmpty()) {
			//String ruta = "c:/empleos/img-vacantes/"; 
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}
		serviceVacantes.guardar(vacante);
		// Procesar objeto de modelo
		attributes.addFlashAttribute("msg", "Registro Guardado"); // Son una forma para almacenar atributos y ser usados
																	// en diferente peticion, SON TEMPORALES, son
																	// eliminados despues del redirect
		System.out.println("Vacante: " + vacante);
		// Peticion tipo GET
		return "redirect:/vacantes/index"; // Cuando aplastemos GUARDAR nos mandara a la vista donde esta la lista de
											// los vacantes
	}

	
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con id: " + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}

	// el "verDetalle" es el que recibe el ID que es pasado desde la tabla HTMl
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		System.out.println("Vacante: " + "vacante");
		model.addAttribute("vacante", vacante);

		// Buscar los detalles de las vacantes en ID BD

		return "detalle"; // antes se tenia que ingresar a la carpeta de vacantes, pero se elimino su
							// archivo
	}

	@InitBinder // Sirve que se puedan convertir fechas, siempre debe de estar este metodo
				// cuando usemos fechas en el formulario
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
