package mx.unam.fi.die.poo.g7.p1;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Guarda la información de una figura de tipo óvalo.
 */
public final class FiguraOvalo extends Figura {
    private final int x;
    private final int y;
    private final int ancho;
    private final int alto;

    /**
     * Constructor de FiguraOvalo.
     * @param x1 Coordenada x inicial.
     * @param y1 Coordenada y inicial.
     * @param x2 Coordenada x final.
     * @param y2 Coordenada y final.
     * @param color Color del óvalo.
     * @param llena Indica si el óvalo va relleno.
     */
    public FiguraOvalo(int x1, int y1, int x2, int y2, Color color, boolean llena) {
        super(color, llena);
        this.x = Math.min(x1, x2);
        this.y = Math.min(y1, y2);
        this.ancho = Math.abs(x2 - x1);
        this.alto = Math.abs(y2 - y1);
    }

    @Override
    public void dibujar(Graphics2D g2) {
        g2.setColor(color);
        if (llena) {
            g2.fillOval(x, y, ancho, alto);
        } else {
            g2.drawOval(x, y, ancho, alto);
        }
    }
}
