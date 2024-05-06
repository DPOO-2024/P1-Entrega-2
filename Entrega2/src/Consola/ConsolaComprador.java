package Consola;


import java.util.ArrayList;
import java.util.List;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Modelo.Subasta;
import Piezas.Pieza;
import Usuarios.Comprador;
import Usuarios.Operador;

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
		System.out.print("\nInicio de sesión de Comprador... ");
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
		    
		    this.comprador.comprarPieza(idx,formapago,this.gal);
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
			Subasta subasta =this.gal.participarSubasta(fecha,this.comprador);
			ConsolaSubasta consolaSub = new ConsolaSubasta(this.gal,subasta);
			consolaSub.iniciarOferta(this.comprador);
			
		}
		catch(MensajedeErrorException e) {
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}        
	
	public void revisarSubasta() {
		try {
		System.out.print("Ingrese la fecha (AAMMDD) de la subasta en la que quiere participar : ");
		String fechat = ConsolaInicial.scanner.nextLine().trim();
		int fecha=Integer.parseInt(fechat);
		Subasta subasta = this.gal.encontrarSubasta(fecha);
		if (!subasta.equals(null)) {

			if(subasta.isActiva()) {
				int i = 1;
				for(Pieza pieza:subasta.getInventario()) {
					System.out.println("\n \n"+i+". " + pieza.getTitulo());
					i++;}
				System.out.print("Ingrese el numero de la pieza : ");
				String pi= ConsolaInicial.scanner.nextLine().trim();
				int idx=Integer.parseInt(pi);
				Pieza p =subasta.getInventario().get(idx-1);
				
				if (!p.equals(null)) {
					Operador op = subasta.getOperador();
					int valorMax = op.mayorOferta(p);
					System.out.println("Si tu oferta fue menor a " + valorMax +" perdiste, quieres volver a hacer una oferta ? ");
					String rta = ConsolaInicial.scanner.nextLine().trim();

					if (rta.equalsIgnoreCase("Si") ) {
						System.out.print("Por favor, ingrese su oferta :");
						String oferta = ConsolaInicial.scanner.nextLine().trim();
						System.out.print("Por favor, ingrese su forma de pago si gana la subasta" );
						String formaPago = ConsolaInicial.scanner.nextLine();
						this.comprador.hacerOferta(this.gal.getAdmin(),oferta,formaPago,subasta.getOperador(),p);
					}
						
					
					else {
						System.out.println("Ya no participas en la subasta");
					}
				}
			}
			else {
				System.out.println("Los ganadores son:");
				for (String ganador:subasta.getGanadores()) {
					System.out.println(ganador);
				}
			}
		}
		else {
			throw new MensajedeErrorException("No existe una subasta en esa fecha");
		}
	}catch( MensajedeErrorException e) {
		System.out.println(e);}
	}
	
	public void revisarComprasRealizadas() {
		try {
			System.out.println("Las piezas que ha comprado son: \n ");
			ArrayList<String> listaPiezas=this.comprador.getHistorialCompras();
			if (listaPiezas.size()>1) {
				for (int i=0; i<listaPiezas.size(); i+=3) {
					Pieza p = this.gal.getInventario().getPieza(listaPiezas.get(i));
					ArrayList<Pieza> pieza = new ArrayList<Pieza>();
					pieza.add(p);
					
					this.gal.imprimirPiezas(pieza);
					System.out.println("\nEsta pieza fue comprada el "+ listaPiezas.get(i+1));
					System.out.println("\nEsta pieza fue comprada por el precio de "+ listaPiezas.get(i+2));
				}
			}
			else {
				System.out.println("No ha adquirido ninguna pieza aun.");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
		
	
}
