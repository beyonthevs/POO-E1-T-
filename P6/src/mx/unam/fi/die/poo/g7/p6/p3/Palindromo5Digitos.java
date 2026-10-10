package mx.unam.fi.die.poo.g7.p6.p3;

import java.util.Scanner;

/**
 * Programa que checa si un número de 5 dígitos se lee igual al derecho y al revés.
 */
public class Palindromo5Digitos {

    /**
     * Método principal que arranca el programa.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero;

        do {
            System.out.print("Dime un número de 5 dígitos: ");
            numero = Math.abs(sc.nextInt());

            if (numero < 10000 || numero > 99999) {
                System.out.println("Error: el número debe tener 5 dígitos");
            }

        } while (numero < 10000 || numero > 99999);

        if (esPalindromo(numero)) {
            System.out.println("Es un palíndromo");
        } else {
            System.out.println("No es un palíndromo");
        }

        sc.close();
    }

    /**
     * Método para esPalindromo.
     * @param numero Número a evaluar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean esPalindromo(int numero) {
        return (numero / 10000) == (numero % 10)
                && ((numero / 1000) % 10) == ((numero / 10) % 10);
    }
}
