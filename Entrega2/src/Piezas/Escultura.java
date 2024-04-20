package Piezas;
import java.util.List;

import Usuarios.Usuario;

public class Escultura extends Pieza{
	
	public final static String ESCULTURA="Escultura";
	
	private int alto;
	private int ancho;
	private int profundidad;
	private List<String> materiales;
	private int peso;
	private boolean electricidad;
	private String instalacion;
	
	public Escultura(String tipoPieza,Usuario propietariot,String titulot, int aniot, String lugarDeCreaciont, List<String> autores, boolean modalidadt,
			int fechaMaxt, int valorInicialt, String ubicaciont, boolean vendidot, int valorFijot, int altot,
			int anchot, int profundidadt, List<String> materialest, int pesot, boolean electricidadt, String instalaciont) 
	{
		super(tipoPieza,propietariot,titulot, aniot, lugarDeCreaciont, autores, modalidadt, fechaMaxt, valorInicialt, ubicaciont, vendidot,
				valorFijot);
		
		this.alto=altot;
		this.ancho=anchot;
		this.profundidad=profundidadt;
		this.materiales=materialest;
		this.peso=pesot;
		this.electricidad=electricidadt;
		this.instalacion=instalaciont;	
	}

	// Getters y Setters
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
	public int getProfundidad() {
		return this.profundidad;
	}
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	public List<String> getMateriales() {
		return this.materiales;
	}
	public void setMateriales(List<String> materiales) {
		this.materiales = materiales;
	}
	public int getPeso() {
		return this.peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public boolean isElectricidad() {
		return this.electricidad;
	}
	public void setElectricidad(boolean electricidad) {
		this.electricidad = electricidad;
	}
	public String getInstalacion() {
		return this.instalacion;
	}
	public void setInstalacion(String instalacion) {
		this.instalacion = instalacion;
	}
	public static String getEscultura() {
		return ESCULTURA;
	}


}