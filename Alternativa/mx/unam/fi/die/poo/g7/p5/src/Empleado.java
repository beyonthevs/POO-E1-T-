package mx.unam.fi.die.poo.g7.p5;
public class Empleado {
    private String nombre;
    private String apellido;
    private double salarioMensual;

    // Constructor que delega totalmente en los setters
    public Empleado(String nombre, String apellido, double salarioMensual) {
        setNombre(nombre);
        setApellido(apellido);
        setSalarioMensual(salarioMensual);
    }

    // Métodos modificadores (Setters)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSalarioMensual(double salarioMensual) {
        if (salarioMensual > 0) {
            this.salarioMensual = salarioMensual;
        }
    }

    // Métodos de acceso (Getters)
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }
}
