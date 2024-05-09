package TestsModelo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import Exceptions.MensajedeErrorException;
import Modelo.Empleado;
import Modelo.Galeria;
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
			Empleado em=galeria.verificarEmpleado("davidM", "david12M");
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
	
	
	
	


}
