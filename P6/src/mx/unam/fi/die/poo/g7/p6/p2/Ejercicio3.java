package mx.unam.fi.die.poo.g7.p6.p2;

/**
 * Tercer ejercicio, para practicar arreglos y mostrar resultados.
 */
public class Ejercicio3 {
    /**
     * Método principal que arranca el programa.
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {

        int n;

        System.out.println("n\t10*n\t100*n\t1000*n");

        for (n = 1; n <= 5; n++) {
            System.out.println(n + "\t" + 10 * n + "\t" + 100 * n + "\t" + 1000 * n);
        }
    }
}
