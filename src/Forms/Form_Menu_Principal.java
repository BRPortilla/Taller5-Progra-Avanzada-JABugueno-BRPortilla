package Forms;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * El formulario del menú principal. Posee 5 botones para realizar funciones:
 * prestar libro, devolver libro, agregar libro, buscar libro, o cerrar sesión.
 */
public class Form_Menu_Principal extends JFrame{

    /**
     * El panel del menú principal (JPanel).
     */
    private JPanel mainMenuForm;

    /**
     * El botón de "agregar nuevo libro".
     */
    private JButton boton_agregarNuevoLibro;

    /**
     * El botón de "buscar libro".
     */
    private JButton boton_buscarLibro;

    /**
     * El botón de "prestar libro".
     */
    private JButton boton_prestarLibro;

    /**
     * El botón de "devolver libro".
     */
    private JButton boton_devolver_libro;

    /**
     * El botón de "cerrar sesión".
     */
    private JButton boton_cerrar_sesion;

    /**
     * La lista de libros (es llamada previamente de otras clases).
     */
    private LinkedList<Libro> listaLibros;

    /**
     * La lista de usuarios (es llamada previamente de otras clases).
     */
    private LinkedList<Usuario> listaUsuarios;

    /**
     * La lista de transacciones (es llamada previamente de otras clases).
     */
    private LinkedList<Transaccion> listaTransacciones;

    /**
     * El usuario que inició sesión o "loggeó".
     */
    private Usuario usuario_loggeado;

    /**
     * El constructor del formulario de menú principal. Dentro del constructor,
     * se tienen las configuraciones para poder hacer visible la ventana del menú,
     * y se tienen los listeners (escuchadores), que hacen posible el ocultar el menú
     * y dirigirse a los otros formularios.
     * @param listaLibros (lista de libros).
     * @param listaUsuarios (lista de usuarios).
     * @param listaTransacciones (lista de transacciones).
     * @param usuario (el usuario que inició sesión o se loggeó).
     */
    public Form_Menu_Principal(LinkedList<Libro> listaLibros, LinkedList<Usuario> listaUsuarios, LinkedList<Transaccion> listaTransacciones, Usuario usuario){

        //Se referencian las listas, que contienen los datos cargados anteriormente.

        this.listaLibros = listaLibros;
        this.listaTransacciones = listaTransacciones;
        this.listaUsuarios = listaUsuarios;
        this.usuario_loggeado = usuario;

        //Configuraciones para poder desplegar la pantalla: el panel de contenidos, título,
        //tamaño, título, operación de cierre, y hacer visible la ventana.

        setContentPane(mainMenuForm);
        setTitle("Menú principal de biblioteca");
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        //Listener del botón "buscar libro".
        boton_buscarLibro.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * El menú principal se oculta, y se inicializa el formulario de buscar libro.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                setVisible(false);
                //Se inicializa el formulario de buscar libro, y se envían las listas, más el usuario.
                Form_Buscar_Libro formBuscarLibro = new Form_Buscar_Libro(listaLibros,listaUsuarios, listaTransacciones,usuario_loggeado);
            }
        });

        //Listener del botón "prestar libro".
        boton_prestarLibro.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * El menú principal se oculta, y se inicializa el formulario de prestar libro.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                setVisible(false);
                //Se inicializa el formulario de prestar libro, y se envían las listas, junto al usuario loggeado (habrán transacciones).
                Form_Prestar_Libro formPrestarLibro = new Form_Prestar_Libro(listaLibros,listaUsuarios, listaTransacciones,usuario);
            }
        });


        //Listener del botón "agregar nuevo libro".
        boton_agregarNuevoLibro.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * El menú principal se oculta, y se inicializa el formulario de agregar nuevo libro.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                setVisible(false);
                //Se inicializa el formulario de agregar nuevo libro, y se envían las listas, junto al usuario loggeado.
                Form_Agregar_Nuevo_Libro formAgregarNuevoLibro = new Form_Agregar_Nuevo_Libro(listaUsuarios,listaLibros, listaTransacciones,usuario);
            }
        });


        //Listener del botón "devolver libro".
        boton_devolver_libro.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * El menú principal se oculta, y se inicializa el formulario de devolver libro.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                setVisible(false);
                //Se inicializa el formulario de devolver libro, y se envían las listas, junto al usuario loggeado (habrán transacciones).
                Form_Devolver_Libro formDevolverLibro = new Form_Devolver_Libro(listaUsuarios,listaLibros, listaTransacciones,usuario);
            }
        });


        //Listener del botón "cerrar sesión".
        boton_cerrar_sesion.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * El menú principal se oculta, y se devuelve hacia el inicio de sesión.
             * El usuario loggeado es nulo.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */

            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                setVisible(false);
                usuario_loggeado = null;
                dispose();
                //Se inicializa el formulario de prestar libro, y se envían las listas (si se cierra el programa, se escribirán los datos
                // en los archivos).
                Form_Inicio_Sesion formInicioSesion = new Form_Inicio_Sesion(listaUsuarios,listaLibros, listaTransacciones);
            }
        });
    }
}
