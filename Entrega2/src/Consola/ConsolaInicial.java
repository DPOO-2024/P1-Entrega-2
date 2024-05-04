package Consola;

import java.util.Scanner;

import Exceptions.MensajedeErrorException;
import Exceptions.PagoRechazado;
import Modelo.Galeria;
import Persistencia.CentralPersistencia;

public class ConsolaInicial {
	private Galeria galeria;
	public static Scanner scanner;
	
	public ConsolaInicial(Galeria galeriat) {
		this.galeria=galeriat;
		this.scanner= new Scanner(System.in);
	}
	
	public void mostrarMenu()
    {
		int opcion;
        try {
	        do {
	            System.out.println("\n**Menú Inicio**");
	            System.out.println("\n**Seleccione la opción de como va a iniciar sesión**");
	            System.out.println("1. Adminstrador");
	            System.out.println("2. Empleado de Galeria");
	            System.out.println("3. Comprador");
	            System.out.println("4. Propietario");
	            System.out.println("5. Registrarse como Comprador o Propietario");
	            System.out.println("6. Salir");
	            System.out.print("Ingrese una opción: ");
	
	            opcion = scanner.nextInt();
	            scanner.nextLine(); // Consumir el salto de línea
	            
	            switch (opcion) {
	            case 1:
	            	ConsolaAdministrador conAdmin = new ConsolaAdministrador(this.galeria);
	            	try {         		
	            		conAdmin.IniciarSesion();
	            		conAdmin.mostrarMenu();
	            	}catch( MensajedeErrorException e){
	            		System.out.println(e);
	            	}
	            	
	                break;
	            case 2:
	            	ConsolaEmpleadoGaleria conEmpleado = new ConsolaEmpleadoGaleria(this.galeria);
	            	try {         		
	            		conEmpleado.IniciarSesion();
	            		conEmpleado.mostrarMenu();
	            	}catch( Exception e){
	            		System.out.println(e);
	            	}
	                break;
	            case 3:
	            	ConsolaComprador conComprador = new ConsolaComprador(this.galeria);
	            	try {         		
	            		conComprador.IniciarSesion();
	            		conComprador.mostrarMenu();
	            	}catch( Exception e){
	            		System.out.println(e);
	            	}
	            	break;
	            case 4:
	            	ConsolaPropietario conPropietario = new ConsolaPropietario(this.galeria);
	            	try {         		
	            		conPropietario.IniciarSesion();
	            		conPropietario.mostrarMenu();
	            	}catch( Exception e){
	            		System.out.println(e);
	            	}
	            	break;
	            case 5:
	            	//Falta
	            	break;
	            case 6:
	            	System.out.println("Saliendo de la Aplicación...");
	                break;
	            default:
	                System.out.println("Opción inválida. Intente nuevamente.");
	                        }
                    } while (opcion != 6);
	        scanner.close();	        
        }catch(Exception e) {
        	System.out.println( "Hubo un error intente nuevamente" );
            mostrarMenu();
        }

    }
	
	public static void main(String[] args) {
		Galeria galeriaNueva = new Galeria();
		galeriaNueva.cargarGaleria(galeriaNueva);
		ConsolaInicial con = new ConsolaInicial(galeriaNueva);
		con.mostrarMenu();
	}
	

}
