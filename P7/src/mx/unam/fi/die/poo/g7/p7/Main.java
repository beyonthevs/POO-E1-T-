package mx.unam.fi.die.poo.g7.p7;

/**
 * Clase principal de prueba para la jerarquía de herencia.
 * Práctica 7 - Grupo 7 - División de Ingeniería Eléctrica (DIE)
 * Facultad de Ingeniería, UNAM
 */
public class Main {
    public static void main(String[] args) {
        // Inicializar los vertices del trapezoide.
        Trapezoide trapezoide = new Trapezoide(
            new Punto(0.0, 0.0),
            new Punto(8.0, 0.0),
            new Punto(6.0, 4.0),
            new Punto(2.0, 4.0)
        );

        // Inicializar los vertices del paralelogramo.
        // Paralelogramo: (0,0), (6,0), (8,4), (2,4) -> A = (6 + 6) * 4 / 2 = 24.0
        Paralelogramo paralelogramo = new Paralelogramo(
            new Punto(0.0, 0.0),
            new Punto(6.0, 0.0),
            new Punto(8.0, 4.0),
            new Punto(2.0, 4.0)
        );

        // Inicializar los vertices del rectángulo.
        Rectangulo rectangulo = new Rectangulo(
            new Punto(0.0, 0.0),
            new Punto(6.0, 0.0),
            new Punto(6.0, 4.0),
            new Punto(0.0, 4.0)
        );

        // Inicializar los vertices del cuadrado.
        Cuadrado cuadrado = new Cuadrado(
            new Punto(0.0, 0.0),
            new Punto(5.0, 0.0),
            new Punto(5.0, 5.0),
            new Punto(0.0, 5.0)
        );

        System.out.println("Area Trapezoide: " + trapezoide.calcularArea());
        System.out.println("Area Paralelogramo: " + paralelogramo.calcularArea());
        System.out.println("Area Rectangulo: " + rectangulo.calcularArea());
        System.out.println("Area Cuadrado: " + cuadrado.calcularArea());
    }
}
