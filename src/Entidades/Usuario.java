package Entidades;

/**
 * La clase que representa a un usuario (trabajador)
 * de la biblioteca. Puede acceder al sistema.
 */

public class Usuario {

    /**
     * El rut del usuario.
     */
    private String rut;

    /**
     * El nombre del usuario.
     */
    private String nombre;

    /**
     * El apellido del usuario.
     */
    private String apellido;

    /**
     * La contraseña del usuario.
     */
    private String contrasenia;

    /**
     * Constructor del usuario.
     * @param rut (Rut del usuario).
     * @param nombre (El nombre del usuario).
     * @param apellido (El apellido del usuario).
     * @param contrasenia (La contraseña del usuario).
     */
    public Usuario(String rut, String nombre, String apellido, String contrasenia) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
    }

    /**
     * Retorna el rut del usuario.
     * @return el rut del usuario.
     */
    public String getRut() {
        return rut;
    }

    /**
     * Retorna el nombre del usuario.
     * @return el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el apellido del usuario.
     * @return el apellido del usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Retorna la contraseña del usuario.
     * @return la contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

}
