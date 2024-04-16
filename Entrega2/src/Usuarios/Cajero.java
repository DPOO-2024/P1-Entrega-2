package Usuarios;

import java.util.ArrayList;
import java.util.List;

import Modelo.Empleado;
import Modelo.Pago;
import Modelo.Rol;
import Piezas.Pieza;

public class Cajero extends Empleado{
	public final static String CAJERO= "Cajero";
	
	private static List<Pago> pagos;
	
	public Cajero(String nombreUsuario, String contraseña, Rol rol,List<Pago> pagos) {
		super(nombreUsuario, contraseña, rol);
		Cajero.pagos = new ArrayList<Pago>();
	}



	
	// como se cuando se registra un pago correctamente ???
	public static boolean generarPagoCajero(int precio,Pieza pieza,String formaPago, Comprador comprador) {
		Pago pago = Pago.generarPago(precio,pieza,formaPago,comprador);
		registrarPago(pago);
		boolean respuesta =true;
		return respuesta;
		
	}

	
	
	public static void registrarPago(Pago pago) {
		pagos.add(pago);
	}



	public List<Pago> getPagos() {
		return pagos;
	}



	public void setPagos(List<Pago> pagos) {
		Cajero.pagos = pagos;
	}



	public static String getCajero() {
		return CAJERO;
	}



	
	
}