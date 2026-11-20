package mx.unam.fi.die.poo.g7.p12y13;

import java.util.Random;

/**
 * Hilo consumidor que representa a un mesero tomando pedidos y llevandolos de cocina.
 * @author Equipo 1
 * @version 1.0
 */
public class Mesero implements Runnable {

    /** Nombre del mesero. */
    private String nombre;

    /** Cola compartida de donde toma los pedidos. */
    private ColaPedidos cola;

    /** Controlador para reportar los eventos hacia la vista. */
    private ControladorRestaurante controlador;

    /** Generador de tiempos aleatorios para simular la preparacion en cocina. */
    private Random random;

    /**
     * Crea un mesero con su nombre y acceso a la cola y al controlador.
     * @param nombre Nombre del mesero.
     * @param cola Cola compartida de pedidos.
     * @param controlador Controlador del restaurante.
     */
    public Mesero(String nombre, ColaPedidos cola, ControladorRestaurante controlador) {
        this.nombre = nombre;
        this.cola = cola;
        this.controlador = controlador;
        this.random = new Random();
    }

    /**
     * Toma pedidos de la cola mientras haya disponibles y simula su preparacion y entrega.
     */
    @Override
    public void run() {
        Pedido pedido;
        while ((pedido = cola.tomarPedido()) != null) {
            controlador.notificar(nombre + " tomo el pedido de Cliente " + pedido.getCliente()
                    + " (" + pedido.getPlatillo() + ")");
            try {
                int tiempoAtencion = 900 + random.nextInt(701);
                Thread.sleep(tiempoAtencion);

                controlador.notificar(nombre + ": preparando en cocina " + pedido.getPlatillo()
                        + " para Cliente " + pedido.getCliente() + "...");
                int tiempoPreparacion = 3500 + random.nextInt(2501);
                Thread.sleep(tiempoPreparacion);
                controlador.notificar(nombre + " entrego " + pedido.getPlatillo()
                        + " a Cliente " + pedido.getCliente());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
