package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Exceptions.MesajedeErrorException;
import Piezas.Pieza;
import Usuarios.Comprador;
import Usuarios.Operador;

public class Subasta {
	
	private int fechaSubasta;
	
	private List<Pieza> inventario;
	
	private List<Comprador> compradores;
	
	
	
	
	//M
	public Subasta(int fechaSubasta, List<Comprador> compradores ) {
		
		this.fechaSubasta=fechaSubasta;
		this.inventario=Inventario.generarInventarioSubasta(this.fechaSubasta);
		this.compradores= new ArrayList<>();		
	}
	
	
	public List<Comprador> getCompradores() {
		return compradores;
	}


	public void setCompradores(List<Comprador> compradores) {
		this.compradores = compradores;
	}


	public List<Pieza> ofrecerPiezas() {
		
		return inventario;
	}
	
	public void ganadorSubasta() {
	 Map<Pieza, List<Oferta>> reporte = Operador.generarReporte();
	 //falta que seleccione ppara cada pieza el que hizo la oferta mas alta y que luego llame a cajero 
	 //debe reservar la pieza?
	 //para generarPagoCajero(oferta.getValorOferta(),Pieza pieza,String formaPago, getComprador())
	 //si este pago es true entonces se accede al cliente y se le suma a las compras y se quita la pieza 
		
	}

	public int getFechaSubasta() {
		return fechaSubasta;
	}

	public void setFechaSubasta(int fechaSubasta) {
		this.fechaSubasta = fechaSubasta;
	}

	public List<Pieza> getInventario() {
		return inventario;
	}

	public void setInventario(List<Pieza> inventario) {
		this.inventario = inventario;
	}
	public Comprador agregarComprador(String nombre) throws MesajedeErrorException {
		List<Comprador> compradoresGenerales = Administrador.getCompradores();
		for (Comprador c :compradoresGenerales) {
			if (c.getNombre().equals(nombre)) {
				if (!compradores.contains(c)) {
				compradores.add(c);
				return c;}
				else {
					throw new MesajedeErrorException("El comprador ya se encuentra registrado en la subasta");
				}
			}
		}
		return null;
		
		
	}
	
	





}