package Consola;

import Exceptions.MensajedeErrorException;
import Modelo.Galeria;

public class ConsolaEmpleadoGaleria implements ConsolaBase {
	private Galeria gal;
	private String login;
	private String password;
	
	public ConsolaEmpleadoGaleria(Galeria g) {
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
		System.out.print("Inicio de sesión de Empleado de la Galeria : ");
		System.out.print("Ingrese login : ");
		String login = ConsolaInicial.scanner.nextLine().trim();
		System.out.print("Ingrese su contraseña : ");
		String password= ConsolaInicial.scanner.nextLine().trim();
		try {
			this.gal.verificarEmpleado(login, password);
			System.out.print("Ingreso Exitoso ");
		} catch (MensajedeErrorException e) {
			throw e;
		}
	}

}
