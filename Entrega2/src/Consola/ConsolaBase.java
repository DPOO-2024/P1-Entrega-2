package Consola;

import Exceptions.MensajedeErrorException;

public interface ConsolaBase {
	public void mostrarMenu();
	public void IniciarSesion() throws MensajedeErrorException;
}
