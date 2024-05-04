package Consola;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Piezas.Pieza;
import Usuarios.Propietario;

public class ConsolaPropietario implements ConsolaBase{
	private Galeria gal;
	private Propietario propietario;
	
	public ConsolaPropietario(Galeria g) {
		this.gal=g;
		this.propietario=null;
	}
	
	@Override
	public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n\n**Menú Propietario**");
            System.out.println("1. Añadir Pieza");
            System.out.println("2. Estado de Piezas Propias Disponibles ");
            System.out.println("3. Ver Historial de Piezas Propias (No disponibles)");
            System.out.println("4. Cerrar sesión");
            System.out.print("Ingrese una opción: ");
  
	            try {
	            String input = ConsolaInicial.scanner.nextLine();
	            opcion = Integer.parseInt(input);

	            switch (opcion) {
	                case 1:
	                    anadirPieza();
	                    break;
	                case 2:
	                    estadoPiezas();
	                    break;
	                case 3:
	                	historialPiezas();
	                    break;
	                case 4:
	                    System.out.println("Cerrando sesión...");
                    break;
                default:
                	System.out.println("Opción inválida. Intente nuevamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número entero.");
            opcion = -1;
            
            }
        } while (opcion != 4);
		
	}
		

	@Override
	public void iniciarSesion() throws MensajedeErrorException {
		System.out.print("\nInicio de sesión de Propietario... ");
		System.out.print("\nIngrese login : ");
		String login = ConsolaInicial.scanner.nextLine().trim();
		System.out.print("\nIngrese su contraseña : ");
		String password= ConsolaInicial.scanner.nextLine().trim();
		try {
			this.propietario=this.gal.getAdmin().verificarPropietario(login, password);
			System.out.print("\nIngreso Exitoso ");
		} catch (MensajedeErrorException e) {
			throw e;
		}
		
	}
	
	public void anadirPieza() {
		try {
			Pieza p = ConsolaInfo.pedirInfoPieza(this.propietario);
			this.gal.getAdmin().agregarPieza(p, this.propietario);
			System.out.println("La pieza se añadio con exito");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void estadoPiezas() {
		System.out.println("Las piezas que tiene disponible son: \n ");
		this.gal.imprimirPiezas(this.propietario.getEstadoPiezas());
	}
	
	public void historialPiezas() {
		System.out.println("El historial de sus piezas es: \n ");
		this.gal.imprimirPiezas(this.propietario.getHistorialPiezas());
	}
}
