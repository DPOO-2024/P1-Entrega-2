package Exceptions;

/**
 * Esta clase se usa pa enviar mensjaes de error, en donde puede variar el contexto.
 */
@SuppressWarnings("serial")
public class MesajedeErrorException extends Exception
{

    public MesajedeErrorException( String mensaje )
    {
        super( mensaje );
    }

}
