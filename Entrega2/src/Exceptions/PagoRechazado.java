package Exceptions;

import Modelo.Pago;



@SuppressWarnings("serial")
public class PagoRechazado extends Exception
{
	public PagoRechazado( )
    {
        super( "el pago fue rechazado" );
    }
}
