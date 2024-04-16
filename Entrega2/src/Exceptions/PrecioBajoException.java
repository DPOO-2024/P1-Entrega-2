package Exceptions;

import Piezas.Pieza;


@SuppressWarnings("serial")
public class PrecioBajoException extends Exception
{
	 public PrecioBajoException( Pieza pieza )
	    {
	        super( "la pieza " + pieza.getTitulo() + "no puede ser vendida por un precio menos a " + pieza.getValorInicial());
	    }

}
