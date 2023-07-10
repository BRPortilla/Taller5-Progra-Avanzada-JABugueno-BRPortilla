package Forms;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * El formulario de devolver libro. Suma 1 el stock de un libro
 * de la lista.
 */
public class Form_Devolver_Libro extends JFrame{

    /**
     * El campo de texto donde se introduce el código ISBN.
     */
    private JTextField campo_isbn;

    /**
     * El botón de "buscar".
     */
    private JButton boton_buscar;

    /**
     * Label con el texto "ISBN":
     */
    private JLabel label_isbn;

    /**
     * El botón de "devolver".
     */
    private JButton boton_devolver;

    /**
     * El botón de "regresar al menú".
     */
    private JButton boton_regresar;

    /**
     * Label con el texto "Título:". Tiene el mismo comportamiento
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_titulo;

    /**
     * Label con el texto "Autor(a):". Tiene el mismo comportamiento
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_autor;

    /**
     * Label con el texto "Categoría:". Tiene el mismo comportamiento
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_categoria;

    /**
     * Label con el texto "Cantidad de páginas del libro:". Tiene el mismo comportamiento
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_paginas;

    /**
     * Label con el texto "Número de copias restantes:". Tiene el mismo comportamiento
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_copias;

    /**
     * El título del libro. El label se hace visible si se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel tituloLibro;

    /**
     * El autor / la autora del libro. El label se hace visible cuando se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel autorLibro;

    /**
     * La categoría del libro. El label se hace visible si se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel categoriaLibro;

    /**
     * La cantidad de páginas del libro. El label se hace visible si se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel cantidadPaginas;

    /**
     * El stock restante del libro. El label se hace visible si se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel cantidadCopias;

    /**
     * El panel del formulario de "devolver libro".
     */
    private JPanel giveBackBook_form;

    /**
     * La lista de usuarios.
     */
    private LinkedList<Usuario> listaUsuarios;

    /**
     * La lista de libros. Se busca el libro en la lista para comprobar
     * si existe en el sistema.
     */
    private LinkedList<Libro> listaLibros;

    /**
     * La lista de transacciones. Como se devolverán los libros,
     * habrá transacciones de devolución.
     */
    private LinkedList<Transaccion> listaTransacciones;

    /**
     * El usuario loggeado. Es necesario obtener sus datos para escribir una nueva
     * transacción.
     */
    private Usuario usuario_loggeado;

    /**
     * La referencia del libro a devolver. Esto es para asegurar que se modifique su stock
     * al devolver el libro.
     */
    private Libro libro;

