package Exceptions;

import Piezas.Pieza;


@SuppressWarnings("serial")
public class PiezaRepetidaException extends Exception
{
	 public PiezaRepetidaException( Pieza pieza )
	    {
	        super( "la pieza " + pieza.getTitulo() + " ya esta en el inventario" );
	    }

}
