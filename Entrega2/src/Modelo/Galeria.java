package Modelo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Consola.ConsolaInicial;
import Exceptions.LoginDuplicadoException;
import Exceptions.MensajedeErrorException;
import Exceptions.PagoRechazado;
import Exceptions.PiezaRepetidaException;
import Usuarios.*;
import Piezas.*;
import Persistencia.CentralPersistencia;

@SuppressWarnings("resource")
public class Galeria {
	private Administrador admin;
	private String nombre;
	private Cajero cajero;
	private Operador operador;
	private Inventario inventario;
	private List<Empleado> empleados;
    private List<Subasta> subastasActivas;


	public Galeria() {
		this.nombre = "Galeria";
		this.admin = null;
		this.cajero = null;
		this.operador= null;
		this.inventario= new Inventario();
		this.empleados = new ArrayList<>();
        this.subastasActivas = new ArrayList<>();
	}
	

	
// Métodos para implementar las funcionalidades específicas (por ejemplo, crearSubasta(), crearUsuario(), etc.)
	
	//Metodos para verificar inicios de sesion
	public Empleado verificarEmpleado(String login2, String password2) throws MensajedeErrorException {
		Empleado empleado = null;
		for(Empleado e :getEmpleados()) {
			if(e.getNombreUsuario().equals(login2)&&e.getContraseña().equals(password2)) {
				empleado = e;
			}
		}
		if (empleado == null) {
			throw new MensajedeErrorException("No se pudo verificar al empleado");
		}
		return empleado;
		
	}
	
	
	//AumentarCupo



	public void aumentarCupo(String loginComprador,int valor) {
		try {
				this.admin.aumentarCupoComprador(loginComprador,valor);			
		}
		catch(Exception e) {
			throw e;
		}
		
	}
	
	//Crea una subasta, unicamente por el administrador
	public void crearSubasta(int fecha, String opcion) throws Exception {
		try {			
			Subasta subasta = this.admin.crearSubastaAdmin(fecha, this,opcion);
			subastasActivas.add(subasta);
		}
		catch(Exception e) {
			throw e;
		}

	}
	
	//meu imprimir pieza oferta 
	public void imprimirPiezas(List<Pieza> piezasSubasta) { 
		int i = 1;
		for(Pieza pieza:piezasSubasta) {
			System.out.println("\n \n"+i+". " + pieza.getTitulo());
			i++;
			System.out.println("La pieza es un " + pieza.getTipoPieza());
			if (pieza.getTipoPieza().equalsIgnoreCase("Escultura")) {
				System.out.println("Alto: " + ((Escultura) pieza).getAlto());
				System.out.println("Ancho: " + ((Escultura) pieza).getAncho());
				System.out.println("Profundidad: " + ((Escultura) pieza).getProfundidad());
				System.out.println("Peso: " + ((Escultura) pieza).getPeso());
				System.out.println("Instalacion: " + ((Escultura) pieza).getInstalacion());
				if (((Escultura) pieza).isElectricidad()) {
					System.out.println("La Escultura funciona con electricidad ");	
				}
				else {
					System.out.println("La Escultura no funciona con electricidad ");
				}
				System.out.println("Materiales: ");
				for (String material :((Escultura) pieza).getMateriales() ) {
					System.out.print(material + ", ");
				}
			}

			else if (pieza.getTipoPieza().equalsIgnoreCase("Fotografia")) {
				System.out.println("Tamaño: " + ((Fotografia) pieza).getTamanio());
				System.out.println("Alto: " + ((Fotografia) pieza).getAlto());
				System.out.println("Resolucion: " + ((Fotografia) pieza).getResolucion());
				System.out.println("Descripcion: " + ((Fotografia) pieza).getDescripcion());
				System.out.println("formato: " + ((Fotografia) pieza).getFormato());
			}

			else if (pieza.getTipoPieza().equalsIgnoreCase("Impresion")) {
				System.out.println("Material del papel: " + ((Impresion) pieza).getTamanio());
				System.out.println("Tamaño: " + ((Impresion) pieza).getTamanio());
				System.out.println("Resolucion: " + ((Impresion) pieza).getResolucion());
				System.out.println("Descripcion: " + ((Impresion) pieza).getDescripcion());
				System.out.println("Flexibilidad: " + ((Impresion) pieza).getFlexibilidad());
				System.out.println("Resistencia: " + ((Impresion) pieza).getResistencia());
			}

			else if (pieza.getTipoPieza().equalsIgnoreCase("Pintura")) {
				System.out.println("Tecnica: " + ((Pintura) pieza).getTecnica());
				System.out.println("Alto: " + ((Pintura) pieza).getAlto());
				System.out.println("Ancho: " + ((Pintura) pieza).getAncho());
				System.out.println("Descripcion: " + ((Pintura) pieza).getDescripcion());
				System.out.println("Origen: " + ((Pintura) pieza).getOrigen());
				System.out.println("Forma: " + ((Pintura) pieza).getForma());
				System.out.println("tiempoDeCreacion: " + ((Pintura) pieza).getTiempoDeCreacion());
			}

			else if (pieza.getTipoPieza().equalsIgnoreCase("Video")) {
				System.out.println("Duracion en minutos: " + ((Video) pieza).getDuracion());
				System.out.println("Tamaño: " + ((Video) pieza).getTamanio());
				System.out.println("Idioma: " + ((Video) pieza).getIdioma());
				System.out.println("Descripcion: " + ((Video) pieza).getDescripcion());
				System.out.println("Resolucion: " + ((Video) pieza).getResolucion());
				System.out.println("Formato: " + ((Video) pieza).getFormato());
			}

			else {
				System.out.println("Informacion: " + ((Otro) pieza).getInfoExtra());

			}

			System.out.println("Año: " + pieza.getAnio());
			System.out.println("Lugar de creacion: " + pieza.getLugarDeCreacion());
			System.out.println("Valor Inicial para subastar la pieza (si es 0 no se subasta): " + pieza.getValorInicial());
			System.out.println("Precio: " + pieza.getValorFijo());
			System.out.println("Autores: ");
			for (Autor autor :pieza.getAutores() ) {
				System.out.print(autor.getNombre() + ", ");
			}

		}

	} 

