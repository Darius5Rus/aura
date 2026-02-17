package com.example.demo.integracion;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;

@DataJpaTest //Arranca solo la parte de Base de Datos de Spring
@AutoConfigureTestDatabase(replace = Replace.NONE) // Usa Base de Datos real (MySQL) en vez de una en memoria
public class ClienteRepositoryTest {

	@Autowired //sirve para no instanciar el objeto. 
    private ClienteRepository clienteRepository;
	
	
	//Guardar y Recuperar de la Base de Datos REAL
    @Test
    public void testGuardarYBuscarCliente() {
        //Crear un cliente nuevo (sin ID, porque es auto-incremental)
        Cliente nuevoCliente = new Cliente("ClienteIntegracion", 40, "femenino", true, "test db");
        
        //Guardarlo en la Base de Datos
        Cliente clienteGuardado = clienteRepository.save(nuevoCliente);

        //Verificar que se ha guardado (ahora sí tiene ID real de la BD)
        assertThat(clienteGuardado).isNotNull();
        assertThat(clienteGuardado.getId()).isGreaterThan(0); // El ID debe ser mayor que 0

        //Intentar buscarlo por ese ID
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(clienteGuardado.getId());
        
        //Verificar que lo hemos encontrado
        assertThat(clienteEncontrado).isPresent();
        assertThat(clienteEncontrado.get().getNombre()).isEqualTo("ClienteIntegracion");
    }
    
    //Borrar Cliente
    @Test
    public void testBorrarCliente() {
    	
        //Primero guardamos uno para tener algo que borrar
        Cliente cliente = new Cliente("Borrar", 50, "masculino", false, "borrar");
        Cliente guardado = clienteRepository.save(cliente);
        Long id = guardado.getId();

        //Intentamos buscarlo y debería existir con ese nombre
        Optional<Cliente> buscado = clienteRepository.findById(id);
        assertThat(buscado.get().getNombre()).isEqualTo("Borrar");
        
        //Lo borramos
        clienteRepository.deleteById(id);

        //Intentamos buscarlo y NO debería existir
        Optional<Cliente> buscado2 = clienteRepository.findById(id);
        assertThat(buscado2).isEmpty();
    }	
	
}
