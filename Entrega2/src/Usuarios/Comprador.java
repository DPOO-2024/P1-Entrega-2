package Usuarios;

import java.util.List;

import Exceptions.MesajedeErrorException;
import Exceptions.PrecioBajoException;
import Modelo.Administrador;
import Modelo.Galeria;
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
	public void agregarCompra(int precio)
	{
		this.comprasTotales+=precio;
	}
	
	
	
	//comprar pero version subasta
	public void hacerOferta(Pieza pieza, int precio,Comprador comprador , String formaPago) throws PrecioBajoException, MesajedeErrorException {
		int valor = pieza.getValorInicial();
		if (precio>valor) {
			Operador.crearOferta(precio,comprador,pieza,formaPago);
		}
		else {
			throw new PrecioBajoException(pieza);
		}
	}
	
	public void participarSubasta(int fecha ,int precio, Pieza pieza, String formaPago) throws MesajedeErrorException, PrecioBajoException {
		List<Subasta> subastas= Galeria.getSubastasActivas();
		boolean subEncontrada = false;
		for (Subasta s : subastas) {
			if (s.getFechaSubasta()==fecha) {
				Subasta subasta = s;
				subEncontrada = true;
				List<Pieza> piezas = subasta.ofrecerPiezas();
				if (piezas.contains(pieza)) {
					Comprador comprador = subasta.agregarComprador(this.nombre);
				hacerOferta(pieza,precio,comprador,formaPago);}
				else {
					throw new MesajedeErrorException("La pieza no se encuentra disponible en la subasta");
				}
			}
		}
		
		if (!subEncontrada) {
			throw new MesajedeErrorException("No existe una subasta en esa fecha");
		}
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