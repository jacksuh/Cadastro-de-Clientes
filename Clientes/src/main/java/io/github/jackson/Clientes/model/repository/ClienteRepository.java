package io.github.jackson.Clientes.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.jackson.Clientes.model.entity.Cliente;


/*classe para conexão com o banco de dados, o repository é do cliente/ integer  ID*/
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
