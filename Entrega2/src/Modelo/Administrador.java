package Modelo;

import java.util.ArrayList;

import Usuarios.Usuario;

public class Administrador {
	
	private String login;
	private String password;
	private ArrayList<Usuario> usuarios;
	
	public Administrador(String login, String contrasena, ArrayList<Usuario> usuarios) {
		this.login = login;
		this.password = contrasena;
		this.usuarios = new ArrayList<Usuario>();
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String contrasena) {
		this.password = contrasena;
	}

	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
}
