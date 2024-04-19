package Piezas;

import java.util.List;

import Usuarios.Usuario;

public class Video extends Pieza{
	
	
	public static final String VIDEO="Video";
	
	private int duracion;//Minutos
	private String tamanio;
	private String idioma;
	private String descripcion;
	private int resolucion; //p
	private String formato;
	
	public Video(String tipoPieza,Usuario propietariot,String titulot, int aniot, String lugarDeCreaciont, List<String> autores, boolean modalidadt, int fechaMaxt,
			int valorInicialt, String ubicaciont, boolean vendidot, int valorFijot, int duraciont, String tamaniot,
			String idiomat, String descripciont, int resoluciont, String formatot) {
		
		super(tipoPieza,propietariot,titulot, aniot, lugarDeCreaciont, autores, modalidadt, fechaMaxt, valorInicialt, ubicaciont, vendidot,
				valorFijot);
		this.duracion=duraciont;
		this.tamanio=tamaniot;
		this.idioma=idiomat;
		this.descripcion=descripciont;
		this.resolucion=resoluciont;
		this.formato=formatot;
	}

	//Getters y Setters
	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getTamanio() {
		return this.tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getResolucion() {
		return this.resolucion;
	}

	public void setResolucion(int resolucion) {
		this.resolucion = resolucion;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public static String getVideo() {
		return VIDEO;
	}
	
	
}