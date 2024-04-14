package Modelo;
import java.util.Scanner;

import Persistencia.CentralPersistencia;
import Usuarios.Cajero;

public class Galeria {
	private Administrador admin;
	private String nombre;
	private Cajero cajero;
	//Falta lista de empleados
	
	
	
	
	public Galeria() {
		this.nombre = "Galeria";
		this.admin = null;
		this.cajero = null;
	}

	public void cargarGaleria() {
		//Pedir los nombres de los archivos 
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Por favor, ingrese el nombre del archivo (.txt) de inicio de galeria: ");
        String archivoInicio = scanner.nextLine();
        
        System.out.print("Por favor, ingrese el nombre del archivo (.txt) de los usuarios: ");
        String archivoUsuarios = scanner.nextLine();
        
        System.out.print("Por favor, ingrese el nombre del archivo (.txt) de las piezas: ");
        String archivoPiezas = scanner.nextLine();
        
        System.out.print("Por favor, ingrese el nombre del archivo (.txt) de las compras: ");
        String archivoCompras = scanner.nextLine();
		
		try {
			CentralPersistencia.cargarGaleria(archivoInicio, archivoUsuarios, archivoPiezas, archivoCompras,this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void guardarGaleria() { 
		String nombre=this.nombre;
		
		try {
			CentralPersistencia.guardarGaleria(nombre,this);
		} catch (Exception e) {
			e.printStackTrace();
	}
	
	
	
}
	public Administrador getAdministrador() {
		return this.admin;
	}

	public void setAdministrador(Administrador admin) {
		this.admin = admin;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cajero getCajero() {
		return this.cajero;
	}

	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}
	
}
