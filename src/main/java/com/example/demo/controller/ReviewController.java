package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Review;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ReviewService;

@Controller 
@RequestMapping("/listaReview") //ruta del controlador y la que usara el listar.
public class ReviewController {

	@Autowired
    private ReviewService reviewService;//creaos he instanciamos la clase clienteService con los CRUD.
	
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaReviews", reviewService.listarTodos());
        return "listaReviews";
    }
    
    
    @GetMapping("/nuevaReview")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("review", new Review());
        return "nuevaReview";
    }
    
    @PostMapping("/guardarReview")
    public String guardar(@ModelAttribute("review") Review review,BindingResult bindingResult) {
        
    	if (bindingResult.hasErrors()) {
            return "nuevaReview";
        }
        
    	reviewService.guardar(review);
        return "redirect:/listaReview";
    }
    
    @GetMapping("/editar/{id}")
    public String editarReview(@PathVariable("id") Long id, Model model) { 
    	Review review = reviewService.buscarPorId(id); 
    	model.addAttribute("review", review); //esto envia el valor de cliente a la vista.
    	return "nuevaReview"; // reutilizamos la misma vista que para crear 
    }
    
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
    	System.out.println("id:"+id);
        reviewService.eliminar(id);
        return "redirect:/listaReview"; // redirige al listado
    }
	
}
