package TestsModelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Modelo.Galeria;
import Piezas.Autor;
import Piezas.Pieza;
import Usuarios.Propietario;

class TestsInventario {
	private static Galeria galeria;
	private static Pieza p;
	
	@BeforeAll
	static void crearDatos() {
		galeria= new Galeria();
		galeria.cargarGaleria(galeria);
		
		Propietario u = galeria.getAdmin().getPropietarios().getFirst();
		
		ArrayList<Autor> autores =new ArrayList();
		
		
		p=new Pieza("Fotografia",u,"Puesta de sol en la playa",2023,"Playa del Carmen, México",
				autores,true,2024,100,"Mostrador",false,);
		
	}

}
