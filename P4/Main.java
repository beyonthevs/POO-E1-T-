import java.util.Scanner;

public class Main {
  public static void main(String[]args){
   Scanner scanner = new Scanner(System.in);


   System.out.println("Ingresa la oracion a analizar");
   String texto = scanner.nextLine();

   AnalizadorPalabras analizador = new AnalizadorPalabras(texto);

   analizador.contarPalabras();

   int cantidadDuplicados = analizador.obtenerNumeroDuplicado();
   System.out.println("\nPalabras diferentes que se duplicaron: " + cantidadDuplicados + "\n");
   
   System.out.println("Detalle de duplicados(Sin ordenar)");
   analizador.mostrarDuplicadas(false);

   System.out.println("\nDetalle de duplicados (Orden alfabetico)");
   analizador.mostrarDuplicadas(true);

   scanner.close();


  }

}