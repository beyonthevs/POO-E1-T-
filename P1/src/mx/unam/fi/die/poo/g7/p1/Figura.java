package mx.unam.fi.die.poo.g7.p1;

import java.awt.Graphics2D;

/**
 * Guarda la información base para todas las figuras.
 */
public abstract class Figura {
    protected final java.awt.Color color;
    protected final boolean llena;

    /**
     * Constructor de Figura.
     * @param color Color de la figura.
     * @param llena Indica si la figura va rellena.
     */
    protected Figura(java.awt.Color color, boolean llena) {
        this.color = color;
        this.llena = llena;
    }

    /**
     * Método para dibujar la figura.
     * @param g2 Objeto para dibujar en pantalla.
     */
    public abstract void dibujar(Graphics2D g2);
}
