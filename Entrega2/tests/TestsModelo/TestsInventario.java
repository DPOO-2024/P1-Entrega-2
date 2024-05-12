package TestsModelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.MensajedeErrorException;
import Exceptions.PiezaRepetidaException;
import Modelo.Galeria;
import Modelo.Inventario;
import Piezas.Fotografia;
import Piezas.Otro;
import Piezas.Pieza;
import Usuarios.Propietario;
import Usuarios.Usuario;

class TestsInventario {
	private static Inventario inventario;
	private static Pieza p1;
	private static Pieza p2;
	private static Propietario u;
	
	@BeforeAll
	static void crearDatos() {
		
		
		List<String> autoresP1 =new ArrayList<String>();
		autoresP1.add("Jane Smith");
		autoresP1.add("Michael Johnson");
		
		
		p1=new Fotografia("Fotografia",(Usuario)u,"Puesta de sol en la playa",2023,"Playa del Carmen, México",
				autoresP1,true,240224,100,"Mostrador",false,500,"8x10 pulgadas",2000,300, 
				"Una impresionante fotografía de una puesta de sol en la playa","JPEG");		
		
		List<String> autoresP2 =new ArrayList<String>();
		autoresP1.add("Jane Smith");
		
		p2=new Otro("Otro",(Usuario)u,"Puesta en la arena",2022,"Playa del Carmen, México",
				autoresP2,false,2024,50,"Mostrador",true,200,
				"Escultura que representa una esfera en constante movimiento, reflejando la interacción entre luz y sombra.");	
		

	}
	
	@BeforeEach
	void cargarDatos() {
		Galeria galeria= new Galeria();
		galeria.cargarGaleria(galeria);
		inventario=galeria.getInventario();
		u = galeria.getAdmin().getPropietarios().getFirst();
	}

	
	
	
	@Test
	void agregarPiezaTest() {
		try {
			int tamLista= inventario.getPiezasDisponibles().size();
			inventario.agregarPieza(p1);
			assertEquals(tamLista+1, inventario.getPiezasDisponibles().size(),"No se agrego correctamente la pieza");		
			
			tamLista= inventario.getHistorialPiezas().size();
			inventario.agregarPieza(p2);
			assertEquals(tamLista+1, inventario.getHistorialPiezas().size(),"No se agrego correctamente la pieza");
			
		} catch (PiezaRepetidaException e) {
			fail("La pieza debio agregarse corretamente");
		}
		
	}
	
	@Test
	void agregarPiezaFallaTest() {
		try {
			inventario.agregarPieza(p1);
			inventario.agregarPieza(p2);
		} catch (PiezaRepetidaException e) {
			fail("La pieza debio agregarse corretamente");
		}
		
		assertThrows(PiezaRepetidaException.class, () -> {
			inventario.agregarPieza(p1);
        });
		
		assertThrows(PiezaRepetidaException.class, () -> {
			inventario.agregarPieza(p2);
        });
	}
	
	@Test
	void getPiezaTest() {
		try {
			Pieza p = inventario.getPieza("Manifestación de la Abstracción");
			assertEquals(" Manifestación de la Abstracción", p.getTitulo(), "La pieza encontrada no es la adecuada");
		} catch (MensajedeErrorException e) {
			fail("La pieza se debio encontrar");
		}
		
	}
	
	@Test
	void getPiezaFalloTest() {
		assertThrows(MensajedeErrorException.class, () -> {
			inventario.getPieza("Hola mundo");
        });
		
	}
	
	
	
	@Test
	void moverPiezaTest() {
		try {
			inventario.agregarPieza(p1);
		} catch (PiezaRepetidaException e) {
			fail("La pieza debio agregarse corretamente");
		}
		int tamListaD= inventario.getPiezasDisponibles().size();
		int tamListaH= inventario.getHistorialPiezas().size();
		inventario.moverPieza(p1);
		
		assertEquals(tamListaD-1, inventario.getPiezasDisponibles().size(), "No se movio correctamente la Pieza");
		assertEquals(tamListaH+1, inventario.getHistorialPiezas().size(), "No se movio correctamente la Pieza");
		
		
		inventario.moverPieza(p1);
		
		assertEquals(tamListaD, inventario.getPiezasDisponibles().size(), "No se movio correctamente la Pieza");
		assertEquals(tamListaH, inventario.getHistorialPiezas().size(), "No se movio correctamente la Pieza");
		
	}
	
	@Test
	void verificarPrecioPiezaTest() {
		try {
			int valFijo= inventario.verificarPrecioPieza(inventario.getPieza("Escultura en Movimiento"));
			assertEquals(0, valFijo,"El valor fijo encontrado es erroneo");
			valFijo= inventario.verificarPrecioPieza(inventario.getPieza("Flores en Primavera"));
			assertEquals(250, valFijo,"El valor fijo encontrado es erroneo");	
		} catch (MensajedeErrorException e) {
			fail("La pieza debio agregarse corretamente");
		}
		
	}
	
	@AfterAll
	static void reiniciarTodo() {
		inventario=null;
	}
	

}
