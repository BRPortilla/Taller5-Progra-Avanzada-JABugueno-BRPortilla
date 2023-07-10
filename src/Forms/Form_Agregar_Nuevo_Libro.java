package Forms;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * El formulario para agregar un nuevo libro a la lista de libros.
 */

public class Form_Agregar_Nuevo_Libro extends JFrame{

    /**
     * La lista de usuarios (solo está en esta clase para que se pueda regresar al menú).
     */
    private LinkedList<Usuario> listaUsuarios;

    /**
     * La lista de libros (necesaria, para agregar el libro nuevo).
     */
    private LinkedList<Libro> listaLibros;

    /**
     * La lista de transacciones (se llama para que se pueda regresar al menú principal).
     */
    private LinkedList<Transaccion> listaTransacciones;

    /**
     * El usuario loggeado (se llama aquí para que se pueda regresar al menú principal).
     */
    private Usuario usuario_loggeado;

    /**
     * La referencia a un libro.
     */
    private Libro libro;

    /**
     * El panel de agregar un nuevo libro.
     */
    private JPanel addBook_form;

    /**
     * El label "ISBN:" al lado de su campo de texto.
     */
    private JLabel label_isbn;

    /**
     * El label "Título del libro:" al lado de su campo de texto.
     */
    private JLabel label_titulo;

    /**
     * El label "Autor(a):" al lado de su campo de texto.
     */
    private JLabel label_autor;

    /**
     * El label "Categoría:" al lado de su campo de texto.
     */
    private JLabel label_categoria;

    /**
     * El label "Cantidad de páginas:" al lado de su campo de texto.
     */
    private JLabel label_numero_paginas;

    /**
     * El label "Cantidad de stock:" al lado de su campo de texto.
     */
    private JLabel label_stock;

    /**
     * El campo de texto para ingresar el ISBN del libro.
     */
    private JTextField campo_isbn;

    /**
     * El campo de texto para ingresar el título del libro.
     */
    private JTextField campo_titulo;

    /**
     * El campo de texto para ingresar el/la autor(a) del libro.
     */
    private JTextField campo_autor;

    /**
     * El campo de texto para ingresar la cantidad de páginas del libro.
     * Se valida si se ingresa una cantidad numérica, mayor a 0.
     */
    private JTextField campo_paginas;

    /**
     * El campo de texto para ingresar la categoría del libro.
     */
    private JTextField campo_categoria;

    /**
     * El campo de texto para ingresar la cantidad de stock/copias del libro.
     * Se valida si se ingresa una cantidad numérica, mayor a 0.
     */
    private JTextField campo_copias;

    /**
     * El botón de "agregar libro".
     */
    private JButton boton_agregar;

    /**
     * El botón de "regresar al menú".
     */
    private JButton boton_regresar_menu;

    /**
     * El constructor del formulario de agregar nuevo libro. Primeramente, las configuraciones para
     * hacer visible la ventana, y se tienen los listeners para los botones en esta ventana.
     * @param listaUsuarios (lista de usuarios).
     * @param listaLibros (lista de libros).
     * @param listaTransacciones (lista de transacciones).
     * @param usuario (usuario loggeado).
     */