	//Le permite a los compradores participar en una subasta
	public Subasta participarSubasta(int fecha,Comprador c) throws Exception {

			Subasta subasta = null;
			for (Subasta s : subastasActivas) {
				if (s.getFechaSubasta()==fecha) {
					subasta = s;
					subasta.agregarComprador(c);
				}
				else {
					throw new MensajedeErrorException("No hay subastas activas para esa fecha");

				}
			}
			
			return subasta;
			
	}
		

	
	//Termina la subasta y ejecuta los pagos a realizar de los ganadores
	public void terminarSubasta(int fecha) throws MensajedeErrorException {
		try {
			Subasta subasta = null;
			for (Subasta s : subastasActivas) {
				if (s.getFechaSubasta()==fecha) {
					subasta = s;
				}
			}
			if (!subasta.equals(null)) {
				this.admin.terminarSubastaAdmin(subasta,this.cajero);
			}
			else {
				throw new MensajedeErrorException("No hay subastas activas para esa fecha");
			}	
		}catch(MensajedeErrorException e) {
			throw e;
		}

	}
	
	
	
	public void historialPiezas() {
		// TODO Auto-generated method stub
		
	}



	public void historialArtista() {
		// TODO Auto-generated method stub
		
	}
	


	public void mostrarPiezasDisponibles() {
		ArrayList<Pieza> piezasDisponibles= this.inventario.getPiezasDisponibles();
	    imprimirPiezas(piezasDisponibles);
	}
	
	public void mostrarHistorialPiezas() {
		ArrayList<Pieza> historialPiezas= this.inventario.getHistorialPiezas();
	    imprimirPiezas(historialPiezas);
	    }

    
   

    public void asignarAdministrador(String login,String password) throws MensajedeErrorException {
    	try {
        Administrador admin= new Administrador(login, password,this.inventario);
        this.setAdmin(admin);
    	}catch (Exception e) {
    		throw new MensajedeErrorException("No se pudo asignar el nuevo administrador");
    	}
    }
    
    public void asignarCajero(String loginC,String passwordC) throws Exception {
    	try {  		
    		Cajero c = new Cajero(loginC, passwordC, "Cajero");
    		setCajero(c);
    		empleados.add(this.cajero);
    	}
    	catch (Exception e) {
    		throw new MensajedeErrorException("No se pudo asignar el cajero");
    	}


    }
    public void agregarEmpleado(String loginE,String passwordE,String rol) throws MensajedeErrorException {
    	try {
    		if (rol.equalsIgnoreCase("Operador")) {
    			Operador operador = new Operador(loginE, passwordE,"Operador");
    			this.empleados.add(operador);
    		}

    		else if (rol.equalsIgnoreCase("Empleado")){
    			Empleado empleado = new Empleado(loginE, passwordE,"Empleado");
    			this.empleados.add(empleado);}

    	}
    	catch (Exception e) {
    		throw new MensajedeErrorException("No se pudo agregar el empleado");
    	}


    }
  

   
    
    

    // Getters y setters para las listas de empleados y subastas activas

    public List<Empleado> getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Subasta> getSubastasActivas() {
        return this.subastasActivas;
    }

    public void setSubastasActivas(List<Subasta> subastasActivas) {
        this.subastasActivas = subastasActivas;
    }
    
    public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Administrador getAdmin() {
		return this.admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Inventario getInventario() {
		return this.inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

    public Cajero getCajero() {
		return this.cajero;
	}


	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}


	public Operador getOperador() {
		return this.operador;
	}


	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	
	//Métodos de persistencia
    
	public void cargarGaleria(Galeria galeria){

        String archivoInicio = "InicioGaleria.txt";
        String archivoUsuarios = "Usuarios.txt";
        String archivoPiezas = "Piezas.txt";
        
		try {
			CentralPersistencia.cargarGaleria(archivoInicio, archivoUsuarios, archivoPiezas,galeria);	
		} catch (Exception e) {
			System.out.println("Ocurrio un error iniciando la aplicacion");
			System.out.println("Los archivos cargados para iniciar la aplicacion no son correctos");
		}
	}
		
	public void guardarGaleria() { 
		try {
			CentralPersistencia.guardarGaleria(this);
		} catch (Exception e) {
			System.out.println("Hubo un error guardando la Galeria");
	}
	
}



	public Subasta encontrarSubasta(int fecha) {
		Subasta subasta = null;
		for (Subasta s : subastasActivas) {
			if (s.getFechaSubasta()==fecha) {
				subasta = s;}}
		return subasta;
	}





	
	
    
}
