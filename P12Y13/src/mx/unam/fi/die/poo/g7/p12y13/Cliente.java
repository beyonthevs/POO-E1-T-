package mx.unam.fi.die.poo.g7.p12y13;

import java.util.Random;

/**
 * Hilo productor que representa a un cliente haciendo un pedido.
 * @author Equipo 1
 * @version 1.0
 */
public class Cliente implements Runnable {

    /** Numero identificador del cliente. */
    private int id;

    /** Cola compartida donde se dejan los pedidos. */
    private ColaPedidos cola;

    /** Controlador para reportar los eventos hacia la vista. */
    private ControladorRestaurante controlador;

    /** Generador de tiempos aleatorios para simular llegadas y esperas. */
    private Random random;

    /**
     * Crea un cliente con su id y las referencias para pedir y avisar.
     * @param id Numero del cliente.
     * @param cola Cola compartida de pedidos.
     * @param controlador Controlador del restaurante.
     */
    public Cliente(int id, ColaPedidos cola, ControladorRestaurante controlador) {
        this.id = id;
        this.cola = cola;
        this.controlador = controlador;
        this.random = new Random();
    }

    /**
     * Genera un pedido, lo agrega a la cola y simula un tiempo de espera.
     */
    @Override
    public void run() {
        String[] menu = {"Tacos", "Enchiladas", "Sopa", "Hamburguesa", "Pasta"};
        String platillo = menu[(id - 1) % menu.length];

        try {
            int tiempoLlegada = 1200 + random.nextInt(2001);
            Thread.sleep(tiempoLlegada);

            Pedido pedido = new Pedido(id, platillo);
            cola.agregarPedido(pedido);
            controlador.notificar("Cliente " + id + " pidio: " + platillo);

            int tiempoEspera = 1500 + random.nextInt(1001);
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
