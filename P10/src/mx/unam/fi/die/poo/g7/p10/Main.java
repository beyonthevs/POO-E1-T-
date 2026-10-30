package mx.unam.fi.die.poo.g7.p10;

/**
 * Clase principal para probar la ejecucion y captura.
 * @author Equipo 1
 * @version 1.0
 */
public class Main {

    /**
     * Metodo principal del programa.
     * @param args Argumentos de linea de comandos.
     */
    public static void main(String[] args) {
        ServicioBase servicio = new ProcesadorPago();
        servicio.ejecutar();
    }
}

