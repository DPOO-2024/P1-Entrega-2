package Piezas;

import java.util.List;

import Usuarios.Usuario;

public class Impresion extends Pieza{
	
	
	public static final String IMPRESION ="Impresion";
	private String materialPapel;
	private String tamanio;
	private int resolucion;
	private String flexibilidad;
	private String resistencia;
	private String descripcion;
	
	public Impresion(String tipoPieza, Usuario propietariot,String titulot, int aniot, String lugarDeCreaciont, List<String> autores, boolean modalidadt,
			int fechaMaxt, int valorInicialt, String ubicaciont, boolean vendidot, int valorFijot,
			String materialPapelt, String tamaniot, int resoluciont, String flexibilidad2, String resistenciat, String descripciont) {
		
		super(tipoPieza,propietariot,titulot, aniot, lugarDeCreaciont, autores, modalidadt, fechaMaxt, valorInicialt, ubicaciont, vendidot,
				valorFijot);
		this.materialPapel=materialPapelt;
		this.tamanio=tamaniot;
		this.resolucion=resoluciont;
		this.flexibilidad=flexibilidad2;
		this.resistencia=resistenciat;
		this.descripcion=descripciont;
		
	}

	//Getters y Setters
	public String getMaterialPapel() {
		return this.materialPapel;
	}

	public void setMaterialPapel(String materialPapel) {
		this.materialPapel = materialPapel;
	}

	public String getTamanio() {
		return this.tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public int getResolucion() {
		return this.resolucion;
	}

	public void setResolucion(int resolucion) {
		this.resolucion = resolucion;
	}

	public String getFlexibilidad() {
		return this.flexibilidad;
	}

	public void setFlexibilidad(String flexibilidad) {
		this.flexibilidad = flexibilidad;
	}

	public String getResistencia() {
		return this.resistencia;
	}

	public void setResistencia(String resistencia) {
		this.resistencia = resistencia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static String getImpresion() {
		return IMPRESION;
	}
	
	

}