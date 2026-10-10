package mx.unam.fi.die.poo.g7.p2;

import java.util.Scanner;

/**
 * Segundo ejercicio, identifica al mayor de 10 enteros.
 */
public class Ejercicio2 {
    /**
     * Método principal que arranca el programa.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int contador = 0, numero, mayor = 0;

        while (contador < 10) {
            System.out.print("Ingresa un número entero positivo: ");
            numero = Math.abs(entrada.nextInt());

            if (numero > 0) {
                if (numero > mayor) {
                    mayor = numero;
                }
                contador++;
            }
        }

        System.out.println("El número mayor es: " + mayor);
        entrada.close();
    }
}
