package Modelo;


import Exceptions.MensajedeErrorException;
import Usuarios.Comprador;

public class Oferta {
	
	
	private int valorOferta;
	
	private Comprador comprador;
	
	private String formaPago;
	
	public Oferta(int valorOferta, Comprador comprador, String formaPago) {
		this.comprador=comprador;
		this.valorOferta=valorOferta;
		this.setFormaPago(formaPago);
		
	}

	public int getValorOferta() {
		return valorOferta;
	}

	public void setValorOferta(int valorOferta) {
		this.valorOferta = valorOferta;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	
	public static Oferta generarOferta(int valorOferta, Comprador comprador, String formaPago) throws MensajedeErrorException {
		if(formaPago.equalsIgnoreCase("Tarjeta") | formaPago.equalsIgnoreCase("Efectivo") | formaPago.equalsIgnoreCase("Transferencia")) {
		Oferta oferta = new Oferta(valorOferta,comprador, formaPago);
		return oferta;}
		else {
			throw new MensajedeErrorException("No es una forma de pago valida");
		}
		
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	
	
	
	
	
	

}

