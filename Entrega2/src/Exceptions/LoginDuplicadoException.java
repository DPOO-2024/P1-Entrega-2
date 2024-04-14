package Exceptions;


@SuppressWarnings("serial")
public class LoginDuplicadoException extends Exception
{

    
    private String loginDuplicado;

    public LoginDuplicadoException( String codigo )
    {
        super( );
        this.loginDuplicado = codigo;
    }

    @Override
    public String getMessage( )
    {
        return "El login '" + loginDuplicado + "' está repetido";
    }
}
