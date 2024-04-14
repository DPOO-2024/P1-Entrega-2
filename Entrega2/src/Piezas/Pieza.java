package Piezas;

import java.util.List;

public abstract class Pieza {
	
	private String titulo;
	
	private int anio;
	
	private String lugarDeCreacion;
	
	private List<Autor> autores;
	
	private boolean modalidad;
	
	private int fechaMax;
	
	private int valorInicial;
	
	private String ubicacion;
	
	private boolean vendido;
	
	private boolean valorFijo;

	public boolean isValorFijo() {
		return valorFijo;
	}

	public void setValorFijo(boolean valorFijo) {
		this.valorFijo = valorFijo;
	}

}