    /**
     * El constructor del formulario de devolver libro. Se configura su visibilidad en la pantalla, y
     * se poseen los listeners de cada uno de sus botones.
     * @param listaUsuarios (la lista de usuarios).
     * @param listaLibros (la lista de libros).
     * @param listaTransacciones (la lista de transacciones).
     * @param usuario (el usuario loggeado).
     */
    public Form_Devolver_Libro(LinkedList<Usuario> listaUsuarios, LinkedList<Libro> listaLibros, LinkedList<Transaccion> listaTransacciones, Usuario usuario) {
        this.listaLibros = listaLibros;
        this.listaUsuarios = listaUsuarios;
        this.listaTransacciones = listaTransacciones;
        this.usuario_loggeado = usuario;
        this.libro = null;


        //Configuraciones para poder desplegar la pantalla:
        //el panel de contenidos, título, tamaño, título, operación de cierre, y hacer visible la ventana.

        setContentPane(giveBackBook_form);
        setSize(600,600);
        setTitle("Devolución de libro");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Listener del botón "buscar".
        boton_buscar.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * Se llama al método de buscar libro, y el libro que retorne actualizará
             * la referencia de este.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                //Se actualiza la referencia del libro, cuando se retorne luego de buscar.
                libro = buscarLibro(listaLibros);
            }
        });


        //Listener del botón "devolver".
        boton_devolver.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * Se llama al método de devolver libro, llevándose las listas, el usuario y la referencia del libro
             * que puede actualizarse luego de presionar el botón de buscar.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                devolverLibro(listaLibros, listaTransacciones,usuario,libro);
            }
        });


        boton_regresar.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * Oculta el menú actual y regresa al menú principal.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                //Se oculta el actual formulario y va atrás al menú.
                setVisible(false);
                dispose();
                Form_Menu_Principal formMenuPrincipal = new Form_Menu_Principal(listaLibros,listaUsuarios, listaTransacciones,usuario);
            }
        });
    }

    /**
     * Primero se busca el libro, a partir del ISBN ingresado en el campo de texto.
     * Si el campo está vacío, se retorna un mensaje de error, y se limpian los labels.
     * @param listaLibros (la lista de libros).
     * @return el libro si lo encuentra, null en el caso contrario.
     */
    public Libro buscarLibro(LinkedList<Libro> listaLibros){
        //Texto del ISBN vacío:
        if (this.campo_isbn.getText().isEmpty()){
            JOptionPane.showMessageDialog(this.giveBackBook_form,"No se puede buscar libros con el campo vacío. Por favor, rellene el campo.","Error de búsqueda",JOptionPane.WARNING_MESSAGE);
            limpiarLabels();
            return null;
        }

        //Se recorre toda la lista de libros.
        String isbn_a_buscar = this.campo_isbn.getText();
        for(Libro libroAux : listaLibros){

            //Si se encuentra el libro (el ISBN a buscar coincide con uno), se despliega la información
            //mediante los labels, y se hacen visibles.
            if (libroAux.getCodigo_isbn().equals(isbn_a_buscar)){
                this.tituloLibro.setText(libroAux.getTitulo());
                this.autorLibro.setText(libroAux.getAutor());
                this.categoriaLibro.setText(libroAux.getCategoria());
                this.cantidadCopias.setText(String.valueOf(libroAux.getStock()));
                this.cantidadPaginas.setText(String.valueOf(libroAux.getCantidad_paginas()));
                this.label_titulo.setText("Título: ");
                this.label_autor.setText("Autor(a): ");
                this.label_categoria.setText("Categoría: ");
                this.label_copias.setText("Número de copias restantes: ");
                this.label_paginas.setText("Cantidad de páginas del libro: ");
                return libroAux;
            }
        }

        //Si no se encuentra el libro, se despliega un mensaje de que no existe,
        //se limpian los labels y se retorna null.
        JOptionPane.showMessageDialog(this.giveBackBook_form,"No existe algún libro con el ISBN buscado en el sistema, intente de nuevo.");
        limpiarLabels();
        return null;
    }

    /**
     * Limpia los labels, haciéndolos invisibles.
     */
    public void limpiarLabels(){
        this.tituloLibro.setText("");
        this.categoriaLibro.setText("");
        this.autorLibro.setText("");
        this.cantidadCopias.setText("");
        this.cantidadPaginas.setText("");
        this.label_autor.setText("");
        this.label_copias.setText("");
        this.label_categoria.setText("");
        this.label_paginas.setText("");
        this.label_titulo.setText("");
    }

    /**
     * Aquí se realiza el proceso de devolución del libro. Si el libro es nulo (no existe, el campo estaba vacío, o
     * los labels están invisibles), se retorna un mensaje de error, además de limpiar los labels.
     * En el caso contrario, se genera la transacción, con todos sus argumentos (datos del usuario, y del libro).
     * Se añade a la lista de transacciones y se llama al método de sumar stock del libro, en 1. También
     * se despliega un mensaje de feedback, informando de la devolución exitosa.
     * @param listaLibros (lista de libros).
     * @param listaTransacciones (lista de transacciones).
     * @param usuario (el usuario loggeado).
     * @param libro (el libro a devolver).
     */
    public void devolverLibro(LinkedList<Libro> listaLibros, LinkedList<Transaccion> listaTransacciones, Usuario usuario, Libro libro){
        //Si el libro no existe o los labels están vacíos, sin información de un libro.
        if (libro == null || (this.tituloLibro.getText().isEmpty() && this.autorLibro.getText().isEmpty())){
            JOptionPane.showMessageDialog(this.giveBackBook_form,"Error: realice primeramente una búsqueda válida.","Error al prestar",JOptionPane.ERROR_MESSAGE);
            limpiarLabels();
            return;
        }

        //Se genera una nueva transacción, de tipo devolución (true).
        Transaccion devolucion = new Transaccion(usuario.getRut(), usuario.getNombre(), usuario.getApellido(),libro.getCodigo_isbn(),libro.getTitulo(),true);
        listaTransacciones.add(devolucion);
        sumarStock(listaLibros);
        JOptionPane.showMessageDialog(this.giveBackBook_form,"¡Devolución realizada con éxito!","Devolución exitosa",JOptionPane.INFORMATION_MESSAGE);
        //Limpieza de labels.
        limpiarLabels();
    }

    /**
     * Luego de la devolución, se busca al libro exacto que coincida con el de la referencia,
     * para asegurarse de incrementar en 1 su stock.
     * @param listaLibros (la lista de libros).
     */
    public void sumarStock(LinkedList<Libro> listaLibros){
        for (Libro libroAux : listaLibros){
            if (libroAux.equals(this.libro)){
                //Si el libro al recorrer la lista coincide con el de la referencia, se incrementa en 1
                //su stock.
                int stock_original = this.libro.getStock();
                libroAux.setStock(stock_original+1);
                return;
            }
        }
    }



}