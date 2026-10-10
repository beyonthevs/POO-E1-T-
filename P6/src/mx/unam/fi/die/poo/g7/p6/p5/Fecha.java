package mx.unam.fi.die.poo.g7.p6.p5;

/**
 * Guarda el día, mes y año, y nos deja imprimir la fecha bien acomodada.
 */
public class Fecha {
    private int mes;
    private int dia;
    private int anio;

    // Constructor basado en los setters para garantizar validaciones
    /**
     * Constructor de Fecha.
     * @param mes Mes de la fecha.
     * @param dia Día de la fecha.
     * @param anio Año de la fecha.
     */
    public Fecha(int mes, int dia, int anio) {
        setMes(mes);
        setDia(dia);
        setAnio(anio);
    }

    // Métodos modificadores (Setters)
    /**
     * Método para setMes.
     * @param mes Mes de la fecha.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Método para setDia.
     * @param dia Día de la fecha.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Método para setAnio.
     * @param anio Año de la fecha.
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    // Métodos de acceso (Getters)
    /**
     * Método para getMes.
     * @return El valor de la propiedad mes.
     */
    public int getMes() {
        return mes;
    }

    /**
     * Método para getDia.
     * @return El valor de la propiedad día.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Método para getAnio.
     * @return El valor de la propiedad anio.
     */
    public int getAnio() {
        return anio;
    }

    // Método para mostrar la fecha consultando a través de los getters
    /**
     * Método para mostrarFecha.
     */
    public void mostrarFecha() {
        System.out.println(getMes() + "/" + getDia() + "/" + getAnio());
    }
}