    public Form_Agregar_Nuevo_Libro(LinkedList<Usuario> listaUsuarios, LinkedList<Libro> listaLibros, LinkedList<Transaccion> listaTransacciones, Usuario usuario){

        //Referencias de las listas y el usuario loggeado.

        this.listaLibros = listaLibros;
        this.listaUsuarios = listaUsuarios;
        this.listaTransacciones = listaTransacciones;
        this.usuario_loggeado = usuario;
        this.libro = null;

        //Configuraciones de la ventana:
        //Panel de contenidos, título, tamaño, operación de cierre, visibilidad : verdadera.

        setContentPane(this.addBook_form);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("Agregar nuevo libro");
        setVisible(true);

        //Listener del botón "agregar libro".
        boton_agregar.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * Se llama al método de agregar libro, y se envía la lista de libros.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                agregarLibro(listaLibros);
            }
        });

        //Listener del botón "regresar al menú".
        boton_regresar_menu.addActionListener(new ActionListener() {
            /**
             * Se invoca este método cuando ocurre una acción (se presiona el botón).
             * El formulario de agregar nuevo libro se hace invisible, y se inicializa el formulario del menú principal.
             * @param boton_presionado (se presiona el botón, la acción a procesar).
             */
            @Override
            public void actionPerformed(ActionEvent boton_presionado) {
                setVisible(false);
                dispose();
                //Se genera el formulario de menú principal.
                Form_Menu_Principal formMenuPrincipal = new Form_Menu_Principal(listaLibros,listaUsuarios, listaTransacciones,usuario_loggeado);
            }
        });
    }

    /**
     * Este método permite agregar el libro a la lista. Aunque, primeramente se realizan varias validaciones
     * para que el libro ingresado sea válido. Si cualquiera de los campos está vacío, se despliega un mensaje de advertencia
     * para rellenar todos los campos. Luego, se valida si se ingresó una cantidad válida de páginas y de stock (mayor a 0, con
     * caracteres numéricos). Si el usuario ingresó una cantidad inválida (como letras), o alguna cantidad inferior a 1, se despliega
     * un mensaje de error (se captura la excepción) y se limpian esos 2 campos (páginas y stock) solamente. Y como última validación,
     * se toma el ISBN ingresado en el campo, y se busca por toda la lista de libros, si existe un libro con ese ISBN. Si se encuentra
     * un libro que posee ese ISBN, se despliega un mensaje de error, comunicando que no se puede agregar, y luego se despliega otro
     * mensaje, con los datos del libro que posee ese ISBN (combinación y concatenación de Strings). Si no se encuentra el libro,
     * se agrega a la lista y se despliega el mensaje de feedback que se agregó exitosamente.
     * @param listaLibros (la lista de libros).
     */
    public void agregarLibro(LinkedList<Libro> listaLibros){

        //Si alguno de los campos está vacío: mensaje de error.

        if (this.campo_autor.getText().isEmpty() || this.campo_categoria.getText().isEmpty() || this.campo_copias.getText().isEmpty()
        || this.campo_isbn.getText().isEmpty() || this.campo_paginas.getText().isEmpty() || this.campo_titulo.getText().isEmpty()){
            JOptionPane.showMessageDialog(this.addBook_form,"Por favor, rellene todos los campos.","Error: campos vacíos",JOptionPane.WARNING_MESSAGE);
            return;
        }


        //Primero se declaran las variables de páginas y copias, y mediante Integer.parseInt() del texto
        //en el campo, se transforman a int. Si una o ambas cantidades son menores a 1, o son caracteres no numéricos,
        //se despliega un mensaje de error, y se captura la excepción. Se limpian solo esos 2 campos de texto.

        int campo_copias_int;
        int campo_paginas_int;
        try {
            String campo_copias_str = this.campo_copias.getText();
            String campo_paginas_str = this.campo_paginas.getText();
            campo_copias_int = Integer.parseInt(campo_copias_str);
            campo_paginas_int = Integer.parseInt(campo_paginas_str);

            //Copias o páginas <= 0
            if (campo_copias_int <= 0 || campo_paginas_int <= 0){
                JOptionPane.showMessageDialog(this.addBook_form,"Error: cantidad inválida de stock o número de páginas. Ingrese cantidades mayores a 0.","Cantidad inválida",JOptionPane.ERROR_MESSAGE);
                //Limpieza de ambos campos.
                this.campo_paginas.setText("");
                this.campo_copias.setText("");
                return;
            }
        }catch (Exception exception){
            //Captura de excepción (caracteres no numéricos).
            JOptionPane.showMessageDialog(this.addBook_form,"Error: término no númerico en campo de copias y/o páginas","Error de cantidad inválida",JOptionPane.ERROR_MESSAGE);
            //Limpieza de ambos campos.
            this.campo_paginas.setText("");
            this.campo_copias.setText("");
            return;
        }

        //Se recorre la lista en busca de algún libro que posea el mismo ISBN.
        String isbn_a_agregar = this.campo_isbn.getText();
        for (Libro libroAux : listaLibros){
            if (libroAux.getCodigo_isbn().equals(isbn_a_agregar)){
                //Si existe un libro con el mismo ISBN, se despliegan 2 mensajes: uno de error, y otro con la información
                //del libro ya existente.
                JOptionPane.showMessageDialog(this.addBook_form,"El ISBN ingresado ya existe para un libro en el sistema.","ISBN ya existente",JOptionPane.ERROR_MESSAGE);
                //Concatenación de Strings:
                String titulo = ("Título del libro: " + libroAux.getTitulo() + "\n");
                String autor = ("Autor(a) del libro: " + libroAux.getAutor() + "\n");
                String categoria = ("Categoría del libro: " + libroAux.getCategoria() + "\n");
                String isbn = ("ISBN del libro: " + libroAux.getCodigo_isbn() + "\n");
                String copias = ("Cantidad de copias: " + libroAux.getStock() + "\n");
                String paginas = ("Cantidad de páginas: " + libroAux.getCantidad_paginas());
                JOptionPane.showMessageDialog(this.addBook_form,titulo + autor + categoria + isbn + copias + paginas,"Libro con el ISBN buscado",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        //Si no existe, se crea y se agrega.
        Libro libro_nuevo = new Libro(isbn_a_agregar,this.campo_titulo.getText(),this.campo_autor.getText(),this.campo_categoria.getText(),campo_paginas_int,campo_copias_int);
        listaLibros.add(libro_nuevo);
        //Limpieza de campos.
        limpiarCampos();
        //Mensaje de feedback.
        JOptionPane.showMessageDialog(this.addBook_form,"¡Libro agregado con éxito!","Proceso exitoso",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Limpia los campos de texto, dejándolos sin texto.
     */
    public void limpiarCampos(){
        this.campo_copias.setText("");
        this.campo_paginas.setText("");
        this.campo_titulo.setText("");
        this.campo_isbn.setText("");
        this.campo_categoria.setText("");
        this.campo_autor.setText("");
    }




}
