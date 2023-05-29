package pcd.util;

public class Traza {
    static int nivel = 0;

    public Traza(int _nivel) {
        nivel = _nivel;
    }

    public Traza() {

    }

    public static void setNivel(int _nivel) {
        nivel = _nivel;
    }

    public static void traza(String _color, int _nivel, String _s) {
        String aImprimir = _color + _s + ColoresConsola.RESET;
        traza(_nivel, aImprimir);
    }

    public static void traza(int _nivel, String s) {
        if (_nivel <= nivel) {
            System.out.println(s);
        }
    }
}