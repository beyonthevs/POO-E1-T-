package mx.unam.fi.die.poo.g7.p10;

/**
 * Subclase que simula una transaccion fallida para probar la captura.
 * @author Equipo 1
 * @version 1.0
 */
public class ProcesadorPago extends ServicioBase {

    /**
     * Lanza una excepcion simulando falta de fondos.
     * @throws Exception Error de saldo insuficiente.
     */
    @Override
    protected void realizarOperacion() throws Exception {
        throw new IllegalArgumentException("Saldo insuficiente para procesar la transaccion.");
    }
}

