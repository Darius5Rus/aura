package com.example.demo.e2e;

//IMPORTS CORRECTOS PARA MOCKMVC
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")

@SpringBootTest // Arranca la aplicación 
@AutoConfigureMockMvc //Configura el "navegador falso"
public class ClienteControllerTest {

	@Autowired
    private MockMvc mockMvc; // El objeto que hace las peticiones falsas
	
	
	//Verificar que la URL /api/clientes funciona
    @Test
    public void testListarClientes() throws Exception {
        // Hacemos una petición GET a la API
        mockMvc.perform(get("/api/clientes"))
               // Verificaciones:
               .andExpect(status().isOk()) // Esperamos que devuelva 200 OK 
               .andExpect(content().contentType(MediaType.APPLICATION_JSON)); // Esperamos recibir un JSON
    }
    
    
	//Verificar que la URL /api/clientes/id funcina
    @Test
    public void testIDCliente() throws Exception {
        // Hacemos una petición GET a la API
        mockMvc.perform(get("/api/clientes/1"))
               // Verificaciones:
               .andExpect(status().isOk()) // Esperamos que devuelva 200 OK 
               .andExpect(jsonPath("$.nombre").value("Darius"))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON)); // Esperamos recibir un JSON
    }
    
    //Verificar que devuelve error 404 si la URL está mal
    @Test
    public void testUrlNoExiste() throws Exception {
        // Hacemos GET a una dirección inventada
        mockMvc.perform(get("/api/ruta-que-no-existe"))
               // Verificaciones:
               .andExpect(status().isNotFound()); // Esperamos error 404 (Not Found)
    }
}
