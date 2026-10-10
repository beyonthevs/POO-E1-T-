package mx.unam.fi.die.poo.g7.p6.p5;

import java.util.Scanner;

/**
 * Prueba que la clase Empleado funcione bien creando algunos trabajadores.
 */
public class MainEmpleado {
    /**
     * Método principal que arranca el programa.
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("REGISTRO DEL EMPLEADO 1");
        System.out.print("Ingrese el nombre: ");
        String nombre1 = scanner.nextLine();
        
        System.out.print("Ingrese el apellido: ");
        String apellido1 = scanner.nextLine();
        
        double salario1 = -1;
        while (salario1 < 0) {
            System.out.print("Ingrese el salario mensual (debe ser mayor o igual a 0): ");
            salario1 = scanner.nextDouble();
            if (salario1 < 0) {
                System.out.println("Error: El salario no puede ser negativo. Intente nuevamente.");
            }
        }
        scanner.nextLine(); // Limpiar el buffer de entrada

        // Creación del primer objeto Empleado
        Empleado emp1 = new Empleado(nombre1, apellido1, salario1);

        System.out.println("\nREGISTRO DEL EMPLEADO 2");
        System.out.print("Ingrese el nombre: ");
        String nombre2 = scanner.nextLine();
        
        System.out.print("Ingrese el apellido: ");
        String apellido2 = scanner.nextLine();
        
        double salario2 = -1;
        while (salario2 < 0) {
            System.out.print("Ingrese el salario mensual (debe ser mayor o igual a 0): ");
            salario2 = scanner.nextDouble();
            if (salario2 < 0) {
                System.out.println("Error: El salario no puede ser negativo. Intente nuevamente.");
            }
        }

        // Creación del segundo objeto Empleado
        Empleado emp2 = new Empleado(nombre2, apellido2, salario2);

        // Mostrar el salario anual inicial
        System.out.println("\nSALARIOS ANUALES INICIALES");
        System.out.println(emp1.getNombre() + " " + emp1.getApellido() + ": " + (emp1.getSalarioMensual() * 12));
        System.out.println(emp2.getNombre() + " " + emp2.getApellido() + ": " + (emp2.getSalarioMensual() * 12));

        // Otorgar un aumento del 10% a cada empleado
        emp1.setSalarioMensual(emp1.getSalarioMensual() * 1.10);
        emp2.setSalarioMensual(emp2.getSalarioMensual() * 1.10);

        // Mostrar nuevamente el salario actual tras el aumento
        System.out.println("\nSALARIOS TRAS AUMENTO DEL 10%");
        System.out.println(emp1.getNombre() + " " + emp1.getApellido() + "\nMensual: " + emp1.getSalarioMensual() + "\nAnual: " + (emp1.getSalarioMensual() * 12)+"\n");
        System.out.println(emp2.getNombre() + " " + emp2.getApellido() + "\nMensual: " + emp2.getSalarioMensual() + "\nAnual: " + (emp2.getSalarioMensual() * 12));

        scanner.close();
    }
}