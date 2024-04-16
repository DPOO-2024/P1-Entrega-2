package Exceptions;

import Piezas.Pieza;


@SuppressWarnings("serial")
public class PiezaSobrevendidoException extends Exception
{

    public PiezaSobrevendidoException( Pieza pieza )
    {
        super( "la pieza " + pieza.getTitulo() + "ya fue vendida" );
    }

}
