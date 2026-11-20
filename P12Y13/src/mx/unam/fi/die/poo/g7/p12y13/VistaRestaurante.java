package mx.unam.fi.die.poo.g7.p12y13;

/**
 * Vista de consola encargada de imprimir los mensajes del restaurante.
 * @author Equipo 1
 * @version 1.0
 */
public class VistaRestaurante {

    /**
     * Constructor por defecto de la vista.
     */
    public VistaRestaurante() {
    }

    /**
     * Imprime un mensaje de estado en consola de forma sincronizada.
     * @param msg Texto que se quiere mostrar.
     */
    public synchronized void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}
