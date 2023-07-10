package Forms;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;

import java.util.LinkedList;

/**
 * La implementación del formulario.
 */
public class FormImpl implements Form{

    /**
     * La lista de usuarios en el sistema.
     */
    LinkedList<Usuario> listaUsuarios;

    /**
     * La lista de libros en el sistema.
     */
    LinkedList<Libro> listaLibros;

    /**
     * La lista de transacciones en el sistema.
     */
    LinkedList<Transaccion> listaTransacciones;

    /**
     * Constructor de la implementación del formulario.
     * @param listaUsuarios (la lista de usuarios).
     * @param listaLibros (la lista de libros).
     * @param listaTransacciones (la lista de transacciones).
     */
    public FormImpl(LinkedList<Usuario> listaUsuarios, LinkedList<Libro> listaLibros, LinkedList<Transaccion> listaTransacciones){
        this.listaLibros = listaLibros;
        this.listaUsuarios = listaUsuarios;
        this.listaTransacciones = listaTransacciones;
        //Se llama al método de iniciar formularios.
        iniciarFormularios(listaUsuarios,listaLibros, listaTransacciones);
    }

    /**
     * Este método inicializa el formulario de inicio de sesión,
     * permitiendo el comienzo del programa. Recibe las listas de usuarios, libros y transacciones.
     * @param listaUsuarios (la lista de los usuarios en el sistema).
     * @param listaLibros (la lista de libros en el sistema).
     * @param listaTransacciones (la lista de las transacciones de los usuarios).
     */
    @Override
    public void iniciarFormularios(LinkedList<Usuario> listaUsuarios, LinkedList<Libro> listaLibros, LinkedList<Transaccion> listaTransacciones) {
        Form_Inicio_Sesion form_inicio_sesion = new Form_Inicio_Sesion(listaUsuarios,listaLibros, listaTransacciones);
    }
}
