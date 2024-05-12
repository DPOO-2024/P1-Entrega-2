package TestsModelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.LoginDuplicadoException;
import Exceptions.MensajedeErrorException;
import Modelo.Administrador;
import Modelo.Galeria;
import Piezas.Fotografia;
import Piezas.Pieza;
import Usuarios.Comprador;
import Usuarios.Propietario;
import Usuarios.Usuario;

class TestsAdministrador {
	private Administrador admin;
	private static ArrayList<String> datos1;
	private static ArrayList<String> datos2;
	
	@BeforeAll
	static void crearDatos() {
		datos1 = new ArrayList<String>();
		datos1.add("Veri_324");
		datos1.add("300214587");
		datos1.add("Veronica Lino");
		datos1.add("veri234@gmil.com");
		datos1.add("Veronica_lino");
		
		datos2 = new ArrayList<String>();
		datos2.add("david_23BR");
		datos2.add("301236587");
		datos2.add("David Andres Brown");
		datos2.add("davBrown@gmil.com");
		datos2.add("david_brown");						
		
	}
	
	@BeforeEach
	void cargarDatos() {
		Galeria galeria= new Galeria();
		galeria.cargarGaleria(galeria);
		admin = galeria.getAdmin();
		
	}
	
	@Test
	void agregarUsuarioTest() {
		try {
			int canC=admin.getCompradores().size();
			admin.verificarLogin("Veronica_lino", "Comprador");
			admin.agregarUsuario(datos1, "Comprador");
			assertEquals(canC+1, admin.getCompradores().size(),"No se agrego el Usuario(comprador) corrdctamente");
			
			int canP=admin.getPropietarios().size();
			admin.verificarLogin("Veronica_lino", "Propietario");
			admin.agregarUsuario(datos1, "Propietario");
			assertEquals(canP+1, admin.getPropietarios().size(),"No se agrego el Usuario(propietario) corrdctamente");
		}catch(Exception e) {
			fail("No se pudo agregar el Usuario");
		}
		
	}
	
	@Test
	void agregarUsuarioFalleTest() {
		assertThrows(LoginDuplicadoException.class, () -> {
			admin.verificarLogin("david_brown", "Propietario");
        });
		
		assertThrows(MensajedeErrorException.class, () -> {
			admin.agregarUsuario(datos2, "None");
        });
		
		
	}
	
	
	@Test
	void verificarCompradorTest() {
		try {
			admin.agregarUsuario(datos1, "Comprador");
			Comprador com = admin.verificarComprador("Veronica_lino" ,"Veri_324");
			assertEquals("Veronica_lino", com.getLogin(), "El comprador encontrado al iniciar sesion no fue el adecuado");
		}catch(Exception e) {
			fail("El inicio de sesion debio funcionar");
		}
	}
	
	@Test
	void verificarCompradorFallidoTest() {		
		assertThrows(MensajedeErrorException.class, () -> {
			admin.verificarComprador("Veronica_lino", "Veri_324");
        });
	}
	
	@Test
	void verificarPropietarioTest() {
		try {
			admin.agregarUsuario(datos1, "Propietario");
			Propietario prop = admin.verificarPropietario("Veronica_lino" ,"Veri_324");
			assertEquals("Veronica_lino", prop.getLogin(), "El comprador encontrado al iniciar sesion no fue el adecuado");
		}catch(Exception e) {
			fail("El inicio de sesion debio funcionar");
		}
	}
	
	@Test
	void verificarPropietarioFallidoTest() {		
		assertThrows(MensajedeErrorException.class, () -> {
			admin.verificarPropietario("Veronica_lino" ,"Veri_324");
        });
	}
	
	@Test
	void agregarPiezaTest() {
		try {
			Propietario u =admin.encontrarPropietario("david_brown");
			List<String> autoresP1 =new ArrayList<String>();
			autoresP1.add("Jane Smith");
			autoresP1.add("Michael Johnson");
			
			
			Pieza p1=new Fotografia("Fotografia",(Usuario)u,"Puesta de sol en la playa",2023,"Playa del Carmen, México",
					autoresP1,true,240224,100,"Mostrador",false,500,"8x10 pulgadas",2000,300, 
					"Una impresionante fotografía de una puesta de sol en la playa","JPEG");		
		
			int tam = u.getEstadoPiezas().size();
			admin.agregarPieza(p1, u);
			assertEquals(tam+1, u.getEstadoPiezas().size(), "No se agrego correctamente la pieza al propietario");
		}catch(Exception e) {
			fail("La pieza se debio agregar sin problema");
		}
	}
	
	
	@Test
	void historialPiezaTest() {
		try {
			
			List<List<String>> info = admin.historialComprador("david_brown");
			assertEquals(2,info.size(),"La lista no esta completa");
			assertEquals(" Manifestación de la Abstracción",info.get(0).get(0), "No es el titulo correcto");
			assertEquals("230524",info.get(0).get(1), "deberia estar disponible");
			assertEquals("500",info.get(0).get(2), "No es el valor correcto");
			assertEquals("6500",info.get(0).get(3), "No es el valor correcto");
			assertEquals(3,info.get(1).size(), "Faltan algunas obras de las que es propietario");
		}catch(Exception e) {
			fail("No debio fallar");
		}
	}
	

}
