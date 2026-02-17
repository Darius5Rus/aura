package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	//query para sacar los generos de cliente. 
	@Query("SELECT c.genero, COUNT(c) FROM Cliente c GROUP BY c.genero")
	List<Object[]> clientesPorGenero();
	
	//query para sacar la edad de los clientes 
	@Query("SELECT c.edad FROM Cliente c")
	List<Integer> obtenerEdades();
	
	//query para sacar si es tolerante o no de cliente. 
	@Query("SELECT c.tolerancia, COUNT(c) FROM Cliente c GROUP BY c.tolerancia")
	List<Object[]> clientesPorTolerancia();

}
