package Modelo;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Exceptions.LoginDuplicadoException;
import Exceptions.MesajedeErrorException;
import Exceptions.PiezaRepetidaException;
import Piezas.Escultura;
import Piezas.Fotografia;
import Piezas.Impresion;
import Piezas.Otro;
import Piezas.Pieza;
import Piezas.Pintura;
import Piezas.Video;
import Usuarios.Cajero;
import Usuarios.Comprador;
import Usuarios.Operador;
import Usuarios.Propietario;
import Usuarios.Usuario;

@SuppressWarnings("resource")
public class Administrador {
	
	private String login;
	private String password;
	private Inventario inventario;
	private ArrayList<Comprador> compradores;
	private ArrayList<Propietario> propietarios;
	private ArrayList<String> loginsReservadosCompradores;
	private ArrayList<String> loginsReservadosPropietarios;
	
	
	public Administrador(String login, String contrasena, Inventario inventario) {
		this.login = login;
		this.password = contrasena;
		this.inventario=inventario;
		this.compradores = new ArrayList<Comprador>();
		this.propietarios = new ArrayList<Propietario>();
		this.loginsReservadosCompradores = new ArrayList<String>();
		this.loginsReservadosPropietarios = new ArrayList<String>();
	}

	
	//Falta Crear Subasta

	//M
	public boolean confirmarVenta(Pieza pieza, Comprador comprador) throws MesajedeErrorException {
				int valorMaximo = comprador.getComprasMaximas();
				int valorActual = comprador.getComprasTotales();
				if (valorMaximo< valorActual+pieza.getValorFijo()) {
					throw new MesajedeErrorException("Comprador excede compras maximas");
				}
				else {
						return true;
					

					}
				}
	
		
	

	public boolean verificarOferta(Oferta oferta) throws MesajedeErrorException {
		boolean rta = false;
		int valorMaximo = oferta.getComprador().getComprasMaximas();
		int valorActual = oferta.getComprador().getComprasTotales();
		if (valorMaximo< valorActual+oferta.getValorOferta()) {
			throw new MesajedeErrorException("Comprador excede compras maximas");
		}
	
		else {
			rta = true;
			
		}
		
		return rta;
	}
	
	//Verifica si la informacion ingresada es de un comprador
	public Comprador verificarComprador(String login2, String password2) throws MesajedeErrorException {
		Comprador comprador=null;
		for(Comprador c :compradores) {
			if(c.getLogin().equals(login2)&&c.getPassword().equals(password2)) {
				comprador = c;
			}
		}
		if (comprador == null) {
			throw new MesajedeErrorException("No estas registrado como comprador");
		}
		return comprador;
	}
	
	//Verifica si la informacion ingresada es de un propietario
	public Propietario verificarPropietario(String login2, String password2) throws MesajedeErrorException {
		Propietario propietario =null;
		for(Propietario p : propietarios) {
			if (p.getLogin().equals(login2)&&p.getPassword().equals(password2)) {
				propietario = p;
			}
		}
		if (propietario == null) {
			throw new MesajedeErrorException("No estas registrado como propietario");
		}
		return propietario;
	}
	
	public Propietario encontrarPropietario(String login) throws MesajedeErrorException {
		Propietario propietario =null;
		for(Propietario p : propietarios) {
			if (p.getLogin().equals(login)) {
				propietario = p;
			}
		}
		if (propietario == null) {
			throw new MesajedeErrorException("No existe ese propietario");
		}
		return propietario;
	}
	

