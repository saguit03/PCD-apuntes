package BookExample;

import java.io.Serializable;
import java.util.Comparator;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private boolean digital;
    private int precio;
    private Lenguaje lenguaje;

    public Book(String titulo, boolean digital, int precio, Lenguaje lenguaje) {
        this.titulo = titulo;
        this.digital = digital;
        this.precio = precio;
        this.lenguaje = lenguaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDigital() {
        return digital;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int _precio) {
        precio = _precio;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public int compare(Book b1, Comparator<Book> c) {
        return c.compare(this, b1);
    }

    @Override
    public String toString() {
        return titulo;
    }

    public enum Lenguaje {ESPANOL, INGLES, OTRO}

} //CLASS