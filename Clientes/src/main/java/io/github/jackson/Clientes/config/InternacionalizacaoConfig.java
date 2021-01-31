package io.github.jackson.Clientes.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/*classe para configurar um arquivo onde esta a fonte das mensagens onde ira disponibilizar no sistema
 * ou seja o arquivo messages.properties */
@Configuration
public class InternacionalizacaoConfig {

	/*messageSource setBaseName qual o nome do arquivo para apontar no caso é messages
	 * encoding de mensagem mensagens com acento por exemplo ele precisa esta com o ISO-8859 para o java entender 
	 * mensagens com acentos. (Caracteres especiais
	 * locale - detectar atraves do sistema ou seja Brasil */
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("ISO-8859-1");
		messageSource.setDefaultLocale(Locale.getDefault());
		return messageSource;
	}
	
	/*objeto que faz a integração entre as mansagens e validações do java 
	 * criar uma instancia do objeto e dizer qual mensagem source vai ser carregado.*/
	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
