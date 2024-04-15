package Modelo;

import Piezas.Pieza;
import Usuarios.Comprador;

public class Pago {
	/* registra el pago realizado al vender una pieza, 
	 considerando el valor de la pieza y la persona que lo va a comprar*/
	
	private int monto;
	
	private Pieza pieza;
	
	private Comprador comprador;
	
	public Pago(int monto, Pieza pieza, Comprador comprador) {
        this.monto = monto;
        this.pieza = pieza;
        this.comprador = comprador;
	}
	
	
	
	
	
	

}