	// le asigna uno de los operadores de la lista a una subasta o cambia un empleado que este en none
	public Operador asignarOperador(List<Empleado> empleados) throws Exception {
		try {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Si desea asignar un operador ya registrado ingrese 1 ");
			System.out.println("Si desea reasignar a un empleado como operador ingrese 2 ");
			String opci = scanner.nextLine();
			Operador operadorAsignado = null;
			boolean escogido = false;
			int i =0;
			if (opci.equals("1")) {

				while (!escogido && i < empleados.size()) {
					Empleado empleado = empleados.get(i);
					if (empleado.getRol().equals("Operador")) {
						Operador ope = (Operador) empleado;
						if (!ope.isAsignado()) {
							operadorAsignado=ope;
							escogido=true;
							ope.setAsignado(true);
						}
						else {
							ope = null;
						}
					}
					i++;
				}
			}

			else if (opci.equals("2")) {

				while (!escogido && i < empleados.size()) {
					Empleado empleado = empleados.get(i);
					if (empleado.getRol().equals("None")) {
						empleado.setRol("Operador");
						escogido = true;
						operadorAsignado=new Operador(empleado.getNombreUsuario(),empleado.getContraseña(),empleado.getRol());
						operadorAsignado.setAsignado(true);

					}
					i++;
				}


			}
			else {
				throw new MesajedeErrorException("No es una respuesta valida");
			}
			if (!escogido) {
				throw new MesajedeErrorException("No se encontro empleado disponible para asignar");
			}
			return operadorAsignado;    
		}
		catch(MesajedeErrorException e ) {
			throw e;
		}
		catch(Exception e ) {
			throw e;
		}
	}
	
	
	//Setters y Getters
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String contrasena) {
		this.password = contrasena;
	}

	
	public  ArrayList<Comprador> getCompradores() {
		return compradores;
	}

	public void setCompradores(ArrayList<Comprador> compradores) {
		this.compradores = compradores;
	}

	public ArrayList<Propietario> getPropietarios() {
		return propietarios;
	}

	public void setPropietarios(ArrayList<Propietario> propietarios) {
		this.propietarios = propietarios;
	}
	


	//Pedir info pieza
	public void pedirInfoPieza(Propietario pro) throws Exception {

		try {
			Scanner scanner = new Scanner(System.in);

			System.out.print("\nRecuerde que los tipos de pieza que tenemos presentes son los siguientes: ");
			System.out.print("\n- Escultura \n - Fotografia \n - Impresion \n - Pintura \n - Video \n - Otro");
			System.out.print("\n\nPor favor, ingrese el tipo de la Pieza: ");
			String tipoDePieza = scanner.nextLine().trim();
			
			

			System.out.print("Por favor, ingrese el titulo de la Pieza: ");
			String titulo = scanner.nextLine().trim();

			System.out.print("Por favor, ingrese el año de creación de la Pieza: ");
			String aniot = scanner.nextLine().trim();
			int anio=Integer.parseInt(aniot);

			System.out.print("Por favor, ingrese el lugar de creación de la Pieza: ");
			String lugarDeCreacion = scanner.nextLine().trim();

			System.out.print("Por favor, ingrese los autores (separados por comas) de la Pieza, si no se conoce el autor ingrese \"Anonimo\": ");
			String autorest = scanner.nextLine().trim();
			String[] autoresf = autorest.split(",");
			List<String> autores = Arrays.asList(autoresf);

			System.out.print("Por favor, ingrese si desea aplicar la modalidad de \"marginalidad\" (Si o No): ");
			String modalidadt = scanner.nextLine().trim();

			boolean modalidad = false;
			int fechaMax = 0;

			if (modalidadt.equals("Si") || modalidadt.equals("si")) {
				modalidad = true;
				System.out.print("Por favor, ingrese la fecha maxima, para la modalidad de \"marginalidad\" (AAMMDD): ");
				String fechaMaxt = scanner.nextLine().trim();
				fechaMax=Integer.parseInt(fechaMaxt);
			}

			System.out.print("Por favor, ingrese el valor inicial de la pieza, si desea que sea incluida en una subasta, si no ingrese 0: ");
			String valorInicialt = scanner.nextLine().trim();
			int valorInicial=Integer.parseInt(valorInicialt);

			System.out.print("Por favor, ingrese  si desea mostrar su Pieza (Si o No): ");
			String ubicaciont = scanner.nextLine().trim();

			String ubicacion = "Bodega";
			if (ubicaciont.equals("Si") || ubicaciont.equals("si")) {
				ubicacion = "Mostrador";
			}

			boolean vendido = false;

			System.out.print("Por favor, ingrese el valor fijo al que desea vender la Pieza, si no desea que tenga valor fijo ingrese 0: ");
			String valorFijot = scanner.nextLine().trim();
			int valorFijo=Integer.parseInt(valorFijot);

			Pieza pieza;


			if (tipoDePieza.equals("Escultura") || tipoDePieza.equals("escultura")) {

				System.out.print("Por favor, ingrese el alto de la Pieza: ");
				String altot = scanner.nextLine().trim();
				int alto=Integer.parseInt(altot);

				System.out.print("Por favor, ingrese el ancho de la Pieza: ");
				String anchot = scanner.nextLine().trim();
				int ancho=Integer.parseInt(anchot);

				System.out.print("Por favor, ingrese la profundidad de la Pieza: ");
				String profundidadt = scanner.nextLine().trim();
				int profundidad=Integer.parseInt(profundidadt);

				System.out.print("Por favor, ingrese los materiales (separados por comas) de la Pieza: ");
				String materialest = scanner.nextLine().trim();
				String[] materialesf = materialest.split(",");
				List<String> materiales = Arrays.asList(materialesf);

				System.out.print("Por favor, ingrese el peso de la Pieza: ");
				String pesot = scanner.nextLine().trim();
				int peso=Integer.parseInt(pesot);

				System.out.print("Por favor, ingrese su Pieza requiere electricidad (Si o No): ");
				String electricidadt = scanner.nextLine().trim();

				boolean electricidad = false;
				if (electricidadt.equals("Si") || electricidadt.equals("si")) {
					electricidad = true;
				}

				System.out.print("Por favor, ingrese alguna especificacion de la instalación: ");
				String instalacion = scanner.nextLine().trim();

				pieza=new Escultura("Escultura",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, 
						valorInicial, ubicaciont, vendido, valorFijo, alto, ancho, profundidad, materiales, peso, electricidad, instalacion);

				this.inventario.agregarPieza(pieza);
				pro.ingresarPieza(pieza);

			}
			else if (tipoDePieza.equals("Fotografia") || tipoDePieza.equals("fotografia")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese el tamaño de la Pieza (En pulgadas): ");
				String tamanio = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el alto de la Pieza (En pulgadas): ");
				String altot = scanner.nextLine().trim();
				int alto=Integer.parseInt(altot);
				
				System.out.print("Por favor, ingrese la resolución de la Pieza (En ppp): ");
				String resoluciont = scanner.nextLine().trim();
				int resolucion=Integer.parseInt(resoluciont);
				
				System.out.print("Por favor, ingrese la descripcion de la Pieza: ");
				String descripcion = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el formato de la Pieza: ");
				String formato = scanner.nextLine().trim();
				
				pieza=new Fotografia("Fotografia",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, ubicaciont, vendido, 
						valorFijo, tamanio, alto, resolucion, descripcion, formato);

				this.inventario.agregarPieza(pieza);
				pro.ingresarPieza(pieza);
				
			}else if (tipoDePieza.equals("Impresion") || tipoDePieza.equals("fotografia")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese el material de papel de la Pieza (En pulgadas): ");
				String materialPapel = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el tamaño de la Pieza (En pulgadas): ");
				String tamanio = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la resolución de la Pieza (En ppp): ");
				String resoluciont = scanner.nextLine().trim();
				int resolucion=Integer.parseInt(resoluciont);
				
				System.out.print("Por favor, ingrese la flexibilidad de la Pieza (Alta, media, baja): ");
				String flexibilidadt = scanner.nextLine().trim();
				int flexibilidad=Integer.parseInt(flexibilidadt);
				
				System.out.print("Por favor, ingrese la resistencia de la Pieza (Alta, media, baja): ");
				String formato = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la descripcion de la Pieza: ");
				String descripcion = scanner.nextLine().trim();
				
				pieza=new Impresion("Impresion",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, 
						ubicaciont, vendido, valorFijo, materialPapel, tamanio, resolucion, flexibilidadt, formato, descripcion);

				this.inventario.agregarPieza(pieza);
				pro.ingresarPieza(pieza);
				
			}else if (tipoDePieza.equals("Otro") || tipoDePieza.equals("otro")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese la información extra que haya de la Pieza: ");
				String infoExtra = scanner.nextLine().trim();
				
				
				pieza=new Otro("Otro",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, 
						ubicaciont, vendido, valorFijo, infoExtra);

				this.inventario.agregarPieza(pieza);
				pro.ingresarPieza(pieza);
				
			}else if (tipoDePieza.equals("Pintura") || tipoDePieza.equals("pintura")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese la tecnica de la Pieza: ");
				String tecnica = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el origen de la Pieza: ");
				String origen = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la descripcion de la Pieza: ");
				String descripcion = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la altura de la Pieza (En pulgadas): ");
				String altot = scanner.nextLine().trim();
				int alto=Integer.parseInt(altot);
				
				System.out.print("Por favor, ingrese el ancho de la Pieza (En pulgadas): ");
				String anchot = scanner.nextLine().trim();
				int ancho=Integer.parseInt(anchot);
				
				System.out.print("Por favor, ingrese la forma de la Pieza (Cuadrada, Rectancular, etc): ");
				String forma = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el tiempo de creación de la Pieza: ");
				String tiempoDeCreacion = scanner.nextLine().trim();
				
				pieza=new Pintura("Pintura",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, 
						ubicaciont, vendido, valorFijo, tecnica, origen, descripcion, alto, ancho, forma, tiempoDeCreacion);
						
				this.inventario.agregarPieza(pieza);
				pro.ingresarPieza(pieza);
				
			}else if (tipoDePieza.equals("Video") || tipoDePieza.equals("video")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese la duración de la Pieza (En minutos): ");
				String duraciont = scanner.nextLine().trim();
				int duracion=Integer.parseInt(duraciont);
				
				System.out.print("Por favor, ingrese el tamaño de la Pieza: ");
				String tamanio = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el idioma de la Pieza: ");
				String idioma = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la descripcion de la Pieza: ");
				String descripcion = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la resolución de la Pieza (En p): ");
				String resoluciont = scanner.nextLine().trim();
				int resolucion=Integer.parseInt(resoluciont);
				
				System.out.print("Por favor, ingrese el formato de la Pieza: ");
				String formato = scanner.nextLine().trim();

				pieza=new Video("Video",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, 
						valorInicial, ubicaciont, vendido, valorFijo, duracion, tamanio, idioma, descripcion, resolucion, formato);
						
				this.inventario.agregarPieza(pieza);
				pro.ingresarPieza(pieza);
				
			}
		}catch(PiezaRepetidaException e) {
			throw e;
		}catch(Exception e) {
			throw e;
		}

	}
	

