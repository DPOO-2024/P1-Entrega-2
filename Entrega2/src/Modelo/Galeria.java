package Modelo;
import java.util.Scanner;
import Usuarios.*;
import Persistencia.*;
import Piezas.*;

import Persistencia.CentralPersistencia;
import Usuarios.Cajero;

public class Galeria {
	private Administrador admin;
	private String nombre;
	private Cajero cajero;
	private List<Empleado> empleados;
	private List<Pieza> piezasDisponibles;
    private List<Subasta> subastasActivas;
    
	
	
	
	public Galeria() {
		this.nombre = "Galeria";
		this.admin = null;
		this.cajero = null;
		this.empleados = new ArrayList<>();
        this.piezasDisponibles = new ArrayList<>();
        this.subastasActivas = new ArrayList<>();
	}

	public void cargarGaleria() {
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
			CentralPersistencia.cargarGaleria(archivoInicio, archivoUsuarios, archivoPiezas, archivoCompras,this);
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

	// Métodos para las nuevas funcionalidades:

    public void crearSubasta(Subasta subasta) {
        if (this.admin != null) {
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

    public void añadirPieza(Pieza pieza) {
        if (this.admin != null) {
            this.admin.añadirPieza(pieza);
            this.piezasDisponibles.add(pieza);
        } else {
            System.out.println("No hay un administrador asignado para añadir piezas");
        }
    }

    public void asignarAdministrador(Administrador admin) {
        this.admin = admin;
    }

    public List<Pieza> obtenerPiezasDisponibles() {
        return piezasDisponibles;
    }

    public void comprarPieza(Pieza pieza, Comprador comprador) {
        if (piezasDisponibles.contains(pieza)) {
            this.admin.confirmarVenta(pieza, comprador);
            piezasDisponibles.remove(pieza);
        } else {
            System.out.println("La pieza no está disponible para la venta");
        }
    }

    // Getters y setters para las listas de empleados y subastas activas

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Subasta> getSubastasActivas() {
        return subastasActivas;
    }

    public void setSubastasActivas(List<Subasta> subastasActivas) {
        this.subastasActivas = subastasActivas;
    }

    // Método para mostrar el menú de la galería
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n**Menú Galería**");
            System.out.println("1. Cargar Galería");
            System.out.println("2. Guardar Galería");
            System.out.println("3. Crear Subasta");
            System.out.println("4. Crear Usuario");
            System.out.println("5. Añadir Pieza");
            System.out.println("6. Asignar Administrador");
            System.out.println("7. Ver Piezas Disponibles");
            System.out.println("8. Comprar Pieza");
            System.out.println("9. Salir");
            System.out.print("Ingrese una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    cargarGaleria();
                    break;
                case 2:
                    guardarGaleria();
                    break;
                case 3:
                    crearSubasta();
                    break;
                case 4:
                    crearUsuario();
                    break
                case 5:
                    añadirPieza();
                    break;
                case 6:
                    asignarAdministrador();
                    break;
                case 7:
                    mostrarPiezasDisponibles();
                    break;
                case 8:
                    comprarPieza();
                    break;
                case 9:
                    System.out.println("Saliendo de la Galería...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                            }
                        } while (opcion != 9);

                        scanner.close();
                    }

                    // Métodos para implementar las funcionalidades específicas (por ejemplo, crearSubasta(), crearUsuario(), etc.)

                    private void crearSubasta() {
                        // Implementar la lógica para crear una subasta
                    }

                    private void crearUsuario() {
                        // Implementar la lógica para crear un usuario
                    }

                    // ... (métodos para las demás funcionalidades)

                    private void mostrarPiezasDisponibles() {
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
                }

}
