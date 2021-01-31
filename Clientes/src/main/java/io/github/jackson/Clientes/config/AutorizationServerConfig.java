package io.github.jackson.Clientes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**/
@Configuration
@EnableAuthorizationServer
public class AutorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/*declarar o authenticationmanager*/
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/*utiliza a senha que colocamos no application.properties*/
	@Value("${security.jwt.signing-key}")
	private String signingKey;
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(signingKey);
		return tokenConverter;
	}
	
	public void configure (AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.authenticationManager(authenticationManager);
	}
	
	
	/*permissão para outras aplicações conectar atraves do usuario e senha
	 * leitura e escrita, token com acesso por 30 minutos- 1800*/
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients
		.inMemory()
		.withClient("my-angular-app")
		.secret("@123")
		.scopes("read", "write")
		.authorizedGrantTypes("password")
		.accessTokenValiditySeconds(1800);
	}
	
}
