package TestsModelo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import Exceptions.MensajedeErrorException;
import Modelo.Empleado;
import Modelo.Galeria;
import Piezas.Pieza;
import Usuarios.Comprador;

class TestsGaleria {
	private Galeria galeria;
	
	
	@BeforeEach
	void cargarDatos() {
		this.galeria= new Galeria();
		galeria.cargarGaleria(galeria);
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {"Empleado","Operador","None"})
	void agregarEmpleadoTest(String rol) {
		try {
			int cant_empleados=galeria.getEmpleados().size();
			galeria.agregarEmpleado("davidM", "david12M", rol);
			assertEquals(cant_empleados+1,galeria.getEmpleados().size(), "El empleado no se agrego adecuadamente");
			if (rol.equals("None")) {
				fail("No se debio agregar el empleado");
			}
		}catch(Exception e) {
			if (!rol.equals("None")) {
				fail("Se debio agregar el empleado");
			}
		}
	}
	
	
	@Test
	void inicioSesionEmpleadoTest() {
		try {
			galeria.agregarEmpleado("davidM", "david12M", "Empleado");
			Empleado em=galeria.verificarEmpleado("davidM", "david12M");
			assertEquals("davidM", em.getNombreUsuario(), "El empleado encontrado al iniciar sesion no fue el adecuado");
		}catch(Exception e) {
			fail("El inicio de sesion debio funcionar");
		}
	}
	
	@Test
	void inicioSesionEmpleadoFallidoTest() {		
		assertThrows(MensajedeErrorException.class, () -> {
			galeria.verificarEmpleado("davidM", "david12M");
        });
	}
	
	@Test
	void aumentarCupoTest() {
		try {
			Comprador comp = galeria.getAdmin().getComprador("robert_johnson");
			galeria.aumentarCupo("robert_johnson", 500);
			assertEquals(5500, comp.getComprasMaximas(), "No se realizo el aumento de cupo");
		}catch(Exception e) {
			fail("No se encontro el comprador");
		}
	}
	
	@Test
	void asignarAdministradorTest() {
		galeria.asignarAdministrador("Laura_villa", "villita43");
		assertEquals("Laura_villa",galeria.getAdmin().getLogin(), "No se reasigno el administrador correctamente");
	}
	
	@Test
	void asignarCajeroTest() {
		try {
			galeria.asignarCajero("Laura_villa", "villita43");
			assertEquals("Laura_villa",galeria.getCajero().getNombreUsuario(), "No se asigno el cajero correctamente");
			galeria.asignarCajero("AlexMartinez22", "SoccerFan!23");
			assertEquals("AlexMartinez22",galeria.getCajero().getNombreUsuario(), "No se asigno el cajero correctamente");
		}catch(Exception e) {
			fail("No se pudo asignar el cajero");
		}
	}
	
	@Test
	void historialPiezaVendidaTest() {
		try {
			Comprador comprador = galeria.getAdmin().getComprador("maria_gomez");
			Pieza pieza = galeria.getInventario().getPiezasDisponibles().get(3);
			comprador.comprarPieza(4, "tarjeta", galeria);
			List<String> info = galeria.historialPiezas(pieza.getTitulo());
			assertEquals(" Flores en Primavera",info.get(0), "No es el titulo correcto");
			assertEquals("vendida",info.get(1), "Deberia estar vendida");
			assertEquals("david_brown",info.get(2), "No es el propietario correcto");
			assertEquals("maria_gomez",info.get(3), "No es el comprador correcto");
			assertEquals("250",info.get(5), "No es el precio  correcto");
		}catch(Exception e) {
			fail("No debio fallar");
		}
	}
	@Test
	void historialPiezaTest() {
		try {
			Pieza pieza = galeria.getInventario().getPiezasDisponibles().get(0);
			List<String> info = galeria.historialPiezas(pieza.getTitulo());
			assertEquals(" Escultura en Movimiento",info.get(0), "No es el titulo correcto");
			assertEquals("disponible",info.get(1), "deberia estar disponible");
			assertEquals("sarah_miller",info.get(2), "No es el propietario correcto");
			assertEquals(" Mostrador",info.get(3), "No es la ubicacion");
			assertEquals("no",info.get(4), "No es la fecha correcta");
			assertEquals("s",info.get(5), "No es el precio  correcto");
		}catch(Exception e) {
			fail("No debio fallar");
		}
	}
	
	@Test
	void historialArtistaTest() {
		try {
			List<List<String>> piezasArtista = galeria.historialArtista("John Swan");
			assertEquals(2,piezasArtista.size(), "El artista deberia tener 2 piezas");
			assertEquals("John Swan",piezasArtista.get(0).get(0), "No es el artista correcto");
			assertEquals(" Atardecer en la Ciudad",piezasArtista.get(0).get(1), "No es el titulo correcto");
			assertEquals("2019",piezasArtista.get(0).get(2), "No es el año correcto");
			assertEquals("disponible",piezasArtista.get(0).get(3), "No es correcto");
			assertEquals("emma_smith",piezasArtista.get(0).get(4), "No es el propietario");
			assertEquals(" Mostrador",piezasArtista.get(0).get(5), "No es la ubicacion correcta");
			assertEquals("no",piezasArtista.get(0).get(6), "No es la modalidad correcta");
			assertEquals(" Retrato en Blanco y Negro",piezasArtista.get(1).get(1), "No es el titulo correcto");
			assertEquals("2009",piezasArtista.get(1).get(2), "No es el año correcto");
			assertEquals("vendida",piezasArtista.get(1).get(3), "No es correcto");
			assertEquals("emma_smith",piezasArtista.get(1).get(4), "No es el propietario");
			assertEquals("maria_gomez",piezasArtista.get(1).get(5), "No es el comprador");
			assertEquals("220315",piezasArtista.get(1).get(6), "No es la fecha");
			assertEquals("300",piezasArtista.get(1).get(7), "No es el precio");


		}catch(Exception e) {
			fail("No debio fallar");
		}
	}
	
	@Test
	void guardarGaleria(){
		try {
			galeria.guardarGaleria();
		}catch(Exception e) {
			fail("La Galeria debio guardarse adecuadamente");
		}
	}
	
	


}
