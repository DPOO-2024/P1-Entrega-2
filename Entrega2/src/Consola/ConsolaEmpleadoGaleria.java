package Consola;

import Exceptions.MensajedeErrorException;
import Modelo.Empleado;
import Modelo.Galeria;

public class ConsolaEmpleadoGaleria implements ConsolaBase {
	private Galeria gal;
	private Empleado empleado;
	
	public ConsolaEmpleadoGaleria(Galeria g) {
		this.gal=g;
		this.empleado=null;
	}
	
	@Override
	public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n\n**Menú Empleado**");
            System.out.println("1. Guardar Galería");
            System.out.println("2. Verificar tipo de empleado");
            System.out.println("3. Ver Piezas Disponibles");
            System.out.println("4. Ver Historial de todas las Piezas (No disponibles)");
            System.out.println("5. Ver Historial de una Pieza");
            System.out.println("6. Ver Historial de un Artista");
            System.out.println("7. Cerrar sesión");
            System.out.print("Ingrese una opción: ");
  
	            try {
	            String input = ConsolaInicial.scanner.nextLine();
	            opcion = Integer.parseInt(input);

	            switch (opcion) {
	                case 1:
	                    this.gal.guardarGaleria();
	                    break;
	                case 2:
	                    verificarTipodeEmpleado();
	                    break;
	                case 3:
	                	this.gal.mostrarPiezasDisponibles();
	                	break;
	                case 4:
	                	this.gal.mostrarHistorialPiezas();
	                    break;
	                case 5:
	                	ConsolaInfo.mostrarHistorialPieza(this.gal);
	                    break;
	                case 6:
	                	ConsolaInfo.mostrarHistorialArtista(this.gal);
	                	break;
	                case 7:
	                    System.out.println("Cerrando sesión...");
                    break;
                default:
                	System.out.println("Opción inválida. Intente nuevamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número entero.");
            opcion = -1;
            
            }
        } while (opcion != 8);
	}
		
	
	@Override
	public void iniciarSesion() throws MensajedeErrorException {
		System.out.print("\nInicio de sesión de Empleado de la Galeria... ");
		System.out.print("\nIngrese login : ");
		String login = ConsolaInicial.scanner.nextLine().trim();
		System.out.print("\nIngrese su contraseña : ");
		String password= ConsolaInicial.scanner.nextLine().trim();
		try {
			this.empleado=this.gal.verificarEmpleado(login, password);
			System.out.print("\nIngreso Exitoso ");	
		} catch (MensajedeErrorException e) {
			throw e;
		}
	}
	
	public void verificarTipodeEmpleado() {
		String rol = this.empleado.getRol();
		if (rol.equals("None")) {
			System.out.println("No tienes ningun rol extra en la Galeria");
		}
		else {
			System.out.println("Tienes el rol extra en la Galeria de "+rol);

		}
		
	}
	

}
