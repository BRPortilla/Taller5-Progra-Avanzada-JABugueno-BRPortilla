package Forms;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;

import java.util.LinkedList;

/**
 * La interface de formulario.
 */

public interface Form {

    /**
     * Este método inicializa el formulario de inicio de sesión,
     * permitiendo el comienzo del programa. Recibe las listas de usuarios, libros y transacciones.
     * @param listaUsuarios (la lista de los usuarios en el sistema).
     * @param listaLibros (la lista de libros en el sistema).
     * @param listaTransacciones (la lista de las transacciones de los usuarios).
     */

    public void iniciarFormularios(LinkedList<Usuario> listaUsuarios, LinkedList<Libro> listaLibros, LinkedList<Transaccion> listaTransacciones);


}
