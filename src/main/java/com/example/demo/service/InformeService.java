package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ReviewRepository;

@Service
public class InformeService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;

    //metodo para genero
    public List<Object[]> clientesPorGenero() {
        return clienteRepository.clientesPorGenero();
    }
    
    //metodo para edad
    public List<Integer> obtenerEdades() {
        return clienteRepository.obtenerEdades();
    }
    
    //metodo para valoraciones
    public List<Object[]> reviewsPorValoracion() {
        return reviewRepository.reviewsPorValoracion();
    }
    
    //metodo para tolerancia
    public List<Object[]> clientesPorTolerancia() {
        return clienteRepository.clientesPorTolerancia();
    }


}
