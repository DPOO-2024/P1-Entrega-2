package TestsModelo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Modelo.Pago;
import Modelo.Subasta;
import Piezas.Pieza;
import Usuarios.Comprador;

class TestsSubasta {
private Galeria galeria;
	
	
	@BeforeEach
	void inicializarGaleria() {
    galeria = new Galeria();
    galeria.cargarGaleria(galeria);
}

	
	
	@Test
	void crearSubastaTest() {
		try {
			
			galeria.crearSubasta(240508,"1");
			Subasta subasta = galeria.getSubastasActivas().get(0);
			assertEquals("JessicaBrown_83",subasta.getOperador().getNombreUsuario(), "El operador asignado no fue correcto");
			int cant_subastas=galeria.getSubastasActivas().size();
			
			galeria.crearSubasta(240509,"2");
			Subasta subasta2 = galeria.getSubastasActivas().get(1);
			assertEquals("AlexMartinez22",subasta2.getOperador().getNombreUsuario(), "El operador asignado no fue correcto");
			
			assertEquals(cant_subastas+1,galeria.getSubastasActivas().size(), "la subasta no se creó adecuadamente");
			
		}catch(Exception e) {
			fail("La subasta se debió crear");
		}
	}
	@Test
	void crearSubastaFallidaTest() {
	assertThrows(MensajedeErrorException.class, () -> {
		galeria.crearSubasta(240508,"1");
		galeria.crearSubasta(240508,"1");
		
    });
}

	
	@Test
	void RegistrarSubastaTest() {	
		try {
			galeria.crearSubasta(240508,"1");
			galeria.crearSubasta(240509,"2");
			Comprador comprador = galeria.getAdmin().getComprador("maria_gomez");
			galeria.participarSubasta(240508, comprador, 1);
			Subasta subasta = galeria.encontrarSubasta(240508);
			assertTrue(subasta.isActiva(),"No se puede participar en esta subasta,ya que no esta activa");
		}catch(Exception e) {
			fail("Se debio registrar correctamente");
		}
		
	}
	

	
	@Test
	void participacionFallidaTest() {	
		try {
			galeria.crearSubasta(240508,"1");
			galeria.crearSubasta(240509,"2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertThrows(MensajedeErrorException.class, () -> {
			galeria.participarSubasta(240508, galeria.getAdmin().getComprador("david_brown"), 0);
        });
	}





	
	@ParameterizedTest
	@ValueSource(strings = {"Tarjeta","Transferencia","Efectivo","otro"})
	void participarSubastaTest(String formaPago) {
		try {
			
			galeria.crearSubasta(240508,"1");
			galeria.crearSubasta(240509,"2");
			Comprador comprador = galeria.getAdmin().getComprador("maria_gomez");
			galeria.participarSubasta(240508, comprador, 1);
			Comprador comp = galeria.getAdmin().getComprador("maria_gomez");
			Subasta subasta = galeria.participarSubasta(240508, comp, 0);
			assertNotNull(subasta,"No se agrego correctamente");
			Pieza pieza = subasta.getInventario().get(1);
			assertNotEquals(0,pieza.getValorInicial(),"No deberia estar en el inventario de una subasta");
			
			comp.hacerOferta(galeria.getAdmin(),"60" ,formaPago , subasta.getOperador(), pieza);
			if (formaPago.equals("otro")) {
				fail("Deberia fallar esta forma de pago");
			}
			assertTrue(subasta.getOperador().getOfertas().containsKey(pieza),"No esta la pieza registrada en la subasta");
			assertEquals(1,subasta.getOperador().getOfertas().get(pieza).size(), "No se registro correctamente la pieza");


		}catch(Exception e) {
			if (!formaPago.equals("otro")) {
				fail("No debio fallar esta forma de pago");
			}
		}
	}
	
	

	
	@Test
	void ofertaFallidaTest() {
		try {
		galeria.crearSubasta(240508,"1");
		galeria.crearSubasta(240509,"2");
		galeria.participarSubasta(240508, galeria.getAdmin().getComprador("maria_gomez"), 1);
		Subasta sub = galeria.participarSubasta(240508, galeria.getAdmin().getComprador("maria_gomez"), 0);
		Pieza p = sub.getInventario().get(1);
		galeria.getAdmin().getComprador("maria_gomez").hacerOferta(galeria.getAdmin(),"60" ,"tarjeta" , sub.getOperador(), p);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		
		
		assertThrows(MensajedeErrorException.class, () -> {
			galeria.participarSubasta(240508, galeria.getAdmin().getComprador("david_brown"), 1);
			Subasta subasta = galeria.participarSubasta(240508, galeria.getAdmin().getComprador("david_brown"), 0);
			Pieza pieza = subasta.getInventario().get(1);
			galeria.getAdmin().getComprador("david_brown").hacerOferta(galeria.getAdmin(),"55" ,"tarjeta" , subasta.getOperador(), pieza);
        });
		
		assertThrows(MensajedeErrorException.class, () -> {
			galeria.participarSubasta(240508, galeria.getAdmin().getComprador("robert_johnson"), 1);
			Subasta subasta = galeria.participarSubasta(240508, galeria.getAdmin().getComprador("robert_johnson"), 0);
			Pieza pieza = subasta.getInventario().get(1);
			galeria.getAdmin().getComprador("robert_johnson").hacerOferta(galeria.getAdmin(),"45" ,"tarjeta" , subasta.getOperador(), pieza);
        });
	}
	

	
	@Test
	void terminarSubastaTest() {
		try {
			
			galeria.crearSubasta(240508,"1");
			galeria.crearSubasta(240509,"2");
			galeria.participarSubasta(240508, galeria.getAdmin().getComprador("maria_gomez"), 1);
			Subasta sub = galeria.participarSubasta(240508, galeria.getAdmin().getComprador("maria_gomez"), 0);
			Pieza p = sub.getInventario().get(1);
			galeria.getAdmin().getComprador("maria_gomez").hacerOferta(galeria.getAdmin(),"60" ,"tarjeta" , sub.getOperador(), p);
			
			
			
			
			galeria.terminarSubasta(240509);
			assertEquals(1,galeria.getSubastasActivas().size(), "No se elimino correctamente la subasta");
			galeria.participarSubasta(240508, galeria.getAdmin().getComprador("robert_johnson"), 1);
			Subasta subasta = galeria.participarSubasta(240508, galeria.getAdmin().getComprador("robert_johnson"), 0);
			Pieza pieza = subasta.getInventario().get(1);
			galeria.getAdmin().getComprador("robert_johnson").hacerOferta(galeria.getAdmin(),"80" ,"tarjeta" , subasta.getOperador(), pieza);
			galeria.terminarSubasta(240508);
			assertFalse(subasta.isActiva(),"La subasta deberia estar inactiva");
			assertEquals(pieza.getTitulo(),galeria.getAdmin().getComprador("robert_johnson").getHistorialCompras().get(0),"La ganadora deberia tener la pieza entre sus compras");
			assertEquals("80",galeria.getAdmin().getComprador("robert_johnson").getHistorialCompras().get(2),"El precio de la obra no es el correcto");
			assertFalse(subasta.getOperador().isAsignado(),"El operador no deberia estar asignado a una subasta inactiva");
		}
		catch(Exception e) {
			fail("Deberia finalizar la subasta correctamente");}
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Tarjeta","Transferencia","Efectivo","otro"})
	void comprarPiezaTest(String formaPago) {
		try {
			Comprador comprador = galeria.getAdmin().getComprador("robert_johnson");
			Pieza pieza = galeria.getInventario().getPiezasDisponibles().get(5);
			comprador.comprarPieza(6, formaPago, galeria);
			assertNotEquals(0,pieza.getValorFijo(),"No deberia poder venderse de esta forma");
			Pago pago = galeria.getCajero().getPagos().get(0);
			assertEquals(pieza.getValorFijo(),pago.getMonto(),"El valor de cobro es incorrecto");
			assertEquals(comprador.getLogin(),pago.getComprador().getLogin(),"Ese no es el verdadero comprador");			
			
			if (formaPago.equals("otro")) {
				fail("Deberia fallar esta forma de pago");
			}
			
			
			
			
		}catch(Exception e) 
		{
			if (!formaPago.equals("otro")) {
			fail("No debio fallar esta forma de pago");
		}
	}
	}
	
	
	@Test
	void compraFallidaTest() {
	assertThrows(MensajedeErrorException.class, () -> {
		Comprador comprador = galeria.getAdmin().getComprador("robert_johnson");
		galeria.getInventario().getPiezasDisponibles().get(5);
		comprador.comprarPieza(1, "tarjeta", galeria);
    });
	
	}

	
	



}