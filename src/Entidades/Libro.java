package Entidades;

/**
 * La clase Libro que representa uno de los libros disponibles
 * en el sistema de la biblioteca.
 */

public class Libro {

    /**
     * El código ISBN que representa a un libro (es único).
     */
    private String codigo_isbn;

    /**
     * El título del libro.
     */

    private String titulo;

    /**
     * El/la autor(a) del libro.
     */
    private String autor;

    /**
     * La categoría o género del libro.
     */
    private String categoria;

    /**
     * La cantidad de páginas del libro.
     */
    private int cantidad_paginas;

    /**
     * El stock o número de copias restantes del libro, en el sistema.
     */
    private int stock;

    /**
     * El constructor del libro.
     * @param codigo_isbn (ISBN del libro).
     * @param titulo (El título del libro).
     * @param autor (El/la autor(a) del libro).
     * @param categoria (La categoría del libro).
     * @param cantidad_paginas (Cantidad de páginas del libro).
     * @param stock (Stock restante del libro).
     */

    public Libro(String codigo_isbn, String titulo, String autor, String categoria, int cantidad_paginas, int stock) {
        this.codigo_isbn = codigo_isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.cantidad_paginas = cantidad_paginas;
        this.stock = stock;
    }

    /**
     * Retorna el código ISBN del libro.
     * @return el código ISBN del libro.
     */

    public String getCodigo_isbn() {
        return codigo_isbn;
    }

    /**
     * Retorna el título del libro.
     * @return el título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Retorna quién escribió el libro.
     * @return el/la autor(a) del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Retorna la categoría del libro.
     * @return la categoría del libro.
     */

    public String getCategoria() {
        return categoria;
    }

    /**
     * Retorna la cantidad de páginas del libro.
     * @return la cantidad de páginas del libro.
     */

    public int getCantidad_paginas() {
        return cantidad_paginas;
    }

    /**
     * Retorna las unidades restantes del libro en el sistema.
     * @return el stock restante del libro.
     */

    public int getStock() {
        return stock;
    }

    /**
     * setStock:
     * Cambia la cantidad de unidades restantes, se usa solamente para los
     * requerimientos de devolver libro (+1) , y prestar libro (-1).
     * @param stock (stock restante del libro).
     */

    public void setStock(int stock) {
        this.stock = stock;
    }
}
