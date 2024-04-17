package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Exceptions.MesajedeErrorException;
import Piezas.Escultura;
import Piezas.Pieza;
import Usuarios.Cajero;
import Usuarios.Comprador;
import Usuarios.Propietario;
import Usuarios.Usuario;

public class Administrador {
	
	private String login;
	private String password;
	private Inventario inventario;
	private static ArrayList<Comprador> compradores;
	private ArrayList<Propietario> propietarios;
	
	public Administrador(String login, String contrasena, Inventario inventario) {
		this.login = login;
		this.password = contrasena;
		this.inventario=inventario;
		this.compradores = new ArrayList<Comprador>();
		this.propietarios = new ArrayList<Propietario>();
	}

	
	//Falta Crear Subasta

	//M
	public boolean confirmarVenta(Pieza pieza, String nombreComprador, String formaPago) throws MesajedeErrorException {
		boolean existeComprador = false;
		for(Comprador comprador : compradores) {
			if (comprador.getNombre().equals(nombreComprador)) {
				existeComprador=true;
				int valorMaximo = comprador.getComprasMaximas();
				int valorActual = comprador.getComprasTotales();
				if (valorMaximo< valorActual+pieza.getValorFijo()) {
					throw new MesajedeErrorException("Comprador excede compras maximas");
				}
				else {
					if (Cajero.generarPagoCajero(pieza.getValorFijo(),pieza,formaPago,comprador)) {
						return true;
					}

					}
				}
			}
		if(!existeComprador) {

				throw new MesajedeErrorException("El comprador no esta registrado correctamente");
			
		}
			
		
		return false;
		
		
	}

	public static boolean verificarOferta(Oferta oferta) throws MesajedeErrorException {
		boolean rta = false;
		int valorMaximo = oferta.getComprador().getComprasMaximas();
		int valorActual = oferta.getComprador().getComprasMaximas();
		if (valorMaximo< valorActual+oferta.getValorOferta()) {
			throw new MesajedeErrorException("Comprador excede compras maximas");
		}
		else {
			rta = true;
			
		}
		
		return rta;
	}
	
	
	//Verificar si si es comprador y existe (M), login y password
	
	
	//Setters y Getters
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String contrasena) {
		this.password = contrasena;
	}

	
	public static ArrayList<Comprador> getCompradores() {
		return compradores;
	}

	public void setCompradores(ArrayList<Comprador> compradores) {
		Administrador.compradores = compradores;
	}

	public ArrayList<Propietario> getPropietarios() {
		return propietarios;
	}

	public void setPropietarios(ArrayList<Propietario> propietarios) {
		this.propietarios = propietarios;
	}
	
	//Pedir info usuario (M)

	//Pedir info pieza
	public void pedirInfoPieza(Comprador c) {

	    try {
	    	Scanner scanner = new Scanner(System.in);
	    	
	    	System.out.print("Por favor, ingrese el tipo de la Pieza: ");
	        String tipoDePieza = scanner.nextLine();
	        
	    	System.out.print("Por favor, ingrese el titulo de la Pieza: ");
	        String titulo = scanner.nextLine();
	        
	        System.out.print("Por favor, ingrese el año de creación de la Pieza: ");
	        String aniot = scanner.nextLine();
	        int anio=Integer.parseInt(aniot);
	        
	        System.out.print("Por favor, ingrese el lugar de creación de la Pieza: ");
	        String lugarDeCreacion = scanner.nextLine();
	        
	        System.out.print("Por favor, ingrese los autores (separados por comas) de la Pieza, si no se conoce el autor ingrese \"Anonimo\": ");
	        String autorest = scanner.nextLine();
	        String[] autoresf = autorest.split(",");
	        List<String> autores = Arrays.asList(autoresf);
	        
	        System.out.print("Por favor, ingrese si desea aplicar la modalidad de \"marginalidad\" (Si o No): ");
	        String modalidadt = scanner.nextLine();
	        
	        boolean modalidad = false;
	        int fechaMax = 0;
	        
	        if (modalidadt.equals("Si") || modalidadt.equals("si")) {
	        	modalidad = true;
	        	System.out.print("Por favor, ingrese la fecha maxima, para la modalidad de \"marginalidad\" (AA/MM/DD): ");
	            String fechaMaxt = scanner.nextLine();
	            fechaMax=Integer.parseInt(fechaMaxt);
	        }
	        
	        System.out.print("Por favor, ingrese el valor inicial de la pieza, si desea que sea incluida en una subasta, si no ingrese 0: ");
	        String valorInicialt = scanner.nextLine();
	        int valorInicial=Integer.parseInt(valorInicialt);
	    	
	        System.out.print("Por favor, ingrese  si desea mostrar su Pieza (Si o No): ");
	        String ubicaciont = scanner.nextLine();
	        
	        String ubicacion = "Bodega";
	        if (ubicaciont.equals("Si") || ubicaciont.equals("si")) {
	        	ubicacion = "Mostrador";
	        }
	        
	        boolean vendido = false;
	        
	        System.out.print("Por favor, ingrese el valor fijo al que desea vender la Pieza, si no desea que tenga valor fijo ingrese 0: ");
	        String valorFijot = scanner.nextLine();
	        int valorFijo=Integer.parseInt(valorFijot);
	        
	        Pieza pieza;
	    	
	        
	    	if (tipoDePieza.equals("Escultura") && tipoDePieza.equals("escultura")) {
	    		
	    		System.out.print("Por favor, ingrese el alto de la Pieza: ");
	            String altot = scanner.nextLine();
	            int alto=Integer.parseInt(altot);
	            
	            System.out.print("Por favor, ingrese el ancho de la Pieza: ");
	            String anchot = scanner.nextLine();
	            int ancho=Integer.parseInt(anchot);
	            
	            System.out.print("Por favor, ingrese la profundidad de la Pieza: ");
	            String profundidadt = scanner.nextLine();
	            int profundidad=Integer.parseInt(profundidadt);
	            
	            System.out.print("Por favor, ingrese los materiales (separados por comas) de la Pieza: ");
	            String materialest = scanner.nextLine();
	            String[] materialesf = materialest.split(",");
	            List<String> materiales = Arrays.asList(materialesf);
	            
	            System.out.print("Por favor, ingrese el peso de la Pieza: ");
	            String pesot = scanner.nextLine();
	            int peso=Integer.parseInt(pesot);
	            
	            System.out.print("Por favor, ingrese su Pieza requiere electricidad (Si o No): ");
	            String electricidadt = scanner.nextLine();
	            
	            boolean electricidad = false;
	            if (electricidadt.equals("Si") || electricidadt.equals("si")) {
	            	electricidad = true;
	            }
	            
	            System.out.print("Por favor, ingrese alguna especificacion de la instalación: ");
	            String instalacion = scanner.nextLine();
	            
	            pieza=new Escultura((Usuario)c, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, 
	            		valorInicial, ubicaciont, vendido, valorFijo, alto, ancho, profundidad, materiales, peso, electricidad, instalacion);
	            
	            this.inventario.agregarPieza(pieza);
	            
	    	}
	    	else if (tipoDePieza.equals("Fotografia") && tipoDePieza.equals("fotografia")){
	    		//Falta hacer para el resto de las Piezas, con la info necesaria
	    	}
	    }catch(Exception e) {
	    	throw e;
	    }
	    
	    
		
	}
	
}
