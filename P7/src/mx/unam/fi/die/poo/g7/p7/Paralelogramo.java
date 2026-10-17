package mx.unam.fi.die.poo.g7.p7;

/**
 * Subclase de Trapezoide donde los lados opuestos son paralelos e iguales.
 * Reutiliza calcularArea() heredado de Trapezoide por polimorfismo/herencia.
 * Facultad de Ingeniería, UNAM - Programación Orientada a Objetos
 * Práctica 7 - Grupo 7
 */
public class Paralelogramo extends Trapezoide {

    public Paralelogramo(Punto p1, Punto p2, Punto p3, Punto p4) {
        super(p1, p2, p3, p4);
    }
}
