import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

public final class Dibujador2D extends JFrame {
    private final PanelDibujo panelDibujo = new PanelDibujo();
    private final JButton botonColor = new JButton();

    public Dibujador2D() {
        super("Dibujador 2D");
        construirInterfaz();
    }

    private void construirInterfaz() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(850, 600));
        setSize(1050, 700);
        setLocationRelativeTo(null);

        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 7));
        barra.setBorder(BorderFactory.createEmptyBorder(4, 7, 4, 7));

        JLabel etiquetaColor = new JLabel("Color:");
        botonColor.setText("Magenta");
        botonColor.setToolTipText("Elegir color");
        botonColor.setForeground(Color.WHITE);
        botonColor.setOpaque(true);
        botonColor.setBorderPainted(false);
        botonColor.setFocusPainted(false);
        botonColor.setPreferredSize(new Dimension(95, 32));
        actualizarAparienciaBotonColor();

        botonColor.addActionListener(e -> elegirColor());

        JLabel etiquetaFigura = new JLabel("Figura:");
        JComboBox<TipoFigura> comboFiguras = new JComboBox<>(TipoFigura.values());
        comboFiguras.setSelectedItem(TipoFigura.RECTANGULO);
        comboFiguras.setPreferredSize(new Dimension(125, 32));
        comboFiguras.addActionListener(e -> panelDibujo.setTipoActual((TipoFigura) comboFiguras.getSelectedItem()));

        JCheckBox checkRelleno = new JCheckBox("Lleno");
        checkRelleno.setSelected(false);
        checkRelleno.addActionListener(e -> panelDibujo.setRelleno(checkRelleno.isSelected()));

        JButton botonDeshacer = new JButton("Deshacer");
        botonDeshacer.setMargin(new Insets(5, 14, 5, 14));
        botonDeshacer.addActionListener(e -> panelDibujo.deshacer());

        JButton botonBorrar = new JButton("Borrar TODO");
        botonBorrar.setMargin(new Insets(5, 14, 5, 14));
        botonBorrar.addActionListener(e -> panelDibujo.borrarTodo());

        barra.add(etiquetaColor);
        barra.add(botonColor);
        barra.add(etiquetaFigura);
        barra.add(comboFiguras);
        barra.add(checkRelleno);
        barra.add(botonDeshacer);
        barra.add(botonBorrar);

        JPanel marco = new JPanel(new BorderLayout(0, 0));
        marco.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));
        marco.add(panelDibujo, BorderLayout.CENTER);

        JLabel pie = new JLabel("Haz clic y arrastra para dibujar.  |  Figuras: 0", SwingConstants.LEFT);
        pie.setFont(pie.getFont().deriveFont(Font.PLAIN, 12f));
        pie.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        panelDibujo.addPropertyChangeListener("figuras", e -> actualizarPie(pie));

        setLayout(new BorderLayout());
        add(barra, BorderLayout.NORTH);
        add(marco, BorderLayout.CENTER);
        add(pie, BorderLayout.SOUTH);

        // Actualización simple del contador después de cada interacción.
        javax.swing.Timer timer = new javax.swing.Timer(120, e -> actualizarPie(pie));
        timer.start();
    }

    private void actualizarPie(JLabel pie) {
        pie.setText("Haz clic y arrastra para dibujar.  |  Figuras: " + panelDibujo.cantidadFiguras());
    }

    private void elegirColor() {
        Color elegido = JColorChooser.showDialog(this, "Elegir color", panelDibujo.getColorActual());
        if (elegido != null) {
            panelDibujo.setColorActual(elegido);
            actualizarAparienciaBotonColor();
        }
    }

    private void actualizarAparienciaBotonColor() {
        Color color = panelDibujo.getColorActual();
        botonColor.setBackground(color);
        botonColor.setText(nombreColor(color));
    }

    private String nombreColor(Color color) {
        if (color.equals(new Color(180, 0, 180))) {
            return "Magenta";
        }
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dibujador2D().setVisible(true);
        });
    }
}
