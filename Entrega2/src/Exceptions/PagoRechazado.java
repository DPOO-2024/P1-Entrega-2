package Exceptions;

import Modelo.Pago;



@SuppressWarnings("serial")
public class PagoRechazado extends Exception
{
	public PagoRechazado( Pago pago )
    {
        super( "el pago fue rechazado" );
    }
}
