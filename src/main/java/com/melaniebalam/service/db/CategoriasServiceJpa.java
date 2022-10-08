package com.melaniebalam.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.melaniebalam.model.Categoria;
import com.melaniebalam.repository.CategoriasRepository;
import com.melaniebalam.service.ICategoriasService;

@Service
@Primary // esta anotacion le esta diciendo a CATEGORIASCONTROLLER que utilize esta implementacion
public class CategoriasServiceJpa implements ICategoriasService {

	@Autowired
	private CategoriasRepository categoriasRepo;
	
	@Override
	public void guardar(Categoria categoria) {
		categoriasRepo.save(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = categoriasRepo.findById(idCategoria);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null; // si el ID no se encuentra va regresra un valor null
	}

}
