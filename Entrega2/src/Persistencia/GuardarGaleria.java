package Persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Modelo.Administrador;
import Modelo.Empleado;
import Modelo.Galeria;
import Modelo.Inventario;
import Piezas.Pieza;
import Usuarios.Comprador;
import Usuarios.Propietario;
import Usuarios.Usuario;

public class GuardarGaleria {
	
	private String archivoGaleria;
	private String archivoUsuarios;
	private String archivoPiezas;
	private Galeria galeria;
	
	
	public GuardarGaleria(String nomGaleria,Galeria gal) {
		this.archivoGaleria = nomGaleria+".txt";
		this.archivoUsuarios = nomGaleria+"Usuarios.txt";
		this.archivoPiezas = nomGaleria+"Piezas.txt";
		this.galeria=gal;
	}
	
	public String generarArchivos() {
		try {
			generarArchivoGaleria();
			generarArchivoUsuarios();
			generarArchivoPiezas();
			
			return "Se guardo con exito la galeria, en los siguientes documentos: " + this.archivoGaleria + "," + this.archivoUsuarios + 
					"," + this.archivoPiezas ;
			
		}catch(Exception e) {
			throw (e);
		}
	}

	private void generarArchivoPiezas() {
		//Quitar comas de descripciones
		String ubicacion = encontrarRuta() + "\\Datos\\"+ this.archivoPiezas;
		File archivof = new File(ubicacion);
		
		try (FileWriter writer = new FileWriter(archivof)) {
            // Escribir datos en el archivo
			Inventario inventario= galeria.getInventario();
			
			for(Pieza p:inventario.getPiezasDisponibles()) {
				String info = guardarPiezas.guardarPieza(p,true);
				writer.write(info);
			}
			for(Pieza p:inventario.getHistorialPiezas()) {
				String info = guardarPiezas.guardarPieza(p,false);
				writer.write(info);
			}
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	private void generarArchivoUsuarios() {
		String ubicacion = encontrarRuta() + "\\Datos\\"+ this.archivoGaleria;
		File archivof = new File(ubicacion);
		
		try (FileWriter writer = new FileWriter(archivof)) {
            // Escribir datos en el archivo
			Administrador admin= galeria.getAdmin();
	
			for(Usuario u: admin.getCompradores()) {
				Comprador comprador = (Comprador) u;
				writer.write("Comprador,"+ u.getLogin()+ "," + u.getPassword() + "," + comprador.getNombre() +
							"," + comprador.getCorreo() + "," + comprador.getTelefono() +"," + comprador.getComprasTotales() +"," + comprador.getComprasMaximas()+ "\n");
			}
			for(Usuario u: admin.getPropietarios()) {
				Propietario propietario= (Propietario) u;
				writer.write("Propietario,"+ u.getLogin()+ "," + u.getPassword() + "," + propietario.getNombre() +
					"," + propietario.getCorreo() + "," + propietario.getTelefono() + "\n");
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}	

	private void generarArchivoGaleria() {
		String ubicacion = encontrarRuta() + "\\Datos\\"+ this.archivoGaleria;
		File archivof = new File(ubicacion);
		
		try (FileWriter writer = new FileWriter(archivof)) {
            // Escribir datos en el archivo
			if (!galeria.getNombre().equals("Galeria")) {
				writer.write("nombreGaleria,"+ galeria.getNombre()+"\n");
			}
			//Añadir Administrador 
				writer.write("Administrador,"+ galeria.getAdmin().getLogin()+ "," + galeria.getAdmin().getPassword() + "\n");
			//Añadir Empleado
				for(Empleado e:galeria.getEmpleados()) {
					String tipoEmpleado="None";
					if(!e.getRol().equals("None")) {
						tipoEmpleado= e.getRol();
					}
					writer.write("Empleado,"+ e.getNombreUsuario()+ "," + e.getContraseña() + "," + tipoEmpleado +"\n");
				}
        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
	
	public String encontrarRuta() {
		String ruta = System.getProperty("user.dir");
		return ruta;
	}
}
