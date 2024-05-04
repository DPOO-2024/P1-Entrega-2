package Persistencia;

import java.util.Arrays;
import java.util.List;

import Exceptions.MensajedeErrorException;
import Piezas.Escultura;
import Piezas.Fotografia;
import Piezas.Impresion;
import Piezas.Otro;
import Piezas.Pieza;
import Piezas.Pintura;
import Piezas.Video;
import Usuarios.Usuario;

public abstract class cargaPiezas {

	public static Pieza cargaPieza (String[] l, Usuario u) throws MensajedeErrorException {
		l[6] = l[6].trim();
		l[6]=l[6].substring(1, l[6].length()-1);
		String[] autoresp = l[6].split("/");
		List<String> autores = Arrays.asList(autoresp);

		try {
			//Añadir por tipos de pieza
			if (l[0].equals("Escultura")) {
				l[16] = l[16].substring(1, l[16].length() - 1);
				String[] materialesp = l[16].split("/");
				List<String> materiales = Arrays.asList(materialesp);

				Escultura e = new Escultura("Escultura",u,l[3],(int)Integer.valueOf(l[4].trim()),l[5],autores,(boolean)Boolean.valueOf(l[7].trim()),
						(int)Integer.valueOf(l[8].trim()),(int)Integer.valueOf(l[9].trim()),l[10],(boolean)Boolean.valueOf(l[11].trim()),
						(int)Integer.valueOf(l[12].trim()),(int)Integer.valueOf(l[13].trim()),(int)Integer.valueOf(l[14].trim()),(int)Integer.valueOf(l[15].trim()),
						materiales,(int)Integer.valueOf(l[17].trim()),(boolean)Boolean.valueOf(l[18].trim()),l[19]);
				return e;

			}else if (l[0].equals("Fotografia")) {

				Fotografia f = new Fotografia("Fotografia",u,l[3],(int)Integer.valueOf(l[4].trim()),l[5],autores,(boolean)Boolean.valueOf(l[7].trim()),
						(int)Integer.valueOf(l[8].trim()),(int)Integer.valueOf(l[9].trim()),l[10],(boolean)Boolean.valueOf(l[11].trim()),
						(int)Integer.valueOf(l[12].trim()),l[13], (int)Integer.valueOf(l[14].trim()),(int)Integer.valueOf(l[15].trim()),
						l[16],l[17]);
				return f;

			}else if (l[0].equals("Impresion")) {
				Impresion i = new Impresion("Impresion",u,l[3],(int)Integer.valueOf(l[4].trim()),l[5],autores,(boolean)Boolean.valueOf(l[7].trim()),
						(int)Integer.valueOf(l[8].trim()),(int)Integer.valueOf(l[9].trim()),l[10],(boolean)Boolean.valueOf(l[11].trim()),
						(int)Integer.valueOf(l[12].trim()), l[13], l[14], (int)Integer.valueOf(l[15].trim()),l[16],
						l[17],l[18]);
				return i;

			}else if (l[0].equals("Otro")) {
				Otro o = new Otro("Otro",u,l[3],(int)Integer.valueOf(l[4].trim()),l[5],autores,(boolean)Boolean.valueOf(l[7].trim()),
						(int)Integer.valueOf(l[8].trim()),(int)Integer.valueOf(l[9].trim()),l[10],(boolean)Boolean.valueOf(l[11].trim()),
						(int)Integer.valueOf(l[12].trim()), l[13]);
				return o;

			}else if (l[0].equals("Pintura")) {
				Pintura p = new Pintura("Pintura",u,l[3],(int)Integer.valueOf(l[4].trim()),l[5],autores,(boolean)Boolean.valueOf(l[7].trim()),
						(int)Integer.valueOf(l[8].trim()),(int)Integer.valueOf(l[9].trim()),l[10],(boolean)Boolean.valueOf(l[11].trim()),
						(int)Integer.valueOf(l[12].trim()), l[13], l[14], l[15],(int)Integer.valueOf(l[16].trim()), (int)Integer.valueOf(l[17].trim()),
						l[18],l[19]);
				return p;
			}else if (l[0].equals("Video")) {
				Video v = new Video("Video",u,l[3],(int)Integer.valueOf(l[4].trim()),l[5],autores,(boolean)Boolean.valueOf(l[7].trim()),
						(int)Integer.valueOf(l[8].trim()),(int)Integer.valueOf(l[9].trim()),l[10],(boolean)Boolean.valueOf(l[11].trim()),
						(int)Integer.valueOf(l[12].trim()), (int)Integer.valueOf(l[13].trim()),l[14], l[15],l[16],(int)Integer.valueOf(l[17].trim()), l[18]);
				return v;
			}else {
				throw new MensajedeErrorException("Ese tipo de Piza no existe");
			}
		}catch(Exception e) {
			throw e;
			//throw new MesajedeErrorException("No se pudo añadir la pieza"+ l[3]);
		}

	}

}
