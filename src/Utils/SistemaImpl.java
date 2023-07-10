package Utils;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;
import Forms.Form;
import Forms.FormImpl;

import java.util.LinkedList;

/**
 * La implementación del Sistema (SistemaImpl).
 */

public class SistemaImpl implements Sistema{

    /**
     * La lista de usuarios, que almacena todos los usuarios (trabajadores)
     * que pueden entrar al sistema.
     */

    LinkedList<Usuario> listaUsuarios;

    /**
     * La lista de libros, que guarda todos los libros disponibles en el sistema.
     */
    LinkedList<Libro> listaLibros;

    /**
     * La lista de transacciones, aquí se registran todas las transacciones (préstamos
     * y devoluciones) de libros por parte de los usuarios.
     */
    LinkedList<Transaccion> listaTransacciones;

    /**
     * El constructor de la implementación del sistema.
     */
    public SistemaImpl(){
        //Se llama al método de lectura de archivos (se llama a la clase Utils).
        lecturaArchivos();

        //Se inicia el programa.
        iniciarPrograma();
    }


    /**
     * iniciarPrograma:
     * Este método permite comenzar la ejecución del programa,
     * se inicializa un nuevo formulario que implementa su interface.
     */
    @Override
    public void iniciarPrograma() {
        //Se inicializa el formulario, y también se lleva las listas de usuarios, libros, y transacciones.
        Form formImpl = new FormImpl(this.listaUsuarios,this.listaLibros,this.listaTransacciones);
    }

    /**
     * lecturaArchivos:
     * En este método, se llama a la clase Utils, y sus métodos que realizan
     * la lectura de los 3 archivos: libros.txt, usuarios.txt, y registro.txt.
     */
    @Override
    public void lecturaArchivos() {
        //Se inicializan las listas necesarias (comienzan en null), y
        //con la clase Utils se realiza la lectura de datos.
        this.listaUsuarios = new LinkedList<>();
        this.listaLibros = new LinkedList<>();
        this.listaTransacciones = new LinkedList<>();
        Utils.LecturaArchivos.leerArchivoLibros(this.listaLibros);
        Utils.LecturaArchivos.leerArchivoUsuarios(this.listaUsuarios);
        Utils.LecturaArchivos.leerArchivoTransaccion(this.listaTransacciones);
    }


}
