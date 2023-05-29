package BookExample;

import pcd.util.Ventana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ClientBookAlumnos {
    final static int puerto = 10_000;
    static Ventana v = null;

    static public void enviarLibro(Book b) {
        String respuesta = null;
        try {
            Socket s = new Socket("localhost", puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream());
            try {
                salida.writeObject(b);
                salida.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            respuesta = entrada.readLine();

            v.addText("Libro: " + b.getTitulo() + " -- en promocion? " + respuesta);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        v = new Ventana("Cliente", 500, 200);

        List<Book> books = Arrays.asList(
                new Book("La Catedral del Mar", false, 20, Book.Lenguaje.ESPANOL),
                new Book("La ciudad de los prodigios", false, 25, Book.Lenguaje.INGLES),
                new Book("Luces de Bohemia", false, 22, Book.Lenguaje.INGLES),
                new Book("Crimen y Castigo", false, 28, Book.Lenguaje.ESPANOL),
                new Book("El Buscon", false, 15, Book.Lenguaje.ESPANOL),
                new Book("Don Quijote de la Mancha", false, 60, Book.Lenguaje.OTRO),
                new Book("Sistemas Operativos", true, 35, Book.Lenguaje.OTRO),
                new Book("Programacion Concurrente", true, 30, Book.Lenguaje.ESPANOL),
                new Book("Aprendizaje Automatico", true, 45, Book.Lenguaje.ESPANOL)
        );

        for (Book b : books) {
            enviarLibro(b);
        }

    }
}