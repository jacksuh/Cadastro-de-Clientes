package io.github.jackson.Clientes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;



@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	/*aqui permitimos os acesso as urls permissão autenticada pra acessar a clientes e servicos-prestados
	 * para qualquer outra requisição denyall*/
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/api/usuarios").permitAll()
		.antMatchers("/api/clientes/**", "/api/servicos-prestados/**").authenticated()
		.anyRequest().denyAll();
	}
	
}
