import java.util.Scanner;

public class Ejercicio1 {
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
