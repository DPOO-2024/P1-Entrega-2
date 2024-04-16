package Modelo;

import Piezas.Pieza;
import Usuarios.Comprador;

public class Pago {
	/* registra el pago realizado al vender una pieza, 
	 considerando el valor de la pieza y la persona que lo va a comprar*/
	//M
	private int monto;
	
	private Pieza pieza;
	
	private Comprador comprador;
	
	private String formaPago;
	
	public Pago(int monto, Pieza pieza, String formaPago, Comprador comprador) {
        this.monto = monto;
        this.pieza = pieza;
        this.comprador = comprador;
        this.formaPago = formaPago;
	}
	
	
	public int getMonto() {
		return monto;
	}


	public void setMonto(int monto) {
		this.monto = monto;
	}


	public Pieza getPieza() {
		return pieza;
	}


	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}


	public Comprador getComprador() {
		return comprador;
	}


	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}


	public String getFormaPago() {
		return formaPago;
	}


	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}


	public static Pago generarPago(int monto, Pieza pieza,String formaPago ,Comprador comprador) {
		
		Pago pago = new Pago(monto, pieza, formaPago, comprador);
		return pago;
		
	}
	
	
	

}