package Modelo;

import java.util.ArrayList;

import Exceptions.MesajedeErrorException;
import Piezas.Pieza;
import Usuarios.Cajero;
import Usuarios.Comprador;
import Usuarios.Propietario;
import Usuarios.Usuario;

public class Administrador {
	
	private String login;
	private String password;
	private static ArrayList<Comprador> compradores;
	private ArrayList<Propietario> propietarios;
	
	public Administrador(String login, String contrasena, ArrayList<Comprador> compradores,ArrayList<Propietario> propietarios ) {
		this.login = login;
		this.password = contrasena;
		this.compradores = new ArrayList<Comprador>();
		this.propietarios = new ArrayList<Propietario>();
	}

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

	
	public static ArrayList<Comprador> getCompradores() {
		return compradores;
	}

	public void setCompradores(ArrayList<Comprador> compradores) {
		Administrador.compradores = compradores;
	}

	public ArrayList<Propietario> getPropietarios() {
		return propietarios;
	}

	public void setPropietarios(ArrayList<Propietario> propietarios) {
		this.propietarios = propietarios;
	}


	//M
	public boolean confirmarVenta(Pieza pieza, String nombreComprador, String formaPago) throws MesajedeErrorException {
		boolean existeComprador = false;
		for(Comprador comprador : compradores) {
			if (comprador.getNombre().equals(nombreComprador)) {
				existeComprador=true;
				int valorMaximo = comprador.getComprasMaximas();
				int valorActual = comprador.getComprasTotales();
				if (valorMaximo< valorActual+pieza.getValorFijo()) {
					throw new MesajedeErrorException("Comprador excede compras maximas");
				}
				else {
					if (Cajero.generarPagoCajero(pieza.getValorFijo(),pieza,formaPago,comprador)) {
						return true;
					}

					}
				}
			}
		if(!existeComprador) {

				throw new MesajedeErrorException("El comprador no esta registrado correctamente");
			
		}
			
		
		return false;
		
		
	}

	public static boolean verificarOferta(Oferta oferta) throws MesajedeErrorException {
		boolean rta = false;
		int valorMaximo = oferta.getComprador().getComprasMaximas();
		int valorActual = oferta.getComprador().getComprasMaximas();
		if (valorMaximo< valorActual+oferta.getValorOferta()) {
			throw new MesajedeErrorException("Comprador excede compras maximas");
		}
		else {
			rta = true;
			
		}
		
		return rta;
	}
	
	
	
}
