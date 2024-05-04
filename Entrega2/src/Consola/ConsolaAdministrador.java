package Consola;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void IniciarSesion() throws MensajedeErrorException {
		System.out.print("Inicio de sesión de Administrador : ");
		System.out.print("Ingrese login : ");
		String login = ConsolaInicial.scanner.nextLine().trim();
		System.out.print("Ingrese su contraseña : ");
		String password= ConsolaInicial.scanner.nextLine().trim();
		if (login.equals(this.gal.getAdmin().getLogin()) && password.equals(this.gal.getAdmin().getPassword())) {
			System.out.print("Ingreso Exitoso ");
			this.login=login;
			this.password=password;
		}
		else {
			throw new MensajedeErrorException("Los datos ingresados para iniciar sesion como administrador fueron erroneos");
		}

		
	}

	
	public static void main(String[] args) {
		
	}
}
