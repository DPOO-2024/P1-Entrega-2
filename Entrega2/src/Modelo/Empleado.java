package Modelo;

import Exceptions.MensajedeErrorException;

//Login, password y rol (para saber si el empleado es de tipo (None, Cajero o Operador))


public class Empleado {

    private String nombreUsuario;
    private String contraseña;
    private String rol;

    public Empleado(String nombreUsuario, String contraseña, String rol) throws MensajedeErrorException {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        
        if (rol.equals("empleado")|| rol.equals("Empleado")) {
        	this.rol="None";
        }else if (rol.equals("operador")|| rol.equals("Operador")) {
        	this.rol="Operador";
        }else if (rol.equals("cajero")|| rol.equals("Cajero")) {
        	this.rol="Cajero";
        }else {
        	throw new MensajedeErrorException("Ese rol de empleado no existe");
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

}

