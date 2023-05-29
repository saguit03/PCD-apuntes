package pcd.util;

public class MainPrueba {

    public static void main(String[] args) {
        // Esta clase muestra algunos ejemplos de uso de la clase
        Traza.setNivel(2); // Establecemos el nivel de traza en 2. Mensajes con n�mero de nivel >2 no se ver�n en pantalla.
        Traza.traza(ColoresConsola.RED, 1, "Este mensaje es de nivel 1. Se ve en rojo. ");
        Traza.traza(ColoresConsola.RED, 3, "Este mensaje no se vera.");
        Traza.traza(ColoresConsola.GREEN_BACKGROUND, 2, "Este mensaje es de nivel 2. Se ve en con fondo verde.");
        Traza.traza(1, "Este mensaje es de nivel 1. Se ve normal.");
        Traza.traza(ColoresConsola.BLACK_UNDERLINED, 1, "Este mensaje se ve subrayado.");

        Ventana v = new Ventana("Titulo de la ventana", 100, 200);
        v.addText("Este texto se ve dentro de una ventana situada en las coordenadas 100,200");
    }
}
