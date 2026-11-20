package mx.unam.fi.die.poo.g7.p12y13;

/**
 * Clase principal que arranca la simulacion del restaurante.
 * @author Equipo 1
 * @version 1.0
 */
public class Restaurante {

    /**
     * Constructor por defecto.
     */
    public Restaurante() {
    }

    /**
     * Punto de entrada del programa.
     * @param args Argumentos de linea de comandos.
     */
    public static void main(String[] args) {
        ColaPedidos cola = new ColaPedidos();
        VistaRestaurante vista = new VistaRestaurante();
        ControladorRestaurante controlador = new ControladorRestaurante(cola, vista);
        controlador.iniciar();
    }
}
