package Consola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Exceptions.LoginDuplicadoException;
import Modelo.Galeria;
import Piezas.Autor;
import Piezas.Escultura;
import Piezas.Fotografia;
import Piezas.Impresion;
import Piezas.Otro;
import Piezas.Pieza;
import Piezas.Pintura;
import Piezas.Video;
import Usuarios.Propietario;
import Usuarios.Usuario;

public class ConsolaInfo {
	
	public static void mostrarHistorialPieza(Galeria gal) {
		try {
    		System.out.println("Por favor, ingrese el nombre de la Pieza: ");
    		String nombreP = ConsolaInicial.scanner.nextLine().trim();
    		List<String> info = gal.historialPiezas(nombreP);
    		System.out.println("\nHISTORIAL DE LA PIEZA");
    		Pieza pieza = gal.getInventario().getPieza(info.get(0));
    		System.out.println("\nDatos Generales de "+ info.get(0));
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
			System.out.println("Valor Inicial para subastar la pieza (si es 0 no se subasta): " + pieza.getValorInicial());
			System.out.println("Precio: " + pieza.getValorFijo());
			System.out.println("Autores: ");
			for (Autor autor :pieza.getAutores() ) {
				System.out.print(autor.getNombre() + ", ");
			}
			
			
			if(info.get(1).equalsIgnoreCase("vendida")) {
				System.out.println("\nLa pieza ya fue vendida");
				System.out.println("Su propietario fue "+info.get(2));
				System.out.println("Fue comprada por "+info.get(3));
				System.out.println("La pieza fue vendida: "+info.get(4));
				System.out.println("El precio por la que fue vendida: "+info.get(5));
			}
			
			else if (info.get(1).equalsIgnoreCase("disponible")){
				System.out.println("\nLa pieza no ha sido vendida");
				System.out.println("Su propietario es "+info.get(2));
				System.out.println("La pieza se encuentra en " + info.get(3));
				if(info.get(4).equalsIgnoreCase("si")) {
				System.out.println("La pieza se encuentra en modalidad de consignacion" );}
				else {
					System.out.println("La pieza se encuentra en modalidad de consignacion" );
				}
				
				if(info.get(5).equalsIgnoreCase("a")) {
					System.out.println("la pieza puede ser vendida directamente en la galeria y en subasta");
				}
				
				else if(info.get(5).equalsIgnoreCase("s")) {
					System.out.println("la pieza puede ser vendida unicamente en subasta");
				}
				
				else if(info.get(5).equalsIgnoreCase("g")) {
					System.out.println("la pieza solo se puede comprar directamente en la galeria");
				}
				else {
					System.out.println("la pieza no se pueede vender");
				}
				
			}
    		
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	public static void mostrarHistorialArtista(Galeria gal) {
		try {
    		System.out.println("Por favor, ingrese el nombre del Artista: ");
    		String nombreA = ConsolaInicial.scanner.nextLine().trim();
    		List<List<String>> piezasArtista = gal.historialArtista(nombreA);
    		System.out.println("\nHISTORIAL DEL ARTISTA");
    		if (piezasArtista.size()!=1) {
    			for(List<String> pieza : piezasArtista){
    			
    				System.out.println("\nCreador de:");
    				System.out.println("- "+pieza.get(1));
    				System.out.println("Fue creada en el año "+pieza.get(2));
    				
    				if (pieza.get(3).equals("vendida")) {
    					System.out.println("La pieza ya fue vendida");
    					System.out.println("Su propietario fue "+pieza.get(4) );
    					System.out.println("Fue comprada por "+pieza.get(5));
    					System.out.println("La pieza fue vendida: "+pieza.get(6));
						System.out.println("El precio por la que fue vendida: "+pieza.get(7));
    				}
    				
    				if (pieza.get(3).equals("disponible")){
    					System.out.println("La pieza no ha sido vendida");
    					System.out.println("Su propietario es "+pieza.get(4));
    					System.out.println("La pieza se encuentra en " + pieza.get(5));
    					if(pieza.get(6).equals("si")) {
    						System.out.println("La pieza se encuentra en modalidad de consignacion" );
    					}
    					
    				}
    				
    				
    				
    			}
    		}
    		
    		else {
    			
    			List<String> pieza = piezasArtista.get(0);
    			System.out.println(pieza.get(0));
				System.out.println("Creador de:");
				System.out.println("- "+pieza.get(1));
				System.out.println("Fue creada en el año "+pieza.get(2));
				
				if (pieza.get(3).equals("vendida")) {
					System.out.println("La pieza ya fue vendida");
					System.out.println("Su propietario fue "+pieza.get(4) );
					System.out.println("Fue comprada por "+pieza.get(5));
					System.out.println("La pieza fue vendida: "+pieza.get(6));
					System.out.println("El precio por la que fue vendida: "+pieza.get(7));
				}
				
				else {
					System.out.println("La pieza no ha sido vendida");
					System.out.println("Su propietario es "+pieza.get(4));
					System.out.println("La pieza se encuentra en " + pieza.get(5));
					if(pieza.get(6).equals("si")) {
						System.out.println("La pieza se encuentra en modalidad de consignacion" );
					}	
				}
    		}
    		
		}	
    
    	catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	//Imprimir Pieza
	public static void imprimirPieza(Pieza pieza, int i) { 
		System.out.println("\n \n"+i+". " + pieza.getTitulo());
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
		System.out.println("Valor Inicial para subastar la pieza (si es 0 no se subasta): " + pieza.getValorInicial());
		System.out.println("Precio: " + pieza.getValorFijo());
		System.out.println("Autores: ");
		for (Autor autor :pieza.getAutores() ) {
			System.out.print(autor.getNombre() + ", ");
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
