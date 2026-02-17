package com.example.demo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Review;
import com.example.demo.service.ReviewService;

@RestController
@RequestMapping("/api/Reviews")
public class InformeRestController {

	@Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> listarInformes() {
        return ResponseEntity.ok(reviewService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getInforme(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reviewService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Review> crearInforme(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.guardar(review), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarInforme(@PathVariable Long id) {
    	reviewService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
