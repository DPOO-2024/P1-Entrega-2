package Consola;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iniciarSesion() throws MensajedeErrorException {
		System.out.print("Inicio de sesión de Propietario... ");
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
}
