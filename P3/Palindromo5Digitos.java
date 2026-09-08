import java.util.Scanner;

public class Palindromo5Digitos {

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

    public static boolean esPalindromo(int numero) {
        return (numero / 10000) == (numero % 10)
                && ((numero / 1000) % 10) == ((numero / 10) % 10);
    }
}
