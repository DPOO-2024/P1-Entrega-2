package Modelo;

import java.util.ArrayList;
import java.util.List;

import Exceptions.MensajedeErrorException;
import Exceptions.PiezaRepetidaException;
import Piezas.Pieza;

public class Inventario {
	
	private ArrayList<Pieza> piezasDisponibles;
	private ArrayList<Pieza> historialPiezas;
	private Pieza piezaReservada;
	
	public Inventario() {
		this.piezasDisponibles= new ArrayList<Pieza>( );
		this.historialPiezas= new ArrayList<Pieza>( );
		this.piezaReservada=null;
	}
	
	
	//Crea la pieza, dependiendo su tipo y la añade a piezas disponibles
	public String agregarPieza(Pieza pieza) throws PiezaRepetidaException {
		
		//Verificar que no haya una pieza con ese titulo
		for (Pieza p : getPiezasDisponibles()) {
			if (p.getTitulo().equals(pieza.getTitulo())) {
				throw new PiezaRepetidaException(pieza);
			}
		}
		for (Pieza p : getHistorialPiezas()) {
			if (p.getTitulo().equals(pieza.getTitulo())) {
				throw new PiezaRepetidaException(pieza);
			}
		}
		if (!pieza.isVendido()) {
			piezasDisponibles.add(pieza);
		}
		else {
			historialPiezas.add(pieza);
		}
		
		
		return "Pieza añadida con exito";
	}
	
	
	/*Si la pieza se encuentra en la lista de piezas disponibles la mueve a la lista de histroial de piezas y viceversa*/
	public void moverPieza(Pieza pieza) {
		if (this.piezasDisponibles.contains(pieza)) {
			this.historialPiezas.add(pieza);
			this.piezasDisponibles.remove(pieza);
		}
		else {
			this.piezasDisponibles.add(pieza);
			this.historialPiezas.remove(pieza);
		}
	}
	
	public Pieza getPieza(String titulo) throws MensajedeErrorException {
		Pieza pE=null;
		String titulo1 = titulo.replaceAll("\\s", "");
		for (Pieza p: piezasDisponibles) {
			String cadena = p.getTitulo().replaceAll("\\s", "");
			if (cadena.equalsIgnoreCase(titulo1)) {
				pE=p;
		}
		}
		for (Pieza p: historialPiezas) {
			String cadena2 = p.getTitulo().replaceAll("\\s", "");
			if (cadena2.equalsIgnoreCase(titulo1)) {
				pE=p;
		}
		}
		if (pE==null) {
			throw new MensajedeErrorException("La pieza no se encontro");
		}
		return pE;
	}
	
	
	/*Genera una lista con las piezas disponibles para subasta, si encuentra que 
	una pieza debe ser devuelta llama a la funcion de devolver pieza*/
	public List<Pieza> generarInventarioSubasta(int fecha){
		ArrayList<Pieza> listaSubasta = new ArrayList<Pieza>();
		for (int i=0; i<piezasDisponibles.size(); i++) {
			Pieza p=piezasDisponibles.get(i);
			if (p.isModalidad()) {
				if (p.getFechaMax()<=fecha) {
					listaSubasta.add(p);
				}
				else {
					retornarPieza(p);
				}
			}
			else if (p.getValorInicial()!= 0) {
				listaSubasta.add(p);
			}
		}
		return listaSubasta;
		
	}
	
	
	//Retorna la pieza al propietario
	public void retornarPieza(Pieza p) {
		if (historialPiezas.contains(p)== false) {
			moverPieza(p);	
		}	
	}
	
	
	//Reserva una pieza mientras se espera si es comprada o no
	public void reservarPieza(Pieza p) {
		setPiezaReservada(p);
		this.piezasDisponibles.remove(p);
	}
	
	
	//Verifica si una pieza tiene precio fijo
	public int verificarPrecioPieza(Pieza p) {
		if (p.getValorFijo()!=0) {
			return p.getValorFijo();
		}
		else {
			return 0;                                                                 
		}
	}

	//Getters y Setters
	public ArrayList<Pieza> getPiezasDisponibles() {
		return this.piezasDisponibles;
	}

	public void setPiezasDisponibles(ArrayList<Pieza> piezasDisponibles) {
		this.piezasDisponibles = piezasDisponibles;
	}

	public ArrayList<Pieza> getHistorialPiezas() {
		return this.historialPiezas;
	}

	public void setHistorialPiezas(ArrayList<Pieza> historialPiezas) {
		this.historialPiezas = historialPiezas;
	}
	
	public Pieza getPiezaReservada() {
		return this.piezaReservada;
	}

	public void setPiezaReservada(Pieza pieza) {
		this.piezaReservada = pieza;
	}

}