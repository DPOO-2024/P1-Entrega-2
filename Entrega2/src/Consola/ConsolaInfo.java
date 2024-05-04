package Consola;

import Modelo.Galeria;

public class ConsolaInfo {
	
	public static void mostrarHistorialPieza(Galeria gal) {
		try {
    		System.out.print("Por favor, ingrese el nombre de la Pieza: ");
    		String nombreP = ConsolaInicial.scanner.nextLine().trim();
    		//Llamar funcion
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	public static void mostrarHistorialArtista(Galeria gal) {
		try {
    		System.out.print("Por favor, ingrese el nombre del Artista: ");
    		String nombreA = ConsolaInicial.scanner.nextLine().trim();
    		//Llamar funcion
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
	}

}
