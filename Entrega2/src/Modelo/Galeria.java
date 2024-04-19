package Modelo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import Exceptions.LoginDuplicadoException;
import Exceptions.MesajedeErrorException;
import Exceptions.PagoRechazado;
import Exceptions.PiezaRepetidaException;
import Usuarios.*;
import Piezas.*;
import Persistencia.CentralPersistencia;

@SuppressWarnings("resource")
public class Galeria {
	private Administrador admin;
	private String nombre;
	private Cajero cajero;
	private Operador operador;
	private Inventario inventario;
	private List<Empleado> empleados;
    private List<Subasta> subastasActivas;
    

	public Galeria() {
		this.nombre = "Galeria";
		this.admin = null;
		this.cajero = null;
		this.operador= null;
		this.inventario= new Inventario();
		this.empleados = new ArrayList<>();
        this.subastasActivas = new ArrayList<>();
	}
	

	//Muestra menú inicial a los Usuarios
	public void mostrarMenu() throws MesajedeErrorException, PagoRechazado,Exception
	{
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n**Menú Galería**");
            System.out.println("1. Guardar Galería");
            System.out.println("2. Crear Subasta");
            System.out.println("3. Registrarse como Comprador o Propietario");
            System.out.println("4. Crear Empledo");
            System.out.println("5. Añadir Pieza");
            System.out.println("6. Aumentar cupo Compras");
            System.out.println("7. Ver Piezas Disponibles");
            System.out.println("8. Comprar Pieza");
            System.out.println("9. Participar en una Subasta");
            System.out.println("10. Revisar Estado de la subasta");
            System.out.println("11. Terminar subasta");
            System.out.println("12. Asignar Cajero");
            System.out.println("13. Volver al menu Inicial");
            System.out.print("Ingrese una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    guardarGaleria();
                    break;
                case 2:
                    crearSubasta();
                    break;
                case 3:
                    registrarUsuario();
                    break;
                case 4:
                	agregarEmpleado();
                	break;
                case 5:
                    añadirPieza();
                    break;
                case 6:
                    aumentarCupo();
                    break;
                case 7:
                    mostrarPiezasDisponibles();
                    break;
                case 8:
                    comprarPieza();
                    break;
                case 9:
                	participarSubasta();
                	break;
                case 10:
                    revisarSubasta();
                    break;
                case 11:
                    terminarSubasta();
                    break;
                case 12:
                    asignarCajero();
                    break;

                case 13:
                    System.out.println("Saliendo de la Galería...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                            }
                        } while (opcion != 10);
                        scanner.close();
                        main(null);
	}

	
	
// Métodos para implementar las funcionalidades específicas (por ejemplo, crearSubasta(), crearUsuario(), etc.)
	
	
	//AumentarCupo
	
	private void aumentarCupo() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Ingrese login (administrador unicamente) : ");
			String login = scanner.nextLine();
			System.out.print("Ingrese su contraseña : ");
			String password= scanner.nextLine();
			if (login.equals(this.admin.getLogin()) && password.equals(this.admin.getPassword())) {
				System.out.print("Ingrese login del comprador : ");
				String loginComprador= scanner.nextLine();
				System.out.print("Ingrese el valor de aumento : ");
				String valort= scanner.nextLine();
				int valor=Integer.parseInt(valort);
				this.admin.aumentarCupoComprador(loginComprador,valor);
			}
			
		}
		catch(Exception e) {
			throw e;
		}
		
	}
	
	//Crea una subasta, unicamente por el administrador
	private void crearSubasta() throws Exception {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Ingrese login (administrador unicamente) : ");
			String login = scanner.nextLine();
			System.out.print("Ingrese su contraseña : ");
			String password= scanner.nextLine();
			if (login.equals(this.admin.getLogin()) && password.equals(this.admin.getPassword())) {
				System.out.print("Ingrese la fecha (AA/MM/DD)en la que desea realizar la subasta : ");
				String fechat = scanner.nextLine();
				int fecha=Integer.parseInt(fechat);
				List<Pieza> piezasSubasta = this.inventario.generarInventarioSubasta(fecha);
				Operador op=this.admin.asignarOperador(this.empleados);
				Subasta subasta = new Subasta(fecha,piezasSubasta,op);
				subastasActivas.add(subasta);
				scanner.close();

			}
			else {
				scanner.close();
				System.out.print("No eres el administrador no puedes crear subastas ");
			}
		}
		catch(Exception e) {
			throw e;
		}

	}
	//meu imprimir pieza oferta 
	public void imprimirPiezas(List<Pieza> piezasSubasta) { 
		int i = 1;
		for(Pieza pieza:piezasSubasta) {
			System.out.println(i+". " + pieza.getTitulo());
			i++;
			System.out.println("La pieza es un " + pieza.getTipoPieza());
			if (pieza.getTipoPieza().equalsIgnoreCase("Escultura")) {
				System.out.println("Alto: " + ((Escultura) pieza).getAlto());
				System.out.println("Ancho: " + ((Escultura) pieza).getAncho());
				System.out.println("Profundidad: " + ((Escultura) pieza).getProfundidad());
				System.out.println("Peso: " + ((Escultura) pieza).getPeso());
				System.out.println("Instalacion: " + ((Escultura) pieza).getInstalacion());
				if (((Escultura) pieza).isElectricidad()) {
					System.out.println("La Escultura funciona con electricidad ");	
				}
				else {
					System.out.println("La Escultura no funciona con electricidad ");
				}
				System.out.println("Materiales: ");
				 for (String material :((Escultura) pieza).getMateriales() ) {
			            System.out.print(material + ", ");
			        }
			}
			
			else if (pieza.getTipoPieza().equalsIgnoreCase("Fotografia")) {
				System.out.println("Tamaño: " + ((Fotografia) pieza).getTamanio());
				System.out.println("Alto: " + ((Fotografia) pieza).getAlto());
				System.out.println("Resolucion: " + ((Fotografia) pieza).getResolucion());
				System.out.println("Descripcion: " + ((Fotografia) pieza).getDescripcion());
				System.out.println("formato: " + ((Fotografia) pieza).getFormato());
			}
			
			else if (pieza.getTipoPieza().equalsIgnoreCase("Impresion")) {
				System.out.println("Material del papel: " + ((Impresion) pieza).getTamanio());
				System.out.println("Tamaño: " + ((Impresion) pieza).getTamanio());
				System.out.println("Resolucion: " + ((Impresion) pieza).getResolucion());
				System.out.println("Descripcion: " + ((Impresion) pieza).getDescripcion());
				System.out.println("Flexibilidad: " + ((Impresion) pieza).getFlexibilidad());
				System.out.println("Resistencia: " + ((Impresion) pieza).getResistencia());
			}
			
			else if (pieza.getTipoPieza().equalsIgnoreCase("Pintura")) {
				System.out.println("Tecnica: " + ((Pintura) pieza).getTecnica());
				System.out.println("Alto: " + ((Pintura) pieza).getAlto());
				System.out.println("Ancho: " + ((Pintura) pieza).getAncho());
				System.out.println("Descripcion: " + ((Pintura) pieza).getDescripcion());
				System.out.println("Origen: " + ((Pintura) pieza).getOrigen());
				System.out.println("Forma: " + ((Pintura) pieza).getForma());
				System.out.println("tiempoDeCreacion: " + ((Pintura) pieza).getTiempoDeCreacion());
			}
			
			else if (pieza.getTipoPieza().equalsIgnoreCase("Video")) {
				System.out.println("Duracion en minutos: " + ((Video) pieza).getDuracion());
				System.out.println("Tamaño: " + ((Video) pieza).getTamanio());
				System.out.println("Idioma: " + ((Video) pieza).getIdioma());
				System.out.println("Descripcion: " + ((Video) pieza).getDescripcion());
				System.out.println("Resolucion: " + ((Video) pieza).getResolucion());
				System.out.println("Formato: " + ((Video) pieza).getFormato());
			}
			
			else {
				System.out.println("Informacion: " + ((Otro) pieza).getInfoExtra());
				
			}
			
			System.out.println("Año: " + pieza.getAnio());
			System.out.println("Lugar de creacion: " + pieza.getLugarDeCreacion());
			System.out.println("Valor Inicial para subastar la pieza, si es 0 no se subasta " + pieza.getValorInicial());
			System.out.println("Precio: " + pieza.getValorFijo());
			System.out.println("Autores: ");
			for (Autor autor :pieza.getAutores() ) {
	            System.out.print(autor.getNombre() + ", ");
	        }
				
			}
			
		
	} 
	  
        
	
	
	

	
	
	//Le permite a los compradores participar en una subasta
	private void participarSubasta() throws Exception {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Ingrese login (solo compradores registrados) : ");
			String login = scanner.nextLine();
			System.out.print("Ingrese su contraseña : ");
			String password= scanner.nextLine();
			Comprador c = this.admin.verificarComprador(login, password);
			List<Pieza> piezasSubasta;
			
			if (!c.equals(null)) {
				System.out.print("Ingrese la fecha (AA/MM/DD) de la subasta en la que quiere participar : ");
				String fechat = scanner.nextLine();
				int fecha=Integer.parseInt(fechat);
				scanner.close();
				Subasta subasta = null;
				for (Subasta s : subastasActivas) {
					if (s.getFechaSubasta()==fecha) {
						subasta = s;
						piezasSubasta = subasta.agregarComprador(c);
						System.out.print("ya estas registrado en la subasta,esta es la oferta de piezas : ");
						imprimirPiezas(piezasSubasta);
						
						
						
						
					}
					else {
						throw new MesajedeErrorException("No hay subastas activas para esa fecha");

					}
				}
				if (subasta.isActiva()) {
					System.out.print("Por favor, ingrese si esta interesado en hacer una oferta para una pieza (Si o No): ");
					String ofertar = scanner.nextLine();


					if (ofertar.equalsIgnoreCase("Si") ) {

						System.out.print("Ingrese el numero de la pieza : ");
						String id= scanner.nextLine();
						int idx=Integer.parseInt(id);
						Pieza pieza = subasta.getInventario().get(idx);
						

						subasta.hacerOferta(c, this.admin,pieza);
					}

					else if (ofertar.equalsIgnoreCase("No") ) {
						subasta.quitarComprador(c);
						System.out.print("Ya no participas en la subasta ");
					}
					else {
						throw new MesajedeErrorException("No es una respuesta valida");
					}

				}
				else {
					throw new MesajedeErrorException("La subasta no esta activa");
				}

			}

		}
		catch(MesajedeErrorException e) {
			throw e;
		}
		catch(Exception e) {
			throw e;
		}
	}        

	
	
	
	//Termina la subasta y ejecuta los pagos a realizar de los ganadores
	private void terminarSubasta() throws MesajedeErrorException {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Ingrese login (administrador unicamente) : ");
			String login = scanner.nextLine();
			System.out.print("Ingrese su contraseña : ");
			String password= scanner.nextLine();
			if (login.equals(this.admin.getLogin()) && password.equals(this.admin.getPassword())) {
				System.out.print("Ingrese la fecha (AA/MM/DD) de la subasta que desea finalizar : ");
				String fechat = scanner.nextLine();
				int fecha=Integer.parseInt(fechat);
				scanner.close();
				Subasta subasta = null;
				for (Subasta s : subastasActivas) {
					if (s.getFechaSubasta()==fecha) {
						subasta = s;

					}
				}
				if (!subasta.equals(null)) {
					subasta.finalizarSubasta();
					subastasActivas.remove(subasta);
					subasta.ganadorSubasta(this.cajero);
					subasta.getOperador().setAsignado(false);
				}
				else {
					throw new MesajedeErrorException("No hay subastas activas para esa fecha");
				}	

			}

		}

		catch(MesajedeErrorException e) {
			throw e;
		}
		catch(Exception e) {
			throw e;
		}

	}
	
	// funcion para que el comprador revise los ganadores de la subasta si ya se acabo o si sigue activa puede hacer otra oferta si la que ya habia hecho fue superada
	private void revisarSubasta() throws MesajedeErrorException {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Ingrese login (solo compradores registrados) : ");
			String login = scanner.nextLine();
			System.out.print("Ingrese su contraseña : ");
			String password= scanner.nextLine();
			Comprador c = this.admin.verificarComprador(login, password);
			if (!c.equals(null)) {
				System.out.print("Ingrese la fecha (AA/MM/DD) de la subasta que quiere revisar : ");
				String fechat = scanner.nextLine();
				int fecha=Integer.parseInt(fechat);	
				Subasta subasta = null;
				for (Subasta s : subastasActivas) {
					if (s.getFechaSubasta()==fecha) {
						subasta = s;}}
				if (!subasta.equals(null)) {

					if(subasta.isActiva()) {
						System.out.print("Ingrese el titulo de la pieza : ");
						String piezaTitulo= scanner.nextLine();
						Pieza p =null;
						for (Pieza pieza : subasta.getInventario()) {
							if(pieza.getTitulo().equals(piezaTitulo)) {
								p = pieza;
							}
						}

						if (!p.equals(null)) {
							Operador op = subasta.getOperador();
							int valorMax = op.mayorOferta(p);
							System.out.println("Si tu oferta fue menor a " + valorMax +"perdiste, quieres volver a hacer una oferta ? ");
							String rta = scanner.nextLine();


							if (rta.equalsIgnoreCase("Si") ) {
								subasta.hacerOferta(c, this.admin,p);
							}
							else {
								System.out.println("Ya no participas en la subasta");

							}
						}
						scanner.close();
					}
					else {
						List<String> ganadores =  subasta.getGanadores();
						for (String ganador:ganadores) {
							System.out.println(ganador);
						}
					}


				}

				else {
					throw new MesajedeErrorException("No hay subastas en esa fecha");
				}
			}
		}catch(MesajedeErrorException e) {
			throw e;
		}catch(Exception e) {
			throw e;
		}

	}
	
	

	
	//A
	private void registrarUsuario() throws LoginDuplicadoException {
		this.admin.pedirInfoUsuario();
	}
	
	private void crearUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del usuario: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese el tipo de usuario (Administrador, Cajero, Comprador, Propietario): ");
        String tipoUsuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña del usuario: ");
        String contrasena = scanner.nextLine();

        // Validación de datos
        if (nombre.isEmpty() || apellido.isEmpty() || contrasena.length() < 6) {
            System.out.println("Error: Nombre, apellido y contraseña son obligatorios (min. 6 caracteres)");
            return;
        }

        if (!tipoUsuario.equalsIgnoreCase("Administrador") &&
                !tipoUsuario.equalsIgnoreCase("Cajero") &&
                !tipoUsuario.equalsIgnoreCase("Comprador") &&
                !tipoUsuario.equalsIgnoreCase("Propietario")) {
            System.out.println("Error: Tipo de usuario no válido. (Permitidos: Administrador, Cajero, Comprador, Propietario)");
            return;
        }

        // Validación específica para la contraseña (alfanumérica con al menos una letra y un número)
        Pattern patronClave = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");
        if (!patronClave.matcher(contrasena).matches()) {
            System.out.println("Error: Contraseña no válida. Debe contener al menos 6 caracteres, una letra y un número.");
            return;
        }}

        // Crear objeto Usuario
	
	// ... (métodos para las demás funcionalidades)
	
	private void mostrarPiezasDisponibles() {
		ArrayList<Pieza> piezasDisponibles= this.inventario.getPiezasDisponibles();
	    if (piezasDisponibles.isEmpty()) {
	        System.out.println("No hay piezas disponibles para la venta");
	    } else {
	        System.out.println("\n**Piezas Disponibles**");
	        for (Pieza pieza : piezasDisponibles) {
	            System.out.println(pieza);
	        }
	    }
	}
	


   
    

    
    //Añade una pieza cuando un Propietario 
    public void añadirPieza()throws Exception {
        if (this.admin != null) {
        	Scanner scanner = new Scanner(System.in);
        	
    		System.out.print("Por favor, ingrese el login ");
            String login = scanner.nextLine();
            
            System.out.print("Por favor, ingrese la contraseña ");
            String password = scanner.nextLine();
            
            scanner.close();
            try {
            	Propietario pro = admin.verificarPropietario(login,password);
            	admin.pedirInfoPieza(pro);
            
            }catch(Exception e) {
            	throw e;
            }    
           
        } else {
            throw new MesajedeErrorException("No hay un administrador asignado para añadir piezas");
        }
    }

    private void asignarAdministrador() {
    		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Por favor, ingrese el login del Administrador: ");
        String login = scanner.nextLine();
        System.out.print("Por favor, ingrese la contraseña del Administrador: ");
        String password = scanner.nextLine();
        
        Administrador admin= new Administrador(login, password,this.inventario);
        this.setAdmin(admin);
    }
    
    //solo existe un cajero
    private void asignarCajero() throws Exception {
    	try {
    		Scanner scanner = new Scanner(System.in);
    		System.out.print("Ingrese login (administrador unicamente) : ");
    		String login = scanner.nextLine();
    		System.out.print("Ingrese su contraseña : ");
    		String password= scanner.nextLine();
    		if (login.equals(this.admin.getLogin()) && password.equals(this.admin.getPassword())) {}

    		System.out.print("Por favor, ingrese el login del Cajero: ");
    		String loginC = scanner.nextLine();
    		System.out.print("Por favor, ingrese la contraseña del Cajero: ");
    		String passwordC = scanner.nextLine();
    		scanner.close(); 
    		
    		Cajero c = new Cajero(loginC, passwordC, "Cajero");
    		setCajero(c);
    		empleados.add(this.cajero);

    	}
    	catch (Exception e) {
    		throw e;
    	}


    }
    // existen varios de estos empleados
    private void agregarEmpleado() throws Exception {
    	try {
    		Scanner scanner = new Scanner(System.in);
    		System.out.print("Ingrese login (administrador unicamente) : ");
    		String login = scanner.nextLine();
    		System.out.print("Ingrese su contraseña : ");
    		String password= scanner.nextLine();
    		if (login.equals(this.admin.getLogin()) && password.equals(this.admin.getPassword())) {}

    		System.out.print("Por favor, ingrese el login del empleado: ");
    		String loginE = scanner.nextLine();
    		System.out.print("Por favor, ingrese la contraseña del empleado: ");
    		String passwordE = scanner.nextLine();
    		System.out.print("Por favor, ingrese si es Operador o empleado: ");
    		String rol = scanner.nextLine();
    		scanner.close();
    		if (rol.equalsIgnoreCase("Operador")) {

    			Operador operador = new Operador(loginE, passwordE,"Operador");
    			this.empleados.add(operador);
    		}


    		else if (rol.equalsIgnoreCase("Empleado")){
    			Empleado empleado = new Empleado(login, passwordE,"None");
    			this.empleados.add(empleado);}


    	}
    	catch (Exception e) {
    		throw e;
    	}


    }
  

    //M
    public void comprarPieza()throws MesajedeErrorException, PagoRechazado, PiezaRepetidaException {
    	if (this.admin != null) {
        	Scanner scanner = new Scanner(System.in);
        	
    		System.out.print("Por favor, ingrese el login ");
            String login = scanner.nextLine();
            
            System.out.print("Por favor, ingrese la contraseña ");
            String password = scanner.nextLine();
            
            System.out.print("Por favor, el titulo de la pieza que quiere comprar ");
            String nomPieza = scanner.nextLine();
           
            
            System.out.print("Por favor, ingrese la forma de pago ");
            String formapago = scanner.nextLine();
            
            scanner.close();
    	
    	try {
    		Comprador c = admin.verificarComprador(login,password);
    		Pieza pieza = null;
    		for (Pieza pi :this.inventario.getPiezasDisponibles()) {
    			if(pieza.getTitulo().equals(nomPieza)) {
    				pieza = pi;
    				if (pieza.getValorFijo()!=0) {
    					this.inventario.reservarPieza(pieza);
    				}
    				else {
    					throw new MesajedeErrorException("La pieza solo se puede vender en una subasta");
    				}
    				
    			}
    		}
    		
    		if (!pieza.equals(null)) {
    			boolean confirmado = this.admin.confirmarVenta(pieza,c);
	            if ( confirmado){
	  
	            	if (this.cajero.generarPagoCajero(pieza.getValorFijo(),pieza,formapago,c)) {
	            		this.inventario.moverPieza(pieza);
	            		c.agregarCompra(pieza.getValorFijo());
	            		Propietario pro =(Propietario) pieza.getPropietario();
	            		pro.venderPieza(pieza);
	            	}
	            	else {
	            		this.inventario.agregarPieza(pieza);
	            		throw new PagoRechazado();
	            	
	            	}
	            }
	            else {
	            	this.inventario.agregarPieza(pieza);
	            	throw new MesajedeErrorException("Superaste el numero de compras maximas contactate con el administrador");
	            }
        
    	
    		}	
    		else {
    			throw new MesajedeErrorException("La pieza no se encuentra disponible");
    		}
    		
        
    		scanner.close();
    	}
    	catch(MesajedeErrorException e) {
    		throw e;
    	} catch (PagoRechazado e) {
			throw e;
		}}
    	else {
            System.out.println("No hay un administrador asignado para añadir piezas");
        }
    	
    }


    
    

    // Getters y setters para las listas de empleados y subastas activas

    public List<Empleado> getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Subasta> getSubastasActivas() {
        return this.subastasActivas;
    }

    public void setSubastasActivas(List<Subasta> subastasActivas) {
        this.subastasActivas = subastasActivas;
    }
    
    public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Administrador getAdmin() {
		return this.admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Inventario getInventario() {
		return this.inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

    public Cajero getCajero() {
		return this.cajero;
	}


	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}


	public Operador getOperador() {
		return this.operador;
	}


	public void setOperador(Operador operador) {
		this.operador = operador;
	}


	// Método para iniciar la aplicación
	public static void main(String[] args) throws MesajedeErrorException, PagoRechazado, Exception {
    	Scanner scanner = new Scanner(System.in);
        int opcion;
        try {
        do {
            System.out.println("\n**Menú Inicio**");
            System.out.println("1. Crear Galería");
            System.out.println("2. Cargar Galería");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            
            switch (opcion) {
            case 1:
            	crearGaleria();
                break;
            case 2:
            	cargarGaleria();     
                break;
            case 3:
            	System.out.println("Saliendo de la Aplicación...");
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                        }
                    } while (opcion != 3);
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}

	
	
	//Este método crea la Galeria y llama a la función de mostrar menu
	private static void crearGaleria() throws MesajedeErrorException, PagoRechazado, Exception {
		try {
			Galeria galeria = new Galeria();

			Scanner scanner = new Scanner(System.in);

			System.out.print("Por favor, ingrese el nombre de su Galeria: ");
			String nomGaleria = scanner.next();    

			galeria.setNombre(nomGaleria);
			galeria.asignarAdministrador();

			galeria.mostrarMenu();	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Métodos de persistencia
    
	private static void cargarGaleria() {
		//Pedir los nombres de los archivos 
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Por favor, ingrese el nombre del archivo (.txt) de inicio de galeria: ");
        String archivoInicio = scanner.nextLine();
        
        System.out.print("Por favor, ingrese el nombre del archivo (.txt) de los usuarios: ");
        String archivoUsuarios = scanner.nextLine();
        
        System.out.print("Por favor, ingrese el nombre del archivo (.txt) de las piezas: ");
        String archivoPiezas = scanner.nextLine();
        
        System.out.print("Por favor, ingrese el nombre del archivo (.txt) de las compras: ");
        String archivoCompras = scanner.nextLine();
		
		try {
			Galeria galeria = new Galeria();
			CentralPersistencia.cargarGaleria(archivoInicio, archivoUsuarios, archivoPiezas, archivoCompras,galeria);
			galeria.mostrarMenu();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void guardarGaleria() { 
		String nombre=this.nombre;
		
		try {
			CentralPersistencia.guardarGaleria(nombre,this);
		} catch (Exception e) {
			e.printStackTrace();
	}
	
}

	
	
    
}
