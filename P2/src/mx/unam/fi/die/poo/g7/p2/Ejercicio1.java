package mx.unam.fi.die.poo.g7.p2;

import java.util.Scanner;

/**
 * Primer ejercicio de la práctica, simula un bono al docente si al menos 9 alumnos aprueban.
 */
public class Ejercicio1 {
    /**
     * Método principal que arranca el programa.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int aprobados = 0;
        int reprobados = 0;

        for (int i = 1; i <= 10; i++) {
            int resultado;

            do {
                System.out.print("Ingresa el resultado (1 = aprobó, 2 = reprobó): ");
                resultado = entrada.nextInt();

                if (resultado != 1 && resultado != 2) {
                    System.out.println("Ingresa únicamente 1 o 2.");
                }
            } while (resultado != 1 && resultado != 2);

            if (resultado == 1) {
                aprobados++;
            } else {
                reprobados++;
            }
        }

        System.out.println("Aprobados: " + aprobados);
        System.out.println("Reprobados: " + reprobados);

        if (aprobados >= 9) {
            System.out.println("Se bonifica al docente!");
        }

        entrada.close();
    }
}
