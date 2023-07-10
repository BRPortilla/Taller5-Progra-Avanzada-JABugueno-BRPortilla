package Utils;

import Entidades.Libro;
import Entidades.Transaccion;
import Entidades.Usuario;
import ucn.StdOut;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.LinkedList;

/**
 * La clase que permite la sobreescritura de los archivos con los datos actualizados,
 * tanto de los usuarios, libros y transacciones.
 */

public class EscrituraArchivos {

    /**
     * Método que permite la escritura del archivo de libros (libros.txt).
     * Se recorre toda la lista de libros, obtienendo los datos de cada uno,
     * y se forma un string separados por comas para agregar al archivo.
     * La presencia de comas en el nombre del libro, autor, etc. afecta el proceso
     * de escritura y lectura, y por eso se reemplaza por slash (al leer el archivo, se
     * realiza lo contrario: cada slash se reemplaza por una coma).
     * @param listaLibros (la lista de libros).
     */
    public static void escribirArchivoLibros(LinkedList<Libro> listaLibros){
        //Se rodea de un try y catch, que permite capturar cualquier excepción.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("libros.txt"))){
            for (Libro libro : listaLibros){
                //Se obtienen el título, autor, isbn, y categoría con comas, y las comas
                //se reemplazan con slash para no afectar el proceso de escritura y lectura.
                String libro_sin_comas = libro.getTitulo().replaceAll(",","/");
                String autor_sin_comas = libro.getAutor().replaceAll(",","/");
                String isbn_sin_comas = libro.getCodigo_isbn().replaceAll(",","/");
                String categoria_sin_comas = libro.getCategoria().replaceAll(",","/");

                //Se forma un string de una linea, cada campo separado por comas, y se escribe.
                String linea = isbn_sin_comas + "," + libro_sin_comas + "," + autor_sin_comas + "," + categoria_sin_comas + "," + libro.getCantidad_paginas() + "," + libro.getStock();
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
        }catch (Exception exception){
            StdOut.println("[!] Ha ocurrido un error [!] " + exception.getMessage());
        }
    }

    /**
     * Permite la escritura del archivo de usuarios (usuarios.txt).
     * Por cada usuario, se obtienen sus datos para escribir una línea, cada campo
     * separado por comas.
     * @param listaUsuarios (la lista de usuarios).
     */

    public static void escribirArchivoUsuarios(LinkedList<Usuario> listaUsuarios){
        //El try-catch permite la captura de cualquier excepción.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"))){
            for (Usuario usuario : listaUsuarios){
                String linea = usuario.getRut() + "," + usuario.getNombre() + "," + usuario.getApellido() + "," + usuario.getContrasenia();
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
        }catch (Exception exception){
            StdOut.println("[!] Ha ocurrido un error [!] " + exception.getMessage());
        }
    }

    /**
     * Realiza la escritura del archivo de transacciones (registro.txt).
     * Así como en el método de escritura de libros, se realiza también una validación
     * referente a las comas del título del libro (o isbn). También, se valida la variable
     * booleana del tipo de transacción (se escribe si es un préstamo o devolución).
     * @param listaTransacciones (la lista de transacciones).
     */

    public static void escribirArchivoTransacciones(LinkedList<Transaccion> listaTransacciones){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("registro.txt"))){
            for (Transaccion reserva : listaTransacciones){
                String transaccion = "";

                //tipo_transacción = TRUE -> devolución.
                //tipo_transacción = FALSE -> préstamo.

                if (reserva.isTipo_transaccion()){
                    transaccion = "devolucion";
                }else{
                    transaccion = "prestamo";
                }

                //Las comas se reemplazan por slashes, que en el proceso de lectura se podrán cambiar a comas,
                //permitiendo que no hayan fallos de lectura o escritura.

                String libro_sin_comas = reserva.getTitulo_libro().replaceAll(",","/");
                String isbn_sin_comas = reserva.getIsbn().replaceAll(",","/");

                //Se escribe la línea.
                String linea = reserva.getRut_vendedor() + "," + reserva.getNombre() + "," + reserva.getApellido() + "," + isbn_sin_comas + "," + libro_sin_comas + "," + transaccion;
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
        }catch (Exception exception){
            StdOut.println("[!] Ha ocurrido un error [!] " + exception.getMessage());
        }
    }
}