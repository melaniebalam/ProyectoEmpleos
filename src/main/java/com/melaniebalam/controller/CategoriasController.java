package com.melaniebalam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.melaniebalam.model.Categoria;
import com.melaniebalam.service.ICategoriasService;

@Controller
@RequestMapping(value="/categorias") /*Ahora en la URL debemos primero ingresar a "categorias/de alli la clase en la que queramos ingresar */
public class CategoriasController {
	
	@Autowired
	//@Qualifier("categoriasServiceJpa")
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
	// METODO PARA ELIMINAR UNA CATEGORIA
	@RequestMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {
		System.out.println("Borrando categoria con id: " + idCategoria);
		serviceCategorias.eliminar(idCategoria);
		attributes.addFlashAttribute("msg","La categoria fue eliminada");
		//attributes.addFlashAttribute("msg", "La categoria fue eliminada!");
		return "redirect:/categorias/index";
	}
	@GetMapping("/edit/{id}")
	private String editar(@PathVariable("id") int idCategoria, Model model) {
		Categoria categoria = serviceCategorias.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/formCategoria";
		
	}
	@ModelAttribute
	public void setGenericos(Model model) { 
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
	}
}
