package Concurrente;

import java.util.concurrent.CyclicBarrier;

public class Principal {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3);
        Letra a = new Letra('A', cb);
        Letra b = new Letra('B', cb);
        Letra c = new Letra('C', cb);
        a.start();
        b.start();
        c.start();
    }
}
