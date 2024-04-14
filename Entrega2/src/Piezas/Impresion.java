package Piezas;

import Usuarios.Usuario;

public class Impresion extends Pieza{
	
	
	public static final String IMPRESION ="Impresion";
	private String materialPapel;
	private int tamanio;
	private int resolucion;
	private int flexibilidad;
	private String resistencia;
	private String descripcion;
	
	public Impresion(Usuario propietariot,String titulot, int aniot, String lugarDeCreaciont, String autores, boolean modalidadt,
			int fechaMaxt, int valorInicialt, String ubicaciont, boolean vendidot, int valorFijot,
			String materialPapelt, int tamaniot, int resoluciont, int flexibilidadt, String resistenciat, String descripciont) {
		
		super(propietariot,titulot, aniot, lugarDeCreaciont, autores, modalidadt, fechaMaxt, valorInicialt, ubicaciont, vendidot,
				valorFijot);
		this.materialPapel=materialPapelt;
		this.tamanio=tamaniot;
		this.resolucion=resoluciont;
		this.flexibilidad=flexibilidadt;
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

	public int getTamanio() {
		return this.tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public int getResolucion() {
		return this.resolucion;
	}

	public void setResolucion(int resolucion) {
		this.resolucion = resolucion;
	}

	public int getFlexibilidad() {
		return this.flexibilidad;
	}

	public void setFlexibilidad(int flexibilidad) {
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