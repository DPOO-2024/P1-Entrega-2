package Modelo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import Exceptions.MesajedeErrorException;
import Usuarios.*;
import Persistencia.*;
import Piezas.*;

import Persistencia.CentralPersistencia;
import Usuarios.Cajero;

public class Galeria {
	private Administrador admin;
	private String nombre;
	private Cajero cajero;
	private Inventario inventario;
	private List<Empleado> empleados;
    private List<Subasta> subastasActivas;
    

	public Galeria() {
		this.nombre = "Galeria";
		this.admin = null;
		this.cajero = null;
		this.inventario= new Inventario();
		this.empleados = new ArrayList<>();
        this.subastasActivas = new ArrayList<>();
	}
	

	public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n**Menú Galería**");
            System.out.println("1. Guardar Galería");
            System.out.println("2. Crear Subasta");
            System.out.println("3. Crear Usuario");
            System.out.println("4. Añadir Pieza");
            System.out.println("5. Asignar Administrador");
            System.out.println("6. Ver Piezas Disponibles");
            System.out.println("7. Comprar Pieza");
            System.out.println("8. Volver al menu Inicial");
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
                    crearUsuario();
                    break;
                case 4:
                    añadirPieza();
                    break;
                case 5:
                    asignarAdministrador();
                    break;
                case 6:
                    mostrarPiezasDisponibles();
                    break;
                case 7:
                    comprarPieza();
                    break;
                case 8:
                    System.out.println("Saliendo de la Galería...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                            }
                        } while (opcion != 8);
                        scanner.close();
                        main(null);
	}

// Métodos para implementar las funcionalidades específicas (por ejemplo, crearSubasta(), crearUsuario(), etc.)

	private void crearSubasta() {
	    // Implementar la lógica para crear una subasta
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
	
	private void comprarPieza() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Ingrese el ID de la pieza que desea comprar: ");
	    int idPieza = scanner.nextInt();
	    scanner.nextLine(); // Consumir el salto de línea
	
	    Pieza piezaABuscar = null;
	    for (Pieza pieza : piezasDisponibles) {
	        if (pieza.getId() == idPieza) {
	            piezaABuscar = pieza;
	            break;
	        }
	    }
	
	    if (piezaABuscar != null) {
	        System.out.print("Ingrese el nombre del comprador: ");
	        String nombreComprador = scanner.nextLine();
	        System.out.print("Ingrese el ID del comprador: ");
	        int idComprador = scanner.nextInt();
	        scanner.nextLine(); // Consumir el salto de línea
	
	        Comprador comprador = new Comprador(nombreComprador, idComprador);
	        comprarPieza(piezaABuscar, comprador);
	        System.out.println("Compra realizada con éxito!");
	    } else {
	        System.out.println("La pieza no existe o no está disponible para la venta");
	        }
	
	        scanner.close();
    }
	


	

	// Métodos para las nuevas funcionalidades:

    public void crearSubasta(Subasta subasta) {
        if (this.admin != null) {
        	Scanner scanner = new Scanner(System.in);
        	
    		System.out.print("Por favor, ingrese la fecha de la subasta ");
            String fechat = scanner.nextLine();
            int fecha=Integer.parseInt(fechat);
            List<Pieza> inventarioSubasta = inventario.generarInventarioSubasta(fecha);
            
            this.admin.crearSubasta(subasta);
            this.subastasActivas.add(subasta);
        } else {
            System.out.println("No hay un administrador asignado para crear subastas");
        }
    }

    public void crearUsuario(Usuario usuario) {
        if (this.admin != null) {
            this.admin.crearUsuario(usuario);
        } else {
            System.out.println("No hay un administrador asignado para crear usuarios");
        }
    }
    

    
    
    public void añadirPieza()throws Exception {
        if (this.admin != null) {
        	Scanner scanner = new Scanner(System.in);
        	
    		System.out.print("Por favor, ingrese el login ");
            String login = scanner.nextLine();
            
            System.out.print("Por favor, ingrese la contraseña ");
            String password = scanner.nextLine();
            
            //Majo
            try {
            Comprador c = admin.verificarPropietario(login,password);
            admin.pedirInfoPieza(c);
            }catch(Exception e) {
            	throw e;
            }    
            
        } else {
            System.out.println("No hay un administrador asignado para añadir piezas");
        }
    }

    private void asignarAdministrador() {
    	Scanner scanner = new Scanner(System.in);
		
		System.out.print("Por favor, ingrese el login del Administrador: ");
        String login = scanner.nextLine();
        System.out.print("Por favor, ingrese la contraseña del Administrador: ");
        String password = scanner.nextLine();
        scanner.close(); 
        Administrador admin= new Administrador(login, password,this.inventario);
        this.setAdmin(admin);
    }
    
    //Añadir empleado

    //M
    public boolean comprarPieza(Pieza pieza, String nombreComprador, String formapago)throws MesajedeErrorException {
        if (this.inventario.getPiezasDisponibles().contains(pieza)) {
			Inventario.reservarPieza(pieza);//deberia ser estatico para no crear una instacia de inverntario??-Majo
            if (this.admin.confirmarVenta(pieza, nombreComprador, formapago));
            piezasDisponibles.remove(pieza);
            Inventario.moverPieza(pieza);
            return true;
        } else {
        	throw new MesajedeErrorException("El comprador no esta registrado correctamente");
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
	


    // Método para iniciar la aplicación
	public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n**Menú Inicio**");
            System.out.println("1. Cargar Galería");
            System.out.println("2. Crear Galería");
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
	}

	//Este método crea la Galeria y llama a la función de mostrar menu
	private static void crearGaleria() {
		Galeria galeria = new Galeria();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Por favor, ingrese el nombre de su Galeria: ");
        String nomGaleria = scanner.nextLine();
        scanner.close();      
        
        galeria.setNombre(nomGaleria);
        
        galeria.mostrarMenu();	
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
