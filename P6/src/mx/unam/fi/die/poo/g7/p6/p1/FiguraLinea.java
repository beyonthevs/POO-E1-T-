package mx.unam.fi.die.poo.g7.p6.p1;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Guarda la información de una figura de tipo línea.
 */
public final class FiguraLinea extends Figura {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    /**
     * Constructor de FiguraLinea.
     * @param x1 Coordenada x inicial.
     * @param y1 Coordenada y inicial.
     * @param x2 Coordenada x final.
     * @param y2 Coordenada y final.
     * @param color Color de la línea.
     */
    public FiguraLinea(int x1, int y1, int x2, int y2, Color color) {
        super(color, false);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void dibujar(Graphics2D g2) {
        g2.setColor(color);
        g2.drawLine(x1, y1, x2, y2);
    }
}
