package Piezas;

import java.util.List;

import Usuarios.Usuario;

public class Fotografia extends Pieza{
	
	
	public final static String FOTOGRAFIA="Fotografia";
	
	private String tamanio;	
	private int alto;
	private int resolucion;
	private String descripcion;
	private String formato;
	
	public Fotografia(String tipoPieza,Usuario propietariot,String titulot, int aniot, String lugarDeCreaciont, List<String> autores, boolean modalidadt,
			int fechaMaxt, int valorInicialt, String ubicaciont, boolean vendidot, int valorFijot,
			String tamaniot, int altot, int resoluciont, String descripciont, String formatot) {
		super(tipoPieza,propietariot,titulot, aniot, lugarDeCreaciont, autores, modalidadt, fechaMaxt, valorInicialt, ubicaciont, vendidot,
				valorFijot);
		this.tamanio=tamaniot;
		this.alto=altot;
		this.resolucion=resoluciont;
		this.descripcion=descripciont;
		this.formato=formatot;	
	}

	//Getters y Setters
	public String getTamanio() {
		return this.tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public int getAlto() {
		return this.alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getResolucion() {
		return this.resolucion;
	}

	public void setResolucion(int resolucion) {
		this.resolucion = resolucion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public static String getFotografia() {
		return FOTOGRAFIA;
	}
	
	

}