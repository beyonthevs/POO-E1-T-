package mx.unam.fi.die.poo.g7.p1;

import java.awt.Graphics2D;

/**
 * Programa para resolver el ejercicio de la clase.
 */
public abstract class Figura {
    protected final java.awt.Color color;
    protected final boolean llena;

    protected Figura(java.awt.Color color, boolean llena) {
        this.color = color;
        this.llena = llena;
    }

    public abstract void dibujar(Graphics2D g2);
}
