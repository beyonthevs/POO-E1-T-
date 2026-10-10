package mx.unam.fi.die.poo.g7.p5;

import java.util.Scanner;

/**
 * Programa para probar que la fecha se guarde y muestre correctamente.
 */
public class MainFecha {
    /**
     * Método principal que arranca el programa.
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("REGISTRO DE FECHA");

        // Solicitud de datos al usuario
        System.out.print("Ingrese el mes (1-12): ");
        int mes = scanner.nextInt();

        System.out.print("Ingrese el día (1-31): ");
        int dia = scanner.nextInt();

        System.out.print("Ingrese el año: ");
        int anio = scanner.nextInt();

        // Creación del objeto Fecha con los valores ingresados
        Fecha miFecha = new Fecha(mes, dia, anio);

        // Mostrar la fecha formateada
        System.out.print("\nLa fecha ingresada es: ");
        miFecha.mostrarFecha();

        scanner.close();
    }
}