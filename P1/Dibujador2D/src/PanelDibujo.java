import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public final class PanelDibujo extends JPanel {
    private final List<Figura> figuras = new ArrayList<>();

    private Color colorActual = new Color(180, 0, 180);
    private TipoFigura tipoActual = TipoFigura.RECTANGULO;
    private boolean relleno = false;

    private boolean dibujando = false;
    private int inicioX;
    private int inicioY;
    private int actualX;
    private int actualY;

    public PanelDibujo() {
        setBackground(Color.WHITE);
        setOpaque(true);

        MouseAdapter mouse = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!javax.swing.SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }
                inicioX = actualX = e.getX();
                inicioY = actualY = e.getY();
                dibujando = true;
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!dibujando) {
                    return;
                }
                actualX = e.getX();
                actualY = e.getY();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!dibujando || !javax.swing.SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }

                actualX = e.getX();
                actualY = e.getY();
                Figura nueva = crearFigura(inicioX, inicioY, actualX, actualY);

                if (nueva != null && (inicioX != actualX || inicioY != actualY)) {
                    figuras.add(nueva);
                }

                dibujando = false;
                repaint();
            }
        };

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    private Figura crearFigura(int x1, int y1, int x2, int y2) {
        return switch (tipoActual) {
            case RECTANGULO -> new FiguraRectangulo(x1, y1, x2, y2, colorActual, relleno);
            case OVALO -> new FiguraOvalo(x1, y1, x2, y2, colorActual, relleno);
            case LINEA -> new FiguraLinea(x1, y1, x2, y2, colorActual);
        };
    }

    public void setColorActual(Color color) {
        if (color != null) {
            colorActual = color;
        }
    }

    public Color getColorActual() {
        return colorActual;
    }

    public void setTipoActual(TipoFigura tipo) {
        if (tipo != null) {
            tipoActual = tipo;
        }
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    public void deshacer() {
        if (!figuras.isEmpty()) {
            figuras.remove(figuras.size() - 1);
            repaint();
        }
    }

    public void borrarTodo() {
        figuras.clear();
        dibujando = false;
        repaint();
    }

    public int cantidadFiguras() {
        return figuras.size();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(2f));

            for (Figura figura : figuras) {
                figura.dibujar(g2);
            }

            if (dibujando) {
                Figura vistaPrevia = crearFigura(inicioX, inicioY, actualX, actualY);
                if (vistaPrevia != null) {
                    g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.45f));
                    vistaPrevia.dibujar(g2);
                }
            }
        } finally {
            g2.dispose();
        }
    }
}
