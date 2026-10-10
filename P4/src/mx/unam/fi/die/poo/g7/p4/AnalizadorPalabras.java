package mx.unam.fi.die.poo.g7.p4;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

/**
 * Cuenta cuántas veces aparece cada palabra en un texto y muestra las repetidas.
 */
public class AnalizadorPalabras{
    public String oracion;
    public Map<String,Integer> frecuencias;
    
    /**
     * Constructor de AnalizadorPalabras.
     * @param nuevaOracion Oración a analizar.
     */
    public AnalizadorPalabras(String nuevaOracion){
        this.oracion=nuevaOracion;
        this.frecuencias=new HashMap<>();
    }

    /**
     * Método para contar el total de palabras.
     */
    public void contarPalabras(){
        String oracionLimpia=oracion.toLowerCase().replaceAll("[^a-záéíóúñ0-9]"," ");
        String[] palabras=oracionLimpia.split("\\s+");
        for (String palabra : palabras){
            if (!palabra.isEmpty()){
                frecuencias.put(palabra,frecuencias.getOrDefault(palabra,0)+1);
            }
        }
    }

    /**
     * Método para obtener el número de duplicados.
     * @return Resultado de la operación.
     */
    public int obtenerNumeroDuplicados(){
        int contadorDuplicados=0;
        for (int cantidad : frecuencias.values()){
            if (cantidad>1){
                contadorDuplicados++;
            }
        }
        return contadorDuplicados;
    }

    /**
     * Método para mostrar las palabras duplicadas.
     * @param ordenar true para ordenar, false en caso contrario.
     */
    public void mostrarDuplicadas(boolean ordenar){
        Map<String,Integer> mapaSalida=frecuencias;
        if (ordenar){
            mapaSalida=new TreeMap<>(frecuencias);
        }
        boolean hayDuplicados=false;
        for (Map.Entry<String, Integer> entrada : mapaSalida.entrySet()){
            if (entrada.getValue()>1){
                System.out.println("- "+entrada.getKey()+": "+entrada.getValue()+" veces");
                hayDuplicados=true;
            }
        }
        if(!hayDuplicados){
            System.out.println("No se encontraron palabras duplicadas en la oracion.");
        }
    }
}    
