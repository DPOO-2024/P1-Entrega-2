package Persistencia;

import java.io.FileWriter;
import java.io.IOException;

import Modelo.Administrador;
import Modelo.Empleado;
import Modelo.Galeria;
import Modelo.Pago;
import Usuarios.Cajero;
import Usuarios.Comprador;
import Usuarios.Propietario;
import Usuarios.Usuario;

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
		try (FileWriter writer = new FileWriter(this.archivoGaleria)) {
            // Escribir datos en el archivo
			Cajero cajero= galeria.getCajero();
			
			for(Pago p: cajero.getPagos()) {
				//Poner pagos
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void generarArchivoPiezas() {
		// TODO Auto-generated method stub
		
	}

	private void generarArchivoUsuarios() {
		// TODO Auto-generated method stub
		try (FileWriter writer = new FileWriter(this.archivoGaleria)) {
            // Escribir datos en el archivo
			Administrador admin= galeria.getAdministrador();
	
			for(Usuario u: admin.getUsuarios()) {
				
				if(u.getRol().equals("Comprador")) {
					Comprador comprador = (Comprador) u;
					writer.write("Comprador,"+ u.getLogin()+ "," + u.getPassword() + "," + comprador.getNombre() +
							"," + comprador.getCorreo() + "," + comprador.getTelefono() +"," + comprador.getComprasMaximas()+ "\n");
				}
				else if(u.getRol().equals("Propietario")) {
					Propietario propietario= (Propietario) u;
					writer.write("Propietario,"+ u.getLogin()+ "," + u.getPassword() + "," + propietario.getNombre() +
							"," + propietario.getCorreo() + "," + propietario.getTelefono() + "\n");
				}	
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}	

	private void generarArchivoGaleria() {
		try (FileWriter writer = new FileWriter(this.archivoGaleria)) {
            // Escribir datos en el archivo
			if (!galeria.getNombre().equals("Galeria")) {
				writer.write("nombreGaleria,"+ galeria.getNombre()+"\n");
			}
			//Añadir Administrador 
				writer.write("Administrador,"+ galeria.getAdministrador().getLogin()+ "," + galeria.getAdministrador().getPassword() + "\n");
			//Añadir Empleado
				for(Empleado e:galeria.getEmpleados()) {
					String tipoEmpleado="None";
					if(!e.getRol.equals("None")) {
						tipoEmpleado= e.getRol();
					}
					writer.write("Empleado,"+ e.getLogin()+ "," + e.getPassword() + "," + tipoEmpleado +"\n");
				}
        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
}
