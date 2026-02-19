package com.example.demo.unitario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

@ActiveProfiles("test")

@ExtendWith(MockitoExtension.class) //Activamos Mockito para este test
public class ClienteServiceTest {

	@Mock //Creamos el "doble" del repositorio
    private ClienteRepository clienteRepository;

    @InjectMocks //Inyectamos el repositorio falso en el servicio real
    private ClienteService clienteService;
	
    
    // Comprobar que el método GUARDAR funciona 
    @Test
    public void testGuardarCliente() {
        // Creamos un cliente de prueba
        Cliente cliente = new Cliente("Pablo", 21, "masculino", true, "de todo");
        cliente.setId(1L);
        
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Llamamos al método real del servicio
        Cliente resultado = clienteService.guardar(cliente);

        //Comprobamos que no ha fallado
        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertEquals(1L, resultado.getId(), "El ID devuelto debe ser 1L");

        // Verificamos que el servicio llamó al método 'save' del repositorio 1 vez
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }
    
    //BUSCAR POR ID
    @Test
    public void testBuscarPorId() {
        // Datos
        Long id = 1L;
        Cliente cliente = new Cliente("Ana", 30, "femenino", false, "nada");
        cliente.setId(id);

        // Mock: "Cuando buscan el ID 1, devuelve un Optional con Ana"
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        //Ejecución
        Cliente resultado = clienteService.buscarPorId(id);

        //Verificación, si el resutlado es igual a lo que hemos escrito es correcto. 
        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());
        assertEquals(1L, resultado.getId());
        
        verify(clienteRepository, times(1)).findById(id);
    }  
}
