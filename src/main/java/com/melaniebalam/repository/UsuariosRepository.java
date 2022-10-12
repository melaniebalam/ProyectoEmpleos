package com.melaniebalam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melaniebalam.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByUsername(String username); // va buscar el nombre del usuario
}
