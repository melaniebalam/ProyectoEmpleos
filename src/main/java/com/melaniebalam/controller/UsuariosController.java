package com.melaniebalam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.melaniebalam.model.Usuario;
import com.melaniebalam.service.IUsuariosService;


@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired //se inyecta la instancia de nuestra clase de servicios
	private IUsuariosService serviceUsuarios;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		// Ejercicio
		List<Usuario> lista = serviceUsuarios.buscarTodos();
		model.addAttribute("usuarios", lista);
		return "usuarios/listUsuarios";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
		// Ejercicio
		System.out.println("Borrando usuario con id: "+ idUsuario);
		serviceUsuarios.eliminar(idUsuario);
		attributes.addFlashAttribute("msg", "El usuario fue eliminado!");
		return "redirect:/usuarios/index";
		
	}
}
