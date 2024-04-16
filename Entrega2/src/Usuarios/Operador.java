package Usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exceptions.MesajedeErrorException;
import Modelo.Administrador;
import Modelo.Empleado;
import Modelo.Oferta;
import Modelo.Rol;
import Modelo.Subasta;
import Piezas.Pieza;



public class Operador extends Empleado{
	
	public static final String OPERADOR = "Operador";
	
	private static  Map<Pieza, List<Oferta>> ofertas;
	
	public Operador(String nombreUsuario, String contraseña, Rol rol,Map<Pieza, List<Oferta>> ofertas) {
		super(nombreUsuario, contraseña, rol);
		Operador.ofertas = new HashMap<>();
	}

	
	
	

	
	public static void crearOferta(int valoferta, Comprador comprador ,Pieza pieza, String formaPago) throws MesajedeErrorException {
		Oferta oferta = Oferta.generarOferta(valoferta, comprador, formaPago);
		verificarOferta(oferta,pieza);
		
	
		
		
	}
	
	public static void verificarOferta(Oferta oferta, Pieza pieza) throws MesajedeErrorException {
		
		boolean aceptado = Administrador.verificarOferta( oferta);
		if (aceptado) {
			guardarOferta( oferta, pieza);
		}
	}
	
	public static void guardarOferta(Oferta oferta, Pieza pieza) {
		if (ofertas.containsKey(pieza)) {
			List<Oferta> ofertasPieza = ofertas.get(pieza);
			ofertasPieza.add(oferta);
		}
		else {
			List<Oferta> nuevasOfertas = new ArrayList<>();
		    nuevasOfertas.add(oferta);
		    ofertas.put(pieza, nuevasOfertas);
		    
			
		}
	}

	public static Map<Pieza, List<Oferta>> generarReporte() {
		return ofertas;
	}

	public void setOfertas(Map<Pieza, List<Oferta>> ofertas) {
		Operador.ofertas = ofertas;
	}

	public static String getOperador() {
		return OPERADOR;
	}
	
	
	
}