package com.melaniebalam.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.melaniebalam.model.Vacante;
import com.melaniebalam.repository.VacantesRepository;
import com.melaniebalam.service.IVacantesService;

// esta es la clase que implementa la base de datos 
@Service
@Primary
public class VacantesServiceJpa implements IVacantesService {

	@Autowired
	private VacantesRepository vacantesRepo;
	@Override
	public List<Vacante> buscarTodas() {
		return vacantesRepo.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> optional = vacantesRepo.findById(idVacante);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		vacantesRepo.save(vacante);

	}

	@Override
	public List<Vacante> buscarDestacadas() {
		return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
	}

	@Override
	public void eliminar(Integer idVacante) {
		vacantesRepo.deleteById(idVacante);
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		return vacantesRepo.findAll(example);
	}

}
