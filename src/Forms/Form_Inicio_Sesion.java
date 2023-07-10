package Forms;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * El formulario inicial, para iniciar sesión.
 */
public class Form_Inicio_Sesion extends JFrame {

    /**
     * El panel del formulario de iniciar sesión.
     */
    private JPanel session_Form;

    /**
     * Campo de texto para ingresar la contraseña.
     */
    private JTextField campo_contrasenia;

    /**
     * Campo de texto para ingresar el rut.
     */
    private JTextField campo_rut;

    /**
     * El botón para iniciar sesión.
     */
    private JButton boton_iniciar_sesion;

    /**
     * El botón para cerrar la aplicación.
     */
    private JButton boton_cerrar_aplicacion;

    /**
     * El texto "Contraseña:" al lado del campo.
     */
    private JLabel label_contrasenia;

    /**
     * El texto "RUT:" al lado del campo.
     */
    private JLabel label_rut;

    /**
     * La lista de usuarios.
     */
    LinkedList<Usuario> listaUsuarios;

    /**
     * La lista de libros.
     */
    LinkedList<Libro> listaLibros;

    /**
     * La lista de transacciones.
     */
    LinkedList<Transaccion> listaTransacciones;

    /**
     * El constructor del formulario de inicio de sesión. Tiene las configuraciones para que pueda ser visible,
     * se tienen las listas para poder llevarlas al menú principal, y los listeners de los botones.
     * @param listaUsuarios (la lista de usuarios).
     * @param listaLibros (la lista de libros).
     * @param listaTransacciones (la lista de transacciones).
     */
    public Form_Inicio_Sesion(LinkedList<Usuario> listaUsuarios, LinkedList<Libro> listaLibros, LinkedList<Transaccion> listaTransacciones){

        //Referencias de las listas.
        this.listaLibros = listaLibros;
        this.listaUsuarios = listaUsuarios;
        this.listaTransacciones = listaTransacciones;

        //Configuraciones iniciales para la ventana.
        //Panel de contenidos, título, tamaño, operación de cierre, visibilidad : verdadera.
        setContentPane(session_Form);
        setTitle("Iniciar sesión");
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        //Listener del botón "iniciar sesión".
        boton_iniciar_sesion.addActionListener(new ActionListener() {

            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * Primeramente, se llama al método de usuarioExiste. Si lo que retorna
             * el método no es nulo (si existe usuario), la pantalla se oculta e inicia
             * el formulario del menú principal. Si el usuario no existe, se despliega
             * un mensaje de error y se limpian los campos de texto.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */

            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                Usuario usuario = usuarioExiste(listaUsuarios);
                if (usuario != null){
                    //Si el usuario no es nulo (existe), se inicia el programa.
                    setVisible(false);
                    dispose();
                    Form_Menu_Principal formMenuPrincipal = new Form_Menu_Principal(listaLibros,listaUsuarios, listaTransacciones,usuario);
                }else{
                    //Mensaje de error y se limpian los campos.
                    JOptionPane.showMessageDialog(session_Form,"RUT y/o contraseña inválidas.","Error de inicio de sesión",JOptionPane.WARNING_MESSAGE);
                    limpiar();
                }
            }
        });

        //Listener del botón "cerrar aplicación".
        boton_cerrar_aplicacion.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * Se llama al método de cerrar, y se envían las 3 listas, para que se pueda
             * realizar la escritura de los 3 archivos.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */

            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                cerrar(listaUsuarios,listaLibros, listaTransacciones);
            }
        });
    }

    /**
     * usuarioExiste: Recorre la lista de usuarios para buscar si un usuario existe con
     * el rut y contraseña ingresados en los campos de texto. Si algún campo está vacío, se despliega
     * un mensaje de error y se limpian los campos. En caso contrario, se recorre la lista buscando
     * a un usuario con ese rut y contraseña.
     * @param listaUsuarios (la lista de usuarios).
     * @return el usuario de la lista, si existe. Retorna null si no existe.
     */
    public Usuario usuarioExiste(LinkedList<Usuario> listaUsuarios){
        try {

            //Se consiguen tanto rut como contraseña.
            String rut = this.campo_rut.getText();
            String contrasenia = this.campo_contrasenia.getText();

            //Si ningún campo está vacío, se recorre la lista buscando a un usuario con el mismo rut y contraseña.
            if (!rut.isEmpty() && !contrasenia.isEmpty()){
                for (Usuario usuario : listaUsuarios){
                    if (usuario.getRut().equals(rut) && usuario.getContrasenia().equals(contrasenia)){
                        return usuario;
                    }
                }
            }else{
                //Mensaje de error y limpieza de campos.
                JOptionPane.showMessageDialog(session_Form,"Por favor, rellene todos los campos.","Campos vacíos",JOptionPane.WARNING_MESSAGE);
                limpiar();
            }
        }catch (Exception exception){
            JOptionPane.showMessageDialog(session_Form,"[!] Ocurrió un error [!] " + exception.getMessage());
        }
        //Null si no se encuentra el usuario.
        return null;
    }

    /**
     * Limpia los campos de contraseña y rut, dejándolos sin texto.
     */
    public void limpiar(){
        this.campo_rut.setText("");
        this.campo_contrasenia.setText("");
    }

    /**
     * Se cierra la aplicación (mediante el comando System.exit, con código 0 (no hay errores)), y se llama a la clase EscrituraArchivos,
     * para poder escribir los archivos de las 3 entidades: usuarios, libros, y transacciones.
     * @param listaUsuarios (la lista de usuarios).
     * @param listaLibros (la lista de libros).
     * @param listaTransacciones (la lista de transacciones).
     */
    public void cerrar(LinkedList<Usuario> listaUsuarios, LinkedList<Libro> listaLibros, LinkedList<Transaccion> listaTransacciones){
        Utils.EscrituraArchivos.escribirArchivoUsuarios(listaUsuarios);
        Utils.EscrituraArchivos.escribirArchivoLibros(listaLibros);
        Utils.EscrituraArchivos.escribirArchivoTransacciones(listaTransacciones);
        System.exit(0);
    }









}