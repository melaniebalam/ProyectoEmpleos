package com.melaniebalam.service;

import java.util.List;
import com.melaniebalam.model.Categoria;

public interface ICategoriasService {
	void guardar(Categoria categoria);
    List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	
}
// Ejercicios video 12
/*
 * 1. Crear la clase CategoriasServiceImpl que implemente esta Interfaz 
 * (Guardar las categorias en una lista (LinkedList))
 
 * 2. Inyectar la clase de servicion en Categorias Controller
 
 * 3. Completar los siguientes metodos en CategoriasController:
 * 		 
 * 		mostrarIndex-> Renderizar el listado de Categorias (listCategorias.html)
 *                     Configurar la URL del boton para crear una categoria.
 *      
 *      guardar-> Guardar el objeto Categoria a traves de la clase de servicio
 *                Validar errores de Data Binding y mostrarlos al usuario en caso de haber
 *                Mostrar al usuario mensaje de confirmacion de registro guardado
  
 * 4. Agregar un nuevo menu llamado Categorias y configurar la URL al listado de Categorias
 */
