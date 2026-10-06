public class Fecha {
    private int mes;
    private int dia;
    private int anio;

    // Constructor basado en los setters para garantizar validaciones
    public Fecha(int mes, int dia, int anio) {
        setMes(mes);
        setDia(dia);
        setAnio(anio);
    }

    // Métodos modificadores (Setters)
    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    // Métodos de acceso (Getters)
    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }

    public int getAnio() {
        return anio;
    }

    // Método para mostrar la fecha consultando a través de los getters
    public void mostrarFecha() {
        System.out.println(getMes() + "/" + getDia() + "/" + getAnio());
    }
}