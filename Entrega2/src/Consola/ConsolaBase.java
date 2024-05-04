package Consola;

import Exceptions.MensajedeErrorException;

public interface ConsolaBase {
	public void mostrarMenu();
	public void iniciarSesion() throws MensajedeErrorException;
}
