package Persistencia;

import java.util.Arrays;
import java.util.List;

import Piezas.Escultura;
import Piezas.Fotografia;
import Piezas.Impresion;
import Piezas.Otro;
import Piezas.Pieza;
import Usuarios.Propietario;
import Usuarios.Usuario;

public abstract class cargaPiezas {
	
	public static Pieza cargaPieza (String[] l, Usuario u) {
		l[6] = l[3].substring(1, l[6].length() - 1);
        String[] autoresp = l[3].split("/");
    	List<String> autores = Arrays.asList(autoresp);
    	
    	
    	//Añadir por tipos de pieza
    	if (l[0].equals("Escultura")) {
    		l[16] = l[16].substring(1, l[16].length() - 1);
            String[] materialesp = l[16].split("/");
        	List<String> materiales = Arrays.asList(materialesp);

        	Escultura e = new Escultura(u,l[3],(int)Integer.valueOf(l[4]),l[5],autores,(boolean)Boolean.valueOf(l[6]),
        			(int)Integer.valueOf(l[7]),(int)Integer.valueOf(l[8]),l[9],(boolean)Boolean.valueOf(l[10]),
        			(int)Integer.valueOf(l[11]),(int)Integer.valueOf(l[12]),(int)Integer.valueOf(l[13]),(int)Integer.valueOf(l[14]),
        			materiales,(int)Integer.valueOf(l[16]),(boolean)Boolean.valueOf(l[17]),l[18]);
        	return e;
        	
    	}else if (l[0].equals("Fotografia")) {
    		
    		Fotografia f = new Fotografia(u,l[3],(int)Integer.valueOf(l[4]),l[5],autores,(boolean)Boolean.valueOf(l[6]),
        			(int)Integer.valueOf(l[7]),(int)Integer.valueOf(l[8]),l[9],(boolean)Boolean.valueOf(l[10]),
        			(int)Integer.valueOf(l[11]),l[12], (int)Integer.valueOf(l[12]),(int)Integer.valueOf(l[13]),
        			l[14],l[15]);
    		return f;
    		
    	}else if (l[0].equals("Impresion")) {
    		Impresion i = new Impresion(u,l[3],(int)Integer.valueOf(l[4]),l[5],autores,(boolean)Boolean.valueOf(l[6]),
        			(int)Integer.valueOf(l[7]),(int)Integer.valueOf(l[8]),l[9],(boolean)Boolean.valueOf(l[10]),
        			(int)Integer.valueOf(l[11]), l[12], l[13], (int)Integer.valueOf(l[14]), (int)Integer.valueOf(l[15]),
        			l[16],l[17]);
    		return i;
    		
    	}else if (l[0].equals("Otro")) {
    		Otro o = new Otro(u,l[3],(int)Integer.valueOf(l[4]),l[5],autores,(boolean)Boolean.valueOf(l[6]),
        			(int)Integer.valueOf(l[7]),(int)Integer.valueOf(l[8]),l[9],(boolean)Boolean.valueOf(l[10]),
        			(int)Integer.valueOf(l[11]), l[12]);
    		return o;
    		
    	}else if (l[0].equals("Otro")) {
    		Otro o = new Otro(u,l[3],(int)Integer.valueOf(l[4]),l[5],autores,(boolean)Boolean.valueOf(l[6]),
        			(int)Integer.valueOf(l[7]),(int)Integer.valueOf(l[8]),l[9],(boolean)Boolean.valueOf(l[10]),
        			(int)Integer.valueOf(l[11]), l[12]);
    		return o;
    	}
    	
	}

}
