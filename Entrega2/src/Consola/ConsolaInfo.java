package Consola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Exceptions.LoginDuplicadoException;
import Modelo.Galeria;
import Piezas.Escultura;
import Piezas.Fotografia;
import Piezas.Impresion;
import Piezas.Otro;
import Piezas.Pieza;
import Piezas.Pintura;
import Piezas.Video;
import Usuarios.Comprador;
import Usuarios.Propietario;
import Usuarios.Usuario;

public class ConsolaInfo {
	
	public static void mostrarHistorialPieza(Galeria gal) {
		try {
    		System.out.print("Por favor, ingrese el nombre de la Pieza: ");
    		String nombreP = ConsolaInicial.scanner.nextLine().trim();
    		gal.historialPiezas();
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	public static void mostrarHistorialArtista(Galeria gal) {
		try {
    		System.out.print("Por favor, ingrese el nombre del Artista: ");
    		String nombreA = ConsolaInicial.scanner.nextLine().trim();
    		gal.historialArtista();
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	//Pedir info pieza	
	public static Pieza pedirInfoPieza(Propietario pro) throws Exception {

		try {
			Pieza pieza=null;
			Scanner scanner = ConsolaInicial.scanner;

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
						valorInicial, ubicacion, vendido, valorFijo, alto, ancho, profundidad, materiales, peso, electricidad, instalacion);

				

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
				
				pieza=new Fotografia("Fotografia",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, ubicacion, vendido, 
						valorFijo, tamanio, alto, resolucion, descripcion, formato);

				
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
				String flexibilidad = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la resistencia de la Pieza (Alta, media, baja): ");
				String formato = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la descripcion de la Pieza: ");
				String descripcion = scanner.nextLine().trim();
				
				pieza=new Impresion("Impresion",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, 
						ubicacion, vendido, valorFijo, materialPapel, tamanio, resolucion, flexibilidad, formato, descripcion);

				
			}else if (tipoDePieza.equals("Otro") || tipoDePieza.equals("otro")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese la información extra que haya de la Pieza: ");
				String infoExtra = scanner.nextLine().trim();
				
				
				pieza=new Otro("Otro",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, 
						ubicacion, vendido, valorFijo, infoExtra);

				
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
						ubicacion, vendido, valorFijo, tecnica, origen, descripcion, alto, ancho, forma, tiempoDeCreacion);
				
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
						valorInicial, ubicacion, vendido, valorFijo, duracion, tamanio, idioma, descripcion, resolucion, formato);
							
			}
			return pieza;
		}catch(Exception e) {
			throw e;
		}

	}
	
	//Pedir informacion para hacer usuario

		public static ArrayList<String> pedirInfoUsuario() throws LoginDuplicadoException  {
			try {
				Scanner scanner = ConsolaInicial.scanner;
			
				ArrayList<String> respuesta = new ArrayList<String> ();
				
				System.out.print("Por favor, ingrese su contraseña: ");
				String password = scanner.nextLine().trim();
				respuesta.add(password);

				System.out.print("Por favor, su numero de telefono: ");
				String telefonof = scanner.nextLine().trim();
				respuesta.add(telefonof);

				System.out.print("Por favor, ingrese su nombre : ");
				String nombre =  scanner.nextLine().trim();
				respuesta.add(nombre);

				System.out.print("Por favor, ingrese su correo electronico: ");
				String correo = scanner.nextLine().trim();
				respuesta.add(correo);
				
				return respuesta;				
			}
			catch(Exception e) {
				throw e;
			}

		}



}
