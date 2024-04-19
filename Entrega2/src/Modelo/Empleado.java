package Modelo;

import Exceptions.MesajedeErrorException;

//Login, password y rol (para saber si el empleado es de tipo (None, Cajero o Operador))


public class Empleado {

    private String nombreUsuario;
    private String contraseña;
    private String rol;

    public Empleado(String nombreUsuario, String contraseña, String rol) throws MesajedeErrorException {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        
        if (rol.equals("empleado")|| rol.equals("Empleado")) {
        	this.rol="None";
        }else if (rol.equals("operador")|| rol.equals("Operador")) {
        	this.rol="Operador";
        }else if (rol.equals("cajero")|| rol.equals("Cajero")) {
        	this.rol="Cajero";
        }else {
        	throw new MesajedeErrorException("Ese rol de empleado no existe");
        }
    }

    public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean iniciarSesion(String nombreUsuarioIngresado, String contraseñaIngresada) {
        if (nombreUsuarioIngresado.equals(nombreUsuario) && contraseñaIngresada.equals(contraseña)) {
            System.out.println("Bienvenido(a), " + nombreUsuario + ". Ha iniciado sesión como " + rol + ".");
            return true;
        } else {
            System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
            return false;
        }
    }

	
	/*
    public static void main(String[] args) {
        Empleado empleado1 = new Empleado("juan.perez", "micontraseña123", Rol.CAJERO);
        Empleado empleado2 = new Empleado("maria.gomez", "micontraseña456", Rol.OPERADOR);
        Empleado empleado3 = new Empleado("pedro.lopez", "", Rol.NONE); // Empleado sin rol asignado

        if (empleado1.iniciarSesion("juan.perez", "micontraseña123")) {
            System.out.println("El empleado1 ha accedido al sistema.");
        } else {
            System.out.println("El empleado1 no pudo acceder al sistema. Intente nuevamente.");
        }

        if (empleado2.iniciarSesion("maria.gomez", "micontraseña456")) {
            System.out.println("El empleado2 ha accedido al sistema.");
        } else {
            System.out.println("El empleado2 no pudo acceder al sistema. Intente nuevamente.");
        }

        if (empleado3.iniciarSesion("pedro.lopez", "")) {
            System.out.println("El empleado3 ha accedido al sistema.");
        } else {
            System.out.println("El empleado3 no pudo acceder al sistema. Intente nuevamente.");
        }
    }*/
}

