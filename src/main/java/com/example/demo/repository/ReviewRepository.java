package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	//para sacar la valoracion de las reviews
	@Query("SELECT r.valoracion, COUNT(r) FROM Review r GROUP BY r.valoracion ORDER BY r.valoracion")
	List<Object[]> reviewsPorValoracion();


	
}
