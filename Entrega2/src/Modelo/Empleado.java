package Modelo;

//Login, password y rol (para saber si el empleado es de tipo (None, Cajero o Operador))


public enum Rol {
    NONE,
    CAJERO,
    OPERADOR
}

public class Empleado {

    private String nombreUsuario;
    private String contraseña;
    private Rol rol;

    public Empleado(String nombreUsuario, String contraseña, Rol rol) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
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
    }
}

