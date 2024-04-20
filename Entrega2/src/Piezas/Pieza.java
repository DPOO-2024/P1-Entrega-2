package Piezas;

import java.util.ArrayList;
import java.util.List;

import Usuarios.Usuario;


public abstract class Pieza {
	
	private String tipoPieza;
	
	private Usuario propietario;
	
	private String titulo;
	
	private int anio;
	
	private String lugarDeCreacion;
	
	private List<Autor> autores;
	
	private boolean modalidad;
	
	private int fechaMax;
	
	private int valorInicial;
	
	private String ubicacion;
	
	private boolean vendido;
	
	private int valorFijo;

	
	
	public Pieza(String tipoPiezat,Usuario propietariot,String titulot,int aniot, String lugarDeCreaciont, List<String> autores2, boolean modalidadt, int fechaMaxt, int valorInicialt, String ubicaciont, boolean vendidot, int valorFijot) {
		this.tipoPieza=tipoPiezat;
		this.propietario=propietariot;
		this.titulo=titulot;
		this.anio=aniot;
		this.lugarDeCreacion=lugarDeCreaciont;
		this.autores=new ArrayList<Autor>();
		addAutores(autores2);
		this.modalidad=modalidadt;
		this.fechaMax=fechaMaxt;
		this.valorInicial=valorInicialt;
		this.ubicacion=ubicaciont;
		this.vendido=vendidot;
		this.valorFijo=valorFijot;
	}

	//Getters y Setters
	public Usuario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Usuario usuario) {
		this.propietario = usuario;
	}
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getLugarDeCreacion() {
		return this.lugarDeCreacion;
	}

	public void setLugarDeCreacion(String lugarDeCreacion) {
		this.lugarDeCreacion = lugarDeCreacion;
	}

	public List<Autor> getAutores() {
		return this.autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public void addAutores(List<String> autores) {
		for (String a:autores) {
			Autor autor = new Autor(a);
			this.autores.add(autor);
		}	
	}

	public boolean isModalidad() {
		return this.modalidad;
	}

	public void setModalidad(boolean modalidad) {
		this.modalidad = modalidad;
	}

	public int getFechaMax() {
		return this.fechaMax;
	}

	public void setFechaMax(int fechaMax) {
		this.fechaMax = fechaMax;
	}

	public int getValorInicial() {
		return this.valorInicial;
	}

	public void setValorInicial(int valorInicial) {
		this.valorInicial = valorInicial;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isVendido() {
		return this.vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public int getValorFijo() {
		return this.valorFijo;
	}

	public void setValorFijo(int valorFijo) {
		this.valorFijo = valorFijo;
	}

	public String getTipoPieza() {
		return tipoPieza;
	}

	public void setTipoPieza(String tipoPieza) {
		this.tipoPieza = tipoPieza;
	}

	

}