package Usuarios;

import java.util.HashMap;
import java.util.Map;

import Modelo.Administrador;
import Modelo.Empleado;



public class Operador extends Empleado{
	
	public static final String OPERADOR = "Operador";
	
	private static  Map<String,Integer> ofertas=new HashMap<>();

	
	public static void guardarOferta(int oferta, String nomComprador) {
		
		ofertas.put(nomComprador, oferta);
		
	}
	
	public void verificarOferta(int oferta, String nomComprador) {
		
		boolean aceptado = Administrador.verificarOferta( oferta,  nomComprador);
		if (aceptado) {
			guardarOferta( oferta,  nomComprador);
		}
	}

	public Map<String, Integer> generarReporte() {
		return ofertas;
	}

	public void setOfertas(Map<String, Integer> ofertas) {
		this.ofertas = ofertas;
	}

	public static String getOperador() {
		return OPERADOR;
	}
	
	
	
}