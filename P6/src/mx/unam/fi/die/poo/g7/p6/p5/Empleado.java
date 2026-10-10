package mx.unam.fi.die.poo.g7.p6.p5;

/**
 * Clase Empleado para la practica p5.
 * Sirve para resolver el problema asignado.
 */
public class Empleado {
    private String nombre;
    private String apellido;
    private double salarioMensual;

    // Constructor que delega totalmente en los setters
    /**
     * Constructor de Empleado.
     */
    public Empleado(String nombre, String apellido, double salarioMensual) {
        setNombre(nombre);
        setApellido(apellido);
        setSalarioMensual(salarioMensual);
    }

    // Métodos modificadores (Setters)
    /**
     * Metodo para setNombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para setApellido.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Metodo para setSalarioMensual.
     */
    public void setSalarioMensual(double salarioMensual) {
        if (salarioMensual > 0) {
            this.salarioMensual = salarioMensual;
        }
    }

    // Métodos de acceso (Getters)
    /**
     * Metodo para getNombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para getApellido.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Metodo para getSalarioMensual.
     */
    public double getSalarioMensual() {
        return salarioMensual;
    }
}
