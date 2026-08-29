import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int opcion;
        double num1, num2, resultado;

        do {
            System.out.println("\n===== CALCULADORA =====");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Producto");
            System.out.println("4. Cociente");
            System.out.println("5. Potencia");
            System.out.println("6. Raíz");
            System.out.println("7. Módulo");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Primer número: ");
                    num1 = teclado.nextDouble();

                    System.out.print("Segundo número: ");
                    num2 = teclado.nextDouble();

                    resultado = num1 + num2;

                    System.out.println("El resultado de "
                            + num1 + " + " + num2 + " = " + resultado);
                    break;
                case 2:
                    System.out.print("Primer número: ");
                    num1 = teclado.nextDouble();

                    System.out.print("Segundo número: ");
                    num2 = teclado.nextDouble();

                    resultado = num1 - num2;

                    System.out.println("El resultado de "
                            + num1 + " - " + num2 + " = " + resultado);
                    break;
                case 3:
                    System.out.print("Primer número: ");
                    num1 = teclado.nextDouble();

                    System.out.print("Segundo número: ");
                    num2 = teclado.nextDouble();

                    resultado = num1 * num2;

                    System.out.println("El resultado de "
                            + num1 + " x " + num2 + " = " + resultado);
                    break;
                case 4:
                    System.out.print("Dividendo: ");
                    num1 = teclado.nextDouble();

                    System.out.print("Divisor: ");
                    num2 = teclado.nextDouble();

                    if (num2 == 0) {
                        System.out.println("No se puede dividir entre cero.");
                    } else {
                        resultado = num1 / num2;

                        System.out.println("El resultado de "
                                + num1 + " / " + num2 + " = " + resultado);
                    }
                    break;
                case 5:
                    System.out.print("Base: ");
                    num1 = teclado.nextDouble();

                    System.out.print("Exponente: ");
                    num2 = teclado.nextDouble();

                    resultado = Math.pow(num1, num2);

                    System.out.println("El resultado de "
                            + num1 + " ^ " + num2 + " = " + resultado);
                    break;
                case 6:
                    System.out.print("Número: ");
                    num1 = teclado.nextDouble();

                    if (num1 < 0) {
                        System.out.println(
                                "No se puede calcular la raíz real de un número negativo.");
                    } else {
                        resultado = Math.sqrt(num1);

                        System.out.println("El resultado de "
                                + "√" + num1 + " = " + resultado);
                    }
                    break;
                case 7:
                    System.out.print("Primer número: ");
                    num1 = teclado.nextDouble();

                    System.out.print("Segundo número: ");
                    num2 = teclado.nextDouble();

                    if (num2 == 0) {
                        System.out.println(
                                "No se puede calcular módulo con cero.");
                    } else {
                        resultado = num1 % num2;

                        System.out.println("El resultado de "
                                + num1 + " % " + num2 + " = " + resultado);
                    }
                    break;

                case 8:
                    System.out.println("Calculadora cerrada.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 8);
        teclado.close();
    }
}