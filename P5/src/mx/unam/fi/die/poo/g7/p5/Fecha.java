package mx.unam.fi.die.poo.g7.p5;

/**
 * Clase Fecha para la practica p5.
 * Sirve para resolver el problema asignado.
 */
public class Fecha {
    private int mes;
    private int dia;
    private int anio;

    // Constructor basado en los setters para garantizar validaciones
    /**
     * Constructor de Fecha.
     */
    public Fecha(int mes, int dia, int anio) {
        setMes(mes);
        setDia(dia);
        setAnio(anio);
    }

    // Métodos modificadores (Setters)
    /**
     * Metodo para setMes.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Metodo para setDia.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Metodo para setAnio.
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    // Métodos de acceso (Getters)
    /**
     * Metodo para getMes.
     */
    public int getMes() {
        return mes;
    }

    /**
     * Metodo para getDia.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Metodo para getAnio.
     */
    public int getAnio() {
        return anio;
    }

    // Método para mostrar la fecha consultando a través de los getters
    /**
     * Metodo para mostrarFecha.
     */
    public void mostrarFecha() {
        System.out.println(getMes() + "/" + getDia() + "/" + getAnio());
    }
}