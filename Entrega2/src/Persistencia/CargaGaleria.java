package Persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Modelo.Galeria;

public class CargaGaleria {
	
	private String archivoGaleria;
	private String archivoUsuarios;
	private String archivoPiezas;
	private String archivoCompras;
	private Galeria galeria;
	
	
	public CargaGaleria(String archivoGaleria, String archivoUsuarios, String archivoPiezas, String archivoCompras) {
		this.archivoGaleria = archivoGaleria;
		this.archivoUsuarios = archivoUsuarios;
		this.archivoPiezas = archivoPiezas;
		this.archivoCompras = archivoCompras;
		this.galeria=new Galeria();
	}
	
	public void cargarArchivos() {
		try {
			cargarBasico();
			cargarUsuarios();
			cargarPiezas();
			cargarCompras();
		}catch(Exception e) {
			throw (e);
		}
	}
	
	//Falta ver bien que toca poner
	private void cargarCompras() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.archivoPiezas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] l = linea.split(",");
                if (l.length>3) {
                	//Agregar compra
                }
                else {
                	throw new IllegalArgumentException("Formato incorrecto en la línea: " + linea);
                } 	
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	private void cargarPiezas() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.archivoPiezas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] l = linea.split(",");
                if (l.length>3) {
                	//Agregar Pieza
                }
                else {
                	throw new IllegalArgumentException("Formato incorrecto en la línea: " + linea);
                } 	
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	//Carga los Usuarios de la Galeria
	private void cargarUsuarios() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.archivoUsuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] l = linea.split(",");
                if (l[0].equals("Comprador") || l[0].equals("Propietario")) {
                	//Agregar Usuario
                }
                else {
                	throw new IllegalArgumentException("Formato incorrecto en la línea: " + linea);
                } 	
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	//Inicializa la galeria 
	private void cargarBasico() {		
		try (BufferedReader br = new BufferedReader(new FileReader(this.archivoGaleria))) {
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] l = linea.split(",");
                if (i==0 && l[0].equals("nombreGaleria")) {
                	String nombre=l[1];
                	galeria.setNombre(nombre);
                }
                else if (l[0].equals("Administrador")) {
                	//generar administrador y asignar
                }
                else if (l[0].equals("Empleado")) {
                	//generar empleado y asignar
                }
                else {
                	throw new IllegalArgumentException("Formato incorrecto en la línea: " + linea);
                }
                i++;
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
	}
	
	
}
