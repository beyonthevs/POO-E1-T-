import java.awt.Graphics2D;

/** Base para cualquier figura 2D del programa. */
public abstract class Figura {
    protected final java.awt.Color color;
    protected final boolean llena;

    protected Figura(java.awt.Color color, boolean llena) {
        this.color = color;
        this.llena = llena;
    }

    public abstract void dibujar(Graphics2D g2);
}
