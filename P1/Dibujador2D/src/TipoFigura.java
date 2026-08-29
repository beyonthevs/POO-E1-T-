public enum TipoFigura {
    RECTANGULO("Rectángulo"),
    OVALO("Óvalo"),
    LINEA("Línea");

    private final String texto;

    TipoFigura(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}
