package com.example.demo.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(length = 50)
	private String nombre;
	private int edad;
    private String genero;
    
    @Column(name = "tolerancia", columnDefinition = "TINYINT(1)")//para que la BD acepte el boolean. 
    private boolean tolerancia;
    
    @Column(name = "detalle")
    private String detalle;
    
    public Cliente() {
    	super();
    }
    
    public Cliente(String nombre, int edad, String genero, boolean tolerancia, String detalle) {
    	
    	this.nombre = nombre;
    	this.edad = edad;
    	this.genero = genero;
    	this.tolerancia = tolerancia;
    	this.detalle = detalle;
    	
    }
    
    // Getters y setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(boolean tolerancia) {
		this.tolerancia = tolerancia;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


    
}
