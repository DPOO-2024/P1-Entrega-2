package Consola;

import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.LoginDuplicadoException;
import Exceptions.MensajedeErrorException;
import Modelo.Galeria;

public class ConsolaInicial {
	private Galeria galeria;
	public static Scanner scanner;
	
	public ConsolaInicial(Galeria galeriat) {
		this.galeria=galeriat;
		scanner= new Scanner(System.in);
	}
	
	public void mostrarMenu()
    {
		int opcion;
        try {
	        do {
	        	System.out.println("\n**Bienvenido a la Galeria "+this.galeria.getNombre() +" **");
	            System.out.println("\n**Menú Inicio**");
	            System.out.println("\n**Seleccione la opción de como va a iniciar sesión**");
	            System.out.println("1. Administrador");
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
	            		conAdmin.iniciarSesion();
	            		conAdmin.mostrarMenu();
	            	}catch( MensajedeErrorException e){
	            		System.out.println(e);
	            	}
	            	
	                break;
	            case 2:
	            	ConsolaEmpleadoGaleria conEmpleado = new ConsolaEmpleadoGaleria(this.galeria);
	            	try {         		
	            		conEmpleado.iniciarSesion();
	            		conEmpleado.mostrarMenu();
	            	}catch( Exception e){
	            		System.out.println(e);
	            	}
	                break;
	            case 3:
	            	ConsolaComprador conComprador = new ConsolaComprador(this.galeria);
	            	try {         		
	            		conComprador.iniciarSesion();
	            		conComprador.mostrarMenu();
	            	}catch( Exception e){
	            		System.out.println(e);
	            	}
	            	break;
	            case 4:
	            	ConsolaPropietario conPropietario = new ConsolaPropietario(this.galeria);
	            	try {         		
	            		conPropietario.iniciarSesion();
	            		conPropietario.mostrarMenu();
	            	}catch( Exception e){
	            		System.out.println(e);
	            	}
	            	break;
	            case 5:
	            	agregarUsuario();
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
	
	
	public void agregarUsuario() {

		try {
			System.out.print("Por favor, ingrese si quiere registrarse como Comprador o Propietario: ");
			String rol = scanner.nextLine().trim();
			System.out.print("Por favor, ingrese su login: ");
			String login = scanner.nextLine().trim();
			
			this.galeria.getAdmin().verificarLogin(login, rol);
			ArrayList<String> info = ConsolaInfo.pedirInfoUsuario();
			info.add(login);
			
			this.galeria.getAdmin().agregarUsuario(info, rol);
			
		} catch (LoginDuplicadoException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void main(String[] args) {
		Galeria galeriaNueva = new Galeria();
		galeriaNueva.cargarGaleria(galeriaNueva);
		ConsolaInicial con = new ConsolaInicial(galeriaNueva);
		con.mostrarMenu();
	}
	

}
