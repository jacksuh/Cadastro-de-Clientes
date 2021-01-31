package io.github.jackson.Clientes;


/*classe que vai inicializar a aplicação */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*essa anotação @ que informa para o springboot que essa classe inicia a aplicação tudo que esta no pacote pra frente
 * sera escaneados automaticamente */
@SpringBootApplication
public class ClientesApplication {

	/*run classe que vai inicializar a aplicação e os argumentos */
	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}

}
