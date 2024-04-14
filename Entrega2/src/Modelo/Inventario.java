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
	public String agregarPieza(String tipoPieza, ArrayList<String> informacion) {
		Pieza p = null;
		if (tipoPieza.equals("Escultura")) {
			ArrayList<String> materiales = new ArrayList<>(Arrays.asList(informacion.get(13).split(",")));			
			p = new Escultura(informacion.get(0), Integer.parseInt(informacion.get(1)), informacion.get(2), informacion.get(3), 
					Boolean.parseBoolean(informacion.get(4)), Integer.parseInt(informacion.get(5)), Integer.parseInt(informacion.get(6)), 
					informacion.get(7), Boolean.parseBoolean(informacion.get(8)), Integer.parseInt(informacion.get(9)), 
					Integer.parseInt(informacion.get(10)), Integer.parseInt(informacion.get(11)), Integer.parseInt(informacion.get(12)), 
					materiales, Integer.parseInt(informacion.get(14)), Boolean.parseBoolean(informacion.get(15)), informacion.get(16));
		}
		else if (tipoPieza.equals("Fotografia")) {
			p = new Fotografia(informacion.get(0), Integer.parseInt(informacion.get(1)), informacion.get(2), informacion.get(3), 
					Boolean.parseBoolean(informacion.get(4)), Integer.parseInt(informacion.get(5)), Integer.parseInt(informacion.get(6)), 
					informacion.get(7), Boolean.parseBoolean(informacion.get(8)), Integer.parseInt(informacion.get(9)), 
					Integer.parseInt(informacion.get(10)), Integer.parseInt(informacion.get(11)), Integer.parseInt(informacion.get(12)), 
					informacion.get(13), informacion.get(14));
		}
		else if (tipoPieza.equals("Impresion")) {
			p = new Impresion(informacion.get(0), Integer.parseInt(informacion.get(1)), informacion.get(2), informacion.get(3), 
					Boolean.parseBoolean(informacion.get(4)), Integer.parseInt(informacion.get(5)), Integer.parseInt(informacion.get(6)), 
					informacion.get(7), Boolean.parseBoolean(informacion.get(8)), Integer.parseInt(informacion.get(9)), 
					informacion.get(10), Integer.parseInt(informacion.get(11)), Integer.parseInt(informacion.get(12)), 
					Integer.parseInt(informacion.get(13)), informacion.get(13), informacion.get(14));
		}
		else if (tipoPieza.equals("Otro")) {
			p = new Otro(informacion.get(0), Integer.parseInt(informacion.get(1)), informacion.get(2), informacion.get(3), 
					Boolean.parseBoolean(informacion.get(4)), Integer.parseInt(informacion.get(5)), Integer.parseInt(informacion.get(6)), 
					informacion.get(7), Boolean.parseBoolean(informacion.get(8)), Integer.parseInt(informacion.get(9)), 
					informacion.get(10));
		}
		else if (tipoPieza.equals("Pintura")) {
			p = new Pintura(informacion.get(0), Integer.parseInt(informacion.get(1)), informacion.get(2), informacion.get(3), 
					Boolean.parseBoolean(informacion.get(4)), Integer.parseInt(informacion.get(5)), Integer.parseInt(informacion.get(6)), 
					informacion.get(7), Boolean.parseBoolean(informacion.get(8)), Integer.parseInt(informacion.get(9)), 
					informacion.get(10),informacion.get(11),informacion.get(12),Integer.parseInt(informacion.get(13)), Integer.parseInt(informacion.get(14)), 
					 informacion.get(15),Integer.parseInt(informacion.get(16)));
		}
		else if (tipoPieza.equals("Video")) {
			p = new Video(informacion.get(0), Integer.parseInt(informacion.get(1)), informacion.get(2), informacion.get(3), 
					Boolean.parseBoolean(informacion.get(4)), Integer.parseInt(informacion.get(5)), Integer.parseInt(informacion.get(6)), 
					informacion.get(7), Boolean.parseBoolean(informacion.get(8)), Integer.parseInt(informacion.get(9)), 
					Integer.parseInt(informacion.get(10)),Integer.parseInt(informacion.get(11)), informacion.get(12),informacion.get(13),
					Integer.parseInt(informacion.get(14)),informacion.get(15));
		}
		else {
			return "No se cuenta con ese tipo de pieza en la Galeria";
		}
	
		piezasDisponibles.add(p);
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