package mx.unam.fi.die.poo.g7.p12y13;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Cola compartida donde los clientes dejan pedidos y los meseros los toman.
 * @author Equipo 1
 * @version 1.0
 */
public class ColaPedidos {

    /** Cola interna que guarda los pedidos en orden de llegada. */
    private Queue<Pedido> pedidos;

    /** Indica si la cola sigue recibiendo pedidos. */
    private boolean abierta;

    /**
     * Inicializa la cola vacia y abierta.
     */
    public ColaPedidos() {
        this.pedidos = new LinkedList<>();
        this.abierta = true;
    }

    /**
     * Agrega un pedido a la cola y avisa a los hilos que esperan.
     * @param pedido Pedido nuevo del cliente.
     */
    public synchronized void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        notifyAll();
    }

    /**
     * Toma el siguiente pedido de la cola. Si esta vacia, espera hasta que llegue uno
     * o hasta que se cierre la cola.
     * @return El siguiente pedido, o null si la cola se cerro y ya no hay pedidos.
     */
    public synchronized Pedido tomarPedido() {
        while (pedidos.isEmpty() && abierta) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        if (pedidos.isEmpty()) {
            return null;
        }
        Pedido pedido = pedidos.poll();
        notifyAll();
        return pedido;
    }

    /**
     * Marca la cola como cerrada y despierta a los meseros que sigan esperando.
     */
    public synchronized void cerrar() {
        this.abierta = false;
        notifyAll();
    }
}
