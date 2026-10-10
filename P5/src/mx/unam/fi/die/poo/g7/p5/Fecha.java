package mx.unam.fi.die.poo.g7.p5;

/**
 * Guarda el día, mes y año, y nos deja imprimir la fecha con formato.
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
     * Método para designar el mes.
     * @param mes Mes de la fecha.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Método para designar el día.
     * @param dia Día de la fecha.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Método para designar el año.
     * @param anio Año de la fecha.
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    // Métodos de acceso (Getters)
    /**
     * Método para obtener el mes.
     * @return El valor de la propiedad mes.
     */
    public int getMes() {
        return mes;
    }

    /**
     * Método para obtener el día.
     * @return El valor de la propiedad día.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Método para obtener el año.
     * @return El valor de la propiedad anio.
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Método para mostrar la fecha a través de los getters.
     */
    public void mostrarFecha() {
        System.out.println(getMes() + "/" + getDia() + "/" + getAnio());
    }
}