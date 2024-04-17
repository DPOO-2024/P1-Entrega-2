package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Piezas.Escultura;
import Piezas.Fotografia;
import Piezas.Impresion;
import Piezas.Otro;
import Piezas.Pieza;
import Piezas.Pintura;
import Piezas.Video;
import Usuarios.Usuario;

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
	public String agregarPieza(Pieza pieza) {
		piezasDisponibles.add(pieza);
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
			else {
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