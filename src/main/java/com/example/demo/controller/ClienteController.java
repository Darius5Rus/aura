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
import com.example.demo.service.ClienteService;

import com.example.demo.entity.Cliente;


@Controller 
@RequestMapping("/listaCliente") //ruta del controlador y la que usara el listar.
public class ClienteController {

	// Prueba del Supuesto 2
	@Autowired
    private ClienteService clienteService;//creamos e instanciamos la clase clienteService con los CRUD.
	
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaClientes", clienteService.listarTodos());
        return "listaClientes";//el return busca el html con ese nombre. 
    }
    
    
    @GetMapping("/nuevoCliente")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "nuevoCliente";
    }
    
    @PostMapping("/guardarCliente")
    public String guardar(@ModelAttribute("cliente") Cliente cliente,BindingResult bindingResult) {
        
    	if (bindingResult.hasErrors()) {
            return "nuevoCliente";
        }
        
        clienteService.guardar(cliente);
        return "redirect:/listaCliente";//redirije al metodo de listar que luego eso hace return al html. 
    }
    
    
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") Long id, Model model) { 
    	Cliente cliente = clienteService.buscarPorId(id); 
    	model.addAttribute("cliente", cliente); //esto envia el valor de cliente a la vista.
    	return "nuevoCliente"; // reutilizamos la misma vista que para crear 
    }
    
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
    	System.out.println("id:"+id);
        clienteService.eliminar(id);
        return "redirect:/listaCliente"; // redirige al listado
    }
	
}
