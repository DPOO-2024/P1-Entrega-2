package Exceptions;

/**
 * Esta clase se usa pa enviar mensjaes de error, en donde puede variar el contexto.
 */
@SuppressWarnings("serial")
public class MensajedeErrorException extends Exception
{

    public MensajedeErrorException( String mensaje )
    {
        super( mensaje );
    }

}
