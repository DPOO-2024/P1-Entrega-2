package Usuarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exceptions.MensajedeErrorException;
import Modelo.Administrador;
import Modelo.Empleado;
import Modelo.Oferta;
import Piezas.Pieza;



public class Operador extends Empleado{
	
	public static final String OPERADOR = "Operador";
	
	private boolean asignado;
	
	private   Map<Pieza, List<Oferta>> ofertas;
	
	public Operador(String nombreUsuario, String contraseña, String rol) throws MensajedeErrorException {
		super(nombreUsuario, contraseña, rol);
		this.ofertas = new HashMap<>();
		this.asignado = false;
	}

	
	public boolean isAsignado() {
		return asignado;
	}


	public void setAsignado(boolean asignado) {
		this.asignado = asignado;
	}


	public Map<Pieza, List<Oferta>> getOfertas() {
		return ofertas;
	}


	public int mayorOferta(Pieza pieza) {
		int max;
		List<Integer> valores = listaValoresOferta(pieza);
		if (!valores.isEmpty()) {
		 max = Collections.max(valores);
		}
		else {
		 max = pieza.getValorInicial();
		}
		
		return max;
		
	}
	public List<Integer> listaValoresOferta(Pieza pieza) {
		List<Oferta> valorOfertas = ofertas.get(pieza);
		List<Integer> valores =new ArrayList<>();
		if (valorOfertas != null) {
		for(Oferta oferta : valorOfertas) {
			valores.add(oferta.getValorOferta());
		}
		}
		
		
		return valores;
		
	}
	

	
	public void crearOferta(int valoferta, Comprador comprador ,Pieza pieza, String formaPago, Administrador admin) throws MensajedeErrorException {
		Oferta oferta = Oferta.generarOferta(valoferta, comprador, formaPago);
		this.verificarOferta(oferta,pieza,admin);
		
	
		
		
	}
	
	public  void verificarOferta(Oferta oferta, Pieza pieza, Administrador admin) throws MensajedeErrorException {
		boolean aceptado = admin.verificarOferta( oferta);
		if (aceptado) {
	
			this.guardarOferta( oferta, pieza);}
			
		}
		
	
	
	public  void guardarOferta(Oferta oferta, Pieza pieza) {
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

	public Map<Pieza, List<Oferta>> generarReporte() {
		return ofertas;
	}

	public void setOfertas(Map<Pieza, List<Oferta>> ofertas) {
		this.ofertas = ofertas;
	}

	public static String getOperador() {
		return OPERADOR;
	}
	
	
	
}