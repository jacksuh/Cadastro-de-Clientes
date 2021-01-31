package io.github.jackson.Clientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.jackson.Clientes.exception.UsuarioCadastradoException;
import io.github.jackson.Clientes.model.entity.Usuario;
import io.github.jackson.Clientes.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repositoy;
	
	public Usuario salvar(Usuario usuario) {
		boolean exists = repositoy.existsByUsername(usuario.getUsername());
		if(exists) {
			throw new UsuarioCadastradoException(usuario.getUsername());
		}
		return repositoy.save(usuario);
	}
		
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
		Usuario usuario = repositoy.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
				
		return User
				.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles("USER")
				.build();
	}
}
