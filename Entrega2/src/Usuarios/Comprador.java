package Usuarios;

import java.util.List;

import Exceptions.PrecioBajoException;
import Modelo.Subasta;
import Piezas.Pieza;

public class Comprador extends Usuario{
	
	public static final String COMPRADOR ="Comprador";
	
	private String nombre;
	
	private String correo;
	
	private int telefono;
	
	private int comprasTotales;
	
	private int comprasMaximas;
	
	public Comprador(String login, String password,String nombre, String correo,int telefono,int comprasTotales, int comprasMaximas) {
		super(login, password, "Comprador");
		this.telefono=telefono;
		this.nombre = nombre;
		this.correo=correo;
		this.comprasTotales=comprasTotales;
		this.comprasMaximas=comprasMaximas;
	}
	
	public void comprarPieza(Pieza pieza ,String formaPago) { 
		boolean aceptado =false; 
		if (pieza.getValorFijo() != 0) { 
			int precio = pieza.getValorFijo();
			boolean verificado = Administrador.verificarComprador(comprasMaximas); 
			if (verificado) { 
				aceptado = Cajero.generarPago(precio, formaPago); } 
			if (aceptado) { 
				comprasTotales += precio; } }
		else { throw new IllegalStateException("No se puede vender esta pieza."); } }

	
	
	
	public void hacerOferta(Pieza pieza, int precio ) throws PrecioBajoException {
		int valor = pieza.getValorInicial();
		if (precio>valor) {
			Operador.guardarOferta(precio,this.nombre);
		}
		else {
			throw new PrecioBajoException(pieza);
		}
	}
	
	public void participarSubasta(Subasta subasta , Pieza pieza, int precio) {
		List<Pieza> piezas = subasta.ofrecerPiezas();
		if (piezas.contains(pieza)) {
		hacerOferta(pieza,precio);}
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getComprasTotales() {
		return comprasTotales;
	}

	public void setComprasTotales(int comprasTotales) {
		this.comprasTotales = comprasTotales;
	}

	public int getComprasMaximas() {
		return comprasMaximas;
	}

	public void setComprasMaximas(int comprasMaximas) {
		this.comprasMaximas = comprasMaximas;
	}

	public static String getComprador() {
		return COMPRADOR;
	}
	


}