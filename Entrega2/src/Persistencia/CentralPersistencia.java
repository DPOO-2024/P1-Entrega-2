package Persistencia;

import Modelo.Galeria;

public class CentralPersistencia {
	
	public static void cargarGaleria(String arvhivoGeneral, String archivoUsuarios, String archivoPiezas, String archivoCompra) {
		CargaGaleria perGal= new CargaGaleria(arvhivoGeneral, archivoUsuarios, archivoPiezas, archivoCompra);
	try {
		perGal.cargarArchivos();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public static void guardarGaleria(String nomGaleria,Galeria gal) {
		GuardarGaleria perGal= new GuardarGaleria(nomGaleria, gal);
	try {
		String respuesta = perGal.generarArchivos();
		System.out.println(respuesta);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}