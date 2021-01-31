package io.github.jackson.Clientes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.jackson.Clientes.service.UsuarioService;


/*classe para segurança de token precisa configurar no application.properties o scope= password igual esta la
 * security.jwt.signing-key também é um token pré definido por nos para criptografia para não ficar modificando sozinho*/
@EnableWebSecurity
public class SecurityConfig extends	WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
		.userDetailsService(usuarioService)
		.passwordEncoder(passwordEncoder());
	}
	
	//gerenciamento de usuario via memoria
	@Bean
	public AuthenticationManager authenticationManager() throws Exception{
		return	super.authenticationManager();
	}
	
	//csrf disable é recurso serve para resgardar a aplicação web mas nao estamos usando nesta aplicação por ser 
	//separada, habilita o cors e metodo and que volta para o http, sessionManagement forma de gerenciamento de sessão
	//ela não ira guardar sessão ira controlar atraves do token
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.
			csrf().disable()
			.cors()
			.and()
			
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
