package com.melaniebalam.controller;

/*import java.text.ParseException;
import java.text.SimpleDateFormat;*/
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.melaniebalam.model.Perfil;
import com.melaniebalam.model.Usuario;
import com.melaniebalam.model.Vacante;
import com.melaniebalam.service.ICategoriasService;
import com.melaniebalam.service.IUsuariosService;
import com.melaniebalam.service.IVacantesService;



/*En este paquete vamos a estar guardando nuestras clases controlador*/
@Controller
public class HomeController {
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@Autowired
	//@Qualifier("categoriasServiceJpa")
	private IUsuariosService serviceUsuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	@GetMapping("/login" )
	public String mostrarLogin() {
	return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
	SecurityContextLogoutHandler logoutHandler =
	new SecurityContextLogoutHandler();
	logoutHandler.logout(request, null, null);
	return "redirect:/login";
	}
	
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody // sirve para que se renderize el texto y no una vista con el nombre
	public String encriptar(@PathVariable("texto") String texto) {
		return texto + " Encriptado en bcrypt: "+ passwordEncoder.encode(texto);
	}
	


	@GetMapping("/") /* URL: localhost:8080/ */
	public String mostrarHome(Model model){/* Es una variable de tipo model */ 
		//List<Vacante> lista = serviceVacantes.buscarTodas(); // con esta linea se esta recuperando los datos de tipo vacante
		//model.addAttribute("vacantes", lista); // Con esta lista agregamos la lista al modelo
		//model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		return "home";
	}
	// Esto funciona cuando ingresamos usuario y contrase??a correcto
	// El httpsession sirve para que podamos ingresar datos en la sesion del usuario 
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session) {
		String username = auth.getName();
		System.out.println("Nombre del usuario: " + username);
		
		for (GrantedAuthority rol: auth.getAuthorities()) { // es para que muestre que rol tiene
			System.out.println("ROL: "+ rol.getAuthority());
		}
		if (session.getAttribute("usuario") == null) {
			Usuario usuario = serviceUsuarios.buscarPorUsername(username);
			usuario.setPassword(null); // para que no guarde la contrase??a o no lo muestre
			System.out.println("Usuario: " + usuario);
			// para buscar datos en la sesion
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}
	// Ejercicio DE USUARIOS 
	@GetMapping("/signup") 
	public String registrarse(Usuario usuario) {
		return "usuarios/formRegistro";
	}
	//Ejercicio
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		//Ejercicio.
		
		 String pwdPlano = usuario.getPassword();
		 String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		 usuario.setPassword(pwdEncriptado);
		 
		 usuario.setEstatus(1);
		 usuario.setFechaRegistro(new Date());
		 
		 Perfil perfil = new Perfil();
		 perfil.setId(3);
		 usuario.agregar(perfil);
		 //
		serviceUsuarios.guardar(usuario); 
		attributes.addFlashAttribute("msg", "Los datos se guardaron correctamente"); 	
		// Ejercicio realizar
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {
		System.out.println("Buscando por : " + vacante);
		
		// where descripcion like '%?$'
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Vacante> example = Example.of(vacante,matcher);
		List<Vacante> lista = serviceVacantes.buscarByExample(example);
		model.addAttribute("vacantes", lista);
		return "home";
	}
	// FIN DE LOS USUARIOS
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute // se esta agregando a nivel del metodo, aqui podemos agregar todos los atributos que queramos y estaran disponible para cualquier metodo
	private void setGenericos(Model model) {
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.reset();
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", serviceCategorias.buscarTodas()); // es para que en la vista de buscar se muestren kas categorias
		model.addAttribute("search",vacanteSearch);
	}
	
	
}
