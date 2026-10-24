package mx.unam.fi.die.poo.g7.p8;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<HuellaCarbono> lista = new ArrayList<>();

        lista.add(new Edificio("Torre Central FI", 4500.0, 180.0));
        lista.add(new Auto("PXZ-4821", 1200.0, 14.5));
        lista.add(new Bicicleta("BIC-2026-X8", 350.0));

        System.out.println("--- REPORTE DE HUELLA DE CARBONO ---");
        for (HuellaCarbono elemento : lista) {
            System.out.println(elemento.toString());
            System.out.println("Huella de carbono calculada: " + elemento.getHuellaCarbono() + " kg CO2\n");
        }
    }
}

