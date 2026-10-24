package mx.unam.fi.die.poo.g7.p8;

public class Edificio implements HuellaCarbono {
    private String nombre;
    private double consumoKwh;
    private double consumoGasM3;

    /**
     * Constructor con los datos principales del edificio.
     */
    public Edificio(String nombre, double consumoKwh, double consumoGasM3) {
        this.nombre = nombre;
        this.consumoKwh = consumoKwh;
        this.consumoGasM3 = consumoGasM3;
    }

    /**
     * Calcula las emisiones sumando el gasto de luz y de gas natural.
     */
    @Override
    public double getHuellaCarbono() {
        return (consumoKwh * 0.42) + (consumoGasM3 * 2.0);
    }

    @Override
    public String toString() {
        return "Edificio: " + nombre + " | Consumo luz: " + consumoKwh + " kWh | Consumo gas: " + consumoGasM3 + " m3";
    }
}

