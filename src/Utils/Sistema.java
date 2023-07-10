package Utils;

/**
 * La interface del Sistema.
 */

public interface Sistema {

    /**
     * iniciarPrograma:
     * Este método permite comenzar la ejecución del programa,
     * se inicializa un nuevo formulario que implementa su interface.
     */
    public void iniciarPrograma();

    /**
     * lecturaArchivos:
     * En este método, se llama a la clase Utils, y sus métodos que realizan
     * la lectura de los 3 archivos: libros.txt, usuarios.txt, y registro.txt.
     */
    public void lecturaArchivos();


}
