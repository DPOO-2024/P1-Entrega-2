package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import Exceptions.MesajedeErrorException;
import Piezas.Pieza;
import Usuarios.Cajero;
import Usuarios.Comprador;
import Usuarios.Operador;

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


	public void hacerOferta(Comprador comprador, Administrador admin, Pieza p) throws MesajedeErrorException {
		try {
			Scanner scanner = new Scanner(System.in);

			if (!p.equals(null)) {
				int valorI = p.getValorInicial();
				System.out.print("Por favor, ingrese su oferta, recuerde que el valor inicial de esta pieza es de" + valorI);
				String oferta = scanner.nextLine();
				int valor=Integer.parseInt(oferta);
				List<Integer> valores = this.operador.listaValoresOferta(p);
				if (!(valores.contains(valor)) && valor>this.operador.mayorOferta(p) && valor>valorI) {
					System.out.print("Por favor, ingrese su forma de pago si gana la subasta" );
					String formaPago = scanner.nextLine();
					this.operador.crearOferta(valor, comprador, p, formaPago, admin);

				}
				else {
					throw new MesajedeErrorException("Aumenta tu oferta");
				}


			}
			else {
				throw new MesajedeErrorException("La pieza ingresada no esta disponible en esta subasta");
			}

		} 
		catch(MesajedeErrorException e) {
			throw e;
		}
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

	public void ganadorSubasta(Cajero cajero) throws MesajedeErrorException {
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
					cajero.generarPagoCajero(max, pieza,formaPago,comprador);
					ganadores.add("("+pieza.getTitulo()+" , " + comprador.getLogin() + ")");

				}
				else {
					throw new MesajedeErrorException("Datos inconsistentes(Max no se ecnuentre en las ofertas)");
				}



			}


		}
		catch(MesajedeErrorException e) {
			throw e;
		}
		catch(Exception e) {
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
	public List<Pieza> agregarComprador(Comprador comprador) throws MesajedeErrorException {
		if (!compradores.contains(comprador)) {
			compradores.add(comprador);
			return this.inventario;}
		else {
			throw new MesajedeErrorException("El comprador ya se encuentra registrado en la subasta");
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