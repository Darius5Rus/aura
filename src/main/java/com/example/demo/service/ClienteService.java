package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
    private ClienteRepository clienteRepository; //creamos e instanciamos la clase del ClienteRepository aqui. 

	//Metodo para listar
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    //metodo para buscar por ID 
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));
    }

    //metodo para guardar en la base de datos
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    //metodo para eliminar
    public void eliminar(Long id) {
    	clienteRepository.deleteById(id);
    }
	
}
