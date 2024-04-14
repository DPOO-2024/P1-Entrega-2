package Usuarios;

public abstract class Usuario {
	
	protected String login;
	
	protected  String password;
	
	private String rol;
	
	
	

	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
        if (rol.equalsIgnoreCase("Comprador") || rol.equalsIgnoreCase("Propietario")) {
            this.rol = rol;
        } else {
            throw new IllegalArgumentException("El rol debe ser 'Comprador' o 'Propietario'");
        }
    }


	public Usuario(String login,String password, String rol) {
		this.password=password;
		this.login=login;
	    if (rol.equalsIgnoreCase("Comprador") || rol.equalsIgnoreCase("Propietario")) {
	        this.rol = rol;
	        } else {
	            throw new IllegalArgumentException("El rol debe ser 'Comprador' o 'Propietario'");
	        }
	    }

	
	
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void cambiarpassword(String password) {
		this.password = password;
	}


	public boolean verificarInicioSesion(String login, String password) {
		
		if (getPassword().equals(password) && getLogin().equals(login)  ) {
			return true;
		}
		else {
	        throw new IllegalArgumentException("Error: Los datos de inicio de sesión son incorrectos.");
	    }
		
	}
	
	
	
}