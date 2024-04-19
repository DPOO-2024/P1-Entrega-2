package Persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
	private String archivoCompras;
	private Galeria galeria;
	
	
	public CargaGaleria(String archivoGaleria, String archivoUsuarios, String archivoPiezas, String archivoCompras, Galeria galeria) {
		this.archivoGaleria = archivoGaleria;
		this.archivoUsuarios = archivoUsuarios;
		this.archivoPiezas = archivoPiezas;
		this.archivoCompras = archivoCompras;
		this.galeria=galeria;
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

	private void cargarPiezas() throws MesajedeErrorException {
		try (BufferedReader br = new BufferedReader(new FileReader(this.archivoPiezas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] l = linea.split(",");
                if (l.length>3) {
                	//Agregar Pieza
                	Propietario p = this.galeria.getAdmin().encontrarPropietario(l[1]);
                	Usuario u = (Usuario)p;
                	Pieza pieza = cargaPiezas.cargaPieza(l, u);
                	
                	this.galeria.getInventario().agregarPieza(pieza);
                	p.ingresarPieza(pieza);
                	
                	if (!l[2].equals("true")) {
                		this.galeria.getInventario().moverPieza(pieza);
                		p.venderPieza(pieza);
                	}
                	
                }else {
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
                	if (l[0].equals("Comprador")) {
                		int telefono=Integer.valueOf(l[5]);
                		int compras = Integer.valueOf(l[6]);
                		int valMax = Integer.valueOf(l[7]);
                		Comprador c = new Comprador(l[1], l[2], l[3], l[4], telefono, compras, valMax );
                		this.galeria.getAdmin().getCompradores().add(c);
                	}else {
                		int telefono=Integer.valueOf(l[5]);
                		Propietario p = new Propietario(l[1], l[2], l[3], l[4], telefono );
                		this.galeria.getAdmin().getPropietarios().add(p);
                	}
                }
                else {
                	throw new IllegalArgumentException("Formato incorrecto en la línea: " + linea);
                } 	
            }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	//Inicializa la galeria, asignando empleados y el administrador
	private void cargarBasico() throws MesajedeErrorException {		
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
                	Administrador admin = new Administrador(l[1], l[2], this.galeria.getInventario());
                	this.galeria.setAdmin(admin);
                }
                else if (l[0].equals("Empleado")) {
                	Empleado e = null;
                	Operador o = null;
                	Cajero c = null;
                	
                	if (l[3].equals("None")) {
                		e = new Empleado(l[1], l[2], "None");
                		this.galeria.getEmpleados().add(e);
                	}else if (l[3].equals("Operador")) {
                		o = new Operador(l[1], l[2], "Operador");
                		this.galeria.setOperador(o);
                		this.galeria.getEmpleados().add(o);
                	}else if (l[3].equals("Cajero")) {
                		c = new Cajero(l[1], l[2], "Cajero");
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
                
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
	}
	
	
}
