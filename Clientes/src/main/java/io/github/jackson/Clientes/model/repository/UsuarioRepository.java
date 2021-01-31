package io.github.jackson.Clientes.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jackson.Clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	/*converter valores bigdecimal*/
	
	Optional<Usuario> findByUsername(String username);
	
	boolean existsByUsername(String username);
}
