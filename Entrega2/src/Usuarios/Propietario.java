package Usuarios;

import java.util.List;

import Piezas.Pieza;

public class Propietario extends Usuario{
	
	public final static String PROPIETARIO="Propietario";
	
	private String nombre;
	
	private String correo;
	
	private int telefono;
	
	private List<Pieza> estadoPiezas;
	
	private List<Pieza> historialPiezas;

}