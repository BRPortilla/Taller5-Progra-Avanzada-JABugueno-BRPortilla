package Utils;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

/**
 * La clase que permite la lectura de archivos de usuarios, libros y transacciones
 * (usuarios.txt, libros.txt, registro.txt).
 * @author Marcelo Céspedes (Ayudante del otro paralelo, que dejó esta clase escrita).
 */
public class LecturaArchivos {

    /*
        Para la correcta lectura de los archivos, estos deben estan en la carpeta del proyecto, fuera
        de la carpeta "src".
     */

    /**
     * Método encargado de leer el archivo de libros (libros.txt). Cada campo de la línea leída
     * se almacena en un arreglo de Strings (chain), y a partir de ello se obtienen las características
     * del libro. Para el libro, autor(a), isbn y categoria, se realiza el reemplazo de slashes por comas,
     * para que en el programa se pueda visualizar con comas (en el caso que por ejemplo, se tenga un libro
     * que en el título contenga una coma). Finalmente, se agrega a la lista de libros.
     * @param listaLibros (lista de libros).
     */
    public static void leerArchivoLibros(LinkedList<Libro> listaLibros) {
        // Leer el archivo "libros.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("libros.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String isbn = chain[0];
                String title = chain[1];
                String author = chain[2];
                String category = chain[3];
                int pages = Integer.parseInt(chain[4]);
                int copies = Integer.parseInt(chain[5]);

                //Validación de slashes y comas (para que en el programa se pueda desplegar los nombres u
                //otras características con comas).
                String libro_con_comas = title.replaceAll("/",",");
                String autor_con_comas = author.replaceAll("/",",");
                String isbn_con_comas = isbn.replaceAll("/",",");
                String categoria_con_comas = category.replaceAll("/",",");

                Libro nuevoLibro = new Libro(isbn_con_comas,libro_con_comas,autor_con_comas,categoria_con_comas,pages,copies);
                listaLibros.add(nuevoLibro);
            }
        } catch (Exception e) {
            //Captura de excepción.
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Método encargado de leer el archivo de usuarios (usuarios.txt). Del arreglo de Strings, se obtienen
     * los datos de cada usuario. Se agrega el usuario.
     * @param listaUsuarios (la lista de usuarios).
     */
    public static void leerArchivoUsuarios(LinkedList<Usuario> listaUsuarios) {

        // Leer el archivo "usuarios.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String rut = chain[0];
                String name = chain[1];
                String lastname = chain[2];
                String password = chain[3];

                Usuario nuevoUsuario = new Usuario(rut,name,lastname,password);
                listaUsuarios.add(nuevoUsuario);
            }
        } catch (Exception e) {
            //Captura de excepción.
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Método encargado de leer el archivo de transacciones (registro.txt). Cada campo de la línea
     * se almacena en un arreglo de Strings. Se realiza, así como en el proceso de lectura de libros,
     * una validación para las comas: cada slash se reemplaza para una coma para que en el programa
     * se pueda desplegar con comas, y no tener fallas de lectura/escritura de archivo.
     * También, dependiendo si la transacción es una devolución o préstamo, se envía true o false
     * al parámetro booleano de tipo de transacción.
     * @param listaTransacciones (lista de transacciones).
     */
    public static void leerArchivoTransaccion(LinkedList<Transaccion> listaTransacciones) {
        //Lee el archivo "registro.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("registro.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String rut_vendedor = chain[0];
                String nombre = chain[1];
                String apellido = chain[2];
                String isbn_libro = chain[3];
                String titulo_libro = chain[4];
                String tipo_transaccion_str = chain[5];

                //Reemplazo de slashes por comas.
                String titulo_libro_comas = titulo_libro.replaceAll("/",",");
                String isbn_comas = isbn_libro.replaceAll("/",",");

                boolean tipo_transaccion = false;

                //Si la transacción es un préstamo (FALSE) o devolución (TRUE).

                if (tipo_transaccion_str.equalsIgnoreCase("prestamo")){
                    tipo_transaccion = false;
                }else if (tipo_transaccion_str.equalsIgnoreCase("devolucion")){
                    tipo_transaccion = true;
                }

                Transaccion transaccion = new Transaccion(rut_vendedor,nombre,apellido,isbn_comas,titulo_libro_comas,tipo_transaccion);
                listaTransacciones.add(transaccion);
            }
        } catch (Exception e) {
            //Captura de excepción.
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}