package mx.unam.fi.die.poo.g7.p11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lee archivos .txt y cuenta cuantas palabras tienen.
 * @author Equipo 1
 * @version 1.0
 */
public class LectorArchivo {

    private String contenido = "";
    private int totalPalabras = 0;

    /**
     * Lee el archivo indicado y cuenta sus palabras.
     * @param archivo Archivo .txt que se va a leer.
     * @throws IOException Si el archivo no es .txt o no se puede leer.
     */
    public void leer(File archivo) throws IOException {
        if (!archivo.getName().endsWith(".txt")) {
            throw new IOException("El archivo debe tener extension .txt.");
        }

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String texto = "";
        int contador = 0;
        String linea;

        while ((linea = br.readLine()) != null) {
            texto += linea + "\n";
            if (!linea.trim().isEmpty()) {
                String[] palabras = linea.trim().split(" ");
                contador += palabras.length;
            }
        }
        br.close();

        this.contenido = texto;
        this.totalPalabras = contador;
    }

    /**
     * Regresa el texto leido del archivo.
     * @return Contenido del archivo.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Regresa el numero de palabras contadas.
     * @return Total de palabras.
     */
    public int getTotalPalabras() {
        return totalPalabras;
    }
}
