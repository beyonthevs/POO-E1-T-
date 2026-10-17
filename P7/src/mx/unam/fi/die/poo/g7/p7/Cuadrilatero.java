package mx.unam.fi.die.poo.g7.p7;

/**
 * Clase base que modela un cuadrilátero a partir de cuatro puntos coordenados.
 * Facultad de Ingeniería, UNAM - Programación Orientada a Objetos
 * Práctica 7 - Grupo 7
 */
public class Cuadrilatero {
    private Punto p1;
    private Punto p2;
    private Punto p3;
    private Punto p4;

    public Cuadrilatero(Punto p1, Punto p2, Punto p3, Punto p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public Punto getP1() {
        return p1;
    }

    public Punto getP2() {
        return p2;
    }

    public Punto getP3() {
        return p3;
    }

    public Punto getP4() {
        return p4;
    }
}
