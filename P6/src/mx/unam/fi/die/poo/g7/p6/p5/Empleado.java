package mx.unam.fi.die.poo.g7.p6.p5;

/**
 * Plantilla para manejar los datos de un empleado, como su nombre y salario.
 */
public class Empleado {
    private String nombre;
    private String apellido;
    private double salarioMensual;

    // Constructor que delega totalmente en los setters
    /**
     * Constructor de Empleado.
     * @param nombre Nombre de la persona.
     * @param apellido Apellido del empleado.
     * @param salarioMensual Salario mensual del empleado.
     */
    public Empleado(String nombre, String apellido, double salarioMensual) {
        setNombre(nombre);
        setApellido(apellido);
        setSalarioMensual(salarioMensual);
    }

    // Métodos modificadores (Setters)
    /**
     * Método para designar nombre.
     * @param nombre Nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para designar apellido.
     * @param apellido Apellido del empleado.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Método para designar salario mensual.
     * @param salarioMensual Salario mensual del empleado.
     */
    public void setSalarioMensual(double salarioMensual) {
        if (salarioMensual > 0) {
            this.salarioMensual = salarioMensual;
        }
    }

    // Métodos de acceso (Getters)
    /**
     * Método para obtener nombre.
     * @return El valor de la propiedad nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener apellido.
     * @return El valor de la propiedad apellido.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método para obtener salario mensual.
     * @return El valor de la propiedad salariomensual.
     */
    public double getSalarioMensual() {
        return salarioMensual;
    }
}
