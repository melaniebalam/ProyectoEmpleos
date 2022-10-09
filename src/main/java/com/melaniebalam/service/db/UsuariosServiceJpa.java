package com.melaniebalam.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.melaniebalam.model.Usuario;
import com.melaniebalam.repository.UsuariosRepository;
import com.melaniebalam.service.IUsuariosService;

@Service
@Primary 
public class UsuariosServiceJpa implements IUsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepo;
	
	@Override
	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);

	}

	@Override
	public void eliminar(Integer idUsuario) {
		usuariosRepo.deleteById(idUsuario);

	}

	@Override
	public List<Usuario> buscarTodos() {
		return usuariosRepo.findAll();
	}

}
