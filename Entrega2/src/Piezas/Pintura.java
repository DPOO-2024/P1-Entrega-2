package Piezas;

import java.util.List;

import Usuarios.Usuario;

public class Pintura extends Pieza{
	
	
	public static final String PINTURA="Pintura";
	private String tecnica;
	private String origen;
	private String descripcion;
	private int alto;
	private int ancho;
	private String forma;
	private String tiempoDeCreacion;

	public Pintura(String tipoPieza,Usuario propietariot,String titulot, int aniot, String lugarDeCreaciont, List<String> autores, boolean modalidadt,
			int fechaMaxt, int valorInicialt, String ubicaciont, boolean vendidot, int valorFijot,
			String tecnicat, String origent, String descripciont, int altot, int anchot, String format, String tiempoDeCreaciont) {
		
		super(tipoPieza,propietariot,titulot, aniot, lugarDeCreaciont, autores, modalidadt, fechaMaxt, valorInicialt, ubicaciont, vendidot,
				valorFijot);
		this.tecnica=tecnicat;
		this.origen=origent;
		this.descripcion=descripciont;
		this.alto=altot;
		this.ancho=anchot;
		this.forma=format;
		this.tiempoDeCreacion=tiempoDeCreaciont;
	}
	
	//Getters y Setters
	public String getTecnica() {
		return this.tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getAlto() {
		return this.alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return this.ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public String getForma() {
		return this.forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public String getTiempoDeCreacion() {
		return this.tiempoDeCreacion;
	}

	public void setTiempoDeCreacion(String tiempoDeCreacion) {
		this.tiempoDeCreacion = tiempoDeCreacion;
	}

	public static String getPintura() {
		return PINTURA;
	}
	
	
}