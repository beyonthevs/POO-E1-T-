import java.util.Scanner;

public class Ejercicio2 {
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
