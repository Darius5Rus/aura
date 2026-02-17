package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Review;
import com.example.demo.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
    private ReviewRepository reviewRepository; //creamos e instanciamos la clase aqui. 

	//Metodo para listar
    public List<Review> listarTodos() {
        return reviewRepository.findAll();
    }

    //metodo para buscar por ID 
    public Review buscarPorId(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review no encontrada: " + id));
    }

    //metodo para guardar en la base de datos
    public Review guardar(Review review) {
        return reviewRepository.save(review);
    }
    
    //metodo para eliminar
    public void eliminar(Long id) {
    	reviewRepository.deleteById(id);
    }
	
	
}
