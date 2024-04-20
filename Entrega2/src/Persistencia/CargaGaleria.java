package Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

import Exceptions.MesajedeErrorException;
import Modelo.Galeria;
import Piezas.Pieza;
import Usuarios.Cajero;
import Usuarios.Comprador;
import Usuarios.Operador;
import Usuarios.Propietario;
import Usuarios.Usuario;
import Modelo.Administrador;
import Modelo.Empleado;

public class CargaGaleria {
	
	private String archivoGaleria;
	private String archivoUsuarios;
	private String archivoPiezas;
	private Galeria galeria;
	
	
	public CargaGaleria(String archivoGaleria, String archivoUsuarios, String archivoPiezas, Galeria galeria) {
		this.archivoGaleria = archivoGaleria;
		this.archivoUsuarios = archivoUsuarios;
		this.archivoPiezas = archivoPiezas;
		this.galeria=galeria;
	}
	
	public void cargarArchivos() throws Exception {
		try {
			cargarBasico();
			cargarUsuarios();
			cargarPiezas();
		}catch(Exception e) {
			throw e;
		}
	}
	

	private void cargarPiezas() throws Exception {
		String ubicacion = encontrarRuta() + "\\Datos\\"+ this.archivoPiezas;
		File archivof = new File(ubicacion);
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivof))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] l = linea.split(",");
                if (l.length>3) {
                	//Agregar Pieza
                	Propietario p = this.galeria.getAdmin().encontrarPropietario(l[1].trim());
                	Usuario u = (Usuario)p;
                	Pieza pieza = cargaPiezas.cargaPieza(l, u);
                	
                	this.galeria.getInventario().agregarPieza(pieza);
                	p.ingresarPieza(pieza);
                	
                	if (!l[2].trim().equalsIgnoreCase("true")) {
                		this.galeria.getInventario().moverPieza(pieza);
                		p.venderPieza(pieza);
                	}
                	
                }else {
                	throw new IllegalArgumentException("Formato incorrecto en la línea: " + linea);
                } 	
                
            }       
        } catch (FileNotFoundException e) {
        	throw new MesajedeErrorException("No se encontro el archivo: " + this.archivoGaleria);
        } catch(Exception e) {
        	throw e;
        }
		
	}
	
	//Carga los Usuarios de la Galeria
	private void cargarUsuarios() throws Exception {
		String ubicacion = encontrarRuta() + "\\Datos\\"+ this.archivoUsuarios;
		File archivof = new File(ubicacion);
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivof))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] l = linea.split(",");
                if (l[0].equals("Comprador") || l[0].equals("Propietario")) {
                	if (l[0].equals("Comprador")) {
                		int telefono=Integer.valueOf(l[5].trim());
                		int compras = Integer.valueOf(l[6].trim());
                		int valMax = Integer.valueOf(l[7].trim());
                		Comprador c = new Comprador(l[1].trim(), l[2].trim(), l[3].trim(), l[4].trim(), telefono, compras, valMax );
                		this.galeria.getAdmin().getCompradores().add(c);
                	}else {
                		int telefono=Integer.valueOf(l[5].trim());
                		Propietario p = new Propietario(l[1].trim(), l[2].trim(), l[3].trim(), l[4].trim(), telefono );
                		this.galeria.getAdmin().getPropietarios().add(p);
                	}
                }
                else {
                	throw new IllegalArgumentException("Formato incorrecto en la línea: " + linea);
                } 	
            }
                
        } catch (FileNotFoundException e) {
        	throw new MesajedeErrorException("No se encontro el archivo: " + this.archivoGaleria);
        } catch(Exception e) {
        	throw e;
        }
		
	}
	
	//Inicializa la galeria, asignando empleados y el administrador
	private void cargarBasico() throws Exception {	
		String ubicacion = encontrarRuta() + "\\Datos\\"+ this.archivoGaleria;
		File archivof = new File(ubicacion);
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivof))) {
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] l = linea.split(",");
                if (i==0 && l[0].equals("nombreGaleria")) {
                	String nombre=l[1];
                	galeria.setNombre(nombre);
                }
                else if (l[0].equals("Administrador")) {
                	Administrador admin = new Administrador(l[1].trim(), l[2].trim(), this.galeria.getInventario());
                	this.galeria.setAdmin(admin);
                }
                else if (l[0].equals("Empleado")) {
                	Empleado e = null;
                	Operador o = null;
                	Cajero c = null;
                	
                	if (l[3].equals("Empleado")) {
                		e = new Empleado(l[1].trim(), l[2].trim(), "Empleado");
                		this.galeria.getEmpleados().add(e);
                	}else if (l[3].equals("Operador")) {
                		o = new Operador(l[1].trim(), l[2].trim(), "Operador");
                		this.galeria.setOperador(o);
                		this.galeria.getEmpleados().add(o);
                	}else if (l[3].equals("Cajero")) {
                		c = new Cajero(l[1].trim(), l[2].trim(), "Cajero");
                		this.galeria.setCajero(c);
                		this.galeria.getEmpleados().add(c);
                	}else {
                		throw new MesajedeErrorException("Ese rol de empleado no existe");
                	}
                }
                else {
                	throw new IllegalArgumentException("Formato incorrecto en la línea: " + linea);
                }
                i++;
            }
            if (galeria.getAdmin().equals(null)) {
            	throw new MesajedeErrorException("La galeria que se intenta cargar no cuenta con administrador y asi no puede funcionar");
            }
                
        } catch (FileNotFoundException e) {
        	throw new MesajedeErrorException("No se encontro el archivo: " + this.archivoGaleria);
        } catch(Exception e) {
        	throw e;
        }
	}

	public String encontrarRuta() {
		String ruta = System.getProperty("user.dir");
		return ruta;
	}
}
