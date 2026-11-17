package mx.unam.fi.die.poo.g7.p11;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Ventana grafica que muestra el contenido de ejemplo.txt y su total de palabras.
 * @author Equipo 1
 * @version 1.0
 */
public class VentanaContador extends JFrame {

    /**
     * Crea la ventana y carga el archivo P11/ejemplo.txt.
     */
    public VentanaContador() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 350);

        JTextArea areaTexto = new JTextArea();
        add(areaTexto);

        LectorArchivo lector = new LectorArchivo();
        try {
            lector.leer(new File("P11/ejemplo.txt"));
            areaTexto.setText(lector.getContenido() + "\nTotal de palabras: " + lector.getTotalPalabras());
        } catch (Exception ex) {
            areaTexto.setText("Error: " + ex.getMessage());
        }
    }
}
