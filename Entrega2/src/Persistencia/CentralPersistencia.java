package Persistencia;

import Modelo.Galeria;

public class CentralPersistencia {
	
	public static void cargarGaleria(String arvhivoGeneral, String archivoUsuarios, String archivoPiezas, Galeria galeria) {
		CargaGaleria perGal= new CargaGaleria(arvhivoGeneral, archivoUsuarios, archivoPiezas, galeria);
	try {
		perGal.cargarArchivos();
	} catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
	}
	
	public static void guardarGaleria(Galeria gal) {
		GuardarGaleria perGal= new GuardarGaleria(gal);
	try {
		String respuesta = perGal.generarArchivos();
		System.out.println(respuesta);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}