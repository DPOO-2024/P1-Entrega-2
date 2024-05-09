package Usuarios;

import java.util.ArrayList;
import java.util.List;

import Exceptions.MensajedeErrorException;
import Exceptions.PagoRechazado;
import Modelo.Empleado;
import Modelo.Pago;
import Piezas.Pieza;

public class Cajero extends Empleado{
	public final static String CAJERO= "Cajero";
	
	private  List<Pago> pagos;
	
	public Cajero(String nombreUsuario, String contraseña, String rol) throws MensajedeErrorException {
		super(nombreUsuario, contraseña, rol);
		this.pagos = new ArrayList<Pago>();
	}



	
	// como se cuando se registra un pago correctamente ???
	public boolean generarPagoCajero(int precio,Pieza pieza,String formaPago, Comprador comprador) throws PagoRechazado {
		boolean respuesta =false;
		Pago pago = null;
		if(formaPago.equalsIgnoreCase("Tarjeta") | formaPago.equalsIgnoreCase("Efectivo") | formaPago.equalsIgnoreCase("Transferencia")) {
		pago = Pago.generarPago(precio,pieza,formaPago,comprador);}
		
		else {
		return respuesta;
		}
		if (pago!=null) {
		registrarPago(pago);
		respuesta =true;}
		return respuesta;
		
	}

	
	
	public  void registrarPago(Pago pago) {
		pagos.add(pago);
	}



	public List<Pago> getPagos() {
		return pagos;
	}



	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}



	public static String getCajero() {
		return CAJERO;
	}



	
	
}