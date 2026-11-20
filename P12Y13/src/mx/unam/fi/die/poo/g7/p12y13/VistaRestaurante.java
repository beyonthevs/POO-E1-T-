package mx.unam.fi.die.poo.g7.p12y13;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Ventana grafica sencilla que muestra lo que pasa en el restaurante.
 * @author Equipo 1
 * @version 1.0
 */
public class VistaRestaurante extends JFrame {

    /** Area de texto para mostrar los mensajes. */
    private JTextArea areaTexto;

    /**
     * Crea la ventana y la deja visible.
     */
    public VistaRestaurante() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 480);

        areaTexto = new JTextArea();
        areaTexto.setFont(areaTexto.getFont().deriveFont(15f));
        add(areaTexto);
        setVisible(true);
    }

    /**
     * Muestra un mensaje en consola y en la ventana.
     * @param msg Texto que se va a mostrar.
     */
    public synchronized void mostrarMensaje(String msg) {
        System.out.println(msg);
        areaTexto.append(msg + "\n");
    }
}
