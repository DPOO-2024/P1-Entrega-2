package Persistencia;

import java.util.List;

import Piezas.Autor;
import Piezas.Escultura;
import Piezas.Fotografia;
import Piezas.Impresion;
import Piezas.Otro;
import Piezas.Pieza;
import Piezas.Pintura;
import Piezas.Video;

public abstract class guardarPiezas {


	public static String guardarPieza(Pieza pieza, boolean disponibilidad) {
		String tipoPieza=pieza.getTipoPieza();
		String loginP = pieza.getPropietario().getLogin();
		String titulo = pieza.getTitulo();
		String anio = Integer.toString(pieza.getAnio());
		String lugarDeCreacion = pieza.getLugarDeCreacion();
		String autores = generarFormatoAutores(pieza.getAutores());
		String modalidad = Boolean.toString(pieza.isModalidad());
		String fechaMaxima = Integer.toString(pieza.getFechaMax());
		String valorInicial = Integer.toString(pieza.getValorInicial());
		String ubicacion = pieza.getUbicacion();
		String vendido = Boolean.toString(pieza.isVendido());
		String valorFijo = Integer.toString(pieza.getValorFijo());

		String info = tipoPieza +","+loginP +","+ Boolean.toString(disponibilidad)+","+titulo+","+anio+","+lugarDeCreacion+","+
				autores+","+modalidad+","+fechaMaxima+","+valorInicial+","+ubicacion+","+vendido+","+valorFijo;

		Escultura e=null;
		Fotografia f=null;
		Impresion i=null;
		Otro o=null;
		Pintura p=null;
		Video v=null;

		if (tipoPieza.equalsIgnoreCase("Escultura")) {
			e = (Escultura) pieza;
			info +=","+ Integer.toString(e.getAlto());
			info +=","+ Integer.toString(e.getAncho());
			info +=","+ Integer.toString(e.getProfundidad());
			info +=","+ getFormato(e.getMateriales());
			info +=","+ Integer.toString(e.getPeso());
			info +=","+ Boolean.toString(e.isElectricidad());
			info +=","+ e.getInstalacion();
		}else if (tipoPieza.equalsIgnoreCase("Fotografia")) {
			f = (Fotografia) pieza;
			info +=","+ f.getTamanio();
			info +=","+ Integer.toString(f.getAlto());
			info +=","+ Integer.toString(f.getResolucion());
			info +=","+ f.getDescripcion();
			info +=","+ f.getFormato();
		}else if (tipoPieza.equalsIgnoreCase("Impresion")) {
			i = (Impresion) pieza;
			info +=","+ i.getMaterialPapel();
			info +=","+ i.getTamanio();
			info +=","+ Integer.toString(i.getResolucion());
			info +=","+ i.getFlexibilidad();
			info +=","+ i.getResistencia();
			info +=","+ i.getDescripcion();
		}else if (tipoPieza.equalsIgnoreCase("Otro")) {
			o = (Otro) pieza;
			info +=","+ o.getInfoExtra();
		}else if (tipoPieza.equalsIgnoreCase("Pintura")) {
			p = (Pintura) pieza;
			info +=","+ p.getTecnica();
			info +=","+ p.getOrigen();
			info +=","+ p.getDescripcion();
			info +=","+ Integer.toString(p.getAlto());
			info +=","+ Integer.toString(p.getAncho());
			info +=","+ p.getForma();
			info +=","+ p.getTiempoDeCreacion();
		}else {
			v = (Video) pieza;
			info +=","+ Integer.toString(v.getDuracion());
			info +=","+ v.getTamanio();
			info +=","+ v.getIdioma();
			info +=","+ v.getDescripcion();
			info +=","+ Integer.toString(v.getResolucion());
			info +=","+ v.getFormato();
		}

		info+="\n";
		return info;
	}

	
	public static String getFormato(List<String> materiales) {
		StringBuilder formato = new StringBuilder();
        formato.append("(");

        for (int i = 0; i < materiales.size(); i++) {
        	formato.append(materiales.get(i));
            if (i < materiales.size() - 1) {
            	formato.append("/");
            }
        }
        formato.append(")");
        
        return formato.toString();
	}

	public static String generarFormatoAutores(List<Autor> autores) {
		
        StringBuilder formato = new StringBuilder();
        formato.append("(");

        for (int i = 0; i < autores.size(); i++) {
        	formato.append(autores.get(i).getNombre());
            if (i < autores.size() - 1) {
            	formato.append("/");
            }
        }
        formato.append(")");
        
        return formato.toString();
	}
	
	public static String getFormatoPiezasUsuario(List<String> piezas) {
		StringBuilder formato = new StringBuilder();
        formato.append("(");

        for (int i = 0; i < piezas.size(); i+=3) {
        	formato.append(piezas.get(i));
        	formato.append("-");
        	formato.append(piezas.get(i+1));
        	formato.append("-");
        	formato.append(piezas.get(i+2));
            if (i+1 < piezas.size() - 1) {
            	formato.append("/");
            }
        }
        formato.append(")");
        
        return formato.toString();
	}
}
