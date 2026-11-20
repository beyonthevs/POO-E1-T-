package mx.unam.fi.die.poo.g7.p12y13;

/**
 * Representa un pedido sencillo hecho por un cliente.
 * @author Equipo 1
 * @version 1.0
 */
public class Pedido {

    /** Numero del cliente que hizo el pedido. */
    private int cliente;

    /** Nombre del platillo solicitado. */
    private String platillo;

    /**
     * Crea un pedido con el numero de cliente y el platillo.
     * @param cliente Numero identificador del cliente.
     * @param platillo Platillo que pidio el cliente.
     */
    public Pedido(int cliente, String platillo) {
        this.cliente = cliente;
        this.platillo = platillo;
    }

    /**
     * Regresa el numero del cliente.
     * @return Numero de cliente.
     */
    public int getCliente() {
        return cliente;
    }

    /**
     * Regresa el nombre del platillo.
     * @return Platillo pedido.
     */
    public String getPlatillo() {
        return platillo;
    }
}
