package Usuarios;

import java.util.List;

import Modelo.Empleado;
import Modelo.Pago;

public class Cajero extends Empleado{
	
	public final static String CAJERO= "Cajero";
	
	private List<Pago> pagos;
	
	public static boolean generarPago(int precio, String formaPago) {
		return false;
		
	}

	
	
	public void registrarPago(Pago pago) {
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