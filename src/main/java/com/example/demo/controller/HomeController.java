package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	//para que no tengamos que poner en la URL la ruta completa, solo con poner http://localhost:8081 ya nos redirige
    @GetMapping("/")
    public String redirigirInicio() {
        return "redirect:/listaCliente";
    }
}
