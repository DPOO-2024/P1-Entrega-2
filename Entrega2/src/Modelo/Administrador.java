package Modelo;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Exceptions.LoginDuplicadoException;
import Exceptions.MensajedeErrorException;
import Exceptions.PiezaRepetidaException;
import Piezas.Escultura;
import Piezas.Fotografia;
import Piezas.Impresion;
import Piezas.Otro;
import Piezas.Pieza;
import Piezas.Pintura;
import Piezas.Video;
import Usuarios.Cajero;
import Usuarios.Comprador;
import Usuarios.Operador;
import Usuarios.Propietario;
import Usuarios.Usuario;

@SuppressWarnings("resource")
public class Administrador {
	
	private String login;
	private String password;
	private Inventario inventario;
	private ArrayList<Comprador> compradores;
	private ArrayList<Propietario> propietarios;
	private ArrayList<String> loginsReservadosCompradores;
	private ArrayList<String> loginsReservadosPropietarios;
	
	
	public Administrador(String login, String contrasena, Inventario inventario) {
		this.login = login;
		this.password = contrasena;
		this.inventario=inventario;
		this.compradores = new ArrayList<Comprador>();
		this.propietarios = new ArrayList<Propietario>();
		this.loginsReservadosCompradores = new ArrayList<String>();
		this.loginsReservadosPropietarios = new ArrayList<String>();
	}

	
	//Falta Crear Subasta

	//M
	public boolean confirmarVenta(Pieza pieza, Comprador comprador) throws MensajedeErrorException {
				int valorMaximo = comprador.getComprasMaximas();
				int valorActual = comprador.getComprasTotales();
				if (valorMaximo< valorActual+pieza.getValorFijo()) {
					throw new MensajedeErrorException("Comprador excede compras maximas");
				}
				else {
						return true;
					

					}
				}
	
		
	

	public boolean verificarOferta(Oferta oferta) throws MensajedeErrorException {
		boolean rta = false;
		int valorMaximo = oferta.getComprador().getComprasMaximas();
		int valorActual = oferta.getComprador().getComprasTotales();
		if (valorMaximo< valorActual+oferta.getValorOferta()) {
			throw new MensajedeErrorException("Comprador excede compras maximas");
		}
	
		else {
			rta = true;
			
		}
		
		return rta;
	}
	
	//Verifica si la informacion ingresada es de un comprador
	public Comprador verificarComprador(String login2, String password2) throws MensajedeErrorException {
		Comprador comprador=null;
		for(Comprador c :compradores) {
			if(c.getLogin().equals(login2)&&c.getPassword().equals(password2)) {
				comprador = c;
			}
		}
		if (comprador == null) {
			throw new MensajedeErrorException("No estas registrado como comprador");
		}
		return comprador;
	}
	
	//Verifica si la informacion ingresada es de un propietario
	public Propietario verificarPropietario(String login2, String password2) throws MensajedeErrorException {
		Propietario propietario =null;
		for(Propietario p : propietarios) {
			if (p.getLogin().equals(login2)&&p.getPassword().equals(password2)) {
				propietario = p;
			}
		}
		if (propietario == null) {
			throw new MensajedeErrorException("No estas registrado como propietario");
		}
		return propietario;
	}
	
	public Propietario encontrarPropietario(String login) throws MensajedeErrorException {
		Propietario propietario =null;
		for(Propietario p : propietarios) {
			if (p.getLogin().equals(login)) {
				propietario = p;
			}
		}
		if (propietario == null) {
			throw new MensajedeErrorException("No existe ese propietario");
		}
		return propietario;
	}
	

	// le asigna uno de los operadores de la lista a una subasta o cambia un empleado que este en none
	public Operador asignarOperador(List<Empleado> empleados) throws Exception {
		try {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Si desea asignar un operador ya registrado ingrese 1 ");
			System.out.println("Si desea reasignar a un empleado como operador ingrese 2 ");
			String opci = scanner.nextLine();
			Operador operadorAsignado = null;
			boolean escogido = false;
			int i =0;
			if (opci.equals("1")) {

				while (!escogido && i < empleados.size()) {
					Empleado empleado = empleados.get(i);
					if (empleado.getRol().equals("Operador")) {
						Operador ope = (Operador) empleado;
						if (!ope.isAsignado()) {
							operadorAsignado=ope;
							escogido=true;
							ope.setAsignado(true);
						}
						else {
							ope = null;
						}
					}
					i++;
				}
			}

			else if (opci.equals("2")) {

				while (!escogido && i < empleados.size()) {
					Empleado empleado = empleados.get(i);
					if (empleado.getRol().equals("None")) {
						empleado.setRol("Operador");
						escogido = true;
						operadorAsignado=new Operador(empleado.getNombreUsuario(),empleado.getContraseña(),empleado.getRol());
						operadorAsignado.setAsignado(true);

					}
					i++;
				}


			}
			else {
				throw new MensajedeErrorException("No es una respuesta valida");
			}
			if (!escogido) {
				throw new MensajedeErrorException("No se encontro empleado disponible para asignar");
			}
			return operadorAsignado;    
		}
		catch(MensajedeErrorException e ) {
			throw e;
		}
		catch(Exception e ) {
			throw e;
		}
	}
	
	
	//Setters y Getters
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String contrasena) {
		this.password = contrasena;
	}

	
	public  ArrayList<Comprador> getCompradores() {
		return compradores;
	}

	public void setCompradores(ArrayList<Comprador> compradores) {
		this.compradores = compradores;
	}

	public ArrayList<Propietario> getPropietarios() {
		return propietarios;
	}

	public void setPropietarios(ArrayList<Propietario> propietarios) {
		this.propietarios = propietarios;
	}
	


	//Agregar piezas
	
	public void agregarPieza(Pieza pieza, Propietario p) throws MensajedeErrorException {
		try {
			this.inventario.agregarPieza(pieza);
			p.ingresarPieza(pieza);
		} catch (PiezaRepetidaException e) {
			throw new MensajedeErrorException("Ya existe una pieza con ese nombre");
		}
	}
	
	public void verificarLogin(String login,String rol) throws LoginDuplicadoException {
		
		if (rol.equalsIgnoreCase("Comprador") && !this.loginsReservadosCompradores.contains(login)) {
			loginsReservadosCompradores.add(this.login);
		}
		else if (rol.equalsIgnoreCase("Propietario") && !this.loginsReservadosPropietarios.contains(login)) {
			loginsReservadosPropietarios.add(this.login);
		}
	
		else { throw new LoginDuplicadoException(login);
		}
	}
	
	public void agregarUsuario(ArrayList<String> info, String rol) throws MensajedeErrorException {
		try {
			int telefono=Integer.parseInt(info.get(1));
			
			if (rol.equalsIgnoreCase("Comprador")) {
				Comprador comprador = new Comprador(login, info.get(0),info.get(2), info.get(3),telefono,0,5000);
				
				this.compradores.add(comprador);
			}


			else if (rol.equalsIgnoreCase("propietario")){
				Propietario propietario = new Propietario(login,info.get(0),info.get(2), info.get(3),telefono);
				this.propietarios.add(propietario);
			}
		}catch(Exception e) {
			throw new MensajedeErrorException("El usuario no se pudo agregar correctamente");
		}
		
	}
	
	public void aumentarCupoComprador(String loginComprador, int aumento) {
		for(Comprador comprador:compradores) {
			if(comprador.getLogin().equals(loginComprador)) {
				comprador.setComprasMaximas(aumento);
			}
		}
		
	}










	
}
