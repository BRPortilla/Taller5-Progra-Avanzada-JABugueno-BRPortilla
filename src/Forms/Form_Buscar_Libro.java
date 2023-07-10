package Forms;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * El formulario para buscar libros, a partir del código ISBN.
 */
public class Form_Buscar_Libro extends JFrame{

    /**
     * El botón de buscar (al introducir el ISBN).
     */
    private JButton buscarButton;

    /**
     * El campo de texto donde el usuario escribe el código ISBN a buscar.
     */
    private JTextField campo_isbn;

    /**
     * El botón para regresar al menú principal.
     */
    private JButton boton_regresar;

    /**
     * El texto de "ISBN:", al lado del campo de texto.
     */
    private JLabel label_isbn;

    /**
     * El panel para el menú de buscar libro (JPanel).
     */
    private JPanel findBook_form;

    /**
     * El texto "Título:" que aparece cuando se encuentra un libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */
    private JLabel label_titulo;

    /**
     * El texto "Autor(a):" que aparece cuando se encuentra un libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */
    private JLabel label_autor;

    /**
     * El texto "Categoría:" que aparece cuando se encuentra un libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */
    private JLabel label_categoria;

    /**
     * El texto "Número de copias restantes:" que aparece cuando se encuentra un libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */
    private JLabel label_copias;

    /**
     * El título del libro, aparece cuando se encuentra el libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */
    private JLabel tituloLibro;

    /**
     * El autor / la autora del libro, aparece cuando se encuentra el libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */

    private JLabel autorLibro;

    /**
     * La categoría del libro, aparece cuando se encuentra el libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */

    private JLabel categoriaLibro;

    /**
     * El stock restante del libro, aparece cuando se encuentra el libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */

    private JLabel cantidadCopiasLibro;

    /**
     * El texto "Cantidad de páginas del libro:" que aparece cuando se encuentra un libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */
    private JLabel label_paginas;

    /**
     * La cantidad de páginas del libro, aparece cuando se encuentra el libro.
     * Cuando no hay algún libro encontrado, no aparece.
     */
    private JLabel cantidadPaginas;

    /**
     * La lista de libros que almacena todos los libros disponibles en el sistema.
     * Obligatorio para este requerimiento.
     */
    private LinkedList<Libro> listaLibros;

    /**
     * La lista de usuarios. No tiene utilidad en esta clase, pero se debe
     * tener en la clase para poder regresar al menú principal.
     */
    private LinkedList<Usuario> listaUsuarios;

    /**
     * La lista de todas las transacciones.
     * Aunque no se tenga utilidad de esta lista en esta clase,
     * es necesaria para poder regresar al menú principal, debido a que
     * se necesita de esta lista para ir hacia atrás.
     */
    private LinkedList<Transaccion> listaTransacciones;

    /**
     * El usuario que se encuentra actualmente loggeado.
     */
    private Usuario usuario_loggeado;

    /**
     * Constructor del formulario de buscar libro. Se tienen las configuraciones para la ventana,
     * y los listeners para cada uno de los botones.
     * @param listaLibros (la lista de libros).
     * @param listaUsuarios (la lista de usuarios).
     * @param listaTransacciones (la lista de transacciones).
     * @param usuario_loggeado (el usuario que inició sesión).
     */

    public Form_Buscar_Libro(LinkedList<Libro> listaLibros, LinkedList<Usuario> listaUsuarios, LinkedList<Transaccion> listaTransacciones, Usuario usuario_loggeado){

        //Se referencian las listas, que contienen los datos cargados anteriormente, y el usuario.

        this.listaLibros = listaLibros;
        this.listaUsuarios = listaUsuarios;
        this.listaTransacciones = listaTransacciones;
        this.usuario_loggeado = usuario_loggeado;

        //Configuraciones para poder desplegar la pantalla: el panel de contenidos, título,
        //tamaño, título, operación de cierre, y hacer visible la ventana.

        setContentPane(findBook_form);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("Búsqueda de libros");
        setVisible(true);

        //Listener del botón "regresar al menú".
        boton_regresar.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * El menú de búsqueda se cierra y se regresa al menú principal.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                setVisible(false);
                dispose();
                //De vuelta al menú principal.
                Form_Menu_Principal formMenuPrincipal = new Form_Menu_Principal(listaLibros,listaUsuarios, listaTransacciones,usuario_loggeado);
            }
        });

        //Listener del botón "buscar".
        buscarButton.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * Se llama el método de buscar libro, enviando la lista de libros.
             * Se realizan validaciones del texto, y se busca el libro en la lista.
             * Si existe, se despliegan sus datos. En caso contrario, mensaje de error.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                buscarLibro(listaLibros);
            }
        });
    }

    /**
     * buscarLibro: permite buscar un libro en la lista de libros.
     * Se valida el texto en el campo de texto (del ISBN). Si el campo está vacío, se
     * despliega un mensaje de error (que primero, el usuario debe rellenar el campo).
     * En caso de que no esté vacío, se extrae el texto, y ese ISBN se busca en la lista de libros.
     * Si el ISBN de un libro coincide con el del campo, se despliegan los datos del libro en la pantalla
     * (el texto de los "labels" cambia a la información del libro). En caso de que no se encuentre, se despliega
     * un mensaje de error, de que el libro no se encontró. Tanto en este caso, como en el que se busque libros
     * con el campo vacío, los labels se settean con texto: "", haciéndose no visibles.
     * @param listaLibros (la lista de libros).
     */


    public void buscarLibro(LinkedList<Libro> listaLibros){
        if (this.campo_isbn.getText().isEmpty()){
            //Si el campo de texto está vacío:
            JOptionPane.showMessageDialog(this.findBook_form,"No se puede buscar un libro con el campo vacío, rellene el campo por favor.");
        }else{
            String isbn_buscado = this.campo_isbn.getText();

            //Se recorre la lista de libros, y se analiza cada libro.
            for(Libro libroAux : listaLibros){

                //Si el ISBN buscado coincide con uno de la lista, se despliegan los datos.
                if (libroAux.getCodigo_isbn().equals(isbn_buscado)){
                    //Los labels se hacen visibles.
                    this.tituloLibro.setText(libroAux.getTitulo());
                    this.autorLibro.setText(libroAux.getAutor());
                    this.categoriaLibro.setText(libroAux.getCategoria());
                    this.cantidadCopiasLibro.setText(String.valueOf(libroAux.getStock()));
                    this.cantidadPaginas.setText(String.valueOf(libroAux.getCantidad_paginas()));
                    this.label_titulo.setText("Título: ");
                    this.label_autor.setText("Autor(a): ");
                    this.label_categoria.setText("Categoría: ");
                    this.label_copias.setText("Número de copias restantes: ");
                    this.label_paginas.setText("Cantidad de páginas del libro: ");
                    return;
                }
            }

            //Cuando se recorra toda la lista y no se encuentre el libro, se despliega un mensaje de error y se limpian los labels.
            JOptionPane.showMessageDialog(this.findBook_form,"No existe algún libro con el ISBN buscado, intente de nuevo.");
            this.tituloLibro.setText("");
            this.autorLibro.setText("");
            this.categoriaLibro.setText("");
            this.cantidadCopiasLibro.setText("");
            this.cantidadPaginas.setText("");
            this.label_titulo.setText("");
            this.label_autor.setText("");
            this.label_categoria.setText("");
            this.label_copias.setText("");
            this.label_paginas.setText("");
        }
    }
}