package Usuarios;

import java.util.ArrayList;
import java.util.List;

import Exceptions.PrecioBajoException;
import Modelo.Administrador;
import Modelo.Galeria;
import Modelo.Pago;
import Modelo.Subasta;
import Piezas.Pieza;

public class Comprador extends Usuario{
	
	public static final String COMPRADOR ="Comprador";
	
	private String nombre;
	
	private String correo;
	
	private int telefono;
	
	private int comprasTotales;
	
	private int comprasMaximas;
	
	private ArrayList<Pieza> historialCompras;
	
	public Comprador(String login, String password,String nombre, String correo,int telefono,int comprasTotales, int comprasMaximas) {
		super(login, password, "Comprador");
		this.telefono=telefono;
		this.nombre = nombre;
		this.correo=correo;
		this.comprasTotales=comprasTotales;
		this.comprasMaximas=comprasMaximas;
		this.historialCompras=new ArrayList<Pieza>();
	}
	public void agregarCompra(int precio, Pieza p)
	{
		this.comprasTotales+=precio;
		this.historialCompras.add(p);
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
	
	public void setHistorialCompras(ArrayList<Pieza> piezas) {
		this.historialCompras = piezas;
	}

	public ArrayList<Pieza> getHistorialCompras() {
		return this.historialCompras;
	}

	public void setComprasMaximas(int comprasMaximas) {
		this.comprasMaximas += comprasMaximas;
	}

	public static String getComprador() {
		return COMPRADOR;
	}
	


}