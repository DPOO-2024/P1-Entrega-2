package Exceptions;

/**
 * Esta excepción se usa para indicar que se encontraron dos clientes con el mismo identificador, lo cual nunca debería ocurrir.
 */
@SuppressWarnings("serial")
public class EmpateSubastaException extends MesajedeErrorException
{
    
    private String cliente1;
    
    /**
     * El identificador de cliente que ya se había encontrado antes
     */
    private String cliente2;

    public EmpateSubastaException( String Cliente1, String cliente2 )
    {
        super( "" );
        this.cliente1 = Cliente1;
        this.cliente2 = cliente2;
    }

    @Override
    public String getMessage( )
    {
        return "Empate: " + cliente1 + " y " + cliente2 + "hicieron la oferta mas alta";
    }

}
