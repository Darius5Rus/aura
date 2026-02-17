package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.InformeService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/informes")
public class InformeController {

    @Autowired
    private InformeService informeService;

    @GetMapping
    public String verInformes(Model model) {

        //CLIENTES POR GÉNERO
        List<Object[]> datosGenero = informeService.clientesPorGenero();

        List<String> labelsGenero = new ArrayList<>();
        List<Long> valoresGenero = new ArrayList<>();

        for (Object[] fila : datosGenero) {
            labelsGenero.add((String) fila[0]);
            valoresGenero.add((Long) fila[1]);
        }

        model.addAttribute("labelsGenero", labelsGenero);
        model.addAttribute("valoresGenero", valoresGenero);


        //REVIEWS POR VALORACIÓN
        List<Object[]> datosValoracion = informeService.reviewsPorValoracion();

        List<String> labelsEstrellas = new ArrayList<>();
        List<Long> valoresEstrellas = new ArrayList<>();

        for (Object[] fila : datosValoracion) {
            labelsEstrellas.add(String.valueOf(fila[0])); // 1,2,3,4,5
            valoresEstrellas.add((Long) fila[1]);         // cantidad
        }

        model.addAttribute("labelsEstrellas", labelsEstrellas);
        model.addAttribute("valoresEstrellas", valoresEstrellas);

     //CLIENTES POR EDAD
     List<Integer> edades = informeService.obtenerEdades();

     long r0_15 = 0;
     long r16_24 = 0;
     long r25_35 = 0;
     long r36_50 = 0;
     long r50mas = 0;

     for (Integer edad : edades) {
         if (edad >= 0 && edad <= 15) r0_15++;
         else if (edad <= 24) r16_24++;
         else if (edad <= 35) r25_35++;
         else if (edad <= 50) r36_50++;
         else r50mas++;
     }

     List<String> labelsEdad = List.of("0-15", "16-24", "25-35", "36-50", "50+");
     List<Long> valoresEdad = List.of(r0_15, r16_24, r25_35, r36_50, r50mas);

     model.addAttribute("labelsEdad", labelsEdad);
     model.addAttribute("valoresEdad", valoresEdad);

	  // 4) TOLERANCIA
	  List<Object[]> datosTolerancia = informeService.clientesPorTolerancia();
	
	  List<String> labelsTolerancia = new ArrayList<>();
	  List<Long> valoresTolerancia = new ArrayList<>();
	
	  for (Object[] fila : datosTolerancia) {
		  boolean tolerancia = (boolean) fila[0];	     
		  long cantidad = (Long) fila[1];
	
	      labelsTolerancia.add(tolerancia ? "Tolerante" : "Intolerante");
	      valoresTolerancia.add(cantidad);
	  }
	
	  model.addAttribute("labelsTolerancia", labelsTolerancia);
	  model.addAttribute("valoresTolerancia", valoresTolerancia);


        return "informes";
    }


}

