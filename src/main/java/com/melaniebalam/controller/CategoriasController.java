package com.melaniebalam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.melaniebalam.model.Categoria;
import com.melaniebalam.service.ICategoriasService;

@Controller
@RequestMapping(value="/categorias") /*Ahora en la URL debemos primero ingresar a "categorias/de alli la clase en la que queramos ingresar */
public class CategoriasController {
	
	@Autowired
	private ICategoriasService serviceCategorias;

	// Ejercicio del video 12
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = serviceCategorias.buscarTodas();
        model.addAttribute("categorias", lista);
		return "categorias/listCategorias"; 
	}

	// @GetMapping("/create") Rresenta la URL, la c ual es "localhost:8080/create*/
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
	}

	// @PostMapping("/save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			return "categorias/formCategoria";
		}
		serviceCategorias.guardar(categoria); 
		attributes.addFlashAttribute("msg", "Los datos de la categoria estan guardados"); 															
	    return "redirect:/categorias/index";
	
	}
}
