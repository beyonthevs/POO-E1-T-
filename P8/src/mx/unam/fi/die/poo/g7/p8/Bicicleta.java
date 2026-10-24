package mx.unam.fi.die.poo.g7.p8;

public class Bicicleta implements HuellaCarbono {
    private String numeroSerie;
    private double kilometrosRecorridos;

    /**
     * Constructor para registrar la bicicleta.
     */
    public Bicicleta(String numeroSerie, double kilometrosRecorridos) {
        this.numeroSerie = numeroSerie;
        this.kilometrosRecorridos = kilometrosRecorridos;
    }

    /**
     * Devuelve cero porque la bicicleta no quema combustible.
     */
    @Override
    public double getHuellaCarbono() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "Bicicleta serie: " + numeroSerie + " | Km recorridos: " + kilometrosRecorridos + " km";
    }
}

