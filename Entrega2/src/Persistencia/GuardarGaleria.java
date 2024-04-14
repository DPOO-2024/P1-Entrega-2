package Persistencia;

import java.io.FileWriter;
import java.io.IOException;

import Modelo.Galeria;

public class GuardarGaleria {
	
	private String archivoGaleria;
	private String archivoUsuarios;
	private String archivoPiezas;
	private String archivoCompras;
	private Galeria galeria;
	
	
	public GuardarGaleria(String nomGaleria,Galeria gal) {
		this.archivoGaleria = nomGaleria+".txt";
		this.archivoUsuarios = nomGaleria+"Usuarios.txt";
		this.archivoPiezas = nomGaleria+"Piezas.txt";
		this.archivoCompras = nomGaleria+"Compras.txt";
		this.galeria=gal;
	}
	
	public String generarArchivos() {
		try {
			generarArchivoGaleria();
			generarArchivoUsuarios();
			generarArchivoPiezas();
			generarArchivoCompras();
			
			return "Se guardo con exito la galeria, en los siguientes documentos: " + this.archivoGaleria + "," + this.archivoUsuarios + 
					"," + this.archivoPiezas + "," + this.archivoCompras;
			
		}catch(Exception e) {
			throw (e);
		}
	}

	private void generarArchivoCompras() {
		// TODO Auto-generated method stub
		
	}

	private void generarArchivoPiezas() {
		// TODO Auto-generated method stub
		
	}

	private void generarArchivoUsuarios() {
		// TODO Auto-generated method stub
		
	}

	private void generarArchivoGaleria() {
		try (FileWriter writer = new FileWriter(this.archivoGaleria)) {
            // Escribir datos en el archivo
			if (!galeria.getNombre().equals("Galeria")) {
				writer.write("nombreGaleria,"+ galeria.getNombre()+"\n");
			}
			//Añadir Administrador 
			
			//Añadir Empleado

        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
}
