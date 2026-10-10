package mx.unam.fi.die.poo.g7.p3;

import java.util.Scanner;

/**
 * Clase Palindromo5Digitos para la practica p3.
 * Sirve para resolver el problema asignado.
 */
public class Palindromo5Digitos {

    /**
     * Metodo principal que arranca el programa.
     * @param args argumentos de linea de comandos
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
     * Metodo para esPalindromo.
     */
    public static boolean esPalindromo(int numero) {
        return (numero / 10000) == (numero % 10)
                && ((numero / 1000) % 10) == ((numero / 10) % 10);
    }
}
