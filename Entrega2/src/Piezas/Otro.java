package Piezas;

public class Otro extends Pieza{
	public static final String OTRO="Otro";
	private String infoExtra;
	
	public Otro(String titulot, int aniot, String lugarDeCreaciont, String autores, boolean modalidadt, int fechaMaxt,
			int valorInicialt, String ubicaciont, boolean vendidot, int valorFijot,String infoExtrat) {
		
		super(titulot, aniot, lugarDeCreaciont, autores, modalidadt, fechaMaxt, valorInicialt, ubicaciont, vendidot,
				valorFijot);
		this.infoExtra=infoExtrat;
	}

	//Getters y Setters
	public String getInfoExtra() {
		return this.infoExtra;
	}

	public void setInfoExtra(String infoExtra) {
		this.infoExtra = infoExtra;
	}

	public static String getOtro() {
		return OTRO;
	}

	

}