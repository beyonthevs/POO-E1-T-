package mx.unam.fi.die.poo.g7.p10;

/**
 * Clase base que centraliza el try-catch de las operaciones.
 * @author Equipo 1
 * @version 1.0
 */
public abstract class ServicioBase {

    /**
     * Ejecuta el flujo y captura cualquier error que ocurra.
     */
    public void ejecutar() {
        try {
            realizarOperacion();
        } catch (Exception e) {
            capturarExcepcion(e);
        }
    }

    /**
     * Metodo que implementa cada subclase con su logica especifica.
     * @throws Exception Si ocurre un fallo durante la ejecucion.
     */
    protected abstract void realizarOperacion() throws Exception;

    /**
     * Imprime los detalles del error capturado.
     * @param e Excepcion que fue atrapada.
     */
    protected void capturarExcepcion(Exception e) {
        System.out.println("Tipo de excepcion: " + e.getClass().getName());
        System.out.println("Mensaje: " + e.getMessage());
    }
}

