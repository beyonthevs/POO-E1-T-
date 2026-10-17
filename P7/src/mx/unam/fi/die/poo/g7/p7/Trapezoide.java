package mx.unam.fi.die.poo.g7.p7;

/**
 * Subclase de Cuadrilatero.
 * Calcula el área con la fórmula: ((baseMayor + baseMenor) * altura) / 2.0
 * Facultad de Ingeniería, UNAM - Programación Orientada a Objetos
 * Práctica 7 - Grupo 7
 */
public class Trapezoide extends Cuadrilatero {

    public Trapezoide(Punto p1, Punto p2, Punto p3, Punto p4) {
        super(p1, p2, p3, p4);
    }

    public double calcularArea() {
        double baseMayor = Math.abs(getP2().getX() - getP1().getX());
        double baseMenor = Math.abs(getP3().getX() - getP4().getX());
        double altura = Math.abs(getP4().getY() - getP1().getY());
        return ((baseMayor + baseMenor) * altura) / 2.0;
    }
}