//Pedir informacion para hacer usuario

	public void pedirInfoUsuario() throws LoginDuplicadoException  {
		try {
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Por favor, ingrese si quiere registrarse como Comprador o Propietario: ");
			String rol = scanner.nextLine().trim();
			

			System.out.print("Por favor, ingrese su login: "); //Falta cambiar esto para verificar que puedan ser compradores y propietarios
			String login = scanner.nextLine().trim();
			if (rol.equalsIgnoreCase("Comprador") && !this.loginsReservadosCompradores.contains(login)) {
				loginsReservadosCompradores.add(this.login);
			}
			else if (rol.equalsIgnoreCase("Propietario") && !this.loginsReservadosPropietarios.contains(login)) {
				loginsReservadosPropietarios.add(this.login);
			}
		
			else { throw new LoginDuplicadoException(login);

			}

			System.out.print("Por favor, ingrese su contraseña: ");
			String password = scanner.nextLine().trim();

			System.out.print("Por favor, su numero de telefono: ");
			String telefonof = scanner.nextLine().trim();
			int telefono=Integer.parseInt(telefonof);

			System.out.print("Por favor, ingrese su nombre : ");
			String nombre =  scanner.nextLine().trim();


			System.out.print("Por favor, ingrese su correo electronico: ");
			String correo = scanner.nextLine().trim();

			if (rol.equalsIgnoreCase("Comprador")) {

				Comprador comprador = new Comprador(login, password,nombre, correo,telefono,0,5000);
				this.compradores.add(comprador);
			}


			else if (rol.equalsIgnoreCase("propietario")){
				Propietario propietario = new Propietario(login, password,nombre, correo,telefono);
				this.propietarios.add(propietario);

			}
		}
		catch(LoginDuplicadoException e) {
			throw e;
		}
		catch(Exception e) {
			throw e;
		}

	}


	public void aumentarCupoComprador(String loginComprador, int aumento) {
		for(Comprador comprador:compradores) {
			if(comprador.getLogin().equals(loginComprador)) {
				comprador.setComprasMaximas(aumento);
			}
		}
		
	}







	
}
