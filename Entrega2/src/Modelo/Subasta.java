package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Exceptions.MensajedeErrorException;
import Exceptions.PagoRechazado;
import Piezas.Pieza;
import Usuarios.Cajero;
import Usuarios.Comprador;
import Usuarios.Operador;
import Usuarios.Propietario;


public class Subasta {
	
	private int fechaSubasta;
	
	private List<Pieza> inventario;
	
	private List<Comprador> compradores;
	
	private Operador operador;
	
	private boolean activa;
	
	private List<String> ganadores;
	
	
	
	
	//M
	public Subasta(int fechaSubasta, List<Pieza> inventario, Operador operador) {

		this.fechaSubasta=fechaSubasta;
		this.inventario=inventario;
		this.compradores= new ArrayList<>();
		this.operador= operador;
		this.activa=true;
		this.ganadores= new ArrayList<>();

	}


	public void finalizarSubasta() {
		this.activa = false;
	}

	public boolean isActiva() {
		return this.activa;
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


	public void ganadorSubasta(Cajero cajero, Galeria gal) throws Exception {
		try {
			Map<Pieza, List<Oferta>> reporte = this.operador.generarReporte();
			for (Map.Entry<Pieza, List<Oferta>> entry : reporte.entrySet()) {
				Pieza pieza = entry.getKey();
				List<Oferta> ofertas = entry.getValue();
				int  max = this.operador.mayorOferta(pieza);
				Comprador comprador = null;
				String formaPago = null;
				for (Oferta oferta:ofertas) {
					if (oferta.getValorOferta()==max) {
						comprador=oferta.getComprador();
						formaPago =oferta.getFormaPago();

					}
				}
				if (!comprador.equals(null) && !formaPago.equals(null)) {
					if (cajero.generarPagoCajero(max, pieza,formaPago,comprador)){
						comprador.agregarCompra(max);
						comprador.agregarPiezaCompra(pieza.getTitulo(), max);
						gal.getInventario().moverPieza(pieza);
						Propietario pro = (Propietario) pieza.getPropietario();
						pro.venderPieza(pieza);
						pieza.setVendido(true);
						
						
					}
					
					else {
						throw new PagoRechazado();					}
					
					ganadores.add("("+pieza.getTitulo()+" , " + comprador.getLogin() + ")");

				}
				else {
					throw new MensajedeErrorException("Datos inconsistentes: ese precio no se encuentra en las ofertas)");
				}



			}


		}
		catch(MensajedeErrorException e) {
			throw e;
		}
		catch(PagoRechazado e) {
			throw e;
		}


	}
	
	

	public List<String> getGanadores() {
		return this.ganadores;
	}


	public void setGanadores(List<String> ganadores) {
		this.ganadores = ganadores;
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
	public void agregarComprador(Comprador comprador) throws MensajedeErrorException {
		if (!compradores.contains(comprador)) {
			compradores.add(comprador);
			}
		else {
			throw new MensajedeErrorException("El comprador ya se encuentra registrado en la subasta");
		}
	}
	
	public boolean revisarInscripcion(Comprador comprador) {
		if (compradores.contains(comprador)) {
		return true ;}
		else {
			return false;
		}
		
		
		
		
	}
	public void quitarComprador(Comprador comprador) {
		compradores.remove(comprador);
	}


	public Operador getOperador() {
		return operador;
	}


	public void setOperador(Operador operador) {
		this.operador = operador;
	}


	


	
		
	
	





}