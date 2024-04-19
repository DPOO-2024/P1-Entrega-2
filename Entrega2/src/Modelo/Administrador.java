package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Exceptions.LoginDuplicadoException;
import Exceptions.MesajedeErrorException;
import Piezas.Escultura;
import Piezas.Pieza;
import Usuarios.Cajero;
import Usuarios.Comprador;
import Usuarios.Operador;
import Usuarios.Propietario;
import Usuarios.Usuario;

public class Administrador {
	
	private String login;
	private String password;
	private Inventario inventario;
	private ArrayList<Comprador> compradores;
	private ArrayList<Propietario> propietarios;
	private ArrayList<String> loginsReservados;
	
	
	public Administrador(String login, String contrasena, Inventario inventario) {
		this.login = login;
		this.password = contrasena;
		this.inventario=inventario;
		this.compradores = new ArrayList<Comprador>();
		this.propietarios = new ArrayList<Propietario>();
		this.loginsReservados = new ArrayList<String>();
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
		int valorActual = oferta.getComprador().getComprasMaximas();
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

			System.out.print("Si desea asignar un operador ya registrado ingrese 1 ");
			System.out.print("Si desea reasignar a un empleado como operador ingrese 2 ");
			String opcion = scanner.nextLine();
			Operador operadorAsignado = null;
			boolean escogido = false;
			int i =0;
			if (opcion.equals("1")) {

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
				}
			}

			else if (opcion.equals("2")) {

				while (!escogido && i < empleados.size()) {
					Empleado empleado = empleados.get(i);
					if (empleado.getRol().equals("None")) {
						empleado.setRol("Operador");
						escogido = true;
						operadorAsignado=(Operador) empleado;// se puede castear?
						operadorAsignado.setAsignado(true);

					}
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
	public void pedirInfoPieza(Propietario pro) {

		try {
			Scanner scanner = new Scanner(System.in);

			System.out.print("Por favor, ingrese el tipo de la Pieza: ");
			String tipoDePieza = scanner.nextLine();

			System.out.print("Por favor, ingrese el titulo de la Pieza: ");
			String titulo = scanner.nextLine();

			System.out.print("Por favor, ingrese el año de creación de la Pieza: ");
			String aniot = scanner.nextLine();
			int anio=Integer.parseInt(aniot);

			System.out.print("Por favor, ingrese el lugar de creación de la Pieza: ");
			String lugarDeCreacion = scanner.nextLine();

			System.out.print("Por favor, ingrese los autores (separados por comas) de la Pieza, si no se conoce el autor ingrese \"Anonimo\": ");
			String autorest = scanner.nextLine();
			String[] autoresf = autorest.split(",");
			List<String> autores = Arrays.asList(autoresf);

			System.out.print("Por favor, ingrese si desea aplicar la modalidad de \"marginalidad\" (Si o No): ");
			String modalidadt = scanner.nextLine();

			boolean modalidad = false;
			int fechaMax = 0;

			if (modalidadt.equals("Si") || modalidadt.equals("si")) {
				modalidad = true;
				System.out.print("Por favor, ingrese la fecha maxima, para la modalidad de \"marginalidad\" (AA/MM/DD): ");
				String fechaMaxt = scanner.nextLine();
				fechaMax=Integer.parseInt(fechaMaxt);
			}

			System.out.print("Por favor, ingrese el valor inicial de la pieza, si desea que sea incluida en una subasta, si no ingrese 0: ");
			String valorInicialt = scanner.nextLine();
			int valorInicial=Integer.parseInt(valorInicialt);

			System.out.print("Por favor, ingrese  si desea mostrar su Pieza (Si o No): ");
			String ubicaciont = scanner.nextLine();

			String ubicacion = "Bodega";
			if (ubicaciont.equals("Si") || ubicaciont.equals("si")) {
				ubicacion = "Mostrador";
			}

			boolean vendido = false;

			System.out.print("Por favor, ingrese el valor fijo al que desea vender la Pieza, si no desea que tenga valor fijo ingrese 0: ");
			String valorFijot = scanner.nextLine();
			int valorFijo=Integer.parseInt(valorFijot);

			Pieza pieza;


			if (tipoDePieza.equals("Escultura") && tipoDePieza.equals("escultura")) {

				System.out.print("Por favor, ingrese el alto de la Pieza: ");
				String altot = scanner.nextLine();
				int alto=Integer.parseInt(altot);

				System.out.print("Por favor, ingrese el ancho de la Pieza: ");
				String anchot = scanner.nextLine();
				int ancho=Integer.parseInt(anchot);

				System.out.print("Por favor, ingrese la profundidad de la Pieza: ");
				String profundidadt = scanner.nextLine();
				int profundidad=Integer.parseInt(profundidadt);

				System.out.print("Por favor, ingrese los materiales (separados por comas) de la Pieza: ");
				String materialest = scanner.nextLine();
				String[] materialesf = materialest.split(",");
				List<String> materiales = Arrays.asList(materialesf);

				System.out.print("Por favor, ingrese el peso de la Pieza: ");
				String pesot = scanner.nextLine();
				int peso=Integer.parseInt(pesot);

				System.out.print("Por favor, ingrese su Pieza requiere electricidad (Si o No): ");
				String electricidadt = scanner.nextLine();

				boolean electricidad = false;
				if (electricidadt.equals("Si") || electricidadt.equals("si")) {
					electricidad = true;
				}

				System.out.print("Por favor, ingrese alguna especificacion de la instalación: ");
				String instalacion = scanner.nextLine();

				pieza=new Escultura((Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, 
						valorInicial, ubicaciont, vendido, valorFijo, alto, ancho, profundidad, materiales, peso, electricidad, instalacion);




				this.inventario.agregarPieza(pieza);
				pro.ingresarPieza(pieza);

			}
			else if (tipoDePieza.equals("Fotografia") && tipoDePieza.equals("fotografia")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
			}
		}catch(Exception e) {
			throw e;
		}



	}
	

//Pedir informacion para hacer usuario

	public void pedirInfoUsuario() throws LoginDuplicadoException  {
		try {
			Scanner scanner = new Scanner(System.in);

			System.out.print("Por favor, ingrese su login: "); //Falta cambiar esto para verificar que puedan ser compradores y propietarios
			String login = scanner.nextLine();
			if (!this.loginsReservados.contains(login)) {
				loginsReservados.add(this.login);
			}
			else { throw new LoginDuplicadoException(login);

			}

			System.out.print("Por favor, ingrese su contraseña: ");
			String password = scanner.nextLine();


			System.out.print("Por favor, ingrese si quiere registrarse como Comprador o Propietario: ");
			String rol = scanner.nextLine();



			System.out.print("Por favor, su numero de telefono: ");
			String telefonof = scanner.nextLine();
			int telefono=Integer.parseInt(telefonof);

			System.out.print("Por favor, ingrese su nombre : ");
			String nombre =  scanner.nextLine();


			System.out.print("Por favor, ingrese su correo electronico: ");
			String correo = scanner.nextLine();

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







	
}
