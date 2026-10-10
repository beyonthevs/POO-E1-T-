package mx.unam.fi.die.poo.g7.p1;

/**
 * Guarda los tipos de figuras que se pueden dibujar.
 */
public enum TipoFigura {
    RECTANGULO("Rectángulo"),
    OVALO("Óvalo"),
    LINEA("Línea");

    private final String texto;

    /**
     * Constructor de TipoFigura.
     * @param texto Nombre de la figura.
     */
    TipoFigura(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}
