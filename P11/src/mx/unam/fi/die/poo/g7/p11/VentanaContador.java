package mx.unam.fi.die.poo.g7.p11;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Ventana grafica que muestra el contenido de ejemplo.txt y su total de palabras.
 * @author Equipo 1
 * @version 1.0
 */
public class VentanaContador extends JFrame {

    private LectorArchivo lector = new LectorArchivo();
    private JTextArea areaTexto = new JTextArea();
    private JLabel etiquetaPalabras = new JLabel("Total de palabras: 0");

    /**
     * Crea la ventana y carga el archivo ejemplo.txt.
     */
    public VentanaContador() {
        super("Contador de palabras (.txt)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLayout(new BorderLayout());

        areaTexto.setEditable(false);
        add(areaTexto, BorderLayout.CENTER);
        add(etiquetaPalabras, BorderLayout.SOUTH);

        try {
            lector.leer(new File("P11/ejemplo.txt"));
            areaTexto.setText(lector.getContenido());
            etiquetaPalabras.setText("Total de palabras: " + lector.getTotalPalabras());
        } catch (Exception ex) {
            areaTexto.setText("Error: " + ex.getMessage());
        }
    }
}
