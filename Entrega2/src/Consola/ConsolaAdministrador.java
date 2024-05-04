package Consola;

import java.util.List;
import java.util.Scanner;

import Exceptions.MensajedeErrorException;
import Modelo.Empleado;
import Modelo.Galeria;
import Modelo.Subasta;
import Piezas.Pieza;
import Usuarios.Cajero;
import Usuarios.Operador;

public class ConsolaAdministrador implements ConsolaBase{
	private Galeria gal;
	private String login;
	private String password;
	
	public ConsolaAdministrador(Galeria g) {
		this.gal=g;
		this.login="";
		this.password="";
	}
	
	@Override
	public void mostrarMenu() {
	
        int opcion;

        do {
            System.out.println("\n\n**Menú Administrador**");
            System.out.println("1. Guardar Galería");
            System.out.println("2. Crear Subasta");
            System.out.println("3. Agregar Empleado");
            System.out.println("4. Ver Piezas Disponibles");
            System.out.println("5. Ver Historial de todas las Piezas (No disponibles)");
            System.out.println("6. Ver Historial de una Pieza");
            System.out.println("7. Ver Historial de un Artista");
            System.out.println("8. Ver Historial de un Cliente");
            System.out.println("9. Terminar subasta");
            System.out.println("10. Asignar Cajero");
            System.out.println("11. Asignar nuevo Administrador");
            System.out.println("12. Cerrar sesión");
            System.out.print("Ingrese una opción: ");
  
	            try {
	            String input = ConsolaInicial.scanner.nextLine();
	            opcion = Integer.parseInt(input);

	            switch (opcion) {
	                case 1:
	                    this.gal.guardarGaleria();
	                    break;
	                case 2:
	                    crearSubasta();
	                    break;
	                case 3:
	                	agregarEmpleado();
	                    break;
	                case 4:
	                	this.gal.mostrarPiezasDisponibles();
	                	break;
	                case 5:
	                	this.gal.mostrarHistorialPiezas();
	                    break;
	                case 6:
	                	ConsolaInfo.mostrarHistorialPieza(this.gal);
	                    break;
	                case 7:
	                	ConsolaInfo.mostrarHistorialArtista(this.gal);
	                	break;
	                case 8:
	                	mostrarHistorialCliente();
	                    break;
	                case 9:
	                	terminarSubasta();
	                    break;
	                case 10:
	                    asignarCajero();
	                    break;
	                case 11:
	                    asignarNuevoAdministrador();
	                    break;
	                case 12:
	                    System.out.println("Cerrando sesión...");
                    break;
                default:
                	System.out.println("Opción inválida. Intente nuevamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número entero.");
            opcion = -1;
            
            }
        } while (opcion != 12);
	}


	@Override
	public void iniciarSesion() throws MensajedeErrorException {
		System.out.print("Inicio de sesión de Administrador... ");
		System.out.print("\nIngrese login : ");
		String login = ConsolaInicial.scanner.nextLine().trim();
		System.out.print("\nIngrese su contraseña : ");
		String password= ConsolaInicial.scanner.nextLine().trim();
		if (login.equals(this.gal.getAdmin().getLogin()) && password.equals(this.gal.getAdmin().getPassword())) {
			System.out.print("\nIngreso Exitoso ");
			this.login=login;
			this.password=password;
		}
		else {
			throw new MensajedeErrorException("Los datos ingresados para iniciar sesion como administrador fueron erroneos");
		}

		
	}
	
	public void crearSubasta() {
		try {			
			System.out.print("Ingrese la fecha (AAMMDD)en la que desea realizar la subasta : ");
			String fechat = ConsolaInicial.scanner.nextLine().trim();
			int fecha=Integer.parseInt(fechat);
			this.gal.crearSubasta(fecha);	
			System.out.print("Subasta creada ");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void terminarSubasta() {
		try {
			System.out.print("Ingrese la fecha (AAMMDD) de la subasta que desea finalizar : ");
			String fechat = ConsolaInicial.scanner.nextLine().trim();
			int fecha=Integer.parseInt(fechat);
			this.gal.terminarSubasta(fecha);
			System.out.print("La subasta de la fecha "+fechat+ " se elimino con exito.");

		}
		catch(MensajedeErrorException e) {
			System.out.println(e);
		}
	}
	
	public void agregarEmpleado() {
    	try {
    		System.out.print("Por favor, ingrese el login del empleado: ");
    		String loginE = ConsolaInicial.scanner.nextLine().trim();
    		System.out.print("Por favor, ingrese la contraseña del empleado: ");
    		String passwordE = ConsolaInicial.scanner.nextLine().trim();
    		System.out.print("Por favor, ingrese si es Operador o empleado: ");
    		String rol = ConsolaInicial.scanner.nextLine().trim();
    		this.gal.agregarEmpleado(loginE,passwordE,rol);
			System.out.print("Empleado agregado con exito ");
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	public void asignarNuevoAdministrador() {
		try {
			System.out.print("Por favor, ingrese el login del nuevo Administrador: ");
	        String login = ConsolaInicial.scanner.nextLine().trim();
	        System.out.print("Por favor, ingrese la contraseña del nuevo Administrador: ");
	        String password = ConsolaInicial.scanner.nextLine().trim();
	        this.gal.asignarAdministrador(login,password);
	        System.out.print("El nuevo administrador fue asignado con exito ");
	        
		}catch(Exception e) {
			System.out.println(e);
		}
	}
		

	public void asignarCajero() {
    	try {
    		System.out.print("Por favor, ingrese el login del Cajero: ");
    		String loginC = ConsolaInicial.scanner.nextLine().trim();
    		System.out.print("Por favor, ingrese la contraseña del Cajero: ");
    		String passwordC = ConsolaInicial.scanner.nextLine().trim();
    		this.gal.asignarCajero(loginC,passwordC);
    		System.out.print("El cajero fue asignado con exito ");
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
		
	}
	
	
	public void mostrarHistorialCliente() {
		try {
    		System.out.print("Por favor, ingrese el login del Usuario: ");
    		String loginU = ConsolaInicial.scanner.nextLine().trim();
    		//Llamar funcion
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	
	public static void main(String[] args) {
		//Esto como seria 
	}
}