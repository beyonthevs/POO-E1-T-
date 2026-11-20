package mx.unam.fi.die.poo.g7.p12y13;

/**
 * Controlador que conecta la cola de pedidos, los hilos y la vista.
 * @author Equipo 1
 * @version 1.0
 */
public class ControladorRestaurante {

    /** Cola compartida entre clientes y meseros. */
    private ColaPedidos cola;

    /** Vista usada para imprimir los mensajes en consola. */
    private VistaRestaurante vista;

    /**
     * Crea el controlador con la cola de pedidos y la vista.
     * @param cola Cola compartida de pedidos.
     * @param vista Vista de consola del restaurante.
     */
    public ControladorRestaurante(ColaPedidos cola, VistaRestaurante vista) {
        this.cola = cola;
        this.vista = vista;
    }

    /**
     * Envia un mensaje de estado hacia la vista.
     * @param mensaje Texto del evento ocurrido.
     */
    public void notificar(String mensaje) {
        vista.mostrarMensaje(mensaje);
    }

    /**
     * Crea los hilos de clientes y meseros, los inicia y espera a que terminen.
     */
    public void iniciar() {
        vista.mostrarMensaje("=== Inicio de simulacion del restaurante ===");

        int totalClientes = 5;
        Thread[] clientes = new Thread[totalClientes];
        for (int i = 0; i < totalClientes; i++) {
            clientes[i] = new Thread(new Cliente(i + 1, cola, this));
        }

        Thread mesero1 = new Thread(new Mesero("Mesero 1", cola, this));
        Thread mesero2 = new Thread(new Mesero("Mesero 2", cola, this));

        mesero1.start();
        mesero2.start();
        for (Thread c : clientes) {
            c.start();
        }

        try {
            for (Thread c : clientes) {
                c.join();
            }
            cola.cerrar();
            mesero1.join();
            mesero2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        vista.mostrarMensaje("=== Todos los pedidos fueron entregados ===");
    }
}
