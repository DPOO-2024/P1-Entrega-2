package Exceptions;


@SuppressWarnings("serial")
public class LoginDuplicadoException extends MesajedeErrorException
{

    
    private String loginDuplicado;

    public LoginDuplicadoException( String codigo )
    {
        super(codigo );
        this.loginDuplicado = codigo;
    }

    @Override
    public String getMessage( )
    {
        return "El login '" + loginDuplicado + "' está repetido";
    }
}
