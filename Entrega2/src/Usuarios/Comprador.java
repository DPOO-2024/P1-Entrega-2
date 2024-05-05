package Usuarios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Exceptions.MensajedeErrorException;
import Exceptions.PagoRechazado;
import Exceptions.PiezaRepetidaException;
import Exceptions.PrecioBajoException;
import Modelo.Administrador;
import Modelo.Galeria;
import Modelo.Pago;
import Modelo.Subasta;
import Piezas.Pieza;

public class Comprador extends Usuario{
	
	public static final String COMPRADOR ="Comprador";
	
	private String nombre;
	
	private String correo;
	
	private int telefono;
	
	private int comprasTotales;
	
	private int comprasMaximas;
	
	private ArrayList<String> historialCompras; //nombrePieza,fecha
	
	public Comprador(String login, String password,String nombre, String correo,int telefono,int comprasTotales, int comprasMaximas) {
		super(login, password, "Comprador");
		this.telefono=telefono;
		this.nombre = nombre;
		this.correo=correo;
		this.comprasTotales=comprasTotales;
		this.comprasMaximas=comprasMaximas;
		this.historialCompras=new ArrayList<String>();
	}
	
	
	
    public void comprarPieza(int idx,String formapago, Galeria gal)throws MensajedeErrorException, PagoRechazado, PiezaRepetidaException {
		ArrayList<Pieza> piezasDisponibles= gal.getInventario().getPiezasDisponibles();
		Pieza pieza =piezasDisponibles.get(idx-1);

    	try {
			if (pieza.getValorFijo()!=0) {
				gal.getInventario().reservarPieza(pieza);
			}
			else {
				throw new MensajedeErrorException("La pieza solo se puede vender en una subasta");
			}

    		if (!pieza.equals(null)) {
    			boolean confirmado = gal.getAdmin().confirmarVenta(pieza,this);
	            if ( confirmado){
	            	//Toca cambiar lo del añadir compra en los compradores para poder guardar la fecha
	            	if (gal.getCajero().generarPagoCajero(pieza.getValorFijo(),pieza,formapago,this)) {
	            		gal.getInventario().moverPieza(pieza);
	            		this.agregarCompra(pieza.getValorFijo());
	            		this.agregarPiezaCompra(pieza.getTitulo());
	            		Propietario pro =(Propietario) pieza.getPropietario();
	            		pro.venderPieza(pieza);
	            		pieza.setVendido(true);
	            		System.out.print("Pieza comprada ");
	            	}
	            	else {
	            		gal.getInventario().agregarPieza(pieza);
	            		throw new PagoRechazado();
	            	
	            	}
	            }
	            else {
	            	gal.getInventario().agregarPieza(pieza);
	            	throw new MensajedeErrorException("Superaste el numero de compras maximas contactate con el administrador");
	            }
        
    	
    		}	
    		else {
    			throw new MensajedeErrorException("La pieza no se encuentra disponible");
    		}
    		
    	}
    	catch(MensajedeErrorException e) {
    		throw e;
    	} catch (PagoRechazado e) {
			throw e;
		}
    	
    }
	
	
	
	public void agregarCompra(int precio)
	{
		this.comprasTotales+=precio;
	}
	
	public void agregarPiezaCompra( String tituloP)
	{
		this.historialCompras.add(tituloP);
		Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyMMdd");
        String fecha = formato.format(fechaActual);
		this.historialCompras.add(fecha);
	}
	
	
	public void hacerOferta(Administrador admin, String oferta, String formaPago, Operador operador, Pieza pieza) throws MensajedeErrorException {
		int valor=Integer.parseInt(oferta);
		int valorI = pieza.getValorInicial();
		List<Integer> valores = operador.listaValoresOferta(pieza);
		if (!(valores.contains(valor)) && valor>operador.mayorOferta(pieza) && valor>valorI) {
			operador.crearOferta(valor, this, pieza, formaPago, admin);
		}
		else {
			throw new MensajedeErrorException("Aumenta tu oferta");
		}
		
		
	}
	
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getComprasTotales() {
		return comprasTotales;
	}

	public void setComprasTotales(int comprasTotales) {
		this.comprasTotales = comprasTotales;
	}

	public int getComprasMaximas() {
		return comprasMaximas;
	}
	
	public void setHistorialCompras(ArrayList<String> piezas) {
		this.historialCompras = piezas;
	}

	public ArrayList<String> getHistorialCompras() {
		return this.historialCompras;
	}

	public void setComprasMaximas(int comprasMaximas) {
		this.comprasMaximas += comprasMaximas;
	}

	public static String getComprador() {
		return COMPRADOR;
	}



	



	


}