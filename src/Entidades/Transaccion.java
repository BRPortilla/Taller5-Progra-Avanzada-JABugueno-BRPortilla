package Entidades;

/**
 * La clase que representa una transacción por parte de un usuario
 * a un libro: un préstamo de un libro o una devolución.
 */

public class Transaccion {

    /**
     * El rut del usuario que realiza la transacción.
     */
    private String rut_vendedor;

    /**
     * El nombre del usuario que realiza la transacción.
     */
    private String nombre;

    /**
     * El apellido del usuario que realiza la transacción.
     */
    private String apellido;

    /**
     * El ISBN del libro involucrado en la transacción.
     */
    private String isbn;

    /**
     * El título del libro involucrado en la transacción.
     */
    private String titulo_libro;

    /**
     * Variable booleana que representa el tipo de transacción.
     * TRUE: devolución.
     * FALSE: préstamo.
     */
    private boolean tipo_transaccion;

    /**
     * Constructor de la transacción.
     * @param rut_vendedor (rut del usuario).
     * @param nombre (nombre del usuario).
     * @param apellido (apellido del usuario).
     * @param isbn (ISBN del libro).
     * @param titulo_libro (título del libro).
     * @param tipo_transaccion (tipo de transacción).
     */
    public Transaccion(String rut_vendedor, String nombre, String apellido, String isbn, String titulo_libro, boolean tipo_transaccion) {
        this.rut_vendedor = rut_vendedor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.isbn = isbn;
        this.titulo_libro = titulo_libro;
        this.tipo_transaccion = tipo_transaccion;
    }

    /**
     * Retorna el rut de usuario.
     * @return el rut del usuario que realiza la transacción.
     */
    public String getRut_vendedor() {
        return rut_vendedor;
    }

    /**
     * Retorna
     * @return el nombre del usuario que realiza la transacción.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna
     * @return el apellido del usuario que realiza la transacción.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Retorna el ISBN del libro
     * @return el ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Retorna el título del libro.
     * @return el título del libro.
     */
    public String getTitulo_libro() {
        return titulo_libro;
    }

    /**
     * Retorna un valor booleano que representa el tipo de transacción.
     * @return true si la transacción es una devolución, false si la transacción es un préstamo.
     */
    public boolean isTipo_transaccion() {
        return tipo_transaccion;
    }
}
