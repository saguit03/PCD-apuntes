package Concurrente;

import java.util.concurrent.CyclicBarrier;

public class Letra extends Thread {
    static Object o = new Object();
    static char anterior = 'C';
    CyclicBarrier cb;
    char letra;
    public Letra(char _letra, CyclicBarrier cb) {
        letra = _letra;
        this.cb = cb;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (o) {
                // Para asegurar la secuencia ABC
                while ((letra=='A' && anterior!='C') ||(letra=='B' && anterior!='A') ||(letra=='C' && anterior!='B'))
                    try {
                        o.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                System.out.println(letra);
                anterior = letra;
                o.notifyAll();
            }
        }
        try {
            // Para que no se imprima fin hasta que todos hayan llegado
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fin");
    }
}
