package Forms;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Form_Prestar_Libro extends JFrame{

    /**
     * El panel del formulario de préstamo de libros.
     */
    private JPanel borrowBook_form;

    /**
     * El campo de texto donde se introduce el código ISBN.
     */
    private JTextField campo_isbn;

    /**
     * El botón de "buscar".
     */
    private JButton buscarButton;

    /**
     * Label con el texto "ISBN":
     */
    private JLabel label_isbn;

    /**
     * El botón de "regresar al menú".
     */
    private JButton boton_regresar;

    /**
     * Label con el texto "Título:". Se comporta de la misma forma
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_titulo;

    /**
     * Label con el texto "Autor(a):". Se comporta de la misma forma
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_autor;

    /**
     * Label con el texto "Categoría:". Se comporta de la misma forma
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_categoria;

    /**
     * Label con el texto "Número de copias restantes:". Se comporta de la misma forma
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_copias;

    /**
     * El título del libro. El label se hace visible cuando se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel tituloLibro;

    /**
     * El autor / la autora del libro. El label se hace visible cuando se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel autorLibro;

    /**
     * La categoría del libro. El label se hace visible cuando se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel categoriaLibro;

    /**
     * La cantidad de copias del libro. El label se hace visible cuando se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel cantidadCopiasLibro;

    /**
     * El botón de prestar libro.
     */
    private JButton boton_prestar;

    /**
     * Label con el texto "Cantidad de páginas:". Se comporta de la misma forma
     * que en el menú de buscar libro, es invisible mientras no se encuentra un libro
     * o se presiona el botón de buscar sin texto en el campo de ISBN. Se despliega
     * la información cuando se encuentra un libro.
     */
    private JLabel label_paginas;

    /**
     * La cantidad de páginas del libro. El label se hace visible cuando se encuentra un libro luego de apretar
     * el botón buscar, con un ISBN en el campo de texto.
     */
    private JLabel cantidadPaginas;

    /**
     * La lista de libros en el sistema. Es obligatorio, para poder buscar
     * el libro cuya ISBN se ingresa.
     */
    private LinkedList<Libro> listaLibros;

    /**
     * La lista de usuarios en el sistema.
     */
    private LinkedList<Usuario> listaUsuarios;

    /**
     * La lista de transacciones realizadas. Se necesita, para registrar préstamos.
     */
    private LinkedList<Transaccion> listaTransacciones;

    /**
     * El usuario loggeado en el momento. Se obtienen algunos de sus datos para registrar una nueva transacción.
     */
    private Usuario usuario_loggeado;

    /**
     * La referencia del libro a prestar. Se tiene en cuenta para modificar su stock
     * (decrementar en 1).
     */
    private Libro libro_prestamo;

    /**
     * El constructor del formulario de prestar libro. Se tienen los respectivos ajustes para hacer visible
     * la pantalla, y los listeners para cada botón.
     * @param listaLibros (la lista de libros).
     * @param listaUsuarios (la lista de usuarios).
     * @param listaTransacciones (la lista de transacciones).
     * @param usuario (el usuario loggeado).
     */
    public Form_Prestar_Libro(LinkedList<Libro> listaLibros, LinkedList<Usuario> listaUsuarios, LinkedList<Transaccion> listaTransacciones, Usuario usuario){

        //Referencias de las listas, el usuario loggeado y el libro se inicializa en null.

        this.listaLibros = listaLibros;
        this.listaUsuarios = listaUsuarios;
        this.listaTransacciones = listaTransacciones;
        this.usuario_loggeado = usuario;
        this.libro_prestamo = null;

        //Configuraciones para poder desplegar la pantalla:
        //el panel de contenidos, título, tamaño, título, operación de cierre, y hacer visible la ventana.

        setContentPane(borrowBook_form);
        setTitle("Préstamo de libros");
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Listener para el botón buscar.
        buscarButton.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * El método buscar libro retorna el libro o null, que se busca a partir
             * del ISBN ingresado por el usuario en el campo de texto.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                libro_prestamo = buscarLibro(listaLibros);
            }
        });


        //Listener para el botón "realizar préstamo".
        boton_prestar.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * Se realiza el proceso de préstamo del libro.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                realizarPrestamo(listaLibros, listaTransacciones,usuario_loggeado,libro_prestamo);
            }
        });


        //Listener para el botón "regresar al menú".
        boton_regresar.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * Se cierrra el menú de préstamo y regresa al menú principal.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                setVisible(false);
                dispose();
                //Nuevo formulario de menú principal.
                Form_Menu_Principal formMenuPrincipal = new Form_Menu_Principal(listaLibros,listaUsuarios, listaTransacciones,usuario_loggeado);
            }
        });
    }

    /**
     * Se busca el libro para comprobar si existe en el sistema.
     * Se despliega un mensaje de error y se limpian los labels en el caso de que
     * el campo de texto de ISBN esté vacío, retornando null, pero si el campo no
     * está vacío, se realiza la búsqueda. Recorriendo la lista de libros, si el ISBN
     * en el campo de texto coincida con el código ISBN de uno de los libros, los labels
     * se hacen visibles, desplegando la información de este, y retornando el libro.
     * Si no se encuentra, se despliega un mensaje de error, se limpian los labels, y
     * se retorna null.
     * @param listaLibros (la lista de libros).
     * @return el libro si se encuentra en la lista, null si el campo de texto está vacío o el libro no se encuentra.
     */
    public Libro buscarLibro(LinkedList<Libro> listaLibros) {
        //Campo de texto del ISBN está vacío.
        if (this.campo_isbn.getText().isEmpty()){
            JOptionPane.showMessageDialog(borrowBook_form,"Por favor, rellene todos los campos.");
            limpiarCampos();
            return null;
        }

        //Recorrido de la lista.
        for (Libro libroAux : listaLibros){
            //Si el ISBN del libro coincide con el del campo de texto.
            if (this.campo_isbn.getText().equals(libroAux.getCodigo_isbn())){
                this.tituloLibro.setText(libroAux.getTitulo());
                this.autorLibro.setText(libroAux.getAutor());
                this.categoriaLibro.setText(libroAux.getCategoria());
                this.cantidadCopiasLibro.setText(String.valueOf(libroAux.getStock()));
                this.cantidadPaginas.setText(String.valueOf(libroAux.getCantidad_paginas()));
                this.label_titulo.setText("Título: ");
                this.label_autor.setText("Autor(a): ");
                this.label_categoria.setText("Categoría: ");
                this.label_copias.setText("Número de copias restantes: ");
                this.label_paginas.setText("Cantidad de páginas: ");
                return libroAux;
            }
        }
        //Despliegue del mensaje de error (el libro no existe).
        JOptionPane.showMessageDialog(this.borrowBook_form,"No existe algún libro con el ISBN buscado, intente de nuevo.","ISBN no válido",JOptionPane.WARNING_MESSAGE);
        //Limpieza de campos y retorno de null.
        limpiarCampos();
        return null;
    }

    /**
     * Este método invisibiliza los labels, cuando no se encuentra un libro o se haya
     * prestado uno.
     */
    public void limpiarCampos() {
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

    /**
     * El proceso de préstamo del libro. Si el libro es nulo (no existe, el campo estaba vacío, o
     * los labels están invisibles), se retorna un mensaje de error, además de limpiar los labels.
     * Luego, se valida que haya stock del libro correspondiente. Si no tiene (stock = 0), se despliega
     * un mensaje de error informando que no hay stock del libro, y se limpian los labels. En caso de que no
     * haya contratiempos, se genera una nueva transacción, de tipo préstamo, se agrega a la lista de transacciones,
     * se cambia el stock del libro con el método de cambiar Stock, y se despliega un mensaje de éxito. Se limpian los labels.
     * @param listaLibros (lista de libros).
     * @param listaTransacciones (lista de transacciones).
     * @param usuario (el usuario loggeado).
     * @param libro (el libro a devolver).
     */
    public void realizarPrestamo(LinkedList<Libro> listaLibros, LinkedList<Transaccion> listaTransacciones, Usuario usuario, Libro libro){
        //Si el libro no existe, o si los labels están vacíos.
        if ((this.tituloLibro.getText().isEmpty() && this.autorLibro.getText().isEmpty()) || libro == null){
            JOptionPane.showMessageDialog(this.borrowBook_form,"Error: realice primeramente una búsqueda válida.","Error de préstamo",JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
            return;
        }

        //Si el stock del libro es 0 y no se puede realizar el préstamo.
        if (libro.getStock() == 0){
            JOptionPane.showMessageDialog(this.borrowBook_form,"Error: el libro ya no posee copias disponibles.","Error de préstamo",JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
            return;
        }

        //Se genera un nuevo préstamo, se añade a la lista, se cambia el stock, mensaje de éxito, limpieza de campos/labels.
        Transaccion nuevaTransaccion = new Transaccion(usuario.getRut(),usuario.getNombre(),usuario.getApellido(),libro.getCodigo_isbn(),libro.getTitulo(),false);
        listaTransacciones.add(nuevaTransaccion);
        cambiarStock(listaLibros);
        JOptionPane.showMessageDialog(this.borrowBook_form,"¡Préstamo realizado con éxito!","Préstamo existoso",JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    /**
     * Después del préstamo, se busca al libro exacto que coincida con el de la referencia,
     * para decrementar en 1 su stock.
     * @param listaLibros (la lista de libros).
     */
    public void cambiarStock(LinkedList<Libro> listaLibros){
        for (Libro libroAux : listaLibros){
            if (libroAux.equals(this.libro_prestamo)){
                //Si el libro de la lista coincide con la de la clase, se decrementa en 1 su stock.
                int stock_original = this.libro_prestamo.getStock();
                libroAux.setStock(stock_original - 1);
                return;
            }
        }
    }







}
