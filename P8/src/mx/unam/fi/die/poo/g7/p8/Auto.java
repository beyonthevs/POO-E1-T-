package mx.unam.fi.die.poo.g7.p8;

public class Auto implements HuellaCarbono {
    private String matricula;
    private double kilometrosRecorridos;
    private double rendimientoKmLitro;

    /**
     * Constructor que recibe las caracteristicas de uso del auto.
     */
    public Auto(String matricula, double kilometrosRecorridos, double rendimientoKmLitro) {
        this.matricula = matricula;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.rendimientoKmLitro = rendimientoKmLitro;
    }

    /**
     * Obtiene los litros gastados y calcula la emision por gasolina.
     */
    @Override
    public double getHuellaCarbono() {
        if (rendimientoKmLitro <= 0) {
            return 0.0;
        }
        double litrosConsumidos = kilometrosRecorridos / rendimientoKmLitro;
        return litrosConsumidos * 2.31;
    }

    @Override
    public String toString() {
        return "Auto placa: " + matricula + " | Km recorridos: " + kilometrosRecorridos + " km | Rendimiento: " + rendimientoKmLitro + " km/l";
    }
}

