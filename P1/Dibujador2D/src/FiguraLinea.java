import java.awt.Color;
import java.awt.Graphics2D;

public final class FiguraLinea extends Figura {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

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
