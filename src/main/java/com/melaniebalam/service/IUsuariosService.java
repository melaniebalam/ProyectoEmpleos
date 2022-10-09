package com.melaniebalam.service;

import java.util.List;

import com.melaniebalam.model.Usuario;

public interface IUsuariosService {

	/* Ejercicio: Implementar metodo para registrar un usuario nuevo
	 * 1. Usar la plantilla del archivo formRegistro.html LISTO
	 * 2. El metodo para mostrar el formulario para registrar y el metodo para guardar el usuario debera estar en el controlador HomeController LISTO
	 * 3. Al guardar el usuario se le asignara el perfiel USUARIO y la fecha de registro ser la fecha actual del sistema
	 * */
	void guardar(Usuario usuario);
	
	// Ejercicio: Metodo que elimina un usuario de la base de datos
	void eliminar(Integer idUsuario);
	
	// Ejercicio: Implementar metodo que recupera todos los usuarios. Usar la vista de listUsuarios.html
	List<Usuario> buscarTodos();
}

// Agregar al archivo menu.html el link para acceder al listado de  USUARIOS y congigurar el link del boton registrarse
