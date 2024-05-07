package Consola;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
import Modelo.Subasta;
import Usuarios.Comprador;
import Piezas.Pieza;

public class ConsolaSubasta {

	private Galeria gal;
	private Subasta subasta;
	
	public ConsolaSubasta(Galeria g,Subasta s) {
		this.gal=g;
		this.subasta=s;
	}
	
	public void iniciarOferta(Comprador c) throws MensajedeErrorException {

		System.out.println("Esta es la oferta de piezas : ");
		gal.imprimirPiezas(this.subasta.getInventario());
		if (this.subasta.isActiva()) {
			System.out.println("Por favor, ingrese si esta interesado en hacer una oferta para una pieza (Si o No): ");
			String ofertar = ConsolaInicial.scanner.nextLine().trim();


			if (ofertar.equalsIgnoreCase("Si") | ofertar.equalsIgnoreCase("Sí") ) {

				System.out.println("Ingrese el numero de la pieza : ");
				String id= ConsolaInicial.scanner.nextLine().trim();
				int idx=Integer.parseInt(id);
				Pieza pieza = this.subasta.getInventario().get(idx-1);
				if (!pieza.equals(null)) {
					int valorI = pieza.getValorInicial();
					System.out.println("Por favor, ingrese su oferta, recuerde que el valor inicial de esta pieza es de " + valorI + ":");
					String oferta = ConsolaInicial.scanner.nextLine().trim();
					System.out.println("Por favor, ingrese su forma de pago si gana la subasta" );
					String formaPago = ConsolaInicial.scanner.nextLine();
					c.hacerOferta(this.gal.getAdmin(),oferta,formaPago,this.subasta.getOperador(), pieza);
					
				}
				else {
					throw new MensajedeErrorException("La pieza ingresada no esta disponible en esta subasta");
				}

				
			}
			else if (ofertar.equalsIgnoreCase("No") ) {
				this.subasta.quitarComprador(c);
				System.out.println("Ya no participas en la subasta ");
			}
			else {
				throw new MensajedeErrorException("No es una respuesta valida");
			}
	}
		else {
			throw new MensajedeErrorException("La subasta no esta activa");
		}
	
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
