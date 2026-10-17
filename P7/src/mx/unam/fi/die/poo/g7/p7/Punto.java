package mx.unam.fi.die.poo.g7.p7;

/**
 * Representa un punto en el plano cartesiano con coordenadas (x, y).
 * Facultad de Ingeniería, UNAM - Programación Orientada a Objetos
 * Práctica 7 - Grupo 7
 */
public class Punto {
    private double x;
    private double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
