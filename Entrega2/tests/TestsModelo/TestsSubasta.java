package TestsModelo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import Consola.ConsolaSubasta;
import Exceptions.MensajedeErrorException;
import Modelo.Empleado;
import Modelo.Galeria;
import Modelo.Subasta;
import Piezas.Pieza;
import Usuarios.Comprador;

class TestsSubasta {
	private Galeria galeria;
	
	
	@BeforeAll
	void cargarDatos() {
		this.galeria= new Galeria();
		galeria.cargarGaleria(galeria);
	}
	
	

	
	
	@Test
	void crearSubastaTest() {
		try {
			
			galeria.crearSubasta(240508,"1");
			Subasta subasta = galeria.getSubastasActivas().get(0);
			assertEquals("JessicaBrown_83",subasta.getOperador(), "El operador asignado no fue correcto");
			int cant_subastas=galeria.getSubastasActivas().size();
			
			galeria.crearSubasta(240509,"2");
			Subasta subasta2 = galeria.getSubastasActivas().get(0);
			assertEquals("AlexMartinez22",subasta2.getOperador(), "El operador asignado no fue correcto");
			
			assertEquals(cant_subastas+1,galeria.getSubastasActivas().size(), "la subasta no se creó adecuadamente");
			
		}catch(Exception e) {
			fail("La subasta se debió crear");
		}
	}
	
	@Test
	void RegistrarSubastaTest() {	
		try {
			
			Comprador comprador = galeria.getAdmin().getComprador("maria_gomez");
			Subasta subasta = galeria.participarSubasta(240508, comprador, 1);
			assertNotNull(subasta.getCompradores(),"No se agrego correctamente");
			assertTrue(subasta.isActiva(),"No se puede participar en esta subasta,ya que no esta activa");
		}catch(Exception e) {
			fail("Se debio registrar correctamente");
		}
		
	}
	@Test
	void participacionFallidaTest() {		
		assertThrows(MensajedeErrorException.class, () -> {
			galeria.participarSubasta(240508, galeria.getAdmin().getComprador("david_brown"), 0);
        });
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Tarjeta","Transferencia","Efectivo","otro"})
	void participarSubastaTest(String formaPago) {
		try {
			Comprador comp = galeria.getAdmin().getComprador("maria_gomez");
			Subasta subasta = galeria.participarSubasta(240508, comp, 0);
			assertNotNull(subasta,"No se agrego correctamente");
			Pieza pieza = subasta.getInventario().get(0);
			assertNotEquals(0,pieza.getValorInicial(),"No deberia estar en el inventario de una subasta");
			int num_ofertas = subasta.getOperador().getOfertas().get(pieza).size();
			comp.hacerOferta(galeria.getAdmin(),"2700" ,formaPago , subasta.getOperador(), pieza);
			if (formaPago.equals("otro")) {
				fail("Deberia fallar esta forma de pago");
			}
			assertTrue(subasta.getOperador().getOfertas().containsKey(pieza),"No esta la pieza registrada en la subasta");
			assertEquals(num_ofertas+1,subasta.getOperador().getOfertas().get(pieza).size(), "No se registro correctamente la pieza");


		}catch(Exception e) {
			if (!formaPago.equals("otro")) {
				fail("No debio fallar esta forma de pago");
			}
		}
	}
	
	@Test
	void ofertaNoMaximaFallidaTest() {		
		assertThrows(MensajedeErrorException.class, () -> {
			galeria.participarSubasta(240508, galeria.getAdmin().getComprador("david_brown"), 1);
			Subasta subasta = galeria.participarSubasta(240508, galeria.getAdmin().getComprador("david_brown"), 0);
			Pieza pieza = subasta.getInventario().get(0);
			galeria.getAdmin().getComprador("david_brown").hacerOferta(galeria.getAdmin(),"2600" ,"tarjeta" , subasta.getOperador(), pieza);
        });
	}
	
	
	
	@Test
	void terminarSubastaTest() {
		galeria.asignarAdministrador("Laura_villa", "villita43");
		assertEquals("Laura_villa",galeria.getAdmin().getLogin(), "No se reasigno el administrador correctamente");
	}
	
	@Test
	void comprarPiezaTest() {
		try {
			galeria.asignarCajero("Laura_villa", "villita43");
			assertEquals("Laura_villa",galeria.getCajero().getNombreUsuario(), "No se asigno el cajero correctamente");
			galeria.asignarCajero("AlexMartinez22", "SoccerFan!23");
			assertEquals("AlexMartinez22",galeria.getCajero().getNombreUsuario(), "No se asigno el cajero correctamente");
		}catch(Exception e) {
			fail("No se pudo asignar el cajero");
		}
	}
	
	
	
	


}
