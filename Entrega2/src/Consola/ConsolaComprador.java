package Consola;


import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Usuarios.Comprador;

public class ConsolaComprador implements ConsolaBase{
	private Galeria gal;
	private Comprador comprador;
	
	public ConsolaComprador(Galeria g) {
		this.gal=g;
		this.comprador=null;
	}
	
	@Override
	public void mostrarMenu() {
		
        int opcion;

        do {
            System.out.println("\n\n**Menú Comprador**");
            System.out.println("1. Comprar Pieza");
            System.out.println("2. Aumentar cupo Compras");
            System.out.println("3. Ver Piezas Disponibles");
            System.out.println("4. Ver Historia de una Pieza");
            System.out.println("5. Ver Historia de un Artista");
            System.out.println("6. Participar en una Subasta");
            System.out.println("7. Revisar Estado de la subasta");
            System.out.println("8. Ver compras realizadas");
            System.out.println("9. Cerrar sesión");
            System.out.print("Ingrese una opción: ");
  
	            try {
	            String input = ConsolaInicial.scanner.nextLine();
	            opcion = Integer.parseInt(input);

	            switch (opcion) {
	                case 1:
	                    comprarPieza();
	                    break;
	                case 2:
	                    aumentarCupo();
	                    break;
	                case 3:
	                	this.gal.mostrarPiezasDisponibles();
	                    break;
	                case 4:
	                	ConsolaInfo.mostrarHistorialPieza(this.gal);
	                	break;
	                case 5:
	                	ConsolaInfo.mostrarHistorialArtista(this.gal);
	                    break;
	                case 6:
	                	participarSubasta();
	                    break;
	                case 7:
	                	revisarSubasta();
	                	break;
	                case 8:
	                	revisarComprasRealizadas();
	                    break;
	                case 9:
	                    System.out.println("Cerrando sesión...");
                    break;
                default:
                	System.out.println("Opción inválida. Intente nuevamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número entero.");
            opcion = -1;
            
            }
        } while (opcion != 9);
	}

		

	@Override
	public void iniciarSesion() throws MensajedeErrorException {
		System.out.print("Inicio de sesión de Comprador... ");
		System.out.print("\nIngrese login : ");
		String login = ConsolaInicial.scanner.nextLine().trim();
		System.out.print("\nIngrese su contraseña : ");
		String password= ConsolaInicial.scanner.nextLine().trim();
		try {
			this.comprador=this.gal.getAdmin().verificarComprador(login, password);
			System.out.print("\nIngreso Exitoso ");
		} catch (MensajedeErrorException e) {
			throw e;
		}
		
	}
	
	public void comprarPieza() {
		try {
			this.gal.mostrarPiezasDisponibles();
			System.out.print("Ingrese el numero de la pieza : ");
			String pi= ConsolaInicial.scanner.nextLine().trim();
			int idx=Integer.parseInt(pi);
			System.out.print("Por favor, ingrese la forma de pago ");
		    String formapago = ConsolaInicial.scanner.nextLine().trim();
		    
		    this.gal.comprarPieza(idx,formapago,this.comprador);
		    System.out.print("\nLa compra de la pieza fue exitosa ");
		}catch (Exception e) {
			System.out.println(e); 
		}
		
	}
	
	public void aumentarCupo() {
		try {
			System.out.print("Ingrese el valor de aumento : ");
			String valort= ConsolaInicial.scanner.nextLine().trim();
			int valor=Integer.parseInt(valort);
			
			this.gal.aumentarCupo(this.comprador.getLogin(), valor);
			System.out.print("\nEl aumento de cupo fue exitoso");
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void participarSubasta() {
		try {
			System.out.print("Ingrese la fecha (AAMMDD) de la subasta en la que quiere participar : ");
			String fechat = ConsolaInicial.scanner.nextLine().trim();
			int fecha=Integer.parseInt(fechat);	
			this.gal.participarSubasta(fecha,this.comprador);
			
			//M
			
		}
		catch(MensajedeErrorException e) {
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}        
	
	public void revisarSubasta() {
		
	}
	
	public void revisarComprasRealizadas() {
		System.out.println("Las piezas que ha comprado son: \n ");
		this.gal.imprimirPiezas(this.comprador.getHistorialCompras());
	}
}
