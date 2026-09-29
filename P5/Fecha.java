public class Fecha {
    private int mes;
    private int dia;
    private int anio;

    // Constructor
    public Fecha(int mes, int dia, int anio) {
        this.mes = mes;
        this.dia = dia;
        this.anio = anio;
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

    // Método para mostrar la fecha separada por '/'
    public void mostrarFecha() {
        System.out.println(mes + "/" + dia + "/" + anio);
    